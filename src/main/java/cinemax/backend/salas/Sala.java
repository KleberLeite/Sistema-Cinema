package cinemax.backend.salas;

public class Sala {
	private int idSala;
	private Estrutura[][] estrutura;
	private boolean[][] bloqueados;
	private int linhas;
	private int colunas;

	protected Sala(int idSala, int linhas, int colunas, Estrutura[][] estrutura) {
		this.idSala = idSala;
		this.estrutura = estrutura;
		this.linhas = linhas;
		this.colunas = colunas;
		this.bloqueados = new boolean[linhas][colunas];
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
	
	public boolean estaBloqueado(int linha, int coluna) {
		if(!estaDentroDaSala(linha, coluna)) {
			return false;
		}
		return bloqueados[linha][coluna];
	}
	
	// Tenta desbloquear o local, retornando falso apenas se a linha e coluna
	// indicada estiver fora dos limites da sala ou se já estiver bloqueada.
	protected boolean tentarBloquearLocal(int linha, int coluna) {
		if(!estaDentroDaSala(linha, coluna)) {
			return false;
		}
		
		bloqueados[linha][coluna] = true;
		
		return estrutura[linha][coluna].tentarBloquear();
	}

	// Tenta desbloquear o local, retornando falso apenas se a linha e coluna
	// indicada estiver fora dos limites da sala ou se já estiver desbloqueada.
	protected boolean tentarDesbloquearLocal(int linha, int coluna) {
		if(!estaDentroDaSala(linha, coluna)) {
			return false;
		}
		
		bloqueados[linha][coluna] = false;
		
		return estrutura[linha][coluna].tentarDesbloquear();
	}

	// Retorna a estrutura da sala na linha e coluna indicada, ou null
	// caso não esteja dentro dos limites da sala.	
	public Estrutura obterTipoDeEstrutura(int linha, int coluna) {
		if(!estaDentroDaSala(linha, coluna)) {
			return null;
		}
		return estrutura[linha][coluna];
	}

	// Retorna se a linha e coluna está dentro dos limites da sala.
	public boolean estaDentroDaSala(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >=0 && coluna < colunas;
	}
}
