package cinemax.sessoes;

import java.time.LocalDateTime;

import cinemax.salas.IEstruturaSala;

/*
Resumo: interface para fábricas de sessões.
*/
public interface IFabricaSessao {
	/*
	Resumo: cria uma nova sessão a partir dos parâmetros.
	Param(estrutura): estrutura da sala da sessão.
	Param(inicio): data e hora do início da exibição do filme. 
	*/
	public ISessao criar(IEstruturaSala estrutura, LocalDateTime inicio);
}
