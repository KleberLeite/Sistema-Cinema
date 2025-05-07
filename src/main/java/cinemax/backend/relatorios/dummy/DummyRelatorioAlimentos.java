package cinemax.backend.relatorios.dummy;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cinemax.backend.alimentos.Alimento;
import cinemax.backend.alimentos.IBancoDeDadosAlimento;
import cinemax.backend.events.Event;
import cinemax.backend.relatorios.alimentos.RelatorioAlimentos;

public class DummyRelatorioAlimentos extends RelatorioAlimentos {
	public DummyRelatorioAlimentos(
		boolean permitirAlteracoes,
		Event<Boolean> aoAlterarPermissaoAlteracoes,
		IBancoDeDadosAlimento bancoAlimentos
	) {
		super(permitirAlteracoes, aoAlterarPermissaoAlteracoes);

		Alimento[] alimentos = bancoAlimentos.obterTodosAlimentos();
		Random random = new Random(42);

		for (int i = 1; i < 10; i++) {
			int n = random.nextInt(6)+1;
			this.adicionarVendas(gerarCompraRandom(random, alimentos, n));
		}		
	}

	private Map<Alimento, Integer> gerarCompraRandom(Random random, Alimento[] alimentos, int n) {
		Map<Alimento, Integer> compra = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int index = random.nextInt(alimentos.length);
			Alimento a = alimentos[index];
			compra.put(a, compra.getOrDefault(a, 0) + 1);
		}
		return compra;
	}
}
