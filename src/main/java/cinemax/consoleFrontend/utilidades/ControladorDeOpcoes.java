package cinemax.consoleFrontend.utilidades;

import java.util.Scanner;

public class ControladorDeOpcoes {	
	public static class Opcao {
		private String opcao;
		private Runnable aoSelecionar;
		private boolean opcaoSair;
		
		private Opcao(String opcao, Runnable aoSelecionar, boolean opcaoSair) {
			this.opcao = opcao;
			this.aoSelecionar = aoSelecionar;
			this.opcaoSair = opcaoSair;
		}
		
		public Opcao comCallback(String opcao, Runnable aoSelecionar) {
			return new Opcao(opcao, aoSelecionar, false);
		}
		
		public Opcao opcaoSair(String opcao) {
			return new Opcao(opcao, null, true);
		}

		public String getOpcao() {
			return opcao;
		}
		
		public boolean eOpcaoSair() {
			return opcaoSair;
		}
		
		public Runnable getAoSelecionar() {
			return aoSelecionar;
		}
	}
	
	private Opcao[] opcoes;
	private Scanner sc;
	
	public ControladorDeOpcoes(Opcao[] opcoes, Scanner sc) {
		this.opcoes = opcoes;
		this.sc = sc;
	}
	
	public boolean exibirOpcoes() {
		for(int i = 0; i < opcoes.length; i++) {
			System.out.println((i+1) + ". " + opcoes[i].getOpcao());
		}

		int opcao = sc.nextInt();
		if(opcao <= 0 || opcao > opcoes.length) {
			return true;
		} else {
			if(opcoes[opcao].eOpcaoSair()) {
				return false;
			} else {
				opcoes[opcao].getAoSelecionar().run();
				return true;
			}
		}
	}
}
