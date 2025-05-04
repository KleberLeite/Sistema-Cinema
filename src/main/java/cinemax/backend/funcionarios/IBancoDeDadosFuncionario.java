package cinemax.backend.funcionarios;

public interface IBancoDeDadosFuncionario {
	Funcionario obterFuncionarioPorCPF(String cpf);
	Funcionario[] obterFuncionariosPorNome(String nome);
	Funcionario[] obterTodosFuncionarios();
	boolean tentarAdicionarFuncionario(
		String nome,
		String cpf,
		CargoFuncionario cargo,
		String telefone,
		String senha
	);
	boolean tentarRemoverFuncionarioPorCPF(String cpf);
	boolean tentarAlterarNome(String cpf, String novoNome);
	boolean tentarAlterarSenha(String cpf, String novaSenha);
	boolean tentarAlterarCargo(String cpf, CargoFuncionario novoCargo);
	boolean tentarAlterarTelefone(String cpf, String novoTelefone);
	boolean tentarAlterarCPF(String cpf, String novoCPF);
	boolean existeFuncionario(String usuario, String senha);
}
