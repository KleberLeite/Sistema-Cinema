package cinemax.backend.funcionarios.dummy;

import cinemax.backend.core.Backend;
import cinemax.backend.funcionarios.BancoDeDadosFuncionario;
import cinemax.backend.funcionarios.CargoFuncionario;

public class DummyBancoDeDadosFuncionario extends BancoDeDadosFuncionario {
	public DummyBancoDeDadosFuncionario(Backend backend) {
		super(backend);
		this.internoAdicionarFuncionario("Pedro de Assis", "12345678912", CargoFuncionario.Gerente, "88988224456", "@1234");
		this.internoAdicionarFuncionario("Paulo Fernande", "33345678912", CargoFuncionario.Gerente, "88988224455", "@5138");
		this.internoAdicionarFuncionario("Maria Paula", "44445678912", CargoFuncionario.Atendente, "88988224454", "1$#23213");
		this.internoAdicionarFuncionario("Mario Henrique", "55555678912", CargoFuncionario.Atendente, "88988224453", "H@#sda4");
		this.internoAdicionarFuncionario("Joelma Alencar", "66666678912", CargoFuncionario.Atendente, "88988224452", "@12#24");
		this.internoAdicionarFuncionario("Santos Dummond", "77777778912", CargoFuncionario.Atendente, "88988224451", "@111");
	}
}
