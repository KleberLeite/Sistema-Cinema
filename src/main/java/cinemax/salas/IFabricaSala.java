package cinemax.salas;

/*
Resumo: interface para fábricas de IEstruturaSala.
*/
public interface IFabricaSala {
	
	/*
	Resumo: cria uma nova IEstruturaSala.
	Return: retorna uma nova IEstruturaSala. 
	*/
	public IEstruturaSala criar();
}
