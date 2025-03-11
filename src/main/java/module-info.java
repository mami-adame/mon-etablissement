module com.groupeisi.monetablissement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.groupeisi.monetablissement.controllers to javafx.fxml; // Ajout nécessaire
    //exports com.groupeisi.monetablissement.models;


    opens com.groupeisi.monetablissement to javafx.fxml;
    exports com.groupeisi.monetablissement;
    exports com.groupeisi.monetablissement.controllers;
}