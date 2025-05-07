package cinemax.frontend.vendadeingressos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cinemax.backend.alimentos.Alimento;
import cinemax.backend.relatorios.Ingresso;
import cinemax.backend.relatorios.TipoDeIngresso;

public class CarrinhoIngressos {
	private List<Ingresso> ingressos;
	private int meias;
	private int inteiras;
    
	public CarrinhoIngressos() {
		super();
		this.ingressos = new ArrayList<>();
	}
	
	public int qtdeDeMeias() {
		return meias;
	}
	
	public int qtdeDeInteiras() {
		return inteiras;
	}
	
	public int qtdeTotalIngressos() {
		return ingressos.size();
	}

	public double totalCompraFilme() {		
		return meias * Ingresso.PRECO_INGRESSO /2 + inteiras * Ingresso.PRECO_INGRESSO;
	}
	
	public void adicionaIngresso(Ingresso ingresso) {
		ingressos.add(ingresso);
		
		if(ingresso.getTipo() == TipoDeIngresso.Inteira) {
			inteiras++;
		} else {
			meias++;
		}
	}
	
	public void removeIngresso(Ingresso ingresso) {
		ingressos.remove(ingresso);
		
		if(ingresso.getTipo() == TipoDeIngresso.Inteira) {
			inteiras--;
		} else {
			meias--;
		}
	}
	
	public List<Ingresso> getIngressos() {
		return ingressos;
	}
}
