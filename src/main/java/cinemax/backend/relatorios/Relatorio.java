package cinemax.backend.relatorios;

public class Relatorio {
	private RelatorioAlimentos relatorioAlimentos;
	private RelatorioFilmes relatorioFilmes;
	private boolean fechado;
	
	private Relatorio() { }
	
	protected static Relatorio vazio() {
		Relatorio r = new Relatorio();
		r.relatorioAlimentos = new RelatorioAlimentos(r);
		r.relatorioFilmes = new RelatorioFilmes(r);
		
		return r;
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
