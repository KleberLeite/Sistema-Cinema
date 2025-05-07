package cinemax.backend.relatorios.filmes;

import cinemax.backend.filmes.Sessao;
import cinemax.backend.salas.Poltrona;

public class Ingresso {
	public static final double PRECO_INGRESSO = 20;
	
	private Sessao sessao;
	private TipoDeIngresso tipo;
	private Poltrona poltrona;
	private String RG;
	
	public Ingresso(Sessao sessao, Poltrona poltrona) {
		super();
		this.sessao = sessao;
		this.poltrona = poltrona;
	}	
	
	public Ingresso(Sessao sessao) {
		super();
		this.sessao = sessao;
	}

	@Override
	public String toString() {
		return "Ingresso [Sala=" + sessao.getSala().getIdSala() + 
				", \nSessao=" + sessao.getId() + 
				", \nFilme=" + sessao.getFilme().getNome() + 
				", \nPreco=" + getPreco() + 
				", \nTipo=" + tipo /*+ 
				", \nPoltrona=" + poltrona.getIdentificador()*/ + 
				", \nRG=" + RG + "]";
	}
	


	public void setTipo(TipoDeIngresso tipo) {
		this.tipo = tipo;
	}
	
	public void setRG(String RG) {
		this.RG = RG;
	}

	public String getRG() {
		return RG;
	}

	public TipoDeIngresso getTipo() {
		return tipo;
	}

	public double getPreco() {
		return tipo == TipoDeIngresso.Meia ? PRECO_INGRESSO / 2 : PRECO_INGRESSO;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public Poltrona getPoltrona() {
		return poltrona;
	}
}
