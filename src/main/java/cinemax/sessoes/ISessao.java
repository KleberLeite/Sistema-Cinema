package cinemax.sessoes;

import java.time.LocalDateTime;

import cinemax.salas.ISala;

/*
Resumo: interface para sessões em exibição.
*/
public interface ISessao {
	/*
	Resumo: tenta reservar o local indicado caso seja uma
	poltrona e ainda não esteja reservada.
	Param(linha): a linha da poltrona.
	Param(coluna): a coluna da poltrona.
	Return: retorna verdadeiro se a poltrona foi reservada com
	sucesso, ou falso caso contrário. 
	*/
	public boolean tentarReservar(int linha, int coluna);
	

	/*
	Resumo: verifica se a coluna indicada já está reservada.
	poltrona e ainda não esteja reservada.
	Param(linha): a linha da poltrona.
	Param(coluna): a coluna da poltrona.
	Return: retorna verdadeiro se a poltrona indicada está
	reservada, ou falso caso contrário. 
	*/
	public boolean estaReservado(int linha, int coluna);
	
	/*
	Resumo: desreserva a poltrona caso exista e já esteja
	reservada.
	Param(linha): a linha da poltrona.
	Param(coluna): a coluna da poltrona.
	Return: retorna verdadeiro se a poltrona foi desreservada
	com sucesso, ou falso caso contrário 
	*/
	public boolean tentarDesreservar(int linha, int coluna);
	
	/*
	Resumo: obtém a estrutura da sala da sessão.
	Return: retorna a estrutura da sala da sessão. 
	*/
	public ISala obterEstruturaSala();
	
	/*
	Resumo: obtém a data e hora do início da exibição da sessão.
	Return: retorna a data e hora do início da exibição da sessão. 
	*/
	public LocalDateTime obterInicio();
	
	/*
	Resumo: obtém o ID da sessão.
	Return: retorna o ID da sessão. 
	*/
	public int obterIdSessao();
}
