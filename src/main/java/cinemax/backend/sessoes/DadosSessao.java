package cinemax.backend.sessoes;

import java.time.LocalDateTime;

import cinemax.backend.salas.DadosSala;
import cinemax.backend.salas.Estrutura;
import cinemax.utilities.ConversorDeCoordenadas;

public class DadosSessao {
	private int id;
	private boolean[] reservados;
	private DadosSala sala;
	private LocalDateTime inicio;
	//private DadosFilme filme;
	
	// TODO: Adicione receber dados filme!
	protected DadosSessao(int id, boolean[] reservados, DadosSala sala, LocalDateTime inicio) {
		this.id = id;
		this.sala = sala;
		this.inicio = inicio;
		this.reservados = reservados;
	}
	
	public int obterId() {
		return id;
	}
	
	public DadosSala obterSala() {
		return sala;
	}
	
	public boolean estaReservado(int linha, int coluna) {
		Estrutura estrutura = sala.obterEstrutura(linha, coluna);
		if(estrutura != null && estrutura != Estrutura.Vazio) {
			int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, sala.obterColunas());
			return reservados[index];
		}
		return true;
	}
	
	public LocalDateTime obterInicio() {
		return inicio;
	}
}
