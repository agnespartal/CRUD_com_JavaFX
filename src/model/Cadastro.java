package model;

import java.time.LocalDate;

public class Cadastro {
	
	private String nome;
	private int cpf;
	private String email;
	private String telefone;
	private LocalDate dataNasc;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getCpf() {
		return cpf;
	}
	
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public LocalDate getDataNasc() {
		return dataNasc;
	}
	
	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	@Override
	public String toString() {
		return this.cpf + " - " + this.nome + " - " + this.telefone + " - " + this.dataNasc + " - " + this.email;
	}
	
	
}
