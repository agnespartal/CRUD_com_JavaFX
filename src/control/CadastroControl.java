package control;

import java.sql.SQLException;
import java.time.LocalDate;

import database.CadastroDAO;
import database.CadastroDAOImpl;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Cadastro;

public class CadastroControl {

	private IntegerProperty cpf = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty telefone = new SimpleStringProperty("");
	private StringProperty email = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> dataNasc = new SimpleObjectProperty<>(LocalDate.now());
	private ObservableList<Cadastro> lista = FXCollections.observableArrayList();
	
    private CadastroDAO cDAO;
	
	public CadastroControl() throws Exception {
		cDAO = new CadastroDAOImpl();
	}
	
	public boolean cpfExist() throws Exception {
		Cadastro c = cDAO.procuraPorCPF(cpf.get());
		if( c != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void cadastrar() throws Exception {
		if (!cpfExist()) {
			Cadastro c = new Cadastro();
			c.setCpf(cpf.get());
			c.setNome(nome.get());
			c.setDataNasc(dataNasc.get());
			c.setTelefone(telefone.get());
			c.setEmail(email.get());
			Cadastro c1 = cDAO.adicionar(c);
			lista.add(c1);
			System.out.println("Cadastro feito!");
		} else {
			Cadastro c = new Cadastro();
			c.setNome(nome.get());
			c.setDataNasc(dataNasc.get());
			c.setTelefone(telefone.get());
			c.setEmail(email.get());
			cDAO.atualizar(cpf.get(), c);
			System.out.println("Alteração feita!");
		}
	}
	
	public void limpar() {
		cpf.set(0);
		nome.set("");
		dataNasc.set(LocalDate.now());
		telefone.set("");
		email.set("");
		
	}
	
	public void excluir() throws SQLException {
		cDAO.remover(cpf.get());
		limpar();
	}
	
	public Cadastro pesquisar() throws Exception {
		Cadastro c = cDAO.procuraPorCPF(cpf.get());
		return c;
	}
	
	public IntegerProperty cpfProperty() {
		return cpf;
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	public StringProperty telefoneProperty() {
		return telefone;
	}
	
	public StringProperty emailProperty() {
		return email;
	}
	
	public ObjectProperty<LocalDate> dataNascProperty(){
		return dataNasc;
	}
	
	public ObservableList<Cadastro> getLista(){
		return lista;
	}
}
