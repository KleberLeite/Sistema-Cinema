package cinemax.backend.salas;

public class EstruturaPassagem extends Estrutura {
	public EstruturaPassagem(int linha, int coluna, String identificador, TipoDeEstrutura tipo) {
		super(linha, coluna, identificador, tipo);
	}

	@Override
	public boolean tentarBloquear() {
		return false;
	}

	@Override
	public boolean tentarDesbloquear() {
		return false;
	}

	@Override
	public boolean getBloqueado() {
		return false;
	}
}
