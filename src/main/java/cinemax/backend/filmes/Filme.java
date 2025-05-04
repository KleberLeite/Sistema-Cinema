package cinemax.backend.filmes;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

// Mantém os dados do filme e suas sessões.
public class Filme {
	private int id;
	private String nome;
	private String sinopse;
	private Set<GeneroFilme> generos;
	private int duracaoEmMinutos;
	private ClassificacaoIndicativa classificacaoIndicativa;
	private Map<Integer, Sessao> sessoes = new HashMap<Integer, Sessao>();
	
	public Filme(
		int id,
		String nome,
		String sinopse,
		GeneroFilme[] generos,
		int duracaoEmMinutos,
		ClassificacaoIndicativa classificacaoIndicativa
	) {
		this.id = id;
		this.nome = nome;
		this.sinopse = sinopse;
		this.generos = newHashSetGeneros(generos);
		this.duracaoEmMinutos = duracaoEmMinutos;
		this.classificacaoIndicativa = classificacaoIndicativa;
	}
	
	private Set<GeneroFilme> newHashSetGeneros(GeneroFilme[] generos) {
		Set<GeneroFilme> s = new HashSet<>();
		for(GeneroFilme g : generos) {
			s.add(g);
		}
		return s;
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

	public GeneroFilme[] getGeneros() {
		return generos.toArray(new GeneroFilme[generos.size()]);
	}
	
	protected boolean addGenero(GeneroFilme genero) {
		return generos.add(genero);
	}
	
	protected boolean removeGenero(GeneroFilme genero) {
		return generos.remove(genero);
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
	
	// Retorna falso caso não encontre a sessão.
	protected boolean tentarRemoverSessao(int id) {
		if(sessoes.containsKey(id)) {
			sessoes.remove(id);
			return true;
		}
		return false;
	}
}
