package Pfe_Education.mongo.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    // method to verify token
    @PostMapping("/verify-token")
    public ResponseEntity<?> verifyToken(@AuthenticationPrincipal Jwt jwt) {
        if (jwt == null) {
            return ResponseEntity.status(401).body("Unauthorized: Invalid Token");
        }

        String uid = jwt.getSubject(); // Firebase UID is stored as "sub" in the JWT

        return ResponseEntity.ok("Token is valid. User ID: " + uid);
    }

}
