package cinemax.filmes;

import cinemax.sessoes.ISessao;

public interface IFilme {
	public ISessao obterTodasSessoes();
	public String obterNome();
	public int obterDuracaoEmMinutos();
	public String obterSinopse();
	public boolean tentarAdicionarSessao(ISessao sessao);
	public boolean tentarRemoverSessao(int idSessao);
	public int obterIdFilme();
}
