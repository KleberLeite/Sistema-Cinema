package cinemax.backend.filmes;

import java.time.LocalDateTime;

public interface IBancoDeDadosFilme {
	Filme[] obterFilmesPorNome(String nome);
	Filme obterFilmePorId(int id);
	Filme[] obterTodosFilmes();
	boolean tentarAdicionarFilme(
		String nome,
		String sinopse,
		int duracaoEmMinutos,
		ClassificacaoIndicativa classificacaoIndicativa
	);
	boolean tentarAlterarNome(int id, String novoNome);
	boolean tentarAlterarSinopse(int id, String novaSinopse);
	boolean tentarAlterarDuracao(int id, int novaDuracaoEmMinutos);
	boolean tentarAlterarClassificacaoIndicativa(int id, ClassificacaoIndicativa novaClassificacaoIndicativa);
	
	boolean tentarAdicionarSessao(int idSala, int idFilme, LocalDateTime inicio);
	boolean tentarRemoverSessao(int idSessao, int idFilme);
}
