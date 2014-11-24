package sample.telas;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.logica.CarroBusinnes;
import sample.modelo.Carro;

/**
 * Created by Ton on 19/11/2014.
 */
public class TelaCadastro {

    private Stage palco;

    private VBox janela;

    private EstadoTela estadoTela = EstadoTela.INSERINDO;
    private Carro entidade;
    private TextField edtCodigo = new TextField();
    private TextField edtModelo = new TextField();
    private TextField edtMarca = new TextField();
    private TextField edtQtdportas = new TextField();
    private TextField edtCor = new TextField();
    private TextField edtProprietario = new TextField();
    private TextField edtPlaca = new TextField();



    public TelaCadastro(Stage palco) {
        this.palco = palco;

        janela = new VBox();
        //componentes de entrada de dados
        Label lblCodigo = new Label("Codigo:");


        edtCodigo.promptTextProperty().setValue("informe o codigo");
        janela.getChildren().addAll(lblCodigo, edtCodigo);
        //adicionando componentes ao janela principal
        Label lblModelo = new Label();
        lblModelo.setText("Modelo: ");


        //adicionando componentes ao janela principal
        janela.getChildren().add(lblModelo);
        janela.getChildren().add(edtModelo);
        //adicionando componentes ao janela principal

        Label lblMarca = new Label("Marca:");
        edtMarca.promptTextProperty().setValue("Marca do veiculo");
        janela.getChildren().addAll(lblMarca, edtMarca);

        Label lblQtdportas = new Label("Qtdportas:");
        edtQtdportas.promptTextProperty().setValue("Qtd de portas");
        janela.getChildren().addAll(lblQtdportas, edtQtdportas);

        Label lblCor = new Label("Cor:");
        edtCor.promptTextProperty().setValue("Cor do veiculo");
        janela.getChildren().addAll(lblCor, edtCor);

        Label lblProprietario = new Label("Proprietario:");
        edtProprietario.promptTextProperty().setValue("Proprietario do veiculo");
        janela.getChildren().addAll(lblProprietario, edtProprietario);

        Label lblPlaca = new Label("Placa:");
        edtPlaca.promptTextProperty().setValue("Placa do veiculo");
        janela.getChildren().addAll(lblPlaca, edtPlaca);

        // controles
        Button btnConfirmar = new Button("Confirmar");
        btnConfirmar.setStyle("-fx-base: rgb("+(51)+","+(255)+","+(0)+");");


        btnConfirmar.setOnAction(event -> {
            CarroBusinnes businnes = new CarroBusinnes();

            if (estadoTela.equals(EstadoTela.INSERINDO)) {
                businnes.addNovoCarro(edtCodigo.getText(), edtModelo.getText(), edtMarca.getText(), edtQtdportas.getText(), edtCor.getText(),edtProprietario.getText(),edtPlaca.getText());
                edtProprietario.textProperty().setValue(null);
                edtPlaca.textProperty().setValue(null);

            } else if (estadoTela.equals(EstadoTela.EDITANDO)) {
                businnes.alterarCarro(this.entidade);
                try {
                    abrirTelaDePesquisa(palco);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnJanelaPesquisa = new Button("Pesquisa");
        btnJanelaPesquisa.setStyle("-fx-base: rgb("+(204)+","+(255)+","+(0)+");");

        ToolBar toolBar = new ToolBar();
        toolBar.getItems().addAll(btnConfirmar, btnJanelaPesquisa);

        janela.getChildren().add(toolBar);


        btnJanelaPesquisa.setOnAction(action -> {
            //comandos a executar quanto clicarmos no botão pesquisa
            try {


                abrirTelaDePesquisa(palco);

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    private void abrirTelaDePesquisa(Stage palco) throws Exception {
        TelaPesquisa pesquisa = new TelaPesquisa();
        pesquisa.start(palco);
        pesquisa.atualizarGrid();
    }

    public VBox getTela() {
        return this.janela;
    }

    public void iniciaInsercao() {
        this.estadoTela = EstadoTela.INSERINDO;
    }

    public void iniciaAlteracao(Carro carro) {
        this.entidade = carro;
        this.estadoTela = EstadoTela.EDITANDO;
        edtCodigo.textProperty().setValue(carro.getCodigo());
        edtModelo.textProperty().setValue(carro.getModelo());
        edtMarca.textProperty().setValue(carro.getMarca());
        edtQtdportas.textProperty().setValue(carro.getCodigo());
        edtCor.textProperty().setValue(carro.getModelo());
        edtProprietario.textProperty().setValue(carro.getCodigo());
        edtPlaca.textProperty().setValue(carro.getModelo());


    }
}
