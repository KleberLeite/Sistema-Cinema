package cinemax.backend.relatorios;

import cinemax.backend.alimentos.IBancoDeDadosAlimento;

public class DummyGerenciadorDeRelatorios  {
	public static GerenciadorDeRelatorios generate(IBancoDeDadosAlimento bancoAlimentos) {
		CircularBuffer<Relatorio> relatorios = new CircularBuffer<Relatorio>(7);
		for(int i = 0; i < 4; i++) {
			relatorios.push(Relatorio.dummy(bancoAlimentos));
		}
		return new GerenciadorDeRelatorios(relatorios);
	}
}
