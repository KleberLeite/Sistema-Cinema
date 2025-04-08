package cinemax.backend.salas;

import cinemax.utilities.ConversorDeCoordenadas;

public class Sala implements ISala {
	private int idSala;
	private boolean[] bloqueados;
	private Estrutura[] estrutura;
	private int linhas;
	private int colunas;
	private DadosSala cachedDados;
	private boolean houveAlteracoes;

	protected Sala(int idSala, int linhas, int colunas, boolean[] bloqueados, Estrutura[] estrutura) {
		this.idSala = idSala;
		this.bloqueados = bloqueados;
		this.estrutura = estrutura;
		this.linhas = linhas;
		this.colunas = colunas;
		this.houveAlteracoes = true;
	}
	
	@Override
	public boolean tentarBloquearLocal(int linha, int coluna) {
		if(linha < 0 || linha >= linhas || coluna < 0 || coluna >= colunas) {
			return false;
		}
		
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, this.linhas);
		Estrutura estrutura = this.estrutura[index];
		if(estrutura != Estrutura.Vazio && !bloqueados[index]) {
			bloqueados[index] = true;
			houveAlteracoes = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean tentarDesbloquearLocal(int linha, int coluna) {
		if(linha < 0 || linha >= linhas || coluna < 0 || coluna >= colunas) {
			return false;
		}
		
		int index = ConversorDeCoordenadas.obter1dPor2d(linha, coluna, this.linhas);
		Estrutura estrutura = this.estrutura[index];
		if(estrutura != Estrutura.Vazio && bloqueados[index]) {
			bloqueados[index] = false;
			houveAlteracoes = true;
			return true;
		}
		return false;
	}

	@Override
	public int obterIdSala() {
		return idSala;
	}
	
	public DadosSala obterCopiaDados() {
		if(houveAlteracoes) {
			cachedDados = new DadosSala(idSala, linhas, colunas, bloqueados, estrutura);
		}
		return cachedDados;
	}
}
