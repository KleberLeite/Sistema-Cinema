package cinemax.frontend.compra;

import cinemax.backend.filmes.Filme;

public class VendasFilme {

	    private Filme filme;
	    private int qtdInteiros;
	    private int qtdMeias;

	    public VendasFilme(Filme filme, int qtdInteiros, int qtdMeias) {
	        this.filme = filme;
	        this.qtdInteiros = qtdInteiros;
	        this.qtdMeias = qtdMeias;
	    }

	    public Filme getFilme() {
	        return filme;
	    }

	    public void adicionarIngressos(int inteiros, int meias) {
	        this.qtdInteiros += inteiros;
	        this.qtdMeias += meias;
	    }

	    public double calcularTotal(double precoInteiro, double precoMeia) {
	        return qtdInteiros * precoInteiro + qtdMeias * precoMeia;
	    }

	    @Override
	    public String toString() {
	        return filme + " - Inteiros: " + qtdInteiros + ", Meias: " + qtdMeias;
	    }

	

	
}
