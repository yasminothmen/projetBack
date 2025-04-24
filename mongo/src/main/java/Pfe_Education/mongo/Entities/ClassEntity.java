package Pfe_Education.mongo.Entities;

import lombok.Data;
import java.util.List;

@Data
public class ClassEntity {
    private String id;
    private String name;//
    private String level;
    private int studentsCount;//nombre des etudinats effecté a cette classe
    private List<Student> students; // Liste des étudiants affectés à cette classe
}
