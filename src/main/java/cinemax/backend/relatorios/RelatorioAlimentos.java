package cinemax.backend.relatorios;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cinemax.backend.alimentos.Alimento;

public class RelatorioAlimentos {
	private Map<Alimento, Integer> vendas = new HashMap<>();
	private LocalDateTime inicio;
	private LocalDateTime fim;
	
	public RelatorioAlimentos() {
		this.inicio = LocalDateTime.now();
	}
	
	public void adicionarVendas(Map<Alimento, Integer> venda) {
		if(fim != null) {
			return;
		}
		
		for(Map.Entry<Alimento, Integer> e : venda.entrySet()) {
			vendas.put(e.getKey(), vendas.getOrDefault(e, 0) + e.getValue());
		}
	}
	
	public Set<Map.Entry<Alimento, Integer>> obterVendas() {
		if(fim == null) {
			return null;
		}
		return vendas.entrySet();
	}
	
	public void fecharRelatorio() {
		fim = LocalDateTime.now();
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public LocalDateTime getFim() {
		return fim;
	}
}
