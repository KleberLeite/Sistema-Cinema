package cinemax.backend.relatorios;

import java.util.List;

import cinemax.backend.events.Event;
import cinemax.backend.relatorios.alimentos.RelatorioAlimentos;
import cinemax.backend.relatorios.filmes.RelatorioFilmes;

public class GerenciadorDeRelatorios {
	private CircularBuffer<Relatorio> relatorios = new CircularBuffer<Relatorio>(7);
	
	public GerenciadorDeRelatorios() { }
	
	public GerenciadorDeRelatorios(CircularBuffer<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}
	
	public Relatorio obterRelatorioDoDia() {
		return relatorios.obterAtual();
	}
	
	// Retorna do mais antigo para o mais novo.
	public List<Relatorio> obterTodos() {
		return relatorios.getAll();
	}
	
	public void gerarNovoRelatorio() {
		Event<Boolean> aoAlterarPermissaoAlteracoes = new Event<>();
		RelatorioAlimentos relatorioAlimentos = new RelatorioAlimentos(true, aoAlterarPermissaoAlteracoes);
		RelatorioFilmes relatorioFilmes = new RelatorioFilmes(true, aoAlterarPermissaoAlteracoes);
		Relatorio relatorio = new Relatorio(true, aoAlterarPermissaoAlteracoes, relatorioAlimentos, relatorioFilmes);
		
		relatorios.push(relatorio);
		System.out.println("[Log:GerenciadorDeRelatorios]: Um novo relatório foi gerado!");
	}
	
	public void finalizarRelatorioAtual() {
		Relatorio relatorio = relatorios.obterAtual();
		if(relatorio != null) {
			relatorio.finalizar();
			System.out.println("[Log:GerenciadorDeRelatorios]: O relatório atual foi finalizado!");
		}
	}
	
	public int size() {
		return relatorios.size();
	}
}
