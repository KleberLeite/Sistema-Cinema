package cinemax.frontend.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel {

	// :=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
	// -/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-Attributes-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-
	// :=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
	private static final String[] colunas = { "ID", "Nome", "Sinopse", "Duracao(min)", "Classifica\u00E7\u00E3o",
			"Sessao" };

	private ArrayList<DadosFilme> filmes;

	// :=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
	// -/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-Constructor-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-
	// :=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
	public ModeloTabela(ArrayList<DadosFilme> filmes) {
		super();
		this.filmes = filmes;
	}

	// :=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
	// -/-/-/-/-/-/-/-/-/-/-/-/-/-/--/-/-/-/-/Getters-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/
	// :=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colunas[column];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return filmes.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		DadosFilme filme = filmes.get(rowIndex);
		if (columnIndex == 0) {
			return filme.getId();
		} else if (columnIndex == 1) {
			return filme.getNome();
		} else if (columnIndex == 2) {
			return filme.getSinopse();
		} else if (columnIndex == 3) {
			return filme.getDuracao();
		} else if (columnIndex == 4) {
			return filme.getClassificacao();
		} else if (columnIndex == 5) {
			return filme.getSessoes();
		}  {
			return null;
		}

	}

}
