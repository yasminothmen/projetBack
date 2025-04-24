package Pfe_Education.mongo.service.file;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    // mlowl bch n9olhom haka
    //File  uploadFile(MultipartFile  file);
    //Optional<File>  downloadFile(String filename);

    ResponseEntity<?> uploadFile(MultipartFile fileToBeUploaded);
    ResponseEntity<?> downloadFile(String filename);
}
