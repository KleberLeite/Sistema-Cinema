package cinemax.frontend.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/*
Model de Funcionalidades...
Funcionaliddaes implementadas:

 Lista de funcionarios;
Retornara linhas corretas da coluna;
Funcionalidade para cadastrar;
Metodos que nos retornara um funcionario;
Metodo para Linhas, colunas e objetos da planilhas;

*/
public class FuncionariosModel extends AbstractTableModel {
    // Lista de funcionarios//
 ArrayList<Funcionarios> funcionarios =new ArrayList();
    
    // Colunas de funcionarios//
    String[] colunas = {"nome", "cpf", "cargo", "telefone", "senha"};
    
    
    
    
    //Retornara linhas corretas da coluna//
    public String getColumnCount(int column){
    return colunas[column];
        
    }
//______________________________________________________________________________________//
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
//____________________________Metodos___________________________________________________//
    
                     //Funcionalidade para cadastrar//
    public void CadastrarFuncionario(Funcionarios f){
        funcionarios.add(f);
        this.fireTableDataChanged();
    }
 
    
        //   Metodos que nos retornara um funcionario//
    
    public Funcionarios returnFuncionario(int index){
        return funcionarios.get(index);
    }
    
//______________________________________________________________________________________//
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
//________________________Linhas, colunas e objetos da planilhas________________________//
    
  
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
