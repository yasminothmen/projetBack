package Pfe_Education.mongo.service.file.local;

import Pfe_Education.mongo.entities.File;
import Pfe_Education.mongo.service.file.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class DefaultFileServiceImplement implements FileService {
    private final FileRepository fileRepository;
    @Autowired
    public DefaultFileServiceImplement(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultFileServiceImplement.class.getName());
    @Override
    public ResponseEntity<?> uploadFile(MultipartFile fileToBeUploaded) {
        try {
            if(!this.fileExists(fileToBeUploaded.getOriginalFilename())) {
                File file = new File();
                file.setFilename(fileToBeUploaded.getOriginalFilename());
                file.setContentType(fileToBeUploaded.getContentType());
                file.setSize(fileToBeUploaded.getSize());
                file.setData(fileToBeUploaded.getBytes());
                // return this.fileRepository.save(file)
                return new ResponseEntity<>( "File Uploaded Successfully "+this.fileRepository.save(file).getFilename(), HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>("File already exists", HttpStatus.CONFLICT);
            }
        }catch (IOException e) {
            LOGGER.error("error getting data from file"+e.getMessage());
            //return null
            return new ResponseEntity<>("Error when getting Date from file",HttpStatus.BAD_REQUEST);
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