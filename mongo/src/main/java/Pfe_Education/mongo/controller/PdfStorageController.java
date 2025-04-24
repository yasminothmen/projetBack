package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.Entities.PdfStorage;
import Pfe_Education.mongo.service.PdfStorage.PdfStorageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pdf-storage")
@CrossOrigin(origins = "*")
public class PdfStorageController {
    private final PdfStorageService pdfStorageService;

    public PdfStorageController(PdfStorageService pdfStorageService) {
        this.pdfStorageService = pdfStorageService;
    }

    @PostMapping
    public PdfStorage savePdfInfo(
            @RequestParam String fileName,
            @RequestParam String entityType,
            @RequestParam String entityName) {
        return pdfStorageService.savePdfInfo(fileName, entityType, entityName);
    }

    @GetMapping
    public List<PdfStorage> getPdfsForEntity(
            @RequestParam String entityType,
            @RequestParam String entityName) {
        return pdfStorageService.getPdfsForEntity(entityType, entityName);
    }
}