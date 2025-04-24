package Pfe_Education.mongo.service.file;

import Pfe_Education.mongo.Entities.File;
import Pfe_Education.mongo.repositories.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
public class FileServiceImplement  implements FileService{
    private final FileRepository fileRepository;
    @Autowired
    public FileServiceImplement(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
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
}
