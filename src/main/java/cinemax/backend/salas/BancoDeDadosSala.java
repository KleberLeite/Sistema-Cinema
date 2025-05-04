package cinemax.backend.salas;

import java.util.HashMap;
import java.util.Map;

import cinemax.backend.core.Backend;

public class BancoDeDadosSala implements IBancoDeDadosSala {
	private final int NUM_SALAS = 4;
	private final int TAM_HOR = 16;
	private final int TAM_VERT = 18;

	private Map<Integer, Sala> salas;
	private Backend backend;

	public BancoDeDadosSala(Backend backend) {
		this.backend = backend;
		this.salas = new HashMap<Integer, Sala>();
		preencherSalas();
	}

	private Estrutura[][] gerarEstruturaSala() {
		Estrutura[][] estrutura = new Estrutura[TAM_VERT][TAM_HOR];
		for (int i = 0; i < TAM_VERT; i++) {
			for (int j = 0; j < TAM_HOR; j++) {
				estrutura[i][j] = getEstrutura(i, j);
			}
		}
		return estrutura;
	}
	
	private Estrutura getEstrutura(int i, int j) {
		TipoDeEstrutura tipo = getTipoEstrutura(i, j);
		String identificador = getIdentificador(i, j, tipo);
		switch(tipo) {
		case Vazio:
			return new EstruturaPassagem(i, j, identificador, tipo);
		default:
			return new Poltrona(i, j , identificador, tipo);
		}
	}

/*
 
D 4  3
C 3  2
B 2  1
  1  0
A 0  0
*/
	private String getIdentificador(int i, int j, TipoDeEstrutura tipo) {
		if(tipo == TipoDeEstrutura.Vazio) {
			return " ";
		}
		if(i == 0) {
			return String.format("%c%d", 'A' + TAM_VERT - 3, j + 1);
		}
		if(i == TAM_VERT - 1) {
			return String.format("A%d", j + 1);
		}
		return String.format("%c%d", 'A' + TAM_VERT - 2 - i, j + 1);
	}

	private TipoDeEstrutura getTipoEstrutura(int i, int j) {
		// Local p/ pessoas obesas
		if(i == TAM_VERT - 1 && (j == 0 || j == 15)) {
			return TipoDeEstrutura.PoltronaObesos;
		}
		// Local p/ cadeirantes
		if(i == TAM_VERT - 1 && (j == 7 || j == 8)) {
			return TipoDeEstrutura.LocalCadeirantes;
		}
		
		// Resto da sala
		if(i == 1 || i == TAM_VERT - 2) {
			return TipoDeEstrutura.Vazio;
		}
		if(i == 0 || i == TAM_VERT - 1) {
			return TipoDeEstrutura.Poltrona;
		}
		if((j >= 3 && j <=4) || (j >= 11 && j <=12)) {
			return TipoDeEstrutura.Vazio;
		}
		return TipoDeEstrutura.Poltrona;
	}

	private void preencherSalas() {
		Estrutura[][] estrutura = gerarEstruturaSala();
		for (int i = 0; i < NUM_SALAS; i++) {
			Sala sala = new Sala(i, TAM_VERT, TAM_HOR, estrutura);
			salas.put(i, sala);
		}
	}

	@Override
	public Sala[] obterTodasSalas() {
		return salas.values().toArray(new Sala[salas.size()]);
	}

	@Override
	public Sala obterSalaPorId(int id) {
		if (!salas.containsKey(id)) {
			return null;
		}
		return salas.get(id);
	}

	@Override
	public boolean tentarBloquearLocal(int idSala, int linha, int coluna) {
		if (backend.diaEstaAberto()) {
			return false;
		}

		if (!salas.containsKey(idSala)) {
			return false;
		}

		Sala sala = salas.get(idSala);
		return sala.tentarBloquearLocal(linha, coluna);
	}
	
	public int obterQtdSalas() {
		return salas.size();
	}

	@Override
	public boolean tentarDesbloquearLocal(int idSala, int linha, int coluna) {
		if (backend.diaEstaAberto()) {
			return false;
		}

		if (!salas.containsKey(idSala)) {
			return false;
		}

		Sala sala = salas.get(idSala);
		return sala.tentarDesbloquearLocal(linha, coluna);
	}

	@Override
	public boolean existeSala(int idSala) {
		return salas.containsKey(idSala);
	}
}
