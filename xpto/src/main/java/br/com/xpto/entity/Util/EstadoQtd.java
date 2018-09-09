package br.com.xpto.entity.Util;

public class EstadoQtd {

	private String estadoComMaior;
	private String estadoComMenor;
	private int qtdCidadesMaior;
	private int qtdCidadesMenor;

	public EstadoQtd() {
	}

	public EstadoQtd(String estadoComMaior, String estadoComMenor, int qtdCidadesMaior, int qtdCidadesMenor) {
		this.estadoComMaior = estadoComMaior;
		this.estadoComMenor = estadoComMenor;
		this.qtdCidadesMaior = qtdCidadesMaior;
		this.qtdCidadesMenor = qtdCidadesMenor;
	}

	public String getEstadoComMaior() {
		return estadoComMaior;
	}

	public void setEstadoComMaior(String estadoComMaior) {
		this.estadoComMaior = estadoComMaior;
	}

	public String getEstadoComMenor() {
		return estadoComMenor;
	}

	public void setEstadoComMenor(String estadoComMenor) {
		this.estadoComMenor = estadoComMenor;
	}

	public int getQtdCidadesMaior() {
		return qtdCidadesMaior;
	}

	public void setQtdCidadesMaior(int qtdCidadesMaior) {
		this.qtdCidadesMaior = qtdCidadesMaior;
	}

	public int getQtdCidadesMenor() {
		return qtdCidadesMenor;
	}

	public void setQtdCidadesMenor(int qtdCidadesMenor) {
		this.qtdCidadesMenor = qtdCidadesMenor;
	}

}
