package br.com.xpto.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cidades implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ibge;
	private String nome;
	private boolean capital;
	private String lon;
	private String lat;
	private String noAccents;
	private String namesAlternatives;
	private String microregion;
	private String mensoregion;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Estado estado;
	
	public Cidades() {
	}

	public Cidades(String ibge, String nome, boolean capital, String lon, String lat, String noAccents,
			String namesAlternatives, String microregion, String mensoregion, Estado estado) {
		this.ibge = ibge;
		this.nome = nome;
		this.capital = capital;
		this.lon = lon;
		this.lat = lat;
		this.noAccents = noAccents;
		this.namesAlternatives = namesAlternatives;
		this.microregion = microregion;
		this.mensoregion = mensoregion;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}