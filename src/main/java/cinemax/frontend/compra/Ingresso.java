package cinemax.frontend.compra;

import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.Sessao;
import cinemax.backend.salas.Sala;

public class Ingresso {
	
	private static double precoGeral = 20;
	
	private Sala sala;
	private Sessao sessao;
	private Filme filme;
	private double preco = precoGeral;
	private TipoDeIngresso tipo;
	private String poltrona;
	
	public Ingresso(Sessao sessao, String poltrona) {
		super();
		this.sala = sessao.getSala();
		this.sessao = sessao;
		this.filme = sessao.getFilme();
		this.poltrona = poltrona;
	}
	
	public static double precoIngresso() {
		return precoGeral;
	}
	
	public double definePreco(TipoDeIngresso tipo) {
		if(this.isMeia(tipo)) return this.preco/2;
		
		return this.preco;
	}
	
	private boolean isMeia(TipoDeIngresso tipo) {
		if(tipo == TipoDeIngresso.Inteira) return false;
		return true;
	}
	
	public TipoDeIngresso getTipo() {
		return tipo;
	}

	public Filme getFilme() {
		return filme;
	}

	public double getPreco() {
		return preco;
	}

	public Sala getSala() {
		return sala;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public String getPoltrona() {
		return poltrona;
	}
	public void setPoltrona(String poltrona) {
		this.poltrona = poltrona;
	}
	
	
	
}
