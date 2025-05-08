package cinemax.backend.filmes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IBancoDeDadosFilme {
	// ------- Filmes -------
	// Retorna um array com todos os filmes que contém o parâmetro "nome" em seu nome.
	Filme[] obterFilmesPorNome(String nome);
	
	// Retorna o filme do respectivo id, ou null caso não encontre.
	Filme obterFilmePorId(int id);
	
	// Retorna um array com todos os filmes.
	Filme[] obterTodosFilmes();
	
	// Tenta adicionar um filme, retorna o id do filme criado ou -1 caso:
	// 1. nome.length() <= 2;
	// 2. sinopse.length() <= 2;
	// 3. duracaoEmMinutos < 1.
	int tentarAdicionarFilme(
		String nome,
		String sinopse,
		GeneroFilme[] generos,
		int duracaoEmMinutos,
		ClassificacaoIndicativa classificacaoIndicativa
	);
	
	// Tenta alterar o nome do filme com o respectivo id, retorna falso caso:
	// 1. Não encontrar o filme;
	// 2. O novoNome ser inválido.
	boolean tentarAlterarNome(int id, String novoNome);
	
	// Tenta adicionar o genero do filme com o respectivo id, retorna falso caso:
	// 1. Não encontrar o filme.
	boolean alterarGeneros(int idFilme, GeneroFilme[] generos);
	
	// Tenta alterar a sinopse do filme com o respectivo id, retorna falso caso:
	// 1. Não encontrar o filme;
	// 2. A novaSinopse ser inválida.
	boolean tentarAlterarSinopse(int id, String novaSinopse);
	
	// Tenta alterar a duracao do filme com o respectivo id, retorna falso caso:
	// 1. Não encontrar o filme;
	// 2. A novaDuracaoEmMinutos ser inválida.
	boolean tentarAlterarDuracao(int id, int novaDuracaoEmMinutos);
	
	// Tenta alterar a duracao do filme com o respectivo id, retorna falso caso:
	// 1. Não encontrar o filme.
	boolean tentarAlterarClassificacaoIndicativa(int id, ClassificacaoIndicativa novaClassificacaoIndicativa);
	
	// Tenta remover o filme do id especificado, retornando falso caso:
	// 1. Não encontrar o filme.
	boolean tentarRemoverFilme(int idFilme);
	
	// Obtém todos os filmes com sessão no dia.
	Filme[] obterTodosFilmesNoDia(LocalDate data);
	

	// Obtém todos os filmes com sessão no dia com os gêneros.
	Filme[] obterTodosFilmesNoDia(LocalDate data, GeneroFilme[] generos);
	
	// ------- Sessao -------
	// Obtém todas as sessões no dia especificado, retornando null caso:
	// 1. O filme não foi encontrado.
	Sessao[] obterTodasSessoesDoFilmeNoDia(int idFilme, LocalDate data);
	
	// Tenta adicionar uma sessao para o respectivo filme e sala, no início indicado,
	// retorna o id da sessão criada ou -1 caso:
	// 1. Não encontrar o filme;
	// 2. Não encontrar a sala;
	// 3. Uma sessao estiver acontecendo na mesma sala e horário.
	int tentarAdicionarSessao(int idSala, int idFilme, LocalDateTime inicio);
	

	// Tenta alterar a sala da sessão, retornando falso caso:
	// 1. Não encontrar o filme;
	// 2. Não encontrar a sala;
	// 3. Uma sessao estiver acontecendo na mesma sala e horário.
	boolean tentarAlterarSalaSessao(int idFilme, int idSessao, int idNovaSala);

	// Tenta remover uma sessao do respectivo filme e sessao, retornando falso se:
	// 1. Não encontrar o filme;
	// 2. Não encontrar a sessao.
	boolean tentarRemoverSessao(int idSessao, int idFilme);

	// Tenta reservar um assento no filme, sessao e posição especificada, retornando falso caso:
	// 1. Não encontrar o filme;
	// 2. Não encontrar a sessao;
	// 3. Posicao inválida;
	// 4. Já está reservada.
	boolean tentarReservar(int idFilme, int idSessao, int linha, int coluna);
	
	// Tenta desreservar um assento no filme, sessao e posição especificada, retornando falso caso:
	// 1. Não encontrar o filme;
	// 2. Não encontrar a sessao;
	// 3. Posicao inválida;
	// 4. A posição não está reservada.
	boolean tentarDesreservar(int idFilme, int idSessao, int linha, int coluna);
	
	// Tenta alterar o início da sessao do filme e id especificados, para um novoInicio,
	// retornando falso caso:
	// 1. Não encontrar o filme;
	// 2. Não encontrar a sessao;
	// 3. Já exista uma sessao ocorrendo na sala e horário da sessao.
	boolean tentarAlterarInicioSessao(int idFilme, int idSessao, LocalDateTime novoInicio);	
}
