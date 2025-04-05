package cinemax.salas;

/*
Resumo: estrutura de toda a sala da sessões.
*/
public interface ISala {
	/*
	Resumo: tenta bloquear o local caso seja uma poltrona comum,
	poltrona para pessoas obesas ou local para cadeirantes.
	Param(linha): a linha do local a ser bloqueado.
	Param(coluna): a coluna do local a ser bloqueado.
	Return: retorna verdadeiro se foi possível bloquear o local
	com sucesso, ou falso caso contrário.
	*/
	public boolean tentarBloquearLocal(int linha, int coluna);
	
	/*
	Resumo: tenta desbloquear o local caso seja uma poltrona comum,
	poltrona para pessoas obesas ou local para cadeirantes.
	Param(linha): a linha do local a ser desbloqueado.
	Param(coluna): a coluna do local a ser desbloqueado.
	Return: retorna verdadeiro se foi possível desbloquear o local
	com sucesso, ou falso caso contrário.
	*/
	public boolean tentarDesbloquearLocal(int linha, int coluna);
	
	/*
	Resumo: obtém o ID da sala.
	Return: retorna o ID da sala.
	*/
	public int obterIdSala();
}
