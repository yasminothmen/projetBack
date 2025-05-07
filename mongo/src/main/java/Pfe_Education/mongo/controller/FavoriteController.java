package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.Entities.Cour;
import Pfe_Education.mongo.service.Favoris.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }
    @GetMapping("/{email}")
    public ResponseEntity<List<Cour>> getUserFavorites(@PathVariable String email) {
        return ResponseEntity.ok(favoriteService.getUserFavorites(email));
    }
    @PostMapping("/toggle/{email}/{courseId}")
    public ResponseEntity<Void> toggleFavorite(
            @PathVariable String email,
            @PathVariable String courseId) {
        favoriteService.toggleFavorite(email, courseId);
        return ResponseEntity.ok().build();
    }
}
