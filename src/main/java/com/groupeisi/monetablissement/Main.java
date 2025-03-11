package com.groupeisi.monetablissement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Charger le fichier FXML de la fenêtre principale (Ex: EtudiantView.fxml)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/groupeisi/monetablissement/view/TableauDesGestions.fxml"));

            Scene scene = new Scene(fxmlLoader.load());

            primaryStage.setTitle("Gestion des Étudiants");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }



