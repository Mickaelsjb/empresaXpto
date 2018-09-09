package br.com.xpto.entity.Util;

public class CidadeEntradaCSV {

	private String ibge;
	private String uf;
	private String nome;
	private boolean capital;
	private String lon;
	private String lat;
	private String noAccents;
	private String namesAlternatives;
	private String microregion;
	private String mensoregion;

	public CidadeEntradaCSV() {
	}

	public CidadeEntradaCSV(String ibge, String uf, String nome, boolean capital, String lon, String lat,
			String noAccents, String namesAlternatives, String microregion, String mensoregion) {
		this.ibge = ibge;
		this.uf = uf;
		this.nome = nome;
		this.capital = capital;
		this.lon = lon;
		this.lat = lat;
		this.noAccents = noAccents;
		this.namesAlternatives = namesAlternatives;
		this.microregion = microregion;
		this.mensoregion = mensoregion;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isCapital() {
		return capital;
	}

	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getNoAccents() {
		return noAccents;
	}

	public void setNoAccents(String noAccents) {
		this.noAccents = noAccents;
	}

	public String getNamesAlternatives() {
		return namesAlternatives;
	}

	public void setNamesAlternatives(String namesAlternatives) {
		this.namesAlternatives = namesAlternatives;
	}

	public String getMicroregion() {
		return microregion;
	}

	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	public String getMensoregion() {
		return mensoregion;
	}

	public void setMensoregion(String mensoregion) {
		this.mensoregion = mensoregion;
	}

}
