package cinemax.consoleFrontend;

import java.util.Scanner;

import cinemax.backend.alimentos.BancoDeDadosAlimento;
import cinemax.backend.alimentos.IBancoDeDadosAlimento;
import cinemax.backend.core.Backend;
import cinemax.consoleFrontend.vendaDeAlimentos.PaginaVendaDeAlimentos;

public class ControladorPaginas {
	public static void main(String[] args) {
		System.out.println("Cinemax Interface.");
		
		Backend backend = Backend.dummy();
		backend.tentarAbrirDia();
		IBancoDeDadosAlimento bancoDeDadosAlimento = backend.getBancoAlimentos();
		preencherAlimentosDummy(bancoDeDadosAlimento);
		
		PaginaVendaDeAlimentos paginaVendaDeAlimentos = new PaginaVendaDeAlimentos(bancoDeDadosAlimento);
		paginaVendaDeAlimentos.abrir();
	}
	
	private static void preencherAlimentosDummy(IBancoDeDadosAlimento bancoDeDadosAlimento) {
		bancoDeDadosAlimento.tentardicionarAlimento("Pipoca P", 5.0, 1001);
		bancoDeDadosAlimento.tentardicionarAlimento("Pipoca M", 10.0, 1002);
		bancoDeDadosAlimento.tentardicionarAlimento("Pipoca G", 15.0, 1003);
	}
}
