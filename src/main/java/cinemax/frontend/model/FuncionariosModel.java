package cinemax.frontend.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class FuncionariosModel extends AbstractTableModel {
    // Lista de funcionarios
 ArrayList<Funcionarios> funcionarios =new ArrayList();
    
    // Colunas de funcionarios
    String[] colunas = {"nome", "cpf", "cargo", "telefone", "senha"};
    
    
    
    
    //Retornara linhas corretas da coluna
    public String getColumnCount(int column){
    return colunas[column];
        
    }

    

  
    //Retorna a quantidade de linhas
    @Override
    public int getRowCount() {
        return funcionarios.size();
    }

    //Retorna aquantidade de colunas
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

   //Retorna a quantidade de 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       
        Funcionarios funcionario = funcionarios.get(rowIndex);

       //Posicacao d Array
        switch (columnIndex) {
            case 0: return funcionario.getNome();
            case 1: return funcionario.getCpf();
            case 2: return funcionario.getCargo();
            case 3: return funcionario.getTelefone();
            case 4: return funcionario.getSenha();
            default: return null;
        }
    }

}
