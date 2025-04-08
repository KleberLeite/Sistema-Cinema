package cinemax.utilities;

public class ConversorDeCoordenadas {
	public static int obterLinhaPor1d(int valor, int tamanhoColuna) {
		return (int)(valor / tamanhoColuna);
	}
	
	public static int obterColunaPor1d(int valor, int tamanhoColuna) {
		return valor % tamanhoColuna;
	}
	
	public static int obter1dPor2d(int linha, int coluna, int tamanhoColuna) {
		return linha * tamanhoColuna + coluna;
	}
}
