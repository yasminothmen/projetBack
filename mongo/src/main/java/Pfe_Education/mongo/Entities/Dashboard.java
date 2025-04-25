package Pfe_Education.mongo.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "dashboard_snapshots")
public class Dashboard {
    @Id
    private String id;
    private LocalDateTime date;
    private long totalStudents;
    private long totalTeachers;
    private long totalSubjects;
    private long totalClasses;
    private long totalSchedules;

    // Constructeurs
    public Dashboard() {}

    public Dashboard(long students, long teachers, long subjects,
                     long classes, long schedules) {
        this.date = LocalDateTime.now();
        this.totalStudents = students;
        this.totalTeachers = teachers;
        this.totalSubjects = subjects;
        this.totalClasses = classes;
        this.totalSchedules = schedules;
    }

    // Getters
    public String getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public long getTotalTeachers() {
        return totalTeachers;
    }

    public long getTotalSubjects() {
        return totalSubjects;
    }

    public long getTotalClasses() {
        return totalClasses;
    }

    public long getTotalSchedules() {
        return totalSchedules;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public void setTotalTeachers(long totalTeachers) {
        this.totalTeachers = totalTeachers;
    }

    public void setTotalSubjects(long totalSubjects) {
        this.totalSubjects = totalSubjects;
    }

    public void setTotalClasses(long totalClasses) {
        this.totalClasses = totalClasses;
    }

    public void setTotalSchedules(long totalSchedules) {
        this.totalSchedules = totalSchedules;
    }

    // Méthode toString() optionnelle
    @Override
    public String toString() {
        return "DashboardSnapshot{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", totalStudents=" + totalStudents +
                ", totalTeachers=" + totalTeachers +
                ", totalSubjects=" + totalSubjects +
                ", totalClasses=" + totalClasses +
                ", totalSchedules=" + totalSchedules +
                '}';
    }
}