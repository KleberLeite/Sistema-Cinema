package cinemax.backend.relatorios;

public class Relatorio {
	private RelatorioAlimentos relatorioAlimentos;
	private RelatorioFilmes relatorioFilmes;
	private boolean fechado;
	
	public Relatorio() {
		this.relatorioAlimentos = new RelatorioAlimentos(this);
		this.relatorioFilmes = new RelatorioFilmes(this);
	}

	public RelatorioAlimentos getRelatorioAlimentos() {
		return relatorioAlimentos;
	}
	
	public RelatorioFilmes getRelatorioFilmes() {
		return relatorioFilmes;
	}
	
	protected void fechar() {
		fechado = true;
	}
	
	protected boolean estaFechado() {
		return fechado;
	}
}
