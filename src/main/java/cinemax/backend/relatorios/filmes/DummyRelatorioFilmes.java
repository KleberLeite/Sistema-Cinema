package cinemax.backend.relatorios.filmes;

import java.util.ArrayList;
import java.util.List;

import cinemax.backend.events.Event;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.IBancoDeDadosFilme;
import cinemax.backend.filmes.Sessao;
import cinemax.backend.salas.Sala;

public class DummyRelatorioFilmes {
	public static RelatorioFilmes dummy(
		IBancoDeDadosFilme bancoFilmes,
		Event<Boolean> aoAlterarPermissaoAlteracoes
	) {
		return new RelatorioFilmes(false, aoAlterarPermissaoAlteracoes, simularVendasFilmes(bancoFilmes));
	}
	
	public static List<Ingresso> simularVendasFilmes(IBancoDeDadosFilme bancoFilmes) {
        List<Ingresso> ingressos = new ArrayList<>();

        for(Filme filme : bancoFilmes.obterTodosFilmes()) {
	        for (Sessao sessao : filme.obterTodasSessoes()) {	
	            for (int i = 0; i < 5; i++) {	                
	                Ingresso ingresso = new Ingresso(sessao);
	                ingresso.setTipo(i % 2 == 0 ? TipoDeIngresso.Inteira : TipoDeIngresso.Meia);
	                ingresso.setRG("12345678" + i);
	
	                ingressos.add(ingresso);
	            }
	        }
        }
        return ingressos;
        
    }
}
