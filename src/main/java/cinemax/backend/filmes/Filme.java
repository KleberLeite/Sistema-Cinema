package cinemax.backend.filmes;

import java.util.Map;
import java.util.HashMap;

public class Filme {
	private int id;
	private String nome;
	private String sinopse;
	private int duracaoEmMinutos;
	private ClassificacaoIndicativa classificacaoIndicativa;
	private Map<Integer, Sessao> sessoes = new HashMap<Integer, Sessao>();
	
	public Filme(
		int id,
		String nome,
		String sinopse,
		int duracaoEmMinutos,
		ClassificacaoIndicativa classificacaoIndicativa
	) {
		this.id = id;
		this.nome = nome;
		this.sinopse = sinopse;
		this.duracaoEmMinutos = duracaoEmMinutos;
		this.classificacaoIndicativa = classificacaoIndicativa;
	}

	public String getNome() {
		return nome;
	}

	protected void setNome(String nome) {
		this.nome = nome;
	}

	public String getSinopse() {
		return sinopse;
	}

	protected void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public int getDuracaoEmMinutos() {
		return duracaoEmMinutos;
	}

	protected void setDuracaoEmMinutos(int duracaoEmMinutos) {
		this.duracaoEmMinutos = duracaoEmMinutos;
	}

	public ClassificacaoIndicativa getClassificacaoIndicativa() {
		return classificacaoIndicativa;
	}

	protected void setClassificacaoIndicativa(ClassificacaoIndicativa classificacaoIndicativa) {
		this.classificacaoIndicativa = classificacaoIndicativa;
	}

	public int getId() {
		return id;
	}
	
	public Sessao[] obterTodasSessoes() {
		return sessoes.values().toArray(new Sessao[sessoes.size()]);
	}
	
	public boolean contemSessao(int id) {
		return sessoes.containsKey(id);
	}
	
	public Sessao obterSessao(int id) {
		return sessoes.getOrDefault(id, null);
	}
	
	protected void adicionarSessao(Sessao sessao) {
		sessoes.put(sessao.getId(), sessao);
	}
	
	protected boolean removerSessao(int id) {
		if(sessoes.containsKey(id)) {
			sessoes.remove(id);
			return true;
		}
		return false;
	}
}
