package Pfe_Education.mongo.repositories;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardStatsDTO {
    private long totalStudents;
    private long totalTeachers;
    private long totalSubjects;
    private long totalClasses;
    private long totalSchedules;

    public DashboardStatsDTO(long totalStudents, long totalTeachers,
                             long totalSubjects, long totalClasses,
                             long totalSchedules) {
        this.totalStudents = totalStudents;
        this.totalTeachers = totalTeachers;
        this.totalSubjects = totalSubjects;
        this.totalClasses = totalClasses;
        this.totalSchedules = totalSchedules;
    }

    // Getters
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

    // Setters (optionnels si l'objet est immuable)
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
}