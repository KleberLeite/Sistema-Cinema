package cinemax.frontend.compra;

import java.util.ArrayList;

import cinemax.backend.filmes.Filme;

public class VendasFilme {

	    private Filme filme;
	    private int qtdInteiras;
	    private int qtdMeias;

	    public VendasFilme(Filme filme, int qtdInteiras, int qtdMeias) {
	        this.filme = filme;
	        this.qtdInteiras = qtdInteiras;
	        this.qtdMeias = qtdMeias;
	    }

	    public Filme getFilme() {
	        return filme;
	    }

	    public void adicionarIngressos(int qtdeInteiras, int qtdeMeias) {
	        this.qtdInteiras += qtdeInteiras;
	        this.qtdMeias += qtdeMeias;
	    }

	    public double calcularTotal(double precoInteira, double precoMeia) {
	        return qtdInteiras * precoInteira + qtdMeias * precoMeia;
	    }

	    @Override
	    public String toString() {
	        return filme + " - Inteiros: " + qtdInteiras + ", Meias: " + qtdMeias;
	    }

	

	
}
