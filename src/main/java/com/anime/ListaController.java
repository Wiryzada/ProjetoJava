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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ListaController extends GenericMenuController implements Initializable{
    @FXML
    private ListView<Anime> lvAnimes;

    private List<Anime> animes = new ArrayList<>();

    private ObservableList<Anime> obsAnimes;

    @FXML
    private Label lblAno;

    @FXML
    private Label lblAvaliacao;

    @FXML
    private TextArea lblDescricao;

    @FXML
    private Label lblId;

    @FXML
    private Label lblTitulo;

    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtTitulo;

    @FXML
    private VBox vboxPesquisa;

    @FXML
    void pesquisarAnime(){
        vboxPesquisa.setVisible(true);
        AnimeDao dao = new AnimeDao();
        Anime anime = dao.select(Integer.parseInt(txtTitulo.getText()));
        if( anime!= null) {
            lblTitulo.setText("Titulo: "+anime.getTitulo());
            lblAno.setText("Ano: "+anime.getAno());
            lblId.setText("Id: "+anime.getId());
            lblDescricao.setText(anime.getDescricao());
            lblStatus.setText("Status: " + (anime.isStatus() ? "Assistido" : "Não Assistido"));
            lblAvaliacao.setText("Avaliação: " + anime.getAvaliacao());
        } else {
            System.out.println("Anime não encotrado :/");
        }
    }

    private void carregarAnimes(){
        AnimeDao dao = new AnimeDao();
        animes = dao.select();

        obsAnimes = FXCollections.observableArrayList(animes);
        lvAnimes.setItems(obsAnimes);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        carregarAnimes();
    }
}
