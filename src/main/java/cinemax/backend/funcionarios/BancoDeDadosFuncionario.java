package cinemax.backend.funcionarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoDeDadosFuncionario implements IBancoDeDadosFuncionario {
	private Map<String, Funcionario> funcionarios = new HashMap<String, Funcionario>();
	
	@Override
	public Funcionario obterFuncionarioPorCPF(String cpf) {
		if(!funcionarios.containsKey(cpf)) {
			return null;
		}
		return funcionarios.get(cpf);
	}

	@Override
	public Funcionario[] obterFuncionariosPorNome(String nome) {
		List<Funcionario> funcionariosValue = new ArrayList<Funcionario>();
		
		for(Funcionario funcionario : funcionarios.values()) {
			if(funcionario.getNome().contains(nome)) {
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

	@Override
	public boolean tentarAdicionarFuncionario(
		String nome,
		String cpf,
		CargoFuncionario cargo,
		String telefone,
		String senha
	) {
		if(funcionarios.containsKey(cpf)) {
			return false;
		}
		nome = nome.trim();
		if(!eNomeValido(nome)) {
			return false;
		}
		cpf = cpf.trim();
		if(!eCPFValido(cpf)) {
			return false;
		}
		telefone = telefone.trim();
		if(!eTelefoneValido(telefone)) {
			return false;
		}
		senha = senha.trim();
		if(!eSenhaValida(senha)) {
			return false;
		}
		if(!eCargoValido(cargo)) {
			return false;
		}
		
		Funcionario funcionario = new Funcionario(nome, cpf, cargo, telefone, senha);
		funcionarios.put(cpf, funcionario);
		return true;
	}

	@Override
	public boolean tentarRemoverFuncionarioPorCPF(String cpf) {
		if(!funcionarios.containsKey(cpf)) {
			return false;
		}
		funcionarios.remove(cpf);
		return true;
	}

	@Override
	public boolean tentarAlterarNome(String cpf, String novoNome) {
		if(!funcionarios.containsKey(cpf)) {
			return false;
		}
		novoNome = novoNome.trim();
		if(!eNomeValido(novoNome)) {
			return false;
		}
		
		Funcionario funcionario = funcionarios.get(cpf);
		funcionario.setNome(novoNome);
		return true;
	}

	@Override
	public boolean tentarAlterarSenha(String cpf, String novaSenha) {
		if(!funcionarios.containsKey(cpf)) {
			return false;
		}
		novaSenha = novaSenha.trim();
		if(!eSenhaValida(novaSenha)) {
			return false;
		}
		
		Funcionario funcionario = funcionarios.get(cpf);
		funcionario.setSenha(novaSenha);
		return true;
	}

	@Override
	public boolean tentarAlterarCargo(String cpf, CargoFuncionario novoCargo) {
		if(!funcionarios.containsKey(cpf)) {
			return false;
		}
		if(!eCargoValido(novoCargo)) {
			return false;
		}
		
		Funcionario funcionario = funcionarios.get(cpf);
		funcionario.setCargo(novoCargo);
		return true;
	}

	@Override
	public boolean tentarAlterarTelefone(String cpf, String novoTelefone) {
		if(!funcionarios.containsKey(cpf)) {
			return false;
		}
		novoTelefone = novoTelefone.trim();
		if(!eTelefoneValido(novoTelefone)) {
			return false;
		}
		
		Funcionario funcionario = funcionarios.get(cpf);
		funcionario.setTelefone(novoTelefone);
		return true;
	}

	@Override
	public boolean tentarAlterarCPF(String cpf, String novoCPF) {
		if(!funcionarios.containsKey(cpf)) {
			return false;
		}
		novoCPF = novoCPF.trim();
		if(!eCPFValido(novoCPF)) {
			return false;
		}
		
		Funcionario funcionario = funcionarios.get(cpf);
		funcionario.setCpf(novoCPF);
		
		funcionarios.remove(cpf);
		funcionarios.put(novoCPF, funcionario);
		return true;
	}

	private boolean eNomeValido(String nome) {
		return nome.length() > 2 && !nome.startsWith(" ") && !nome.endsWith(" ");
	}
	
	private boolean eCPFValido(String cpf) {
		if(cpf.length() != 11) {
			return false;
		}
		
		for(int i = 0; i < 11; i++) {
			char c = cpf.charAt(i);
			if(c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}
	
	private boolean eTelefoneValido(String telefone) {
		if(telefone.length() != 8) {
			return false;
		}
		
		for(int i = 0; i < 8; i++) {
			char c = telefone.charAt(i);
			if(c < '0' || c > '9') {
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
