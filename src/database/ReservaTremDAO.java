package database;

import java.sql.SQLException;
import java.util.List;

import model.ReservaTrem;

public interface ReservaTremDAO {
	
	ReservaTrem adicionar (ReservaTrem rTrem) throws SQLException;
	void atualizar (int cpf, ReservaTrem rTrem) throws SQLException;
	void remover(int cpf) throws SQLException;
	ReservaTrem procurarPorCPF(int cpf) throws SQLException;
}
