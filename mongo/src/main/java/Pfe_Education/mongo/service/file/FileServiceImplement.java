package Pfe_Education.mongo.service.file;

import Pfe_Education.mongo.Entities.File;
import Pfe_Education.mongo.Entities.UserEntity;
import Pfe_Education.mongo.repositories.FileRepository;
import Pfe_Education.mongo.repositories.UserRepo;
import com.google.firebase.remoteconfig.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Slf4j
@Service
public class FileServiceImplement  implements FileService{
    @Autowired
    private final FileRepository fileRepository;

    @Autowired
    private UserRepo userRepo; // Assurez-vous que c'est bien injecté

    public FileServiceImplement(FileRepository fileRepository,UserRepo userRepo) {
        this.fileRepository = fileRepository;
        this.userRepo = userRepo;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImplement.class.getName());
    @Override
    public ResponseEntity<?> uploadFile(MultipartFile fileToBeUploaded) {
        try {
            // Vérification de la taille du fichier (ex: limite à 500MB)
            long maxSize = 500 * 1024 * 1024; // 500MB
            if (fileToBeUploaded.getSize() > maxSize) {
                return new ResponseEntity<>("File size exceeds maximum limit of 500MB", HttpStatus.PAYLOAD_TOO_LARGE);
            }

            // Vérification du type de fichier autorisé
            String contentType = fileToBeUploaded.getContentType();
            if (contentType == null ||
                    (!contentType.startsWith("video/") &&
                            !contentType.startsWith("image/") &&
                            !contentType.equals("application/pdf") &&
                            !contentType.startsWith("audio/"))) {
                return new ResponseEntity<>("Unsupported file type", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            }

            if (!this.fileExists(fileToBeUploaded.getOriginalFilename())) {
                File file = new File();
                file.setFilename(fileToBeUploaded.getOriginalFilename());
                file.setContentType(fileToBeUploaded.getContentType());
                file.setSize(fileToBeUploaded.getSize());

                // Pour les gros fichiers, utilisez un buffer
                byte[] bytes;
                try (InputStream inputStream = fileToBeUploaded.getInputStream();
                     ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

                    byte[] buffer = new byte[1024 * 1024]; // Buffer de 1MB
                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    bytes = outputStream.toByteArray();
                }

                file.setData(bytes);

                File savedFile = this.fileRepository.save(file);
                // Génére l'URL de téléchargement
                String fileUrl = "http://192.168.1.12:8080/file/download/" + savedFile.getFilename();

// Renvoie un JSON contenant l'URL
                Map<String, String> response = new HashMap<>();
                response.put("fileUrl", fileUrl);

                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("File already exists", HttpStatus.CONFLICT);
            }
        } catch (IOException e) {
            LOGGER.error("Error processing file: " + e.getMessage());
            return new ResponseEntity<>("Error processing file: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> downloadFile(String filename) {
        Optional<File> optionalFile = this.fileRepository.findFileByFilename(filename);
        if(optionalFile.isPresent()) {
            File file = optionalFile.get();
            return new ResponseEntity<>(file, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    private boolean fileExists(String filename) {
        return fileRepository.existsByFilename(filename);
    }

    @Override
    public ResponseEntity<?> saveImageToDatabase(MultipartFile image, String userId) {
        try {
            // 1. Vérifier que l'utilisateur existe
            UserEntity user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

            // 2. Générer un nom de fichier unique avec l'ID utilisateur
            String uniqueFilename = "profile_" + userId + "_" + System.currentTimeMillis() +
                    (image.getOriginalFilename() != null ?
                            "_" + image.getOriginalFilename() : "");

            // 3. Supprimer l'ancienne image si elle existe
            if (user.getProfileImageId() != null) {
                try {
                    fileRepository.deleteById(user.getProfileImageId());
                    log.info("Ancienne image supprimée pour l'utilisateur {}", userId);
                } catch (Exception e) {
                    log.warn("Échec de la suppression de l'ancienne image", e);
                }
            }

            // 4. Sauvegarder la nouvelle image
            File file = new File();
            file.setFilename(uniqueFilename);
            file.setContentType(image.getContentType());
            file.setSize(image.getSize());
            file.setData(image.getBytes());

            File savedFile = fileRepository.save(file);

            // 5. Mettre à jour l'utilisateur avec la référence de la nouvelle image
            user.setProfileImageId(savedFile.getId());
            userRepo.save(user);

            log.info("Image de profil sauvegardée pour l'utilisateur {}", userId);

            // 6. Retourner la réponse avec l'URL d'accès à l'image
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "message", "Image de profil enregistrée avec succès",
                            "imageId", savedFile.getId(),
                            "filename", uniqueFilename,
                            "imageUrl", "/file/images/" + uniqueFilename
                    ));

        } catch (IOException e) {
            log.error("Erreur lors de l'enregistrement de l'image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'enregistrement de l'image: " + e.getMessage());
        } catch (Exception e) {
            log.error("Erreur inattendue", e);
            return ResponseEntity.badRequest()
                    .body("Erreur: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<byte[]> displayImageFromDatabase(String filename) {
        Optional<File> fileOptional = fileRepository.findFileByFilename(filename);

        if (fileOptional.isEmpty()) {
            log.warn("Image non trouvée en base de données: {}", filename);
            return ResponseEntity.notFound().build();
        }

        File file = fileOptional.get();

        // Vérifier que c'est bien une image
        if (!file.getContentType().startsWith("image/")) {
            log.warn("Le fichier {} n'est pas une image (type: {})", filename, file.getContentType());
            return ResponseEntity.badRequest().body(("Le fichier n'est pas une image").getBytes());
        }

        // Construire la réponse
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .body(file.getData());
    }
}
