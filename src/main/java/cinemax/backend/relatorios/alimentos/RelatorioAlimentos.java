package cinemax.backend.relatorios.alimentos;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cinemax.backend.alimentos.Alimento;
import cinemax.backend.events.Event;
import cinemax.backend.relatorios.BaseRelatorio;

public class RelatorioAlimentos extends BaseRelatorio {
	private Map<Alimento, Integer> vendas = new HashMap<>();

	public RelatorioAlimentos(boolean permitirAlteracoes, Event<Boolean> aoAlterarPermissaoAlteracoes) {
		super(permitirAlteracoes, aoAlterarPermissaoAlteracoes);
	}

	public void adicionarVendas(Map<Alimento, Integer> venda) {
		if (super.getPermitirAlteracoes()) {
			internoAdicionarVendas(venda);
		}
	}

	protected void internoAdicionarVendas(Map<Alimento, Integer> venda) {
		for (Map.Entry<Alimento, Integer> e : venda.entrySet()) {
			vendas.put(e.getKey(), vendas.getOrDefault(e.getKey(), 0) + e.getValue());
		}
	}

	public Set<Map.Entry<Alimento, Integer>> obterVendas() {
		return vendas.entrySet();
	}
}
