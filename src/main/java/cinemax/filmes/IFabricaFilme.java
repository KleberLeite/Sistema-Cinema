package cinemax.filmes;

public interface IFabricaFilme {
	public IFilme criar(
		int id,
		String nome,
		String sinopse,
		ClassificacaoIndicativa classificacaoIndicativa,
		int duracaoEmMinutos
	);
}
