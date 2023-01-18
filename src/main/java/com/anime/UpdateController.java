package com.anime;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class UpdateController extends GenericMenuController implements Initializable{
    @FXML
    private ComboBox<String> cbAvaliacao;

    @FXML
    private RadioButton rbAssistido;

    @FXML
    private RadioButton rbNAssistido;

    @FXML
    private TextField idPesquisa;

    @FXML
    private ToggleGroup status;

    @FXML
    private TextField txtAno;

    @FXML
    private TextArea txtDescricao;

    @FXML
    private TextField txtTitulo;

    @FXML
    private Button btnAtualizar;

    private List<String> avaliacoes = new ArrayList<>();

    private ObservableList obsAvaliacoes;

    private int idAnime;

    @FXML
    void updateAnime(){
        AnimeDao dao = new AnimeDao();
        Anime anime = new Anime();
        anime.setId(idAnime);
        anime.setAno(Integer.parseInt(txtAno.getText()));
        anime.setTitulo(txtTitulo.getText());
        anime.setDescricao(txtDescricao.getText());
        anime.setStatus(getOpcao());
        anime.setAvaliacao(cbAvaliacao.getValue());
        dao.update(anime);
        txtAno.setText("");
        txtTitulo.setText("");
        txtDescricao.setText("");
        btnAtualizar.setDisable(true);
    }

    @FXML
    void selectAnime(){
        AnimeDao dao = new AnimeDao();
        try {
            idAnime = Integer.parseInt(idPesquisa.getText());
            Anime anime = dao.select(idAnime);
        if(anime!=null){
            txtAno.setText(""+anime.getAno());
            txtTitulo.setText(anime.getTitulo());
            txtDescricao.setText(anime.getDescricao());
            int avaliacao = 0;
            switch (anime.getAvaliacao()) {
                case "Bom":
                    break;

                case "Mediano":
                    avaliacao = 1;
                    break;

                case "Ruim":
                    avaliacao = 2;
                    break;
                }
                cbAvaliacao.getSelectionModel().select(avaliacao);
            }
            btnAtualizar.setDisable(false);
        } catch (NumberFormatException e ){
            System.out.println("Anime não encontrado!");
        }

        idPesquisa.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        caregarAnimes();
    }

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
