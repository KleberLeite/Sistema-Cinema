package cinemax.backend.funcionarios;

import cinemax.backend.core.Backend;

public class DummyBancoDeDadosFuncionario extends BancoDeDadosFuncionario {
	public DummyBancoDeDadosFuncionario(Backend backend) {
		super(backend);
		this.tentarAdicionarFuncionario("Pedro de Assis", "12345678912", CargoFuncionario.Gerente, "88927733", "@1234");
		this.tentarAdicionarFuncionario("Paulo Fernande", "33345678912", CargoFuncionario.Gerente, "87147432", "@5138");
		this.tentarAdicionarFuncionario("Maria Paula", "44445678912", CargoFuncionario.Atendente, "88997733", "1$#23213");
		this.tentarAdicionarFuncionario("Mario Henrique", "55555678912", CargoFuncionario.Atendente, "88997733", "H@#sda4");
		this.tentarAdicionarFuncionario("Joelma Alencar", "66666678912", CargoFuncionario.Atendente, "88997733", "@12#24");
		this.tentarAdicionarFuncionario("Santos Dummond", "77777778912", CargoFuncionario.Atendente, "88997733", "@111");
	}
}
