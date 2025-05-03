package cinemax.frontend.compra;

import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.Sessao;
import cinemax.backend.salas.Sala;

public class Ingresso {
	
	private static double precoIngresso = 20;
	
	private Sala sala;
	private Sessao sessao;
	private Filme filme;
	private double preco = precoIngresso;
	private TipoDeIngresso tipo;
	private String poltrona;
	private String RG;
	
	public Ingresso(Sessao sessao, String poltrona) {
		super();
		this.sala = sessao.getSala();
		this.sessao = sessao;
		this.filme = sessao.getFilme();
		this.poltrona = poltrona;
	}
	
	

	@Override
	public String toString() {
		return "Ingresso [sala=" + sala.getIdSala() + 
				", \nsessao=" + sessao.getId() + 
				", \nfilme=" + filme.getNome() + 
				", \\npreco=" + preco + 
				", \\ntipo=" + tipo + 
				", \\npoltrona=" + poltrona + 
				", \\nRG=" + RG + "]";
	}



	public static double getPrecoIngresso() {
		return precoIngresso;
	}
	
	private boolean isMeia(TipoDeIngresso tipo) {
		if(tipo == TipoDeIngresso.Inteira) return false;
		return true;
	}
	
	public void setTipo(TipoDeIngresso tipo) {
		this.tipo = tipo;
	}

	public String getRG() {
		return RG;
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
	
	
	
	
}
