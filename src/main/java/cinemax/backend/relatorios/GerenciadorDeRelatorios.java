package cinemax.backend.relatorios;

import java.util.List;

import cinemax.backend.events.Event;
import cinemax.backend.relatorios.alimentos.RelatorioAlimentos;
import cinemax.backend.relatorios.filmes.RelatorioFilmes;

public class GerenciadorDeRelatorios {
	private CircularBuffer<Relatorio> relatorios = new CircularBuffer<Relatorio>(7);
	
	protected GerenciadorDeRelatorios() { }
	
	public Relatorio obterRelatorioDoDia() {
		return relatorios.obterAtual();
	}
	
	// Retorna do mais antigo para o mais novo.
	public List<Relatorio> obterTodos() {
		return relatorios.getAll();
	}
	
	protected void gerarNovoRelatorio() {
		Event<Boolean> aoAlterarPermissaoAlteracoes = new Event<>();
		RelatorioAlimentos relatorioAlimentos = new RelatorioAlimentos(true, aoAlterarPermissaoAlteracoes);
		RelatorioFilmes relatorioFilmes = new RelatorioFilmes(true, aoAlterarPermissaoAlteracoes);
		Relatorio relatorio = new Relatorio(aoAlterarPermissaoAlteracoes, relatorioAlimentos, relatorioFilmes);
		
		relatorios.push(relatorio);
		System.out.println("[Log:GerenciadorDeRelatorios]: Um novo relatório foi gerado!");
	}
	
	protected void finalizarRelatorioAtual() {
		Relatorio relatorio = relatorios.obterAtual();
		if(relatorio != null) {
			relatorio.finalizar();
			System.out.println("[Log:GerenciadorDeRelatorios]: O relatório atual foi finalizado!");
		}
	}
}
