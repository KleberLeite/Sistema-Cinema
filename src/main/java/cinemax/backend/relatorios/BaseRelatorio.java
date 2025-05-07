package cinemax.backend.relatorios;

import cinemax.backend.events.Event;

public class BaseRelatorio {
	private boolean permitirAlteracoes;
	
	public BaseRelatorio(boolean permitirAlteracoes, Event<Boolean> aoAlterarPermissaoAlteracoes) {
		this.permitirAlteracoes = permitirAlteracoes;
		aoAlterarPermissaoAlteracoes.register(e -> {
			this.permitirAlteracoes = e;
		});
	}

	protected boolean getPermitirAlteracoes() {
		return permitirAlteracoes;
	}
}
