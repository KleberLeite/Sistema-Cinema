package cinemax.backend.filmes;

import java.time.LocalDate;
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
	boolean tentarRemoverFilme(int idFilme);
	boolean tentarAlterarNome(int id, String novoNome);
	boolean tentarAlterarSinopse(int id, String novaSinopse);
	boolean tentarAlterarDuracao(int id, int novaDuracaoEmMinutos);
	boolean tentarAlterarClassificacaoIndicativa(int id, ClassificacaoIndicativa novaClassificacaoIndicativa);
	
	boolean tentarAdicionarSessao(int idSala, int idFilme, LocalDateTime inicio);
	boolean tentarRemoverSessao(int idSessao, int idFilme);
	
	Sessao[] obterSessoesNoDia(LocalDate data);
	
	boolean tentarReservar(int idFilme, int idSessao, int linha, int coluna);
	boolean tentarDesreservar(int idFilme, int idSessao, int linha, int coluna);
	
	boolean tentarAlterarInicioSessao(int idFilme, int idSessao, LocalDateTime novoInicio);
}
