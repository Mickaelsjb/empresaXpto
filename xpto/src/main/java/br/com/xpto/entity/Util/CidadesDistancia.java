package br.com.xpto.entity.Util;

public class CidadesDistancia {
	
	private String cidadeA;
	private String cidadeB;
	private double distacia;
	
	
	public CidadesDistancia() {
	}
	public CidadesDistancia(String cidadeA, String cidadeB, double distacia) {
		this.cidadeA = cidadeA;
		this.cidadeB = cidadeB;
		this.distacia = distacia;
	}
	public String getCidadeA() {
		return cidadeA;
	}
	public void setCidadeA(String cidadeA) {
		this.cidadeA = cidadeA;
	}
	public String getCidadeB() {
		return cidadeB;
	}
	public void setCidadeB(String cidadeB) {
		this.cidadeB = cidadeB;
	}
	public double getDistacia() {
		return distacia;
	}
	public void setDistacia(double distacia) {
		this.distacia = distacia;
	}
	
	

}
