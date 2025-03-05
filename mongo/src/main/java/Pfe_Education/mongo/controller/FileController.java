package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.entities.File;
import Pfe_Education.mongo.service.file.local.DefaultFileServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
    private final DefaultFileServiceImplement fileService;

    @Autowired
    public FileController(DefaultFileServiceImplement fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile fileToBeUploaded) {
        return this.fileService.uploadFile(fileToBeUploaded);

    }
    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename) {
        ResponseEntity<?> response = this.fileService.downloadFile(filename);
        if (response.getStatusCode() == HttpStatus.OK) {
            File file = (File) response.getBody();
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+file.getFilename()+"\"")
                    .body(file.getData());
        }else{
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

    }
}