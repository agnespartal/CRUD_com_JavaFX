package view;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import control.CadastroControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import model.Cadastro;

public class CadastroTela extends Application{

	private TextField txtDataNasc = new TextField();
	private TextField txtTelefone = new TextField();
	private TextField txtEmail = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtCPF = new TextField();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");	
	
	private CadastroControl control;
	
	public void adicionar() {
		try {
			control.cadastrar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pesquisar() {
		
		try {
			Cadastro c = control.pesquisar();
			txtNome.setText(c.getNome());
			txtDataNasc.setText(dtf.format(c.getDataNasc()));
			txtTelefone.setText(c.getTelefone());
			txtEmail.setText(c.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void excluir() {
		try {
			control.excluir();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar() {
		try {
			control.cadastrar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void limpar() {
		control.limpar();
	}
	
	
	public void ligacoes() {
		Bindings.bindBidirectional(txtCPF.textProperty(), 
				control.cpfProperty(), (StringConverter)new IntegerStringConverter());
		Bindings.bindBidirectional(txtDataNasc.textProperty(), 
				control.dataNascProperty(),(StringConverter)new LocalDateStringConverter());
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtTelefone.textProperty(), control.telefoneProperty());
		Bindings.bindBidirectional(txtEmail.textProperty(), control.emailProperty());
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		
		control = new CadastroControl();
		
		BorderPane principal = new BorderPane();
		Scene scn = new Scene(principal, 400, 350);
		
		GridPane grid = new GridPane();
		principal.setCenter(grid);
		
		
		grid.add(new Label("CPF: "), 0, 0);
		grid.add(txtCPF, 1, 0);
		grid.add(new Label(""), 0, 1);
		grid.add(new Label("Nome: "), 0, 2);
		grid.add(txtNome, 1, 2);
		grid.add(new Label(""), 0, 3);
		grid.add(new Label("Data Nascimento: "), 0, 4);
		grid.add(txtDataNasc, 1, 4);
		grid.add(new Label(""), 0, 5);
		grid.add(new Label("Telefone: "), 0, 6);
		grid.add(txtTelefone, 1, 6);
		grid.add(new Label(""), 0, 7);
		grid.add(new Label("E-mail: "), 0, 8);
		grid.add(txtEmail, 1, 8);
		grid.add(new Label(""), 0, 9);
		grid.add(new Label(""), 0, 10);
		grid.add(new Label(""), 0, 11);
		
		ligacoes();
		
		Button btnCadastrar = new Button("Cadastrar");
		btnCadastrar.setOnAction( e -> adicionar());
		
		Button btnAlterar = new Button("Alterar");
		btnAlterar.setOnAction(e -> alterar());
		
		Button btnPesquisar = new Button("Pesquisar");
		btnPesquisar.setOnAction(e -> pesquisar());
		
		Button btnExcluir = new Button("Excluir");
		btnExcluir.setOnAction(e -> excluir());
		
		Button btnLimpar = new Button("Limpar");
		btnLimpar.setOnAction(e -> limpar());
		
		FlowPane painelBotoes1 = new FlowPane();
		painelBotoes1.getChildren().addAll(btnCadastrar, btnPesquisar);
		
		FlowPane painelBotoes2 = new FlowPane();
		painelBotoes2.getChildren().addAll(btnExcluir, btnAlterar, btnLimpar);
		
		grid.add(painelBotoes2, 0, 12);
		grid.add(painelBotoes1, 1, 12);
		
		stage.setScene(scn);
		stage.setTitle("Cadastro de Usuário");
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
