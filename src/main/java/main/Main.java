package main;

import cinemax.backend.core.Backend;
import cinemax.backend.salas.Sala;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		Backend b = Backend.dummy();
		b.tentarAbrirDia();
		
		Sala s = b.getBancoSalas().obterSalaPorId(0);
		for(int i = 0; i < s.getLinhas(); i++) {
			for(int j = 0; j < s.getColunas(); j++) {
				System.out.print(s.obterTipoDeEstrutura(i, j).getIdentificador() + " ");
			}
			System.out.println();
		}
	}
}
