package cinemax.sessoes;

import java.time.LocalDateTime;

import cinemax.salas.IEstruturaSala;

public interface ISessao {
	public boolean tentarReservar(int linha, int coluna);
	public boolean estaReservado(int linha, int coluna);
	public boolean tentarDesreservar(int linha, int coluna);
	public IEstruturaSala obterEstruturaSala();
	public LocalDateTime obterInicio();
	public int obterIdSessao();
}
