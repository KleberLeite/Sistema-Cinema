package cinemax.backend.filmes.dummy;

import java.time.LocalDateTime;

import cinemax.backend.core.Backend;
import cinemax.backend.filmes.BancoDeDadosFilme;
import cinemax.backend.filmes.ClassificacaoIndicativa;
import cinemax.backend.filmes.GeneroFilme;
import cinemax.backend.salas.IBancoDeDadosSala;

public class DummyBancoDeDadosFilme extends BancoDeDadosFilme {
	public DummyBancoDeDadosFilme(Backend backend, IBancoDeDadosSala bancoDeDadosSala) {
		super(backend, bancoDeDadosSala);

		this.internoAdicionarFilme(
			"Procurando Nemo",
			"Um peixe-palhaço cruza o oceano para resgatar seu filho.",
			new GeneroFilme[] { GeneroFilme.Infantil },
			100,
			ClassificacaoIndicativa.AL
		);
		this.internoAdicionarFilme(
			"Paddington 2",
			"O adorável urso Paddington tenta provar sua inocência após ser acusado injustamente.",
			new GeneroFilme[] { GeneroFilme.Infantil },			
			103,
			ClassificacaoIndicativa.AL10
		);
		this.internoAdicionarFilme(
			"Pantera Negra",
			"O rei T'Challa luta para proteger Wakanda após a morte de seu pai.",
			new GeneroFilme[] { GeneroFilme.Acao, GeneroFilme.Herois },	
			141,
			ClassificacaoIndicativa.AL12
		);
		this.internoAdicionarFilme(
			"Jogos Vorazes",
			"Uma jovem participa de uma competição mortal televisionada.",
			new GeneroFilme[] { GeneroFilme.Acao, GeneroFilme.Terror },	
			142,
			ClassificacaoIndicativa.AL14
		);
		this.internoAdicionarFilme(
			"Coringa",
			"A origem sombria do vilão Coringa em uma sociedade caótica.",
			new GeneroFilme[] { GeneroFilme.Acao, GeneroFilme.Herois },	
			122,
			ClassificacaoIndicativa.AL16
		);
		this.internoAdicionarFilme(
			"O Lobo de Wall Street",
			"A ascensão e queda de um corretor da bolsa envolvido em excessos.",
			new GeneroFilme[] { GeneroFilme.Documentario },	
			360,
			ClassificacaoIndicativa.AL18
		);
		
		// Dia 1
		this.internoAdicionarSessao(0, 0, LocalDateTime.now().plusHours(1).withMinute(0));
		this.internoAdicionarSessao(1, 1, LocalDateTime.now().plusHours(1).withMinute(0));
		this.internoAdicionarSessao(0, 2, LocalDateTime.now().plusHours(4).withMinute(0));

		// Dia 2
		this.internoAdicionarSessao(1, 3, LocalDateTime.now().plusDays(1).withHour(10).withMinute(0));
		this.internoAdicionarSessao(1, 4, LocalDateTime.now().plusDays(1).withHour(14).withMinute(37));
		this.internoAdicionarSessao(1, 5, LocalDateTime.now().plusDays(1).withHour(20).withMinute(54)); // Lobo: 16:54–19:54

		// Dia 3
		this.internoAdicionarSessao(0, 1, LocalDateTime.now().plusDays(2).withHour(7).withMinute(0)); // Paddington
		this.internoAdicionarSessao(0, 3, LocalDateTime.now().plusDays(2).withHour(11).withMinute(0)); // Jogos
		this.internoAdicionarSessao(0, 4, LocalDateTime.now().plusDays(2).withHour(15).withMinute(37)); // Coringa

		// Dia 4
		this.internoAdicionarSessao(1, 2, LocalDateTime.now().plusDays(3).withHour(9).withMinute(0)); // Pantera
		this.internoAdicionarSessao(1, 5, LocalDateTime.now().plusDays(3).withHour(14).withMinute(36)); // Lobo

		// Dia 5
		this.internoAdicionarSessao(0, 0, LocalDateTime.now().plusDays(4).withHour(10).withMinute(30)); // Nemo
		this.internoAdicionarSessao(0, 2, LocalDateTime.now().plusDays(4).withHour(15).withMinute(25)); // Pantera
		this.internoAdicionarSessao(0, 3, LocalDateTime.now().plusDays(4).withHour(19).withMinute(56)); // Jogos

		// Dia 6
		this.internoAdicionarSessao(1, 1, LocalDateTime.now().plusDays(5).withHour(10).withMinute(15)); // Paddington
		this.internoAdicionarSessao(1, 4, LocalDateTime.now().plusDays(5).withHour(15).withMinute(33)); // Coringa
		this.internoAdicionarSessao(1, 5, LocalDateTime.now().plusDays(5).withHour(20).withMinute(35)); // Lobo

		// Dia 7
		this.internoAdicionarSessao(0, 0, LocalDateTime.now().plusDays(6).withHour(11).withMinute(0)); // Nemo
		this.internoAdicionarSessao(0, 1, LocalDateTime.now().plusDays(6).withHour(12).withMinute(55)); // Paddington
		this.internoAdicionarSessao(0, 4, LocalDateTime.now().plusDays(6).withHour(14).withMinute(53)); // Coringa
	}
}
