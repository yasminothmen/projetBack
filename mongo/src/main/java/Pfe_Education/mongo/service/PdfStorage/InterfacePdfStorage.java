package Pfe_Education.mongo.service.PdfStorage;

import Pfe_Education.mongo.Entities.PdfStorage;

import java.util.List;

public interface InterfacePdfStorage {
    PdfStorage savePdfInfo(String fileName, String entityType, String entityName);
    List<PdfStorage> getPdfsForEntity(String entityType, String entityName);
}