package br.com.xpto.entity.Util;

public class EstadoQtdCidade {
	
	private String estado;
	private int qtd;
	
	
	public EstadoQtdCidade() {
	}
	
	public EstadoQtdCidade(String estado, int qtd) {
		this.estado = estado;
		this.qtd = qtd;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	

}
