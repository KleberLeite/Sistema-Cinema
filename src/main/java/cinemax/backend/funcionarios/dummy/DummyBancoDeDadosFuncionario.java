package cinemax.backend.funcionarios.dummy;

import cinemax.backend.core.Backend;
import cinemax.backend.funcionarios.BancoDeDadosFuncionario;
import cinemax.backend.funcionarios.CargoFuncionario;

public class DummyBancoDeDadosFuncionario extends BancoDeDadosFuncionario {
	public DummyBancoDeDadosFuncionario(Backend backend) {
		super(backend);
		this.internoAdicionarFuncionario("Pedro de Assis", "12345678912", CargoFuncionario.Gerente, "88988224456", "123456");
		this.internoAdicionarFuncionario("Paulo Fernande", "33345678912", CargoFuncionario.Gerente, "88988224455", "123456123");
		this.internoAdicionarFuncionario("Maria Paula", "44445678912", CargoFuncionario.Atendente, "88988224454", "732161");
		this.internoAdicionarFuncionario("Mario Henrique", "55555678912", CargoFuncionario.Atendente, "88988224453", "45612123");
		this.internoAdicionarFuncionario("Joelma Alencar", "66666678912", CargoFuncionario.Atendente, "88988224452", "5612334");
		this.internoAdicionarFuncionario("Santos Dummond", "10987654321", CargoFuncionario.Atendente, "88988224451", "654321");
	}
}
