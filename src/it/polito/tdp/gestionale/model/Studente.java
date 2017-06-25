package it.polito.tdp.gestionale.model;

public class Studente extends Nodo {

	private int matricola;
	private String cognome;
	private String nome;
	private String cds;

	public Studente(int matricola, String cognome, String nome, String cds) {
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.cds = cds;
	}

	/*
	 * Getters and Setters
	 */

	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public String getCognome() {
		if (cognome == null)
			return "";
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		if (nome == null)
			return "";
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCds() {
		if (cds == null)
			return "";
		return cds;
	}

	public void setCds(String cds) {
		this.cds = cds;
	}

	@Override
	public String toString() {
		return matricola+"";
	}

}
