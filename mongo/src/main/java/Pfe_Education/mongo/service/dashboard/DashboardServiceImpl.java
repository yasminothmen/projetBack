package Pfe_Education.mongo.service.dashboard;

import Pfe_Education.mongo.repositories.DashboardStatsDTO;

import Pfe_Education.mongo.repositories.ClassRepository;
import Pfe_Education.mongo.repositories.ScheduleRepository;
import Pfe_Education.mongo.repositories.SubjectRepository;
import Pfe_Education.mongo.repositories.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardInterface { // Implémente l'interface

    private final UserRepo userRepo;
    private final SubjectRepository subjectRepo;
    private final ClassRepository classRepo;
    private final ScheduleRepository scheduleRepo;

    public DashboardServiceImpl(UserRepo userRepo,
                                SubjectRepository subjectRepo,
                                ClassRepository classRepo,
                                ScheduleRepository scheduleRepo) {
        this.userRepo = userRepo;
        this.subjectRepo = subjectRepo;
        this.classRepo = classRepo;
        this.scheduleRepo = scheduleRepo;
    }

    @Override // Implémentation de la méthode de l'interface
    public DashboardStatsDTO getDashboardStats() {
        return new DashboardStatsDTO(
                userRepo.countByRole("student"),
                userRepo.countByRole("teacher"),
                subjectRepo.count(),
                classRepo.count(),
                scheduleRepo.count()
        );
    }
}