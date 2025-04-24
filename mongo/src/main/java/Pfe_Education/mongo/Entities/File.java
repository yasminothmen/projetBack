package Pfe_Education.mongo.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class File {

    @Id
    private String id;
    private String filename;
    private String contentType;
    private long size;
    private byte[] data;

    public File(String filename, String contentType, long size, byte[] data) {
        this.filename = filename;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
    }
    public File() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}