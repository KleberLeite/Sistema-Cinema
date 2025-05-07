package cinemax.backend.relatorios;

import java.time.LocalDateTime;

import cinemax.backend.events.Event;
import cinemax.backend.relatorios.alimentos.RelatorioAlimentos;
import cinemax.backend.relatorios.filmes.RelatorioFilmes;

public class Relatorio {
	private RelatorioAlimentos relatorioAlimentos;
	private RelatorioFilmes relatorioFilmes;
	private LocalDateTime inicio;
	private LocalDateTime fim;
	
	private boolean fechado;
	private boolean permitirAlteracoes;
	
	public final Event<Boolean> aoAlterarPermissaoAlteracoes;
	
	protected Relatorio(
		Event<Boolean> aoAlterarPermissaoAlteracoes,
		RelatorioAlimentos relatorioAlimentos,
		RelatorioFilmes relatorioFilmes
	) {
		this.inicio = LocalDateTime.now();
		this.relatorioAlimentos = relatorioAlimentos;
		this.relatorioFilmes = relatorioFilmes;
		this.aoAlterarPermissaoAlteracoes = aoAlterarPermissaoAlteracoes;
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
	
	protected void setPermitirAlteracoes(boolean permitirAlteracoes) {
		if(!fechado && this.permitirAlteracoes != permitirAlteracoes) {
			this.permitirAlteracoes = permitirAlteracoes;
			aoAlterarPermissaoAlteracoes.raiseEvent(permitirAlteracoes);
		}
	}
	
	protected void finalizar() {
		setPermitirAlteracoes(false);
		fechado = true;
		fim = LocalDateTime.now();
	}
}
