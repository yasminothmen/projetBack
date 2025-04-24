package Pfe_Education.mongo.service.PdfStorage;

import Pfe_Education.mongo.Entities.PdfStorage;
import Pfe_Education.mongo.repositories.PdfStorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdfStorageService implements InterfacePdfStorage {

    private final PdfStorageRepository pdfStorageRepository;
    private final String baseUrl = "http://192.168.1.12:8080";
    // Injection du repository
    public PdfStorageService(PdfStorageRepository pdfStorageRepository) {
        this.pdfStorageRepository = pdfStorageRepository;
    }

    @Override
    public PdfStorage savePdfInfo(String fileName, String entityType, String entityName) {
        String fileUrl = baseUrl + "/pdfs/" + fileName; // URL complète

        PdfStorage pdfStorage = new PdfStorage(fileName, fileUrl, entityType, entityName);
        return pdfStorageRepository.save(pdfStorage);
    }

    @Override
    public List<PdfStorage> getPdfsForEntity(String entityType, String entityName) {
        return pdfStorageRepository.findByEntityTypeAndEntityName(entityType, entityName);
    }
}