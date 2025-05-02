package cinemax.backend.salas;

public interface IBancoDeDadosSala {
	
	// Obtém todas as salas cadastradas.
	public Sala[] obterTodasSalas();
	
	// Obtém quantidade de salas existentes.
	public int obterQtdSalas();
	
	// Obtém a sala a partir de seu ID ou null caso não encontre.
	public Sala obterSalaPorId(int id);
	
	// Tenta bloquear o local da sala a partir de seu ID
	// baseado nas coordenadas passadas.
	public boolean tentarBloquearLocal(int idSala, int linha, int coluna);
	

	// Tenta desbloquear o local da sala a partir de seu ID
	// baseado nas coordenadas passadas.
	public boolean tentarDesbloquearLocal(int idSala, int linha, int coluna);
}
