package Pfe_Education.mongo.service.Favoris;

import Pfe_Education.mongo.Entities.Cour;
import Pfe_Education.mongo.Entities.UserEntity;
import Pfe_Education.mongo.repositories.CourRepository;
import Pfe_Education.mongo.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    private final UserRepo userRepository;
    private final CourRepository courRepository;
    public FavoriteService(UserRepo userRepository, CourRepository courRepository) {
        this.userRepository = userRepository;
        this.courRepository = courRepository;
    }

    public List<Cour> getUserFavorites(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"))
                .getFavoriteCourses();
    }

    public void toggleFavorite(String email, String courseId) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Cour course = courRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé"));

        if (user.getFavoriteCourses().removeIf(c -> c.getId().equals(courseId))) {
            // Cours retiré des favoris
            course.setBookmarked(false);
        } else {
            // Cours ajouté aux favoris
            user.getFavoriteCourses().add(course);
            course.setBookmarked(true);
        }

        courRepository.save(course);
        userRepository.save(user);
    }
}
