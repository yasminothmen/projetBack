package Pfe_Education.mongo.Entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pdf_storage")
public class PdfStorage {
    @Id
    private String id;
    private String fileName;
    private String fileUrl;
    private String entityType;
    private String entityName;
    private Date createdAt = new Date();

    public PdfStorage(String fileName, String fileUrl, String entityType, String entityName) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.entityType = entityType;
        this.entityName = entityName;
        this.createdAt = new Date();
    }

}