package cinemax.backend.core;

import cinemax.backend.alimentos.BancoDeDadosAlimento;
import cinemax.backend.alimentos.DummyBancoDeDadosAlimento;
import cinemax.backend.alimentos.IBancoDeDadosAlimento;
import cinemax.backend.filmes.BancoDeDadosFilme;
import cinemax.backend.filmes.DummyBancoDeDadosFilme;
import cinemax.backend.filmes.IBancoDeDadosFilme;
import cinemax.backend.funcionarios.BancoDeDadosFuncionario;
import cinemax.backend.funcionarios.DummyBancoDeDadosFuncionario;
import cinemax.backend.funcionarios.IBancoDeDadosFuncionario;
import cinemax.backend.salas.BancoDeDadosSala;
import cinemax.backend.salas.IBancoDeDadosSala;

public class Backend {
	private boolean diaEstaAberto;
	private IBancoDeDadosFilme bancoFilmes;
	private IBancoDeDadosFuncionario bancoFuncionarios;
	private IBancoDeDadosAlimento bancoAlimentos;
	private IBancoDeDadosSala bancoSalas;
	
	private Backend(
		IBancoDeDadosFilme bancoFilmes,
		IBancoDeDadosFuncionario bancoFuncionarios,
		IBancoDeDadosAlimento bancoAlimentos,
		IBancoDeDadosSala bancoSalas
	) {
		this.diaEstaAberto = false;
		this.bancoFilmes = bancoFilmes;
		this.bancoFuncionarios = bancoFuncionarios;
		this.bancoAlimentos = bancoAlimentos;
		this.bancoSalas = bancoSalas;
	}
	
	public static Backend vazio() {
		IBancoDeDadosAlimento bancoAlimentos = new BancoDeDadosAlimento();
		IBancoDeDadosFuncionario bancoFuncionarios = new BancoDeDadosFuncionario();
		IBancoDeDadosSala bancoSalas = new BancoDeDadosSala();
		IBancoDeDadosFilme bancoFilmes = new BancoDeDadosFilme(bancoSalas);
		return new Backend(bancoFilmes, bancoFuncionarios, bancoAlimentos, bancoSalas);
	}
	
	public static Backend dummy() {
		IBancoDeDadosAlimento bancoAlimentos = new DummyBancoDeDadosAlimento();
		IBancoDeDadosFuncionario bancoFuncionarios = new DummyBancoDeDadosFuncionario();
		IBancoDeDadosSala bancoSalas = new BancoDeDadosSala();
		IBancoDeDadosFilme bancoFilmes = new DummyBancoDeDadosFilme(bancoSalas);
		return new Backend(bancoFilmes, bancoFuncionarios, bancoAlimentos, bancoSalas);
	}
	
	public boolean tentarAbrirDia() {
		if(diaEstaAberto) {
			return false;
		}
		diaEstaAberto = true;
		return true;
	}
	
	public boolean tentarFecharDia() {
		if(!diaEstaAberto) {
			return false;
		}
		diaEstaAberto = false;
		return true;
	}

	public boolean isDiaEstaAberto() {
		return diaEstaAberto;
	}

	public IBancoDeDadosFilme getBancoFilmes() {
		return bancoFilmes;
	}

	public IBancoDeDadosFuncionario getBancoFuncionarios() {
		return bancoFuncionarios;
	}

	public IBancoDeDadosAlimento getBancoAlimentos() {
		return bancoAlimentos;
	}

	public IBancoDeDadosSala getBancoSalas() {
		return bancoSalas;
	}
}
