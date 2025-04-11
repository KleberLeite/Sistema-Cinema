package cinemax.backend.alimentos;

public interface IBancoDeDadosAlimento {
	Alimento obterAlimentoPorCodigo(int codigo);
	Alimento[] obterAlimentoPorNome(String nome);
	Alimento[] obterTodosAlimentos();
	boolean existeAlimentoComCodigo(int codigo);
	boolean tentardicionarAlimento(String nome, double preco, int codigo);
	boolean tentarRemoverAlimento(int codigo);
	boolean tentarAlterarNome(int codigo, String novoNome);
	boolean tentarAlterarCodigo(int codigo, int novoCodigo);
	boolean tentarAlterarPreco(int codigo, double novoPreco);
}
