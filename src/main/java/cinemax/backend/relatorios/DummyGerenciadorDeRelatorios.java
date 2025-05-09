package cinemax.backend.relatorios;

import java.time.LocalDateTime;

import cinemax.backend.alimentos.IBancoDeDadosAlimento;
import cinemax.backend.events.Event;
import cinemax.backend.filmes.IBancoDeDadosFilme;
import cinemax.backend.relatorios.alimentos.DummyRelatorioAlimentos;
import cinemax.backend.relatorios.alimentos.RelatorioAlimentos;
import cinemax.backend.relatorios.filmes.DummyRelatorioFilmes;
import cinemax.backend.relatorios.filmes.RelatorioFilmes;

public class DummyGerenciadorDeRelatorios {
	public static GerenciadorDeRelatorios dummy(
		IBancoDeDadosAlimento bancoAlimentos,
		IBancoDeDadosFilme bancoFilmes
	) {
		CircularBuffer<Relatorio> relatorios = new CircularBuffer<>(7);
		
		for(int i = 0; i < 6; i++) {
			Event<Boolean> aoAlterarPermissaoAlteracoes = new Event<>();
			RelatorioAlimentos relatorioAlimentos = DummyRelatorioAlimentos.dummy(bancoAlimentos, aoAlterarPermissaoAlteracoes);
			RelatorioFilmes relatorioFilmes = DummyRelatorioFilmes.dummy(bancoFilmes, aoAlterarPermissaoAlteracoes);
			Relatorio relatorio = new Relatorio(
				aoAlterarPermissaoAlteracoes,
				relatorioAlimentos,
				relatorioFilmes,
				LocalDateTime.now().minusDays(7 - i),
				LocalDateTime.now().minusDays(7 - i).plusHours(12)
			);
			
			relatorios.push(relatorio);
		}
		
		return new GerenciadorDeRelatorios(relatorios);
	}
}
