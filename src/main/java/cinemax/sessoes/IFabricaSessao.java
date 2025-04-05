package cinemax.sessoes;

import java.time.LocalDate;

import cinemax.salas.IEstruturaSala;

public interface IFabricaSessao {
	public ISessao criar(IEstruturaSala estrutura, LocalDate inicio);
}
