package com.example.demo.endpoint.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@RestController
public class StoredIntController {

    private static final String FILE_PATH = "/tmp/stored-int.txt";

    @GetMapping("/stored-int")
    public String getStoredInt() {
        Path path = Paths.get(FILE_PATH);

        try {
            if (Files.exists(path)) {
                String content = Files.readString(path);
                return "Nombre stocké: " + content;
            } else {
                Random random = new Random();
                int randomNumber = random.nextInt(1000); // Nombre aléatoire entre 0 et 999
                String numberString = String.valueOf(randomNumber);
                Files.writeString(path, numberString);
                return "Nouveau nombre généré et stocké: " + numberString;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Erreur lors de l\\'accès au fichier: " + e.getMessage();
        }
    }
}
