package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.basededados.BancoDeDadosEmMemoria;
import sample.logica.PessoaBusinnes;
import sample.telas.TelaPesquisa;


public class Main extends Application {

    @Override
    public void start(Stage palco) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        palco.setTitle("Primeiro programa");
        VBox painel = new VBox();
        //componentes de entrada de dados
        Label lblCodigo = new Label("Codigo:");

        final TextField edtCodigo = new TextField();

        edtCodigo.promptTextProperty().setValue("informe o código");
        painel.getChildren().addAll(lblCodigo, edtCodigo);
        //adicionando componentes ao painel principal
        Label lblNome = new Label();
        lblNome.setText("Nome: ");

        final TextField edtNome = new TextField();

        //adicionando componentes ao painel principal
        painel.getChildren().add(lblNome);
        painel.getChildren().add(edtNome);
        //adicionando componentes ao painel principal
        Label lblEmail = new Label("Email:");
        final TextField edtEmail = new TextField();
        edtEmail.promptTextProperty().setValue("Digite aqui o email");
        painel.getChildren().addAll(lblEmail, edtEmail);

        // controles
        Button btnOK = new Button("OK");


        btnOK.setOnAction(event -> {
            PessoaBusinnes businnes = new PessoaBusinnes();
            businnes.addNovaPessoa(edtCodigo.getText(), edtNome.getText(), edtEmail.getText());
            edtCodigo.textProperty().setValue(null);
            edtNome.textProperty().setValue(null);
            edtEmail.textProperty().setValue(null);
        });

        Button btnJanelaPesquisa = new Button("Pesquisa");


        painel.getChildren().add(btnOK);
        painel.getChildren().add(btnJanelaPesquisa);


        btnJanelaPesquisa.setOnAction(action -> {
            //comandos a executar quanto clicarmos no botão pesquisa
            try {
                TelaPesquisa pesquisa = new TelaPesquisa();
                pesquisa.start(palco);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        palco.setScene(new Scene(painel, 800, 600));
        palco.show();
    }


    public static void main(String[] args) {
        launch(args);

        BancoDeDadosEmMemoria b = BancoDeDadosEmMemoria.getInstance();


    }
}
