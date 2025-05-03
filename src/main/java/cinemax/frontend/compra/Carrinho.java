package cinemax.frontend.compra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cinemax.backend.alimentos.Alimento;

public class Carrinho {
	
	private double total;
	private List<Ingresso> ingressos;
    

	public Carrinho() {
		super();
		this.ingressos = new ArrayList<>();
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
	
	public int qtdeTotalIngressos() {
		int qtdeIngressos = 0;
		
		for(Ingresso ingresso : ingressos) {
			qtdeIngressos++;
		}
		return qtdeIngressos;
	}

	public double totalCompraFilme() {

		Ingresso ingresso = ingressos.get(0);
		double precoInteiro = Ingresso.getPrecoIngresso();
		
		return this.qtdeDeMeias()*precoInteiro/2 + this.qtdeDeMeias()*precoInteiro;
	}
	

	public void adicionaIngresso(Ingresso ingresso) {
		ingressos.add(ingresso);
	}
	
	public void removeIngresso(Ingresso ingresso) {
		ingressos.remove(ingresso);
		
	}
	
	public List<Ingresso> getIngressos() {
		return ingressos;
	}
	
	public List<Ingresso> getIngressosMeias() {
		List<Ingresso> ingressosMeias = new ArrayList();
		
		for(Ingresso ingresso : ingressos) {
			if(ingresso.getTipo()==TipoDeIngresso.Meia) ingressosMeias.add(ingresso);
		}
		
		return ingressosMeias;
	}


    
    
	
}
