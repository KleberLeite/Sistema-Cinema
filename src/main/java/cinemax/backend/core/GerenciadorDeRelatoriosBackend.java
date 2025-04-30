package cinemax.backend.core;

import cinemax.backend.relatorios.CircularBuffer;
import cinemax.backend.relatorios.GerenciadorDeRelatorios;
import cinemax.backend.relatorios.Relatorio;

public class GerenciadorDeRelatoriosBackend extends GerenciadorDeRelatorios {
	protected GerenciadorDeRelatoriosBackend() {
		super();
	}
	
	protected GerenciadorDeRelatoriosBackend(GerenciadorDeRelatorios gerenciador) {
		super(gerenciador);
	}

	@Override
	public void novoDia() {
		super.novoDia();
	}
}
