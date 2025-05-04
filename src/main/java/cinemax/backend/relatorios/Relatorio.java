package cinemax.backend.relatorios;

import cinemax.backend.alimentos.IBancoDeDadosAlimento;

public class Relatorio {
	private RelatorioAlimentos relatorioAlimentos;
	private RelatorioFilmes relatorioFilmes;
	private boolean fechado;
	
	protected static Relatorio vazio() {
		Relatorio r = new Relatorio();
		r.relatorioAlimentos = new RelatorioAlimentos(r);
		r.relatorioFilmes = new RelatorioFilmes(r);
		
		return r;
	}
	
	protected static Relatorio dummy(IBancoDeDadosAlimento bancoAlimentos) {
		Relatorio r = new Relatorio();
		r.relatorioAlimentos = new DummyRelatorioAlimentos(bancoAlimentos, r);
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
