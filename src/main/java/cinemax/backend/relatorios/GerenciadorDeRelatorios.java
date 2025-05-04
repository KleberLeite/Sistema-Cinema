package cinemax.backend.relatorios;

import java.util.List;

public class GerenciadorDeRelatorios {
	private CircularBuffer<Relatorio> relatorios = new CircularBuffer<Relatorio>(7);
	
	public GerenciadorDeRelatorios() { }
	
	protected GerenciadorDeRelatorios(CircularBuffer<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}
	
	protected GerenciadorDeRelatorios(GerenciadorDeRelatorios gerenciador) {
		this.relatorios = gerenciador.relatorios;
	}
	
	public Relatorio obterRelatorioDoDia() {
		return relatorios.obterAtual();
	}
	
	// Retorna do mais antigo para o mais novo.
	public List<Relatorio> obterTodos() {
		return relatorios.getAll();
	}
	
	protected void gerarNovoRelatorio() {
		relatorios.push(new Relatorio());
	}
	
	protected void finalizarRelatorioAtual() {
		Relatorio relatorio = relatorios.obterAtual();
		if(relatorio != null) {
			relatorio.fechar();
		}
	}
}
