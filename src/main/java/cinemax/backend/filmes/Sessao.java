package cinemax.backend.filmes;

import java.time.LocalDateTime;

import cinemax.backend.salas.Sala;
import cinemax.backend.salas.TipoDeEstrutura;
import cinemax.utilities.ConversorDeCoordenadas;

// Mantém informações da sessão e de quais locais foram reservados.
public class Sessao {
	private int id;
	private boolean[][] reservados;
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
		this.reservados = new boolean[sala.getLinhas()][sala.getColunas()];
	}

	public boolean estaReservado(int linha, int coluna) {
		if(!sala.estaDentroDaSala(linha, coluna)) {
			return false;
		}
		if(sala.obterTipoDeEstrutura(linha, coluna).getTipo() == TipoDeEstrutura.Vazio) {
			return false;
		}
		return reservados[linha][coluna];
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
	
	// Tenta reservar um local, retornando falso caso:
	// 1. A posição seja inválida;
	// 2. A estrutura é uma passagem;
	// 3. Já esteja reservado.
	protected boolean tentarReservar(int linha, int coluna) {
		if(!sala.estaDentroDaSala(linha, coluna)) {
			return false;
		}
		if(sala.obterTipoDeEstrutura(linha, coluna).getTipo() == TipoDeEstrutura.Vazio) {
			return false;
		}
		if(reservados[linha][coluna]) {
			return false;
		}
		reservados[linha][coluna] = true;
		return true;
	}

	// Tenta desreservar um local, retornando falso caso:
	// 1. A posição seja inválida;
	// 2. A estrutura é uma passagem;
	// 3. A posição não esteja reservada.
	protected boolean tentarDesreservar(int linha, int coluna) {
		if(!sala.estaDentroDaSala(linha, coluna)) {
			return false;
		}
		if(sala.obterTipoDeEstrutura(linha, coluna).getTipo() == TipoDeEstrutura.Vazio) {
			return false;
		}
		if(!reservados[linha][coluna]) {
			return false;
		}
		reservados[linha][coluna] = false;
		return true;
	}
}
