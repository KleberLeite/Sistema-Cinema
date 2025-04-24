package cinemax.backend.filmes;

import cinemax.backend.core.Backend;
import cinemax.backend.salas.IBancoDeDadosSala;

public class DummyBancoDeDadosFilme extends BancoDeDadosFilme {
	public DummyBancoDeDadosFilme(Backend backend, IBancoDeDadosSala bancoDeDadosSala) {
		super(backend, bancoDeDadosSala);

		this.tentarAdicionarFilme("Cinderela e os 7 Anões", "Um filme da Cinderela com os 7 anões.", 120, ClassificacaoIndicativa.AL);
		this.tentarAdicionarFilme("SpiderMan: O retorno", "Homem Aranha está de volta!", 180, ClassificacaoIndicativa.AL14);
		this.tentarAdicionarFilme("Os 7 Monstrinhos", "Assustador!", 80, ClassificacaoIndicativa.AL);
		this.tentarAdicionarFilme("Bob Sponja & Patrick", "Eu sou o Bob Esponja.", 150, ClassificacaoIndicativa.AL);
		this.tentarAdicionarFilme("Velozes & Furiosos", "Vruuuum!", 170, ClassificacaoIndicativa.AL18);
		this.tentarAdicionarFilme("O Sniper", "Apenas um disparo.", 130, ClassificacaoIndicativa.AL16);
		this.tentarAdicionarFilme("MekaAnimais", "Animais mecânicos!", 50, ClassificacaoIndicativa.AL);
	}
}
