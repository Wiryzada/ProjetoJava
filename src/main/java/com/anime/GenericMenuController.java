package com.anime;

import java.io.IOException;

import javafx.fxml.FXML;

public class GenericMenuController {
    @FXML
    void switchToDelete() throws IOException {
        App.setRoot("delete");
    }

    @FXML
    void switchToList() throws IOException {
        App.setRoot("lista");
    }

    @FXML
    void switchToRegistration() throws IOException {
        App.setRoot("cadastro");
    }

    @FXML
    void switchToUpdate() throws IOException {
        App.setRoot("update");
    }
}
