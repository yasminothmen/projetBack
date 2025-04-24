package Pfe_Education.mongo.Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseInitialisation {

    @PostConstruct
    public void initialization() {
        try (InputStream serviceAccount = new ClassPathResource("serviceAccountKey.json").getInputStream()) {

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {  // Vérifie qu'il n'y a pas déjà une instance
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase initialisé avec succès !");
            }

        } catch (IOException e) {
            System.err.println("ERREUR: Impossible d'initialiser Firebase");
            e.printStackTrace();
            throw new RuntimeException("Échec de l'initialisation Firebase", e);
        }
    }
}