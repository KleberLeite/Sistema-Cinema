package cinemax.backend.sessoes;

import java.time.LocalDateTime;

import cinemax.backend.filmes.IFilme;
import cinemax.backend.salas.ISala;
import cinemax.utilities.ConversorDeCoordenadas;

public class Sessao implements ISessao {
	private int id;
	private ISala sala;
	private IFilme filme;
	private boolean[] reservados;
	private LocalDateTime inicio;
	private DadosSessao cachedDados;
	private boolean houveMudancas;
		
	public Sessao(int id, ISala sala, IFilme filme, LocalDateTime inicio) {
		this.id = id;
		this.sala = sala;
		this.filme = filme;
		this.inicio = inicio;
		this.reservados = new boolean[sala.obterLinhas() * sala.obterColunas()];
		this.houveMudancas = true;
	}
	
	@Override
	public boolean tentarReservar(int linha, int coluna) {
		if(!sala.estaDentroDaSala(linha, coluna)) {
			return false;
		}
		
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, sala.obterColunas());
		if(reservados[index]) {
			return false;
		}
		reservados[index] = true;
		houveMudancas = true;
		return true;
	}

	@Override
	public boolean estaReservado(int linha, int coluna) {
		if(!sala.estaDentroDaSala(linha, coluna)) {
			return false;
		}
		
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, sala.obterColunas());
		return reservados[index];
	}

	@Override
	public boolean tentarDesreservar(int linha, int coluna) {
		if(!sala.estaDentroDaSala(linha, coluna)) {
			return false;
		}
		
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, sala.obterColunas());
		if(!reservados[index]) {
			return false;
		}
		reservados[index] = false;
		houveMudancas = true;
		return true;
	}

	@Override
	public ISala obterEstruturaSala() {
		return sala;
	}

	@Override
	public LocalDateTime obterInicio() {
		return inicio;
	}

	@Override
	public int obterIdSessao() {
		return id;
	}

	@Override
	public IFilme obterFilme() {
		return filme;
	}

	@Override
	public DadosSessao obterCopiaDados() {
		if(houveMudancas) {
			cachedDados = new DadosSessao(id, reservados, sala.obterCopiaDados(), inicio);
		}
		return cachedDados;
	}
}
