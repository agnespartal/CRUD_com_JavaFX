package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.ReservaTrem;

public class ReservaTremDAOImpl implements ReservaTremDAO {

	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/Trem?allowMultiQueries=true";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "";
	private Connection con;

	public ReservaTremDAOImpl() throws Exception {
		Class.forName(JDBC_DRIVER);
		con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
	}

	@Override
	public ReservaTrem adicionar(ReservaTrem rTrem) throws SQLException {
		String sql = "INSERT INTO ReservaTrem " + "(cpf, partida, destino, dataPartida, horaPartida, qtdPassageiro)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		st.setInt(1, rTrem.getCpf());
		st.setString(2, rTrem.getPartida());
		st.setString(3, rTrem.getDestino());
		st.setDate(4, Date.valueOf(rTrem.getDataPartida()));
		st.setString(5, rTrem.getHoraPartida());
		st.setInt(6, rTrem.getQtdPassageiro());
		st.executeUpdate();
		return rTrem;
	}

	@Override
	public void atualizar(int cpf, ReservaTrem rTrem) throws SQLException {
		String sql = "UPDATE ReservaTrem SET partida = ?, destino = ?, dataPartida = ?, "
				+ "horaPartida = ?, qtdPassageiro = ? WHERE cpf = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, rTrem.getPartida());
		st.setString(2, rTrem.getDestino());
		st.setDate(3, Date.valueOf(rTrem.getDataPartida()));
		st.setString(4, rTrem.getHoraPartida());
		st.setInt(5, rTrem.getQtdPassageiro());
		st.setInt(6, cpf);
		st.executeUpdate();
	}

	@Override
	public void remover(int cpf) throws SQLException {
		String sql = "DELETE FROM ReservaTrem WHERE cpf = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cpf);
		st.executeUpdate();
	}

	@Override
	public ReservaTrem procurarPorCPF(int cpf) throws SQLException {
		String sql = "Select * from ReservaTrem where cpf=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cpf);
		ResultSet rs = st.executeQuery();
		ReservaTrem rTrem = new ReservaTrem();
		if (rs.next()) {
			rTrem.setCpf(rs.getInt("cpf"));
			rTrem.setPartida(rs.getString("partida"));
			rTrem.setDestino(rs.getString("destino"));
			Date dt = rs.getDate("dataPartida");
			rTrem.setDataPartida(dt.toLocalDate());
			rTrem.setHoraPartida(rs.getString("horaPartida"));
			rTrem.setQtdPassageiro(rs.getInt("qtdPassageiro"));

			return rTrem;
		}
		return null;
	}

}
