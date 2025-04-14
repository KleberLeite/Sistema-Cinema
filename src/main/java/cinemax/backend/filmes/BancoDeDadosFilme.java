package cinemax.backend.filmes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cinemax.backend.salas.IBancoDeDadosSala;
import cinemax.backend.salas.Sala;

public class BancoDeDadosFilme implements IBancoDeDadosFilme {
	private final int TEMPO_LIMPEZA = 10;
	
	private Map<Integer, Filme> filmes = new HashMap<Integer, Filme>();	
	private IBancoDeDadosSala bancoDeDadosSala;
	private int idFilmesAtual = 0;
	private int idSessoesAtual = 0;
	
	public BancoDeDadosFilme(IBancoDeDadosSala bancoDeDadosSala) {
		this.bancoDeDadosSala = bancoDeDadosSala;
	}
	
	@Override
	public Filme[] obterFilmesPorNome(String nome) {
		List<Filme> result = new ArrayList<Filme>();
		for(Filme filme : filmes.values()) {
			if(filme.getNome().contains(nome)) {
				result.add(filme);
			}
		}
		
		return result.toArray(new Filme[result.size()]);
	}

	@Override
	public Filme obterFilmePorId(int id) {
		if(filmes.containsKey(id)) {
			return filmes.get(id);
		}
		return null;
	}

	@Override
	public Filme[] obterTodosFilmes() {
		Collection<Filme> filmeValues = filmes.values();
		return filmeValues.toArray(new Filme[filmeValues.size()]);
	}

	@Override
	public boolean tentarAdicionarFilme(
		String nome,
		String sinopse,
		int duracaoEmMinutos,
		ClassificacaoIndicativa classificacaoIndicativa
	) {
		nome = nome.trim();
		if(!eNomeValido(nome)) {
			return false;
		}
		sinopse = sinopse.trim();
		if(!eSinopseValida(sinopse)) {
			return false;
		}
		if(!eDuracaoValida(duracaoEmMinutos)) {
			return false;
		}
		
		Filme filme = new Filme(
			idFilmesAtual, nome, sinopse, duracaoEmMinutos, classificacaoIndicativa
		);
		filmes.put(idFilmesAtual, filme);
		
		idFilmesAtual++;
		return true;
	}

	@Override
	public boolean tentarAlterarNome(int id, String novoNome) {
		if(!filmes.containsKey(id)) {
			return false;
		}
		novoNome = novoNome.trim();
		if(!eNomeValido(novoNome)) {
			return false;
		}
		
		Filme filme = filmes.get(id);
		filme.setNome(novoNome);
		return true;
	}

	@Override
	public boolean tentarAlterarSinopse(int id, String novaSinopse) {
		if(!filmes.containsKey(id)) {
			return false;
		}
		novaSinopse = novaSinopse.trim();
		if(!eSinopseValida(novaSinopse)) {
			return false;
		}
		
		Filme filme = filmes.get(id);
		filme.setSinopse(novaSinopse);
		return true;
	}

	@Override
	public boolean tentarAlterarDuracao(int id, int novaDuracaoEmMinutos) {
		if(!filmes.containsKey(id)) {
			return false;
		}
		if(!eDuracaoValida(novaDuracaoEmMinutos)) {
			return false;
		}
		
		Filme filme = filmes.get(id);
		filme.setDuracaoEmMinutos(novaDuracaoEmMinutos);
		return true;
	}

	@Override
	public boolean tentarAlterarClassificacaoIndicativa(int id, ClassificacaoIndicativa novaClassificacaoIndicativa) {
		if(!filmes.containsKey(id)) {
			return false;
		}

		Filme filme = filmes.get(id);
		filme.setClassificacaoIndicativa(novaClassificacaoIndicativa);
		return true;
	}

	private boolean eNomeValido(String nome) {
		return nome.length() > 2 && !nome.startsWith(" ") && !nome.endsWith(" ");
	}
	
	private boolean eSinopseValida(String sinopse) {
		return sinopse.length() > 2 && !sinopse.startsWith(" ") && !sinopse.endsWith(" ");
	}
	
	private boolean eDuracaoValida(int duracaoEmMinutos) {
		return duracaoEmMinutos >= 1;
	}

	@Override
	public boolean tentarAdicionarSessao(int idSala, int idFilme, LocalDateTime inicio) {
		Sala sala = bancoDeDadosSala.obterSalaPorId(idSala);
		if(sala == null) {
			return false;
		}
		if(!filmes.containsKey(idFilme)) {
			return false;
		}
		if(existeAlgumFilmeNaMesmaSala(idSala, inicio)) {
			return false;
		}
		
		Filme filme = filmes.get(idFilme);
		Sessao sessao = new Sessao(idSessoesAtual, sala, filme, inicio);
		filme.adicionarSessao(sessao);
		
		idSessoesAtual++;
		return true;
	}

	@Override
	public boolean tentarRemoverSessao(int idSessao, int idFilme) {
		if(!filmes.containsKey(idFilme)) {
			return false;
		}
		
		Filme filme = filmes.get(idFilme);
		filme.removerSessao(idSessao);
		return true;
	}
	
	private boolean existeAlgumFilmeNaMesmaSala(int idSala, LocalDateTime inicio) {
		for(Filme filme : filmes.values()) {
			for(Sessao sessao : filme.obterTodasSessoes()) {
				if(sessao.getSala().getIdSala() != idSala) {
					continue;
				}
				
				LocalDateTime fimSessao = sessao.getInicio().plusMinutes(filme.getDuracaoEmMinutos() + TEMPO_LIMPEZA);
				if(inicio.isEqual(inicio)) {
					return true;
				}
				if(inicio.isAfter(sessao.getInicio()) && inicio.isBefore(fimSessao)) {
					return true;
				}
			}
		}
		return false;
	}
}
