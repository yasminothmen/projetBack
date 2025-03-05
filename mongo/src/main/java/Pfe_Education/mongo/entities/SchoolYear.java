package Pfe_Education.mongo.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@Document(collection = "school_years")
public class SchoolYear {

    @Id
    private String id;

    @Indexed(unique = true) // Rend le nom unique dans la collection MongoDB
    private String name;

    private Date startDate;
    private Date endDate;

    public SchoolYear() {}

    public SchoolYear(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
