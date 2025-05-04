package cinemax.backend.relatorios;

import java.util.ArrayList;
import java.util.List;

public class RelatorioFilmes {
	private ArrayList<Ingresso> vendas = new ArrayList<>();
	private Relatorio relatorio;
	
	public RelatorioFilmes(Relatorio relatorio) {
		this.relatorio = relatorio;
	}
	
	public void adicionarVendas(List<Ingresso> venda) {		
		if(!relatorio.estaFechado()) {
			vendas.addAll(venda);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ingresso> obterVendas() {
		return (List<Ingresso>)vendas.clone();
	}
}
