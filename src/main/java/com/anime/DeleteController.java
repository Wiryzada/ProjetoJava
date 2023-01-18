package com.anime;

import com.models.Anime;
import com.models.AnimeDao;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeleteController extends GenericMenuController{
    @FXML
    private Label lblMensage;

    @FXML
    private TextField txtID;

    @FXML
    void deleteById() {
        Anime anime = null;
        AnimeDao dao = new AnimeDao();
        anime = dao.select(Integer.parseInt(txtID.getText()));
        if(anime != null){
            String nome = anime.getTitulo();
            dao.delete(anime);
            lblMensage.setText("Anime "+ nome +".\nDeletado com sucesso!");
        } else {
            lblMensage.setText("Anime não encontrado!");
        }
    }
}
