package cinemax.frontend.compra;

import cinemax.backend.alimentos.Alimento;

public class VendasAlimento {

	    private Alimento alimento;
	    private int quantidade;

	    public VendasAlimento(Alimento alimento,int quantidade) {
	        this.alimento = alimento;
	        this.quantidade = quantidade;
	    }
	    
	    public double calcularTotal() {
	        return quantidade * alimento.getPreco();
	    }
	    

	    public Alimento getAlimento() {
	        return alimento;
	    }

	    public void adicionarAlimentos(int quantidade) {
	        this.quantidade += quantidade;
	    }

	    @Override
	    public String toString() {
	        return alimento + " - Qtde: " + quantidade;
	    }

	

	
}
