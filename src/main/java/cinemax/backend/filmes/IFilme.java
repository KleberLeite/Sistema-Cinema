package cinemax.backend.filmes;

import cinemax.sessoes.ISessao;

public interface IFilme {
	public ISessao obterTodasSessoes();
	public String obterNome();
	public boolean tentarAlterarNome(String novoNome);
	public int obterDuracaoEmMinutos();
	public boolean tentarAlterarDuracaoEmMinutos(int novaDuracaoEmMinutos);
	public String obterSinopse();
	public boolean tentarAlterarSinopse(String novaSinopse);
	public ClassificacaoIndicativa obterClassificacaoIndicativa();
	public boolean tentarAlterarClassificacaoIndicativa(ClassificacaoIndicativa novaClassificacaoIndicativa);
	public boolean tentarAdicionarSessao(ISessao sessao);
	public boolean tentarRemoverSessao(int idSessao);
	public int obterIdFilme();
}
