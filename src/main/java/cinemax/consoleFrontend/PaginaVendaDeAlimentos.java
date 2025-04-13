package cinemax.consoleFrontend;

import cinemax.backend.alimentos.IBancoDeDadosAlimento;

public class PaginaVendaDeAlimentos extends PaginaBase {	
	private PaginaCarrinhoAlimentos paginaCarrinhoAlimentos;
	
	public PaginaVendaDeAlimentos(IBancoDeDadosAlimento bancoDeDadosAlimento) {
		this.paginaCarrinhoAlimentos = new PaginaCarrinhoAlimentos(bancoDeDadosAlimento);
	}
	
	@Override
	public void abrir() {
		super.limparConsole();
		
		System.out.println(
			"----- Venda de Alimentos -----\n\n" +
			"Opções:\n" +
			"1. Novo Pedido\n" +
			"2. Sair"
		);
		int opcao = super.getScanner().nextInt();
		switch(opcao) {
		case 1:
			paginaCarrinhoAlimentos.abrir();
			break;
		case 2:
			return;
		}
	}

	@Override
	public void reabrir() {
		abrir();
	}
}
