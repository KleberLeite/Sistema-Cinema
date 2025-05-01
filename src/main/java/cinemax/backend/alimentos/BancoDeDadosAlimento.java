package cinemax.backend.alimentos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cinemax.backend.core.Backend;

public class BancoDeDadosAlimento implements IBancoDeDadosAlimento {
	private Map<Integer, Alimento> alimentos;
	private Backend backend;
	
	public BancoDeDadosAlimento(Backend backend) {
		this.alimentos = new HashMap<Integer, Alimento>();
		this.backend = backend;
	}

	@Override
	public Alimento obterAlimentoPorCodigo(int codigo) {
		if(alimentos.containsKey(codigo)) {
			return alimentos.get(codigo);
		}
		return null;
	}

	@Override
	public Alimento[] obterAlimentoPorNome(String nome) {
		List<Alimento> result = new ArrayList<Alimento>();
		for(Alimento alimento : alimentos.values()) {
			if(alimento.getNome().contains(nome)) {
				result.add(alimento);
			}
		}
		
		return result.toArray(new Alimento[result.size()]);
	}

	@Override
	public Alimento[] obterTodosAlimentos() {
		Collection<Alimento> alimentosValues = alimentos.values();
		return alimentosValues.toArray(new Alimento[alimentosValues.size()]);
	}

	@Override
	public boolean existeAlimentoComCodigo(int codigo) {
		return alimentos.containsKey(codigo);
	}

	@Override
	public boolean tentardicionarAlimento(String nome, double preco, int codigo) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		
		if(alimentos.containsKey(codigo)) {
			return false;
		}
		else if(nome.length() <= 2) {
			return false;
		}
		else if(preco <= 0) {
			return false;
		}
		
		Alimento novoAlimento = new Alimento(nome, preco, codigo);
		alimentos.put(codigo, novoAlimento);
		return true;
	}

	@Override
	public boolean tentarRemoverAlimento(int codigo) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		
		if(alimentos.containsKey(codigo)) {
			alimentos.remove(codigo);
			return true;
		}
		return false;
	}

	@Override
	public boolean tentarAlterarNome(int codigo, String novoNome) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		
		if(alimentos.containsKey(codigo)) {
			Alimento alimento = alimentos.get(codigo);
			alimento.setNome(novoNome);
			return true;
		}
		return false;
	}

	@Override
	public boolean tentarAlterarCodigo(int codigo, int novoCodigo) {
		if(backend.diaEstaAberto()) {
			return false;
		}
		
		if(alimentos.containsKey(novoCodigo) || !alimentos.containsKey(codigo)) {
			return false;
		}
		
		Alimento alimento = alimentos.get(codigo);
		alimento.setcodigo(novoCodigo);
		alimentos.remove(codigo);
		alimentos.put(novoCodigo, alimento);
		return true;
	}

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
}
