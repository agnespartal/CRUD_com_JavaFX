package database;

import java.sql.SQLException;

import model.Cadastro;

public interface CadastroDAO {

	Cadastro adicionar(Cadastro c) throws SQLException;
	void atualizar (int cpf, Cadastro c) throws SQLException;
	void remover(int cpf) throws SQLException;
	Cadastro procuraPorCPF(int cpf) throws Exception;
}
