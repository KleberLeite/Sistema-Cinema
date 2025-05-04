package cinemax.backend.core;

import cinemax.backend.relatorios.GerenciadorDeRelatorios;

public class GerenciadorDeRelatoriosBackend extends GerenciadorDeRelatorios {
	protected GerenciadorDeRelatoriosBackend() {
		super();
	}
	
	protected GerenciadorDeRelatoriosBackend(GerenciadorDeRelatorios gerenciador) {
		super(gerenciador);
	}

	protected void iniciarDia() {
		super.gerarNovoRelatorio();
	}
	
	protected void finalizarDia() {
		super.finalizarRelatorioAtual();
	}
}
