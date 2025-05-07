package cinemax.backend.relatorios.filmes;

import cinemax.backend.filmes.Filme;

public class VendasIngressos {
	private Filme filme;
	private int qtdInteiras;
	private int qtdMeias;
	
	public VendasIngressos(Filme filme, int qtdInteiras, int qtdMeias) {
		this.filme = filme;
		this.qtdInteiras = qtdInteiras;
		this.qtdMeias = qtdMeias;
	}

	public Filme getFilme() {
		return filme;
	}

	public int getQtdInteiras() {
		return qtdInteiras;
	}

	public int getQtdMeias() {
		return qtdMeias;
	}
}
