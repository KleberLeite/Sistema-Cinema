package cinemax.consoleFrontend;

import java.util.Scanner;

public abstract class PaginaBase {
	private static final Scanner sc = new Scanner(System.in);
	
	public abstract void abrir();
	public abstract void reabrir();
	
	public void limparConsole() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	public Scanner getScanner() {
		return PaginaBase.sc;
	}
}
