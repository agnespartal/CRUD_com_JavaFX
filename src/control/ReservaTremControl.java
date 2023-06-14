package control;

import java.sql.SQLException;
import java.time.LocalDate;


import database.ReservaTremDAO;
import database.ReservaTremDAOImpl;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ReservaTrem;

public class ReservaTremControl {

	private IntegerProperty cpf = new SimpleIntegerProperty(0);
	private IntegerProperty qtdPassageiro = new SimpleIntegerProperty(0);
	private StringProperty partida = new SimpleStringProperty("");
	private StringProperty destino = new SimpleStringProperty("");
	private StringProperty horaPartida = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> dataPartida = new SimpleObjectProperty<>(LocalDate.now());
	private ObservableList<ReservaTrem> lista = FXCollections.observableArrayList();

	private ReservaTremDAO rTremDAO;

	public ReservaTremControl() throws Exception {
		rTremDAO = new ReservaTremDAOImpl();
	}
	
	public boolean cpfExist() throws SQLException {
		ReservaTrem rTrem = rTremDAO.procurarPorCPF(cpf.get());
		if (rTrem != null) {
			return true;
		}else
			return false;
	}

	public void reservar() throws SQLException {
		if (!cpfExist()) {
			ReservaTrem rTrem = new ReservaTrem();
			rTrem.setPartida(partida.get());
			rTrem.setDestino(destino.get());
			rTrem.setDataPartida(dataPartida.get());
			rTrem.setHoraPartida(horaPartida.get());
			rTrem.setQtdPassageiro(qtdPassageiro.get());
			rTrem.setCpf(cpf.get());
			ReservaTrem trem = rTremDAO.adicionar(rTrem);
			lista.add(trem);
			System.out.println("Reserva control");
		} else {
			ReservaTrem rTrem = new ReservaTrem();
			rTrem.setPartida(partida.get());
			rTrem.setDestino(destino.get());
			rTrem.setDataPartida(dataPartida.get());
			rTrem.setHoraPartida(horaPartida.get());
			rTrem.setQtdPassageiro(qtdPassageiro.get());
			rTremDAO.atualizar(cpf.get(), rTrem);
			System.out.println("Altera control");
		}
	}

	public void limpar() {
		cpf.set(0);
		partida.set("");
		destino.set("");
		dataPartida.set(LocalDate.now());
		horaPartida.set("");
		qtdPassageiro.set(0);

	}

	public void excluir() throws SQLException {
		rTremDAO.remover(cpf.get());
		limpar();
		
	}

	public ReservaTrem pesquisar() throws SQLException {
		ReservaTrem rTrem = rTremDAO.procurarPorCPF(cpf.get());
		return rTrem;
	}

	public StringProperty partidaProperty() {
		return partida;
	}

	public StringProperty destinoProperty() {
		return destino;
	}

	public StringProperty horaPatidaProperty() {
		return horaPartida;
	}

	public IntegerProperty cpfProperty() {
		return cpf;
	}

	public IntegerProperty qtdPassageiroProperty() {
		return qtdPassageiro;
	}

	public ObjectProperty<LocalDate> dataPartidaProperty() {
		return dataPartida;
	}

	public ObservableList<ReservaTrem> getLista() {
		return lista;
	}

}
