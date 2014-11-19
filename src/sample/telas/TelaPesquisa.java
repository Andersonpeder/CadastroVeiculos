package sample.telas;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import sample.basededados.BancoDeDadosEmMemoria;
import sample.logica.PessoaBusinnes;
import sample.modelo.Pessoa;

import static javafx.scene.layout.Priority.ALWAYS;

/**
 * Created by Ton on 12/11/2014.
 */
public class TelaPesquisa extends Application {

    private ObservableList<Pessoa> dadosGrid = FXCollections.observableArrayList();

    private Stage stage;

    @Override
    public void start(Stage palco) throws Exception {

        dadosGrid.addAll(BancoDeDadosEmMemoria.getInstance().tbPessoas);

        palco.setTitle("Pesquisa");
        VBox painel = new VBox();
        painel.paddingProperty().setValue(new Insets(10));
        painel.setPrefSize(780, 590);
        painel.setFillWidth(true);

        HBox boxCampoDePesquisa = new HBox();
        boxCampoDePesquisa.paddingProperty().setValue(new Insets(10));
        boxCampoDePesquisa.setHgrow(painel, ALWAYS);
        //campo de texto
        TextField ct = new TextField();
        ct.setPrefColumnCount(60);

        ct.promptTextProperty().setValue("digite aqui para pesquisar");

        //botão de pesquisa
        Button btnPesquisa = new Button("Busca");
        boxCampoDePesquisa.getChildren().add(ct);
        boxCampoDePesquisa.getChildren().add(new Separator());
        boxCampoDePesquisa.getChildren().add(btnPesquisa);


        painel.getChildren().add(boxCampoDePesquisa);
        //componentes de entrada de dados
        Label lblCodigo = new Label("Codigo:");

        final TextField edtCodigo = new TextField();

        //Grid de pesquisa com Table view
        TableColumn colunaCodigo = new TableColumn();
        colunaCodigo.minWidthProperty().setValue(100);
        //cabecalho da coluna
        colunaCodigo.setText("Código");
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        TableColumn colunaNome = new TableColumn();
        colunaNome.minWidthProperty().setValue(100);
        colunaNome.setText("Nome");
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));

        TableColumn colunaEmail = new TableColumn();
        colunaEmail.minWidthProperty().setValue(100);
        colunaEmail.setText("Email");
        colunaEmail.setMinWidth(200);
        colunaEmail.setCellValueFactory(new PropertyValueFactory("email"));

        TableView tableView = new TableView();
        tableView.getColumns().addAll(colunaCodigo, colunaNome, colunaEmail);

        tableView.setItems(dadosGrid);

        painel.getChildren().add(tableView);


        btnPesquisa.setOnAction(action -> {
            String chave = ct.textProperty().get();
            PessoaBusinnes businnes = new PessoaBusinnes();
            try {
                Pessoa pessoa = businnes.pesquisaPorCodigo(chave);
                if (pessoa != null) {
                    dadosGrid.clear();
                    dadosGrid.add(pessoa);
                } else {
                    System.out.println(" Pessoa não encontrada");
                }
            } catch (Exception e) {
                e.printStackTrace();
                /*Popup erro = new Popup();
                erro.centerOnScreen();
                erro.getContent().addAll( new Label(e.getMessage()));
                erro.show(palco);*/

            }

        });

        palco.setScene(new Scene(painel, 800, 600));
        palco.show();

        this.stage= palco;
    }


}
