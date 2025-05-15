package cinemax.backend.alimentos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cinemax.backend.core.Backend;

/*
Resumo: banco de dados dos alimentos do cinema.
Observação: alterações só são permitidas quando o dia não estiver aberto!
*/
public class BancoDeDadosAlimento implements IBancoDeDadosAlimento {
	private Map<Integer, Alimento> alimentos;
	private Backend backend;
	
	public BancoDeDadosAlimento(Backend backend) {
		this.alimentos = new HashMap<Integer, Alimento>();
		this.backend = backend;
	}

	// Retorna o alimento por seu código ou null caso não ache.
	@Override
	public Alimento obterAlimentoPorCodigo(int codigo) {
		return alimentos.getOrDefault(codigo, null);
	}

	// Retorna um array de alimentos que contém o parâmetro "nome" em seu nome.
	@Override
	public Alimento[] obterAlimentoPorNome(String nome) {
		String lowerNome = nome.toLowerCase();
		List<Alimento> result = new ArrayList<Alimento>();
		for(Alimento alimento : alimentos.values()) {			
			if(alimento.getNome().toLowerCase().contains(lowerNome)) {				
				result.add(alimento);
			}
		}
		
		return collectionAlimentoToArray(result);
	}

	// Retorna todos os alimentos cadastrados.
	@Override
	public Alimento[] obterTodosAlimentos() {
		return collectionAlimentoToArray(alimentos.values());
	}
	
	private Alimento[] collectionAlimentoToArray(Collection<Alimento> collection) {
		return collection.toArray(new Alimento[collection.size()]);
	}

	// Retorna verdadeiro caso exista algum alimento com o código especificado.
	@Override
	public boolean existeAlimentoComCodigo(int codigo) {
		return alimentos.containsKey(codigo);
	}

	// Tenta adicionar um alimento novo, retornando falso se:
	// 1. O nome conter no máximo 2 caracteres;
	// 2. Se preco <= 0;
	// 3. Se o código já foi cadastrado.
	@Override
	public boolean tentardicionarAlimento(String nome, double preco, int codigo) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		
		if(alimentos.containsKey(codigo)) {
			return false;
		}
		nome = nome.trim();
		if(!nomeValido(nome)) {
			return false;
		}
		if(!precoValido(preco)) {
			return false;
		}
		if(!codigoValido(codigo)) {
			return false;
		}
		
		internoAdicionarAlimento(nome, preco, codigo);
		return true;
	}
	
	protected void internoAdicionarAlimento(String nome, double preco, int codigo) {
		Alimento novoAlimento = new Alimento(nome, preco, codigo);
		alimentos.put(codigo, novoAlimento);
	}

	// Tenta remover o alimento pelo código, retorna falso caso:
	// 1. O alimento não foi encontrado.
	@Override
	public boolean tentarRemoverAlimento(int codigo) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		if(!alimentos.containsKey(codigo)) {
			return false;
		}
		
		alimentos.remove(codigo);
		return true;
	}

	// Tenta alterar o nome do alimento pelo seu código, retorna falso caso:
	// 1. O alimento não foi encontrado;
	// 2. O nome contém no máximo 2 caracteres.
	@Override
	public boolean tentarAlterarNome(int codigo, String novoNome) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		if(!alimentos.containsKey(codigo)) {
			return false;
		}
		
		novoNome = novoNome.trim();
		if(!nomeValido(novoNome)) {
			return false;
		}
		
		alterarNome(codigo, novoNome);
		return true;
	}
	
	private void alterarNome(int codigo, String novoNome) {
		alimentos.get(codigo).setNome(novoNome);
	}

	// Tenta alterar o código do alimento pelo seu código atual,
	// retornando falso caso:
	// 1. O alimento não foi encontrado;
	// 2. Existe outro alimento com o novo código;
	// 3. É um código inválido.
	@Override
	public boolean tentarAlterarCodigo(int codigoAtual, int novoCodigo) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		if(!codigoValido(novoCodigo)) {
			return false;
		}
		if(!alimentos.containsKey(codigoAtual)) {
			return false;
		}
		if(alimentos.containsKey(novoCodigo)) {
			return false;
		}
		
		alterarCodigo(codigoAtual, novoCodigo);
		return true;
	}
	
	private void alterarCodigo(int codigoAtual, int novoCodigo) {
		Alimento alimento = alimentos.get(codigoAtual);
		alimento.setcodigo(novoCodigo);
		alimentos.remove(codigoAtual);
		alimentos.put(novoCodigo, alimento);
	}

	// Tenta alterar o preço do alimento pelo seu código,
	// retornando falso caso:
	// 1. O alimento não foi encontrado;
	// 2. preço <= 0.
	@Override
	public boolean tentarAlterarPreco(int codigo, double novoPreco) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		
		if(alimentos.containsKey(codigo)) {
			Alimento alimento = alimentos.get(codigo);
			alimento.setPreco(novoPreco);
			return true;
		}
		return false;
	}
	
	private boolean nomeValido(String nome) {
		return !nome.startsWith(" ") && !nome.endsWith(" ") && nome.length() > 2;
	}
	
	private boolean precoValido(double preco) {
		return preco > 0;
	}
	
	private boolean codigoValido(int codigo) {
		return codigo >= 0 && codigo <= 9999;
	}
}
