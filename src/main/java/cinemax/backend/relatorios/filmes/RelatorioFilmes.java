package cinemax.backend.relatorios.filmes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cinemax.backend.events.Event;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.Sessao;
import cinemax.backend.relatorios.BaseRelatorio;

public class RelatorioFilmes extends BaseRelatorio {
	private List<Ingresso> vendas = new ArrayList<>();
	
	protected RelatorioFilmes(
		boolean permitirAlteracoes,
		Event<Boolean> aoAlterarPermissaoAlteracoes,
		List<Ingresso> vendas
	) {
		super(permitirAlteracoes, aoAlterarPermissaoAlteracoes);
		this.vendas = vendas;
	}
	
	public RelatorioFilmes(boolean permitirAlteracoes, Event<Boolean> aoAlterarPermissaoAlteracoes) {
		super(permitirAlteracoes, aoAlterarPermissaoAlteracoes);
	}
	
	public void adicionarVendas(List<Ingresso> venda) {		
		if(super.getPermitirAlteracoes()) {
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
