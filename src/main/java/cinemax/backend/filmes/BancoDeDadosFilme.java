package cinemax.backend.filmes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cinemax.backend.core.Backend;
import cinemax.backend.salas.IBancoDeDadosSala;
import cinemax.backend.salas.Sala;

/*
Resumo: banco de dados dos filmes (e suas sessões) do cinema.
Observação: alterações só são permitidas quando o dia não estiver aberto!
*/
public class BancoDeDadosFilme implements IBancoDeDadosFilme {
	private final int TEMPO_LIMPEZA = 10;
	
	private Map<Integer, Filme> filmes = new HashMap<Integer, Filme>();	
	private IBancoDeDadosSala bancoDeDadosSala;
	private int idFilmesAtual = 0;
	private int idSessoesAtual = 0;
	private Backend backend;
	
	public BancoDeDadosFilme(Backend backend, IBancoDeDadosSala bancoDeDadosSala) {
		this.backend = backend;
		this.bancoDeDadosSala = bancoDeDadosSala;
	}
	
	// Retorna um array com todos os filmes que contém o parâmetro "nome" em seu nome.
	@Override
	public Filme[] obterFilmesPorNome(String nome) {
		List<Filme> result = new ArrayList<Filme>();
		for(Filme filme : filmes.values()) {
			if(filme.getNome().contains(nome)) {
				result.add(filme);
			}
		}
		
		return collectionFilmeToArray(result);
	}

	// Retorna o filme do respectivo id, ou null caso não encontre.
	@Override
	public Filme obterFilmePorId(int id) {
		return filmes.getOrDefault(id, null);
	}

	// Retorna um array com todos os filmes.
	@Override
	public Filme[] obterTodosFilmes() {
		return collectionFilmeToArray(filmes.values());
	}

	private Filme[] collectionFilmeToArray(Collection<Filme> collection) {
		return collection.toArray(new Filme[collection.size()]);
	}
	
	// Tenta adicionar um filme, retorna o id do filme criado ou -1 caso:
	// 1. nome.length() <= 2;
	// 2. sinopse.length() <= 2;
	// 3. duracaoEmMinutos < 1.
	@Override
	public int tentarAdicionarFilme(
		String nome,
		String sinopse,
		GeneroFilme[] generos,
		int duracaoEmMinutos,
		ClassificacaoIndicativa classificacaoIndicativa
	) {
		if(backend.diaEstaAberto()) {
			return -1;
		}
		
		nome = nome.trim();
		if(!nomeValido(nome)) {
			return -1;
		}
		sinopse = sinopse.trim();
		if(!sinopseValida(sinopse)) {
			return -1;
		}
		if(!duracaoValida(duracaoEmMinutos)) {
			return -1;
		}
		
		return adicionarFilme(nome, sinopse, generos, duracaoEmMinutos, classificacaoIndicativa);
	}
	
	private int adicionarFilme(
		String nome,
		String sinopse,
		GeneroFilme[] generos,
		int duracaoEmMinutos,
		ClassificacaoIndicativa classificacaoIndicativa
	) {
		Filme filme = new Filme(
			idFilmesAtual,
			nome,
			sinopse,
			generos,
			duracaoEmMinutos,
			classificacaoIndicativa
		);
		filmes.put(idFilmesAtual, filme);
		
		idFilmesAtual++;
		return filme.getId();
	}

	// Tenta alterar o nome do filme com o respectivo id, retorna falso caso:
	// 1. Não encontrar o filme;
	// 2. O novoNome ser inválido.
	@Override
	public boolean tentarAlterarNome(int idFilme, String novoNome) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		if(!filmes.containsKey(idFilme)) {
			return false;
		}
		novoNome = novoNome.trim();
		if(!nomeValido(novoNome)) {
			return false;
		}
		
		filmes.get(idFilme).setNome(novoNome);		
		return true;
	}

	// Tenta alterar a sinopse do filme com o respectivo id, retorna falso caso:
	// 1. Não encontrar o filme;
	// 2. A novaSinopse ser inválida.
	@Override
	public boolean tentarAlterarSinopse(int idFilme, String novaSinopse) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		if(!filmes.containsKey(idFilme)) {
			return false;
		}
		novaSinopse = novaSinopse.trim();
		if(!sinopseValida(novaSinopse)) {
			return false;
		}
		
		filmes.get(idFilme).setSinopse(novaSinopse);
		return true;
	}

	// Tenta alterar a duracao do filme com o respectivo id, retorna falso caso:
	// 1. Não encontrar o filme;
	// 2. A novaDuracaoEmMinutos ser inválida.
	@Override
	public boolean tentarAlterarDuracao(int id, int novaDuracaoEmMinutos) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		if(!filmes.containsKey(id)) {
			return false;
		}
		if(!duracaoValida(novaDuracaoEmMinutos)) {
			return false;
		}
		
		filmes.get(id).setDuracaoEmMinutos(novaDuracaoEmMinutos);
		return true;
	}

	// Tenta alterar a duracao do filme com o respectivo id, retorna falso caso:
	// 1. Não encontrar o filme.
	@Override
	public boolean tentarAlterarClassificacaoIndicativa(
		int id,
		ClassificacaoIndicativa novaClassificacaoIndicativa
	) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		if(!filmes.containsKey(id)) {
			return false;
		}

		filmes.get(id).setClassificacaoIndicativa(novaClassificacaoIndicativa);
		return true;
	}

	// Tenta adicionar uma sessao para o respectivo filme e sala, no início indicado,
	// retorna o id da sessão criada ou -1 caso:
	// 1. Não encontrar o filme;
	// 2. Não encontrar a sala;
	// 3. Uma sessao estiver acontecendo na mesma sala e horário;
	// 4. A data e hora ser um momento anterior ao atual.
	@Override
	public int tentarAdicionarSessao(int idSala, int idFilme, LocalDateTime inicio) {
		if(backend.diaEstaAberto()) {
			return -1;
		}
		Sala sala = bancoDeDadosSala.obterSalaPorId(idSala);
		if(sala == null) {
			return -1;
		}
		if(!filmes.containsKey(idFilme)) {
			return -1;
		}
		if(inicio.isBefore(LocalDateTime.now())) {
			return -1;
		}
		if(existeOutraSessaoNoMesmoLugarHora(idSala, inicio)) {
			return -1;
		}
		
		return adicionarSessao(sala, idFilme, inicio);
	}
	
	private int adicionarSessao(Sala sala, int idFilme, LocalDateTime inicio ) {
		Filme filme = filmes.get(idFilme);
		Sessao sessao = new Sessao(idSessoesAtual, sala, filme, inicio);
		filme.adicionarSessao(sessao);
		
		idSessoesAtual++;
		return sessao.getId();
	}
	
	// Tenta remover uma sessao do respectivo filme e sessao, retornando falso se:
	// 1. Não encontrar o filme;
	// 2. Não encontrar a sessao.
	@Override
	public boolean tentarRemoverSessao(int idSessao, int idFilme) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		if(!filmes.containsKey(idFilme)) {
			return false;
		}
		
		return filmes.get(idFilme).tentarRemoverSessao(idSessao);
	}

	// Obtém todas as sessões no dia especificado, retornando null caso:
	// 1. O filme não foi encontrado.
	@Override
	public Sessao[] obterTodasSessoesDoFilmeNoDia(int idFilme, LocalDate data) {
		Filme f = filmes.getOrDefault(idFilme, null);
		if(f == null) {
			return null;
		}
		
		List<Sessao> result = new ArrayList<Sessao>();
		for(Sessao s : f.obterTodasSessoes()) {
			if(data.equals(s.getInicio().toLocalDate())) {
				result.add(s);
			}
		}
		
		return collectionSessaoToArray(result);
	}
	
	private Sessao[] collectionSessaoToArray(Collection<Sessao> collection) {
		return collection.toArray(new Sessao[collection.size()]);
	}
	
	// Tenta reservar um assento no filme, sessao e posição especificada, retornando falso caso:
	// 1. Não encontrar o filme;
	// 2. Não encontrar a sessao;
	// 3. Posicao inválida;
	// 4. Já está reservada.
	@Override
	public boolean tentarReservar(int idFilme, int idSessao, int linha, int coluna) {
		if(!backend.diaEstaAberto()) {
			return false;
		}
		if(!filmes.containsKey(idFilme)) {
			return false;
		}
		Filme filme = filmes.get(idFilme);
		if(!filme.contemSessao(idSessao)) {
			return false;
		}
		
		return filme.obterSessao(idSessao).tentarReservar(linha, coluna);
	}
	
	// Tenta desreservar um assento no filme, sessao e posição especificada, retornando falso caso:
	// 1. Não encontrar o filme;
	// 2. Não encontrar a sessao;
	// 3. Posicao inválida;
	// 4. A posição não está reservada.
	@Override
	public boolean tentarDesreservar(int idFilme, int idSessao, int linha, int coluna) {
		if(!backend.diaEstaAberto()) {
			return false;
		}
		if(!filmes.containsKey(idFilme)) {
			return false;
		}
		Filme filme = filmes.get(idFilme);
		if(!filme.contemSessao(idSessao)) {
			return false;
		}
		
		return filme.obterSessao(idSessao).tentarDesreservar(linha, coluna);
	}

	// Tenta remover o filme do id especificado, retornando falso caso:
	// 1. Não encontrar o filme.
	@Override
	public boolean tentarRemoverFilme(int idFilme) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		if(!filmes.containsKey(idFilme)) {
			return false;
		}
		filmes.remove(idFilme);
		return true;
	}
	
	// Tenta alterar o início da sessao do filme e id especificados, para um novoInicio,
	// retornando falso caso:
	// 1. Não encontrar o filme;
	// 2. Não encontrar a sessao;
	// 3. Já exista uma sessao ocorrendo na sala e horário da sessao.
	@Override
	public boolean tentarAlterarInicioSessao(int idFilme, int idSessao, LocalDateTime novoInicio) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		if(!filmes.containsKey(idFilme)) {
			return false;
		}
		Filme filme = filmes.get(idFilme);
		if(!filme.contemSessao(idSessao)) {
			return false;
		}
		Sessao sessao = filme.obterSessao(idSessao);
		if(existeOutraSessaoNoMesmoLugarHora(sessao.getSala().getIdSala(), novoInicio, sessao.getId())) {
			return false;
		}
		sessao.setInicio(novoInicio);
		return true;
	}
	// Tenta alterar a sala da sessão, retornando falso caso:
	// 1. Não encontrar o filme;
	// 2. Não encontrar a sala;
	// 3. Uma sessao estiver acontecendo na mesma sala e horário;~
	// 4. A sessão já ocorrer na sala especificada.
	@Override
	public boolean tentarAlterarSalaSessao(int idFilme, int idSessao, int idNovaSala) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		if(!filmes.containsKey(idFilme)) {
			return false;
		}
		Filme filme = filmes.get(idFilme);
		if(!filme.contemSessao(idSessao)) {
			return false;
		}
		Sessao sessao = filme.obterSessao(idSessao);
		if(sessao.getSala().getIdSala() == idNovaSala) {
			return false;
		}
		if(existeOutraSessaoNoMesmoLugarHora(idNovaSala, sessao.getInicio())) {
			return false;
		}
		
		Sala novaSala = bancoDeDadosSala.obterSalaPorId(idNovaSala);
		sessao.setSala(novaSala);
		return true;
	}
	
	// Obtém todos os filmes com sessão no dia.
	@Override
	public Filme[] obterTodosFilmesNoDia(LocalDate data) {
		Set<Filme> result = new HashSet<Filme>();
		for(Filme f : filmes.values()) {
			for(Sessao s : f.obterTodasSessoes()) {
				if(data.equals(s.getInicio().toLocalDate())) {
					result.add(f);
					break;
				}
			}
		}
		
		return result.toArray(new Filme[result.size()]);
	}
	
	// Tenta alterar o genero do filme com o respectivo id, retorna falso caso:
	// 1. Não encontrar o filme.
	@Override
	public boolean tentarAdicionarGenero(int idFilme, GeneroFilme novoGenero) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		
		Filme f = filmes.getOrDefault(idFilme, null);
		return f!= null && f.addGenero(novoGenero);
	}

	// Tenta remover o genero do filme com o respectivo id, retorna falso caso:
	// 1. Não encontrar o filme;
	// 2. Não conter o gênero indicado.
	@Override
	public boolean tentarRemoverGenero(int idFilme, GeneroFilme genero) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		
		Filme f = filmes.getOrDefault(idFilme, null);
		return f!= null && f.removeGenero(genero);
	}

	// Retorna se existe outra sessao no mesmo lugar e hora,
	// ignorando a sessao de id ignoreIdSessao.
	private boolean existeOutraSessaoNoMesmoLugarHora(int idSala, LocalDateTime inicio, int ignoreIdSessao) {
		for(Filme filme : filmes.values()) {
			for(Sessao sessao : filme.obterTodasSessoes()) {
				if(sessao.getSala().getIdSala() != idSala) {
					continue;
				}
				if(sessao.getId() == ignoreIdSessao) {
					continue;
				}
				
				LocalDateTime fimNovaSessao = inicio.plusMinutes(filme.getDuracaoEmMinutos() + TEMPO_LIMPEZA);
				LocalDateTime inicioSessaoExistente = sessao.getInicio();
				LocalDateTime fimSessaoExistente = sessao.getInicio().plusMinutes(filme.getDuracaoEmMinutos() + TEMPO_LIMPEZA);
				if(haChoqueDeHorario(inicio, fimNovaSessao, inicioSessaoExistente, fimSessaoExistente)) {
					System.out.println("Choque com: " + filme.getNome());
					System.out.println("Inicio desejado: " + inicio);
					return true;
				}
			}
		}
		return false;
	}

	// Retorna se existe outra sessao no mesmo lugar e hora.
	private boolean existeOutraSessaoNoMesmoLugarHora(int idSala, LocalDateTime inicio) {
		for(Filme filme : filmes.values()) {
			for(Sessao sessao : filme.obterTodasSessoes()) {
				if(sessao.getSala().getIdSala() != idSala) {
					continue;
				}
				
				LocalDateTime fimNovaSessao = inicio.plusMinutes(filme.getDuracaoEmMinutos() + TEMPO_LIMPEZA);
				LocalDateTime inicioSessaoExistente = sessao.getInicio();
				LocalDateTime fimSessaoExistente = sessao.getInicio().plusMinutes(filme.getDuracaoEmMinutos() + TEMPO_LIMPEZA);
				if(haChoqueDeHorario(inicio, fimNovaSessao, inicioSessaoExistente, fimSessaoExistente)) {
					return true;
				}
			}
		}
		return false;
	}
	
	// Retorna se há choque de horário.
	private boolean haChoqueDeHorario(
		LocalDateTime inicioNovaSessao,
		LocalDateTime fimNovaSessao,
		LocalDateTime inicioSessaoExistente,
		LocalDateTime fimSessaoExistente
	) {
		return !fimNovaSessao.isBefore(inicioSessaoExistente) && !inicioNovaSessao.isAfter(fimSessaoExistente);
	}

	private boolean nomeValido(String nome) {
		return nome.length() > 2 && !nome.startsWith(" ") && !nome.endsWith(" ");
	}
	
	private boolean sinopseValida(String sinopse) {
		return sinopse.length() > 2 && !sinopse.startsWith(" ") && !sinopse.endsWith(" ");
	}
	
	private boolean duracaoValida(int duracaoEmMinutos) {
		return duracaoEmMinutos >= 1;
	}
}
