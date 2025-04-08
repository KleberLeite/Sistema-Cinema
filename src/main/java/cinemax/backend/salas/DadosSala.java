package cinemax.backend.salas;

import cinemax.utilities.ConversorDeCoordenadas;

public class DadosSala {
	private int idSala;
	private boolean[] bloqueados;
	private Estrutura[] estrutura;
	private int linhas;
	private int colunas;
	
	protected DadosSala(int idSala, int linhas, int colunas, boolean[] bloqueados, Estrutura[] estrutura) {
		this.idSala = idSala;
		this.linhas = linhas;
		this.colunas = colunas;
		this.bloqueados = bloqueados;
		this.estrutura = estrutura;
	}
	
	public int obterLinhas() {
		return linhas;
	}
	
	public int obterColunas() {
		return colunas;
	}
	
	public int obterId() {
		return idSala;
	}
	
	public boolean estaBloqueado(int linha, int coluna) {
		if(linha < 0 || linha >= linhas || coluna < 0 || coluna >= colunas) {
			return false;
		}
		
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, this.colunas);
		Estrutura estrutura = this.estrutura[index];
		return estrutura == Estrutura.Vazio ? false : bloqueados[index];
	}
	
	public Estrutura obterEstrutura(int linha, int coluna) {
		if(linha < 0 || linha >= linhas || coluna < 0 || coluna >= colunas) {
			return null;
		}
		
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, this.colunas);
		return this.estrutura[index];
	}
	
	public boolean estaDentroDaSala(int linha, int coluna) {
		return linha < 0 || linha >= linhas || coluna < 0 || coluna >= colunas;
	}
	
	public boolean ePoltronaOuLocalCadeirante(int linha, int coluna) {
		if(!estaDentroDaSala(linha, coluna)) {
			return false;
		}
		
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, this.colunas);
		return estrutura[index] != Estrutura.Vazio;
	}
}
