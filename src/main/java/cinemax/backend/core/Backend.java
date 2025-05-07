package cinemax.backend.core;

import cinemax.backend.alimentos.BancoDeDadosAlimento;
import cinemax.backend.alimentos.IBancoDeDadosAlimento;
import cinemax.backend.alimentos.dummy.DummyBancoDeDadosAlimento;
import cinemax.backend.filmes.BancoDeDadosFilme;
import cinemax.backend.filmes.IBancoDeDadosFilme;
import cinemax.backend.filmes.dummy.DummyBancoDeDadosFilme;
import cinemax.backend.funcionarios.BancoDeDadosFuncionario;
import cinemax.backend.funcionarios.IBancoDeDadosFuncionario;
import cinemax.backend.funcionarios.dummy.DummyBancoDeDadosFuncionario;
import cinemax.backend.relatorios.GerenciadorDeRelatorios;
import cinemax.backend.salas.BancoDeDadosSala;
import cinemax.backend.salas.IBancoDeDadosSala;

public class Backend {
	private boolean diaEstaAberto;
	private IBancoDeDadosFilme bancoFilmes;
	private IBancoDeDadosFuncionario bancoFuncionarios;
	private IBancoDeDadosAlimento bancoAlimentos;
	private IBancoDeDadosSala bancoSalas;
	private GerenciadorDeRelatoriosBackend gerenciadorDeRelatorios;
	
	private Backend() { }
	
	private void setup(
		IBancoDeDadosFilme bancoFilmes,
		IBancoDeDadosFuncionario bancoFuncionarios,
		IBancoDeDadosAlimento bancoAlimentos,
		IBancoDeDadosSala bancoSalas,
		GerenciadorDeRelatorios gerenciadorDeRelatorios
	) {
		this.diaEstaAberto = false;
		this.bancoFilmes = bancoFilmes;
		this.bancoFuncionarios = bancoFuncionarios;
		this.bancoAlimentos = bancoAlimentos;
		this.bancoSalas = bancoSalas;
		this.gerenciadorDeRelatorios = new GerenciadorDeRelatoriosBackend();
	}
	
	public static Backend vazio() {
		Backend backend = new Backend();
		IBancoDeDadosAlimento bancoAlimentos = new BancoDeDadosAlimento(backend);
		IBancoDeDadosFuncionario bancoFuncionarios = new BancoDeDadosFuncionario(backend);
		IBancoDeDadosSala bancoSalas = new BancoDeDadosSala(backend);
		IBancoDeDadosFilme bancoFilmes = new BancoDeDadosFilme(backend, bancoSalas);
		GerenciadorDeRelatorios gerenciadorDeRelatorios = new GerenciadorDeRelatoriosBackend();
		backend.setup(bancoFilmes, bancoFuncionarios, bancoAlimentos, bancoSalas, gerenciadorDeRelatorios);
		return backend;
	}
	
	public static Backend dummy() {
		Backend backend = new Backend();
		IBancoDeDadosAlimento bancoAlimentos = new DummyBancoDeDadosAlimento(backend);
		IBancoDeDadosFuncionario bancoFuncionarios = new DummyBancoDeDadosFuncionario(backend);
		IBancoDeDadosSala bancoSalas = new BancoDeDadosSala(backend);
		IBancoDeDadosFilme bancoFilmes = new DummyBancoDeDadosFilme(backend, bancoSalas);
		GerenciadorDeRelatorios gerenciadorDeRelatorios = new GerenciadorDeRelatoriosBackend();
		backend.setup(bancoFilmes, bancoFuncionarios, bancoAlimentos, bancoSalas, gerenciadorDeRelatorios);
		return backend;
	}
	
	public boolean tentarAbrirDia() {
		if(diaEstaAberto) {
			return false;
		}
		diaEstaAberto = true;
		gerenciadorDeRelatorios.iniciarDia();
		return true;
	}
	
	public boolean tentarFecharDia() {
		if(!diaEstaAberto) {
			return false;
		}
		diaEstaAberto = false;
		gerenciadorDeRelatorios.finalizarDia();
		return true;
	}

	public boolean diaEstaAberto() {
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
	
	public GerenciadorDeRelatorios getGerenciadorRelatorios() {
		return gerenciadorDeRelatorios;
	}
}
