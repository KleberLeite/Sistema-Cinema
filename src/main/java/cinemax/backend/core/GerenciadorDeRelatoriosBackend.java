package cinemax.backend.core;

import cinemax.backend.relatorios.GerenciadorDeRelatorios;

public class GerenciadorDeRelatoriosBackend extends GerenciadorDeRelatorios {
	protected GerenciadorDeRelatoriosBackend() {
		super();
	}

	protected void iniciarDia() {
		super.gerarNovoRelatorio();
	}
	
	protected void finalizarDia() {
		super.finalizarRelatorioAtual();
	}
}
