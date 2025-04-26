package cinemax.frontend.compra;

import cinemax.backend.alimentos.Alimento;

public class VendasAlimento {

	    private Alimento alimento;
	    private double preco;
	    private int quantidade;

	    public VendasAlimento(Alimento alimento, double preco,int quantidade) {
	        this.alimento = alimento;
	        this.preco = preco;
	        this.quantidade = quantidade;
	    }

	    public Alimento getAlimento() {
	        return alimento;
	    }

	    public void adicionarAlimentos(int quantidade) {
	        this.quantidade += quantidade;
	    }

	    public double calcularTotal() {
	        return quantidade * preco;
	    }

	    @Override
	    public String toString() {
	        return alimento + " - Qtde: " + quantidade;
	    }

	

	
}
