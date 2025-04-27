package cinemax.backend.salas;

public abstract class Estrutura {
	private int linha;
	private int coluna;
	private String identificador;
	private TipoDeEstrutura tipo;
	
	public Estrutura(int linha, int coluna, String identificador, TipoDeEstrutura tipo) {
		this.linha = linha;
		this.coluna = coluna;
		this.identificador = identificador;
		this.tipo = tipo;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	public TipoDeEstrutura getTipo() {
		return tipo;
	}
	
	public String getIdentificador() {
		return identificador;
	}
	
	protected abstract boolean tentarBloquear();
	
	protected abstract boolean tentarDesbloquear();
}
