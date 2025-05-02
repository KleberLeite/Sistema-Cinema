package cinemax.backend.alimentos;

// Observação: alterações só são permitidas quando o dia não estiver aberto!
public interface IBancoDeDadosAlimento {
	// Retorna o alimento por seu código ou null caso não ache.
	Alimento obterAlimentoPorCodigo(int codigo);
	
	// Retorna um array de alimentos que contém o parâmetro "nome" em seu nome.
	Alimento[] obterAlimentoPorNome(String nome);
	
	// Retorna todos os alimentos cadastrados.
	Alimento[] obterTodosAlimentos();
	
	// Retorna verdadeiro caso exista algum alimento com o código especificado.
	boolean existeAlimentoComCodigo(int codigo);
	
	// Tenta adicionar um alimento novo, retornando falso se:
	// 1. O nome conter no máximo 2 caracteres;
	// 2. Se preco <= 0;
	// 3. Se o código já foi cadastrado.
	boolean tentardicionarAlimento(String nome, double preco, int codigo);
	
	// Tenta remover o alimento pelo código, retornando falso
	// caso não ache o alimento.
	boolean tentarRemoverAlimento(int codigo);
	
	// Tenta alterar o nome do alimento pelo seu código,
	// retornando falso caso não ache o alimento.
	boolean tentarAlterarNome(int codigo, String novoNome);
	
	// Tenta alterar o codigo do alimento pelo seu código,
	// retornando falso caso não ache o alimento ou já exista
	// outro alimento com o novo código.
	boolean tentarAlterarCodigo(int codigo, int novoCodigo);
	
	// Tenta alterar o preco do alimento pelo seu código,
	// retornando falso caso não ache o alimento.
	boolean tentarAlterarPreco(int codigo, double novoPreco);
}
