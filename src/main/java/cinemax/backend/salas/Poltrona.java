package cinemax.backend.salas;

public class Poltrona extends Estrutura {
	private boolean bloqueado;
	
	public Poltrona(int linha, int coluna, String identificador, TipoDeEstrutura tipo) {
		super(linha, coluna, identificador, tipo);
	}

	@Override
	protected boolean tentarBloquear() {
		if(bloqueado) {
			return false;
		}
		bloqueado = true;
		return true;
	}

	@Override
	protected boolean tentarDesbloquear() {
		if(!bloqueado) {
			return false;
		}
		bloqueado = false;
		return true;
	}
}
