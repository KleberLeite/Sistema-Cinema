package cinemax.backend.relatorios;

import java.time.LocalDateTime;

public class Relatorio {
	private RelatorioAlimentos relatorioAlimentos;
	private RelatorioFilmes relatorioFilmes;
	private boolean fechado;
	private LocalDateTime inicio;
	private LocalDateTime fim;
	
	private Relatorio() {
		this.inicio = LocalDateTime.now();
	}
	
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
	
	public LocalDateTime getInicio() {
		return inicio;
	}
	
	public LocalDateTime getFim() {
		return fim;
	}
	
	protected void fechar() {
		fechado = true;
		fim = LocalDateTime.now();
	}
	
	protected boolean estaFechado() {
		return fechado;
	}
}
