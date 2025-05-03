package cinemax.frontend.gerenciamentofilmes;

import javax.swing.JPanel;

import cinemax.backend.filmes.Filme;

public interface TelaManutencaoFilme {

	abstract void atualizarListaDeSessoes(JPanel panelSessoes, Filme filme);
	
	abstract void atualizarListaDeSessoesPosEdicaoOuAdicao();
	
}
