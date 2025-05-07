package cinemax.backend.relatorios;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cinemax.backend.alimentos.Alimento;

public class RelatorioAlimentos {
	private Map<Alimento, Integer> vendas = new HashMap<>();
	private Relatorio relatorio;
	
	public RelatorioAlimentos(Relatorio relatorio) {
		this.relatorio = relatorio;
	}
	
	public void adicionarVendas(Map<Alimento, Integer> venda) {		
		if(!relatorio.estaFechado()) {
			internoAdicionarVendas(venda);
		}
	}
	
	protected void internoAdicionarVendas(Map<Alimento, Integer> venda) {
		for(Map.Entry<Alimento, Integer> e : venda.entrySet()) {
			vendas.put(e.getKey(), vendas.getOrDefault(e.getKey(), 0) + e.getValue());
		}
	}
	
	public Set<Map.Entry<Alimento, Integer>> obterVendas() {
		return vendas.entrySet();
	}
}
