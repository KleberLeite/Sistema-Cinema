package cinemax.utilities;

public class ConversorDeCoordenadas {
	public static int obterLinhaPor1d(int valor, int tamanhoLinha) {
		return (int)(valor / tamanhoLinha);
	}
	
	public static int obterColunaPor1d(int valor, int tamanhoLinha) {
		return valor % tamanhoLinha;
	}
	
	public static int obter1dPor2d(int linha, int coluna, int tamanhoLinha) {
		return linha * tamanhoLinha + coluna;
	}
}
