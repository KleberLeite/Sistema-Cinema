package cinemax.frontend.model;

public class DadosFilme {

	// :=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
	// -/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-Attributes-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-
	// :=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

	
	private int id;
	private String nome;
	private String sinopse;
	private String sessoes;
	private int duracao;
	private String classificacao;
	
	// :=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
	// -/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-Constructor-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-
	// :=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

	public DadosFilme() {
	}
	public DadosFilme(int id, String nome, String sinopse, String sessoes, int duracao, String classificacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.sinopse = sinopse;
		this.sessoes = sessoes;
		this.duracao = duracao;
		this.classificacao = classificacao;
	}





	// :=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
	// -/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-Methods-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-
	// :=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

	
	
	
	// :=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
	// -/-/-/-/-/-/-/-/-/-/-/-/-/-/-Getters and Setters-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/
	// :=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
	
	
	
	public String getSessoes() {
		return sessoes;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public void setSessoes(String sessoes) {
		this.sessoes = sessoes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDurancao(int duracao) {
		this.duracao = duracao;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacaoIndicada) {
		classificacao = classificacaoIndicada;
	}

}
