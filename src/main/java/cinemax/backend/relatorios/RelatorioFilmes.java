package cinemax.backend.relatorios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.Sessao;

public class RelatorioFilmes {
	private List<Ingresso> vendas = new ArrayList<>();
	private Relatorio relatorio;
	
	public RelatorioFilmes(Relatorio relatorio) {
		this.relatorio = relatorio;
	}
	
	public void adicionarVendas(List<Ingresso> venda) {		
		if(!relatorio.estaFechado()) {
			vendas.addAll(venda);
		}
	}
	
	private class TuplaIngressos {
		private int inteiras;
		private int meias;
		
		public TuplaIngressos(int inteiras, int meias) {
			this.inteiras = inteiras;
			this.meias = meias;
		}
		
		public TuplaIngressos soma(int p, int q) {
			return new TuplaIngressos(inteiras + p, meias + q);
		}
	}
	
	public List<VendasIngressos> obterVendas() {
		Map<Filme, TuplaIngressos> aux = new HashMap<>();
		
		for(Ingresso i : vendas) {
			Sessao s = i.getSessao();
			int addInteira = i.getTipo() == TipoDeIngresso.Inteira ? 1 : 0;
			int addMeia = addInteira == 1 ? 0 : 1;
			aux.put(
				s.getFilme(),
				(TuplaIngressos)(aux.getOrDefault(s.getFilme(), new TuplaIngressos(0, 0))).soma(addInteira, addMeia)
			);
		}
		
		List<VendasIngressos> result = new ArrayList<>();
		for(Map.Entry<Filme, TuplaIngressos> e : aux.entrySet()) {
			result.add(new VendasIngressos(e.getKey(), e.getValue().inteiras, e.getValue().meias));
		}
		return result;
	}
}
