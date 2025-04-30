package cinemax.backend.relatorios;

public class Relatorio {
	private RelatorioAlimentos relatorioAlimentos;
	
	public Relatorio(RelatorioAlimentos relatorioAlimentos) {
		this.relatorioAlimentos = relatorioAlimentos;
	}

	public RelatorioAlimentos getRelatorioAlimentos() {
		return relatorioAlimentos;
	}
	
	public void fechar() {
		relatorioAlimentos.fecharRelatorio();
	}
}
