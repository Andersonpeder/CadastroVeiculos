package sample.telas;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.basededados.BancoDeDadosEmMemoria;
import sample.modelo.Carro;
import sample.logica.CarroBusinnes;

import static javafx.scene.layout.Priority.ALWAYS;

/**
 * Created by Ton on 12/11/2014.
 */
public class TelaPesquisa extends Application {

    private ObservableList<Carro> dadosGrid = FXCollections.observableArrayList();


    private BorderPane borderPane = new BorderPane();

    private TableView<Carro> gridCarros;

    private Stage stage;

    @Override
    public void start(Stage palco) throws Exception {


        dadosGrid.addAll(BancoDeDadosEmMemoria.getInstance().tbCarros);

        palco.setTitle("PESQUISA DE CARROS");

        borderPane.paddingProperty().setValue(new Insets(10));
        borderPane.setPrefSize(780, 590);

        HBox boxCampoDePesquisa = montaPainelDePesquisa();

        borderPane.setTop(boxCampoDePesquisa);

        gridCarros = montaGridPessoas();

        borderPane.setCenter(gridCarros);


        ToolBar toolBar = montaBarraDeFerramentas();

        borderPane.setBottom(toolBar);

        palco.setScene(new Scene(borderPane, 800, 600));
        palco.show();

        this.stage = palco;
    }

    private ToolBar montaBarraDeFerramentas() {

        ToolBar toolBar = new ToolBar();

        Button btnNovo = new Button("Novo");
        btnNovo.setStyle("-fx-base: rgb("+(50)+","+(255)+","+(0)+");");
        Button btnAlterar = new Button("Alterar");
        btnAlterar.setStyle("-fx-base: rgb("+(204)+","+(255)+","+(0)+");");
        Button btnExcluir = new Button("Excluir");
        btnExcluir.setStyle("-fx-base: rgb("+(255)+","+(0)+","+(0)+");");



        toolBar.getItems().addAll(btnNovo, btnAlterar, btnExcluir);


        btnNovo.setOnAction(click -> {

            invocarTelaDeCadastro(null);

        });

        btnExcluir.setOnAction(click -> {


            if (gridCarros.getSelectionModel().getSelectedItem() != null) {
                Carro carro = gridCarros.getSelectionModel().getSelectedItems().get(0);
                CarroBusinnes businnes = new CarroBusinnes();
                businnes.excluirCarro(carro);
                //atualizar a teoala
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dadosGrid = FXCollections.observableArrayList();
                        dadosGrid.addAll(BancoDeDadosEmMemoria.getInstance().tbCarros);
                        gridCarros.setItems(null);
                        gridCarros.setItems(dadosGrid);
                    }
                });


            }
        });


        btnAlterar.setOnAction(click -> {
            if (gridCarros.getSelectionModel().getSelectedItem() != null) {
                Carro carro = gridCarros.getSelectionModel().getSelectedItems().get(0);

                //chamar a tela de cadastro

                invocarTelaDeCadastro(carro);

                CarroBusinnes businnes = new CarroBusinnes();
                businnes.alterarCarro(carro);


            }
        });


        return toolBar;
    }

    private void invocarTelaDeCadastro(Carro carro) {
        TelaCadastro cadastro = new TelaCadastro(stage);
        stage.setScene(new Scene(cadastro.getTela(), 800, 600));

        if (carro == null) {
            cadastro.iniciaInsercao();
        } else {
            cadastro.iniciaAlteracao(carro);
        }

    }

    private HBox montaPainelDePesquisa() {

        HBox boxCampoDePesquisa = new HBox();
        boxCampoDePesquisa.paddingProperty().setValue(new Insets(10));
        boxCampoDePesquisa.setHgrow(borderPane, ALWAYS);
        //campo de texto
        TextField ct = new TextField();
        ct.setPrefColumnCount(60);

        ct.promptTextProperty().setValue("digite aqui para pesquisar");

        //botão de pesquisa
        Button btnPesquisa = new Button("Busca");
        btnPesquisa.setStyle("-fx-base: rgb("+(20)+","+(20)+","+(20)+");");
        boxCampoDePesquisa.getChildren().add(ct);
        boxCampoDePesquisa.getChildren().add(btnPesquisa);

        btnPesquisa.setOnAction(action -> {
            String chave = ct.textProperty().get();
            CarroBusinnes businnes = new CarroBusinnes();
            try {
                Carro carro = businnes.pesquisaPorCodigo(chave);
                if (carro != null) {
                    dadosGrid.clear();
                    dadosGrid.add(carro);
                } else {
                    System.out.println(" Carro não encontrado");
                }
            } catch (Exception e) {
                e.printStackTrace();
                /*Popup erro = new Popup();
                erro.centerOnScreen();
                erro.getContent().addAll( new Label(e.getMessage()));
                erro.show(palco);*/

            }

        });

        // final TextField edtCodigo = new TextField();
        return boxCampoDePesquisa;
    }

    private TableView<Carro> montaGridPessoas() {

        //Grid de pesquisa com Table view
        TableColumn colunaCodigo = new TableColumn();
        colunaCodigo.minWidthProperty().setValue(100);
        //cabecalho da coluna
        colunaCodigo.setText("Codigo");
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        TableColumn colunaModelo = new TableColumn();
        colunaModelo.minWidthProperty().setValue(100);
        colunaModelo.setText("Modelo");
        colunaModelo.setCellValueFactory(new PropertyValueFactory("modelo"));

        TableColumn colunaMarca = new TableColumn();
        colunaMarca.minWidthProperty().setValue(100);
        colunaMarca.setText("Marca");
        colunaMarca.setMinWidth(200);
        colunaMarca.setCellValueFactory(new PropertyValueFactory("marca"));

        TableColumn colunaQtdportas = new TableColumn();
        colunaQtdportas.minWidthProperty().setValue(100);
        //cabecalho da coluna
        colunaQtdportas.setText("Qtdportas");
        colunaQtdportas.setCellValueFactory(new PropertyValueFactory<>("qtdportas"));

        TableColumn colunaCor = new TableColumn();
        colunaCor.minWidthProperty().setValue(100);
        colunaCor.setText("Cor");
        colunaCor.setCellValueFactory(new PropertyValueFactory("cor"));

        TableColumn colunaProprietario = new TableColumn();
        colunaProprietario.minWidthProperty().setValue(100);
        colunaProprietario.setText("Proprietario");
        colunaProprietario.setMinWidth(200);
        colunaProprietario.setCellValueFactory(new PropertyValueFactory("proprietario"));

        TableColumn colunaPlaca = new TableColumn();
        colunaPlaca.minWidthProperty().setValue(100);
        colunaPlaca.setText("Placa");
        colunaPlaca.setMinWidth(200);
        colunaPlaca.setCellValueFactory(new PropertyValueFactory("placa"));


        TableView<Carro> gridCarros = new TableView();

        gridCarros.getColumns().addAll(colunaCodigo, colunaModelo, colunaMarca, colunaQtdportas, colunaCor,
                colunaProprietario,colunaPlaca);

        gridCarros.setItems(dadosGrid);


        return gridCarros;
    }


    public void atualizarGrid() {
        //atualizar a tela
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dadosGrid = FXCollections.observableArrayList();
                dadosGrid.addAll(BancoDeDadosEmMemoria.getInstance().tbCarros);
                gridCarros.setItems(null);
                gridCarros.setItems(dadosGrid);
            }
        });
    }
}
