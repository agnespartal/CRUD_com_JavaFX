package view;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import control.ReservaTremControl;
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
import model.ReservaTrem;

public class ReservaTremTela extends Application{
	
	private TextField txtPartida = new TextField();
	private TextField txtDestino = new TextField();
	private TextField txtDataPartida = new TextField();
	private TextField txtHoraPartida = new TextField();
	private TextField txtQtdPassageiro = new TextField();
	private TextField txtCPF = new TextField();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private ReservaTremControl control;
	
	public void adicionar() {
		try {
			control.reservar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void pesquisar() {
		try {
			ReservaTrem rTrem = control.pesquisar();
			txtPartida.setText(rTrem.getPartida());
			txtDestino.setText(rTrem.getDestino());
			txtDataPartida.setText(dtf.format(rTrem.getDataPartida()));
			txtHoraPartida.setText(rTrem.getHoraPartida());
			txtQtdPassageiro.setText(String.valueOf(rTrem.getQtdPassageiro()));
		} catch (SQLException e) {
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
			control.reservar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void limpar() {
		control.limpar();
	}

	public void ligacoes() {
		Bindings.bindBidirectional(txtPartida.textProperty(), control.partidaProperty());	
		Bindings.bindBidirectional(txtDestino.textProperty(), control.destinoProperty());
		Bindings.bindBidirectional(txtHoraPartida.textProperty(), control.horaPatidaProperty());
		Bindings.bindBidirectional(txtCPF.textProperty(), control.cpfProperty(),
				(StringConverter)new IntegerStringConverter());
		Bindings.bindBidirectional(txtQtdPassageiro.textProperty(), control.qtdPassageiroProperty(),
				(StringConverter)new IntegerStringConverter());
		Bindings.bindBidirectional(txtDataPartida.textProperty(), control.dataPartidaProperty(),
				(StringConverter)new LocalDateStringConverter());
	}
	
	@Override
	public void start(Stage stage) {
		try {
			control = new ReservaTremControl();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		BorderPane principal = new BorderPane();
		Scene scn = new Scene(principal, 400, 350);
		
		GridPane grid = new GridPane();
		principal.setCenter(grid);
		
		grid.add(new Label("Partida: "), 0, 0);
		grid.add(txtPartida, 1, 0);
		grid.add(new Label(""), 0, 1);
		grid.add(new Label("Destino: "), 0, 2);
		grid.add(txtDestino, 1, 2);
		grid.add(new Label(""), 0, 3);
		grid.add(new Label("Data da Partida: "), 0, 4);
		grid.add(txtDataPartida, 1, 4);
		grid.add(new Label(""), 0, 5);
		grid.add(new Label("Hora da Partida: "), 0, 6);
		grid.add(txtHoraPartida, 1, 6);
		grid.add(new Label(""), 0, 7);
		grid.add(new Label("Quantidade de Passageiros: "), 0, 8);
		grid.add(txtQtdPassageiro, 1, 8);
		grid.add(new Label(""), 0, 9);
		grid.add(new Label("CPF do Titular: "), 0, 10);
		grid.add(txtCPF, 1, 10);
		grid.add(new Label(""), 0, 11);
		grid.add(new Label(""), 0, 12);
		grid.add(new Label(""), 0, 13);
		
		ligacoes();

		Button btnReservar = new Button("Reservar");
		btnReservar.setOnAction(e -> adicionar());
		
		Button btnAlterar = new Button("Alterar");
		btnAlterar.setOnAction(e -> alterar());
		
		Button btnPesquisar = new Button("Pesquisar");
		btnPesquisar.setOnAction(e -> pesquisar());

		Button btnExcluir = new Button("Excluir");
		btnExcluir.setOnAction(e -> excluir());
		
		Button btnLimpar = new Button("Limpar");
		btnLimpar.setOnAction(e -> limpar());
		
		FlowPane painelBotoes1 = new FlowPane();
		painelBotoes1.getChildren().addAll(btnReservar,btnPesquisar);
	
		FlowPane painelBotoes2 = new FlowPane();
		painelBotoes2.getChildren().addAll(btnExcluir, btnAlterar, btnLimpar);
	
		grid.add(painelBotoes2, 0, 14);
		grid.add(painelBotoes1, 1, 14);
		
		stage.setScene(scn);
		stage.setTitle("Reservar Viagem de Trem");
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
