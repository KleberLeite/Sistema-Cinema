package cinemax.backend.filmes;

import java.time.LocalDateTime;

import cinemax.backend.salas.Sala;
import cinemax.backend.salas.TipoDeEstrutura;
import cinemax.utilities.ConversorDeCoordenadas;

public class Sessao {
	private int id;
	private boolean[] reservados;
	private Sala sala;
	private LocalDateTime inicio;
	private Filme filme;
	
	public Sessao(
		int id,
		Sala sala,
		Filme filme,
		LocalDateTime inicio
	) {
		this.id = id;
		this.sala = sala;
		this.filme = filme;
		this.inicio = inicio;
		this.reservados = new boolean[sala.getLinhas() * sala.getColunas()];
	}

	public boolean estaReservado(int linha, int coluna) {
		if(!sala.estaDentroDaSala(linha, coluna)) {
			return false;
		}
		if(sala.obterTipoDeEstrutura(linha, coluna) == TipoDeEstrutura.Vazio) {
			return false;
		}
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, sala.getColunas());
		return reservados[index];
	}

	public Sala getSala() {
		return sala;
	}

	protected void setSala(Sala sala) {
		this.sala = sala;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	protected void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public Filme getFilme() {
		return filme;
	}

	protected void setFilme(Filme filme) {
		this.filme = filme;
	}

	public int getId() {
		return id;
	}
	
	protected boolean tentarReservar(int linha, int coluna) {
		if(!sala.estaDentroDaSala(linha, coluna)) {
			return false;
		}
		if(sala.obterTipoDeEstrutura(linha, coluna) == TipoDeEstrutura.Vazio) {
			return false;
		}
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, sala.getColunas());
		if(reservados[index]) {
			return false;
		}
		reservados[index] = true;
		return true;
	}
	
	protected boolean tentarDesreservar(int linha, int coluna) {
		if(!sala.estaDentroDaSala(linha, coluna)) {
			return false;
		}
		if(sala.obterTipoDeEstrutura(linha, coluna) == TipoDeEstrutura.Vazio) {
			return false;
		}
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, sala.getColunas());
		if(!reservados[index]) {
			return false;
		}
		reservados[index] = false;
		return true;
	}
}
