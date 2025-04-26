package cinemax.frontend.compra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cinemax.backend.alimentos.Alimento;

public class Carrinho {
	
	private double total;
	private List<Ingresso> ingressos;
    private Map<Alimento, Integer> alimentos;
    

	public Carrinho() {
		super();
		this.ingressos = new ArrayList<>();
		this.alimentos = new HashMap<>();
	}
	
	public int qtdeDeMeias() {
		int qtdeMeias = 0;
		
		for(Ingresso ingresso : ingressos) {
			if(ingresso.getTipo() == TipoDeIngresso.Meia) qtdeMeias++;
		}
		return qtdeMeias;
	}
	
	public int qtdeDeInteiras() {
		int qtdeInteiras = 0;
		
		for(Ingresso ingresso : ingressos) {
			if(ingresso.getTipo() == TipoDeIngresso.Inteira) qtdeInteiras++;
		}
		return qtdeInteiras;
	}

	public double totalCompraFilme() {

		Ingresso ingresso = ingressos.get(0);
		double precoInteiro = Ingresso.precoIngresso();
		
		return this.qtdeDeMeias()*precoInteiro/2 + this.qtdeDeMeias()*precoInteiro;
	}
	
	public double totalCompraAlimento() {
		total = 0;
		for (Map.Entry<Alimento, Integer> entry : alimentos.entrySet()) {
			Alimento alimento = entry.getKey();
			int quantidade = entry.getValue();
			total += alimento.getPreco() * quantidade;
		}
		return total;
	}

	public void adicionaIngresso(Ingresso ingresso) {
		ingressos.add(ingresso);
		
	}
	
	public void adicionaAlimento(Alimento alimento) {
		alimentos.put(alimento, alimentos.getOrDefault(alimento, 0) +1);
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public Map<Alimento, Integer> getAlimentos() {
		return alimentos;
	}

    
    
	
}
