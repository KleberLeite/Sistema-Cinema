package cinemax.backend.relatorios;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cinemax.backend.alimentos.Alimento;
import cinemax.backend.alimentos.IBancoDeDadosAlimento;

public class DummyRelatorioAlimentos extends RelatorioAlimentos {
	public DummyRelatorioAlimentos(IBancoDeDadosAlimento bancoAlimentos, Relatorio relatorio) {
		super(relatorio);

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
