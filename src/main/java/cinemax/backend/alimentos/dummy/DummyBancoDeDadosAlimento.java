package cinemax.backend.alimentos.dummy;

import cinemax.backend.alimentos.BancoDeDadosAlimento;
import cinemax.backend.core.Backend;

// Cria um banco de dados com alguns alimentos já preenchidos.
public class DummyBancoDeDadosAlimento extends BancoDeDadosAlimento {
	public DummyBancoDeDadosAlimento(Backend backend) {
		super(backend);
		super.internoAdicionarAlimento("Pipoca P", 5.0, 1001);
		super.internoAdicionarAlimento("Pipoca M", 5.0, 1002);
		super.internoAdicionarAlimento("Pipoca G", 5.0, 1003);
		super.internoAdicionarAlimento("Pipoca GG", 5.0, 1004);
		super.internoAdicionarAlimento("Coca-Cola 300ml", 7.0, 2001);
		super.internoAdicionarAlimento("Coca-Cola 600ml", 10.0, 2002);
		super.internoAdicionarAlimento("Fanta Uva 300ml", 6.0, 2003);
		super.internoAdicionarAlimento("Fanta Laranja 600ml", 9.0, 2004);
	}
}
