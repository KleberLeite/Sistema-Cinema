package cinemax.backend.funcionarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cinemax.backend.core.Backend;

public class BancoDeDadosFuncionario implements IBancoDeDadosFuncionario {
	private static final String USUARIO_ADMIN = "admin";
	private static final String SENHA_ADMIN = "admin2025"; 
	
	private static final int TAMANHO_TELEFONE = 11;
	private static final int TAMANHO_CPF = 11;

	private Map<String, Funcionario> funcionarios = new HashMap<String, Funcionario>();
	private Backend backend;

	public BancoDeDadosFuncionario(Backend backend) {
		this.backend = backend;
	}

	@Override
	public Funcionario obterFuncionarioPorCPF(String cpf) {
		if (!funcionarios.containsKey(cpf)) {
			return null;
		}
		return funcionarios.get(cpf);
	}

	@Override
	public Funcionario[] obterFuncionariosPorNome(String nome) {
		List<Funcionario> funcionariosValue = new ArrayList<Funcionario>();

		for (Funcionario funcionario : funcionarios.values()) {
			if (funcionario.getNome().contains(nome)) {
				funcionariosValue.add(funcionario);
			}
		}

		return funcionariosValue.toArray(new Funcionario[funcionariosValue.size()]);
	}

	@Override
	public Funcionario[] obterTodosFuncionarios() {
		Collection<Funcionario> collection = funcionarios.values();
		return collection.toArray(new Funcionario[collection.size()]);
	}

	protected boolean internoAdicionarFuncionario(String nome, String cpf, CargoFuncionario cargo, String telefone,
			String senha) {
		if (funcionarios.containsKey(cpf)) {
			return false;
		}
		nome = nome.trim();
		if (!eNomeValido(nome)) {
			return false;
		}
		cpf = cpf.trim();
		if (!eCPFValido(cpf)) {
			return false;
		}
		telefone = telefone.trim();
		if (!eTelefoneValido(telefone)) {
			return false;
		}
		senha = senha.trim();
		if (!eSenhaValida(senha)) {
			return false;
		}
		if (!eCargoValido(cargo)) {
			return false;
		}

		Funcionario funcionario = new Funcionario(nome, cpf, cargo, telefone, senha);
		funcionarios.put(cpf, funcionario);
		return true;
	}

	@Override
	public boolean tentarAdicionarFuncionario(String nome, String cpf, CargoFuncionario cargo, String telefone,
			String senha) {
		if (backend.diaEstaAberto()) {
			return false;
		}

		return internoAdicionarFuncionario(nome, cpf, cargo, telefone, senha);
	}

	@Override
	public boolean tentarRemoverFuncionarioPorCPF(String cpf) {
		if (backend.diaEstaAberto()) {
			return false;
		}

		if (!funcionarios.containsKey(cpf)) {
			return false;
		}
		funcionarios.remove(cpf);
		return true;
	}

	@Override
	public boolean tentarAlterarNome(String cpf, String novoNome) {
		if (backend.diaEstaAberto()) {
			return false;
		}

		if (!funcionarios.containsKey(cpf)) {
			return false;
		}
		novoNome = novoNome.trim();
		if (!eNomeValido(novoNome)) {
			return false;
		}

		Funcionario funcionario = funcionarios.get(cpf);
		funcionario.setNome(novoNome);
		return true;
	}

	@Override
	public boolean tentarAlterarSenha(String cpf, String novaSenha) {
		if (backend.diaEstaAberto()) {
			return false;
		}

		if (!funcionarios.containsKey(cpf)) {
			return false;
		}
		novaSenha = novaSenha.trim();
		if (!eSenhaValida(novaSenha)) {
			return false;
		}

		Funcionario funcionario = funcionarios.get(cpf);
		funcionario.setSenha(novaSenha);
		return true;
	}

	@Override
	public boolean tentarAlterarCargo(String cpf, CargoFuncionario novoCargo) {
		if (backend.diaEstaAberto()) {
			return false;
		}

		if (!funcionarios.containsKey(cpf)) {
			return false;
		}
		if (!eCargoValido(novoCargo)) {
			return false;
		}

		Funcionario funcionario = funcionarios.get(cpf);
		funcionario.setCargo(novoCargo);
		return true;
	}

	@Override
	public boolean tentarAlterarTelefone(String cpf, String novoTelefone) {
		if (backend.diaEstaAberto()) {
			return false;
		}

		if (!funcionarios.containsKey(cpf)) {
			return false;
		}
		novoTelefone = novoTelefone.trim();
		if (!eTelefoneValido(novoTelefone)) {
			return false;
		}

		Funcionario funcionario = funcionarios.get(cpf);
		funcionario.setTelefone(novoTelefone);
		return true;
	}

	@Override
	public boolean tentarAlterarCPF(String cpf, String novoCPF) {
		if (backend.diaEstaAberto()) {
			return false;
		}

		if (!funcionarios.containsKey(cpf)) {
			return false;
		}
		novoCPF = novoCPF.trim();
		if (!eCPFValido(novoCPF)) {
			return false;
		}

		Funcionario funcionario = funcionarios.get(cpf);
		funcionario.setCpf(novoCPF);

		funcionarios.remove(cpf);
		funcionarios.put(novoCPF, funcionario);
		return true;
	}

	@Override
	public CargoFuncionario login(String usuario, String senha) {
		if (usuario == USUARIO_ADMIN && senha == SENHA_ADMIN) {
			return CargoFuncionario.Administrador;
		}

		Funcionario f = funcionarios.getOrDefault(usuario, null);
		return f == null ? null : f.getCargo();
	}

	private boolean eNomeValido(String nome) {
		return nome.length() > 2 && !nome.startsWith(" ") && !nome.endsWith(" ");
	}

	private boolean eCPFValido(String cpf) {
		if (cpf.length() != TAMANHO_CPF) {
			return false;
		}

		for (int i = 0; i < TAMANHO_CPF; i++) {
			char c = cpf.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	private boolean eTelefoneValido(String telefone) {
		if (telefone.length() != TAMANHO_TELEFONE) {
			return false;
		}

		for (int i = 0; i < TAMANHO_TELEFONE; i++) {
			char c = telefone.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	private boolean eSenhaValida(String senha) {
		return senha.length() > 2;
	}

	private boolean eCargoValido(CargoFuncionario cargo) {
		return cargo != CargoFuncionario.Administrador;
	}
}
