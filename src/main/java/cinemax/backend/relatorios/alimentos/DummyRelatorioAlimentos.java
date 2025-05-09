package cinemax.backend.relatorios.alimentos;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cinemax.backend.alimentos.Alimento;
import cinemax.backend.alimentos.IBancoDeDadosAlimento;
import cinemax.backend.events.Event;

public class DummyRelatorioAlimentos {
	public static RelatorioAlimentos dummy(
		IBancoDeDadosAlimento bancoAlimentos,
		Event<Boolean> aoAlterarPermissaoAlteracoes
	) {
		Alimento[] alimentos = bancoAlimentos.obterTodosAlimentos();
		Random random = new Random(42);

		Map<Alimento, Integer> vendas = new HashMap<>();
		for (int j = 1; j < 10; j++) {
			int n = random.nextInt(6)+1;
			
			for (int i = 0; i < n; i++) {
				int index = random.nextInt(alimentos.length);
				Alimento a = alimentos[index];
				vendas.put(a, vendas.getOrDefault(a, 0) + 1);
			}
		}
		
		return new RelatorioAlimentos(false, aoAlterarPermissaoAlteracoes, vendas);
	}
}
