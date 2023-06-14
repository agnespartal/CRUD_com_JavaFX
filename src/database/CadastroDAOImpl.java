package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cadastro;

public class CadastroDAOImpl implements CadastroDAO{
	
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/Cadastro?allowMultiQueries=true";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "";
	private Connection con;
	
	public CadastroDAOImpl() throws Exception {
		Class.forName(JDBC_DRIVER);
		con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
	}

	@Override
	public Cadastro adicionar(Cadastro c) throws SQLException {
		String sql = "INSERT INTO Usuarios (cpf, nome, dataNasc, telefone, email) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		st.setInt(1, c.getCpf());
		st.setString(2, c.getNome());
		st.setDate(3, Date.valueOf(c.getDataNasc()));
		st.setString(4, c.getTelefone());
		st.setString(5, c.getEmail());
		st.executeUpdate();
		return c;
	}

	@Override
	public void atualizar(int cpf, Cadastro c) throws SQLException {
		String sql = "UPDATE Usuarios SET nome = ?, dataNasc = ?,"
				+ "telefone = ?, email = ? WHERE cpf = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, c.getNome());
		st.setDate(2, Date.valueOf(c.getDataNasc()));
		st.setString(3, c.getTelefone());
		st.setString(4, c.getEmail());
		st.setInt(5, cpf);
		st.executeUpdate();
	}

	@Override
	public void remover(int cpf) throws SQLException {
		String sql = "DELETE FROM usuarios WHERE cpf = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cpf);
		st.executeUpdate();
	}

	@Override
	public Cadastro procuraPorCPF(int cpf) throws Exception {
		String sql = "Select * from usuarios where cpf=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cpf);
		ResultSet rs = st.executeQuery();
		Cadastro c = new Cadastro();
		if (rs.next()) {
			c.setCpf(rs.getInt("cpf"));
			c.setNome(rs.getString("nome"));
			Date dt = rs.getDate("dataNasc");
			c.setDataNasc(dt.toLocalDate());
			c.setTelefone(rs.getString("telefone"));
			c.setEmail(rs.getString("email"));
			
			return c;
		}
		return null;
	}
	
	
}
