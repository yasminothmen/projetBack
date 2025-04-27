package Pfe_Education.mongo.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "file")
public class File {
    @Id
    private String id;
    private String filename;
    private String contentType;
    private long size;
    private byte[] data;

}