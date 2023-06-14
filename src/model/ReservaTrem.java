package model;

import java.time.LocalDate;

public class ReservaTrem {
	
	private int cpf;
	private String partida;
	private String destino;
	private LocalDate dataPartida;
	private String horaPartida;
	private int qtdPassageiro;
	
	
	public int getCpf() {
		return cpf;
	}
	
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	public String getPartida() {
		return partida;
	}
	
	public void setPartida(String partida) {
		this.partida = partida;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getHoraPartida() {
		return horaPartida;
	}
	
	public void setHoraPartida(String horaPartida) {
		this.horaPartida = horaPartida;
	}
	
	public int getQtdPassageiro() {
		return qtdPassageiro;
	}
	
	public void setQtdPassageiro(int qtdPassageiro) {
		this.qtdPassageiro = qtdPassageiro;
	}
	
	public LocalDate getDataPartida() {
		return dataPartida;
	}
	
	public void setDataPartida(LocalDate dataPartida) {
		this.dataPartida = dataPartida;
	}
	
	@Override
	public String toString() {
		return this.cpf + " - " + this.partida + " - " + this.destino + " - " + this.qtdPassageiro + " - " + 
				this.horaPartida ;
	}
	
}
