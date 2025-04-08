package cinemax.salas;

public interface IBancoDeDadosSala {
	public DadosSala[] obterTodasSalas();
	public DadosSala obterSalaPorId(int id);
	public boolean tentarBloquearLocal(int idSala, int linha, int coluna);
	public boolean tentarDesbloquearLocal(int idSala, int linha, int coluna);
}
