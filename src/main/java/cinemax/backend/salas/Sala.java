package cinemax.backend.salas;

import cinemax.utilities.ConversorDeCoordenadas;

public class Sala {
	private int idSala;
	private boolean[] bloqueados;
	private TipoDeEstrutura[] estrutura;
	private int linhas;
	private int colunas;

	protected Sala(int idSala, int linhas, int colunas, boolean[] bloqueados, TipoDeEstrutura[] estrutura) {
		this.idSala = idSala;
		this.bloqueados = bloqueados;
		this.estrutura = estrutura;
		this.linhas = linhas;
		this.colunas = colunas;
	}
	
	public int getLinhas() {
		return linhas;
	}
	
	public int getColunas() {
		return colunas;
	}

	public int getIdSala() {
		return idSala;
	}
	
	protected boolean tentarBloquearLocal(int linha, int coluna) {
		if(!estaDentroDaSala(linha, coluna)) {
			return false;
		}
		
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, this.colunas);
		TipoDeEstrutura estrutura = this.estrutura[index];
		if(estrutura != TipoDeEstrutura.Vazio && !bloqueados[index]) {
			bloqueados[index] = true;
			return true;
		}
		return false;
	}

	protected boolean tentarDesbloquearLocal(int linha, int coluna) {
		if(!estaDentroDaSala(linha, coluna)) {
			return false;
		}
		
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, this.colunas);
		TipoDeEstrutura estrutura = this.estrutura[index];
		if(estrutura != TipoDeEstrutura.Vazio && bloqueados[index]) {
			bloqueados[index] = false;
			return true;
		}
		return false;
	}
	
	public TipoDeEstrutura obterTipoDeEstrutura(int linha, int coluna) {
		if(!estaDentroDaSala(linha, coluna)) {
			return null;
		}
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, this.colunas);
		return estrutura[index];
	}
	
	public boolean ePoltronaOuLocalCadeirante(int linha, int coluna) {
		if(!estaDentroDaSala(linha, coluna)) {
			return false;
		}
		
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, this.colunas);
		return estrutura[index] != TipoDeEstrutura.Vazio;
	}
	
	public boolean estaDentroDaSala(int linha, int coluna) {
		return linha < 0 || linha >= linhas || coluna < 0 || coluna >= colunas;
	}
}
