package cinemax.backend.filmes;

import java.util.List;
import java.util.ArrayList;

public class Filme {
	private int id;
	private String nome;
	private String sinopse;
	private int duracaoEmMinutos;
	private ClassificacaoIndicativa classificacaoIndicativa;
	private List<Sessao> sessoes = new ArrayList<Sessao>();
	
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
		return sessoes.toArray(new Sessao[sessoes.size()]);
	}
	
	protected void adicionarSessao(Sessao sessao) {
		sessoes.add(sessao);
	}
	
	protected boolean removerSessao(int id) {
		return sessoes.removeIf(sessao -> sessao.getId() == id);
	}
}
