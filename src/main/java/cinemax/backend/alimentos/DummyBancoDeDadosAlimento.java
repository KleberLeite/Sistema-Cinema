package cinemax.backend.alimentos;

import cinemax.backend.core.Backend;

// Cria um banco de dados com alguns alimentos já preenchidos.
public class DummyBancoDeDadosAlimento extends BancoDeDadosAlimento {
	public DummyBancoDeDadosAlimento(Backend backend) {
		super(backend);
		tentardicionarAlimento("Pipoca P", 5.0, 1001);
		tentardicionarAlimento("Pipoca M", 5.0, 1002);
		tentardicionarAlimento("Pipoca G", 5.0, 1003);
		tentardicionarAlimento("Pipoca GG", 5.0, 1004);
		tentardicionarAlimento("Coca-Cola 300ml", 7.0, 2001);
		tentardicionarAlimento("Coca-Cola 600ml", 10.0, 2002);
		tentardicionarAlimento("Fanta Uva 300ml", 6.0, 2003);
		tentardicionarAlimento("Fanta Laranja 600ml", 9.0, 2004);
	}
}
