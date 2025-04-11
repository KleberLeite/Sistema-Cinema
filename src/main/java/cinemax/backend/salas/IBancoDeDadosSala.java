package cinemax.backend.salas;

public interface IBancoDeDadosSala {
	public Sala[] obterTodasSalas();
	public Sala obterSalaPorId(int id);
	public boolean tentarBloquearLocal(int idSala, int linha, int coluna);
	public boolean tentarDesbloquearLocal(int idSala, int linha, int coluna);
}
