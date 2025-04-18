package cinemax.frontend.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class FuncionariosModel extends AbstractTableModel {
    private ArrayList<Funcionarios> listaDeFuncionarios = new ArrayList<>();

    private String[] colunas = {"Nome", "CPF", "Cargo", "Telefone", "Senha"};

    // Outras funções como adicionar, remover, etc...

    @Override
    public int getRowCount() {
        return listaDeFuncionarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Funcionarios f = listaDeFuncionarios.get(rowIndex);
        switch (columnIndex) {
            case 0: return f.getNome();
            case 1: return f.getCpf();
            case 2: return f.getCargo();
            case 3: return f.getTelefone();
            case 4: return f.getSenha();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    // 👉 AQUI você coloca o método de atualizar:
    public void atualizarFuncionario(int index, Funcionarios funcionarioAtualizado) {
        listaDeFuncionarios.set(index, funcionarioAtualizado);
        fireTableRowsUpdated(index, index); // Atualiza a linha na tabela
    }

    public void CadastrarFuncionario(Funcionarios f) {
        listaDeFuncionarios.add(f);
        fireTableRowsInserted(listaDeFuncionarios.size() - 1, listaDeFuncionarios.size() - 1);
    }

    public Funcionarios returnFuncionario(int index) {
        return listaDeFuncionarios.get(index);
    }

    public void removerFuncionario(int index) {
        listaDeFuncionarios.remove(index);
        fireTableRowsDeleted(index, index);
    }
}
