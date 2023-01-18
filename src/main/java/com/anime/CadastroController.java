package com.anime;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.models.Anime;
import com.models.AnimeDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CadastroController extends GenericMenuController implements Initializable{
    @FXML
    private ComboBox<String> cbAvaliacao;

    @FXML
    private ToggleGroup status;

    @FXML
    private TextField txtAno;

    @FXML
    private TextArea txtDescricao;

    @FXML
    private TextField txtTitulo;

    private List<String> avaliacoes = new ArrayList<>();

    private ObservableList<String> obsAvaliacoes;

    @FXML
    void cadastrar() throws IOException{
        Anime anime = new Anime();
        AnimeDao dao = new AnimeDao();
        anime.setTitulo(txtTitulo.getText());
        anime.setAno(Integer.parseInt(txtAno.getText()));
        anime.setDescricao(txtDescricao.getText());
        anime.setAvaliacao(cbAvaliacao.getValue());
        anime.setStatus(getOpcao());

        if(dao.insert(anime)){
            txtTitulo.setText("");
            txtAno.setText("");
            txtDescricao.setText("");
        } else {
            System.out.println("Erro ao Cadastrar Anime");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        caregarAnimes();
        cbAvaliacao.getSelectionModel().selectFirst();
    }

    //MÉTODOS UTILITÁRIOS DA CLASSE
    private boolean getOpcao(){
        RadioButton radio = (RadioButton) status.getSelectedToggle();
        return radio.getText().equals("Assistido");
    }

    private void caregarAnimes(){
        avaliacoes.add("Bom");
        avaliacoes.add("Mediano");
        avaliacoes.add("Ruim");

        obsAvaliacoes = FXCollections.observableArrayList(avaliacoes);

        cbAvaliacao.setItems(obsAvaliacoes);
    }
}