
package cinemax.frontend.geranciadefuncionarios;

import cinemax.frontend.model.Funcionarios;
import cinemax.frontend.model.FuncionariosModel;
import javax.swing.JOptionPane;




/**
 *
 * @author Geraldo Luiz
 * 
 * 
 */
public class Gerencia extends javax.swing.JFrame {
 FuncionariosModel model=new FuncionariosModel();
  private int indiceSelecionado = -1;
    /**
     * Criado interface de Gerencia de funcionarios;
     * Funcionalidades:
     * 
     * Bot�o cadastrar;
     * Metodo validar campos de texto;
     * Criando funcionario;
     * metodo para limpar campos de comando apos a cri�a�o do objeto;
     * Metodos para validar campos de Funcionario completo;
     */
    public Gerencia() {
         initComponents();
       PlanilhaGerenciaFuncionarios.setModel(model);
      
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        PlanilhaGerenciaFuncionarios = new javax.swing.JTable();
        BotaoCadastrarFuncionarios = new javax.swing.JButton();
        BotaoEditarFuncionarios = new javax.swing.JButton();
        BotaoRemoverFuncionarios = new javax.swing.JButton();
        TXTNomeFuncionarios = new javax.swing.JLabel();
        TXTCPFFuncionarios = new javax.swing.JLabel();
        TXTCargoFuncionarios = new javax.swing.JLabel();
        TXTTelefoneFuncionarios = new javax.swing.JLabel();
        TXTSenhaFuncionarios = new javax.swing.JLabel();
        CapturarTXTNomeFuncionarios = new javax.swing.JTextField();
        CapturarTXTCPFFuncionarios = new javax.swing.JTextField();
        CapturarTXTCargoFuncionarios = new javax.swing.JTextField();
        CapturarTXTTelefoneFuncionarios = new javax.swing.JTextField();
        CapturarTXTSenhaFuncionarios = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Funcionários");

        PlanilhaGerenciaFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nome", "CPF", "Cargo", "Teléfone", "Senha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        PlanilhaGerenciaFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlanilhaGerenciaFuncionariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(PlanilhaGerenciaFuncionarios);

        BotaoCadastrarFuncionarios.setText("Cadastrar");
        BotaoCadastrarFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCadastrarFuncionariosActionPerformed(evt);
            }
        });

        BotaoEditarFuncionarios.setText("Editar");
        BotaoEditarFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoEditarFuncionariosActionPerformed(evt);
            }
        });

        BotaoRemoverFuncionarios.setText("Remover");

        TXTNomeFuncionarios.setText("Nome");

        TXTCPFFuncionarios.setText("CPF");

        TXTCargoFuncionarios.setText("Cargo");

        TXTTelefoneFuncionarios.setText("Teléfone");

        TXTSenhaFuncionarios.setText("Senha");

        CapturarTXTNomeFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapturarTXTNomeFuncionariosActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 32, 64));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Gerência de Funcionários");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TXTTelefoneFuncionarios)
                                    .addComponent(TXTSenhaFuncionarios, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CapturarTXTTelefoneFuncionarios)
                                    .addComponent(CapturarTXTSenhaFuncionarios)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXTNomeFuncionarios)
                                    .addComponent(TXTCPFFuncionarios)
                                    .addComponent(TXTCargoFuncionarios))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CapturarTXTNomeFuncionarios)
                                    .addComponent(CapturarTXTCPFFuncionarios)
                                    .addComponent(CapturarTXTCargoFuncionarios, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(BotaoCadastrarFuncionarios)
                        .addGap(124, 124, 124)
                        .addComponent(BotaoEditarFuncionarios)
                        .addGap(126, 126, 126)
                        .addComponent(BotaoRemoverFuncionarios)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TXTNomeFuncionarios)
                                    .addComponent(CapturarTXTNomeFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addComponent(TXTCPFFuncionarios))
                            .addComponent(CapturarTXTCPFFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXTCargoFuncionarios)
                            .addComponent(CapturarTXTCargoFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXTTelefoneFuncionarios)
                            .addComponent(CapturarTXTTelefoneFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXTSenhaFuncionarios)
                            .addComponent(CapturarTXTSenhaFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoCadastrarFuncionarios)
                            .addComponent(BotaoEditarFuncionarios)
                            .addComponent(BotaoRemoverFuncionarios))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CapturarTXTNomeFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapturarTXTNomeFuncionariosActionPerformed
     
    }//GEN-LAST:event_CapturarTXTNomeFuncionariosActionPerformed
//______________________________________________________________________________________//
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
//__________________________________Bot�o e a��es______________________________________//
                          //Bot�o cadastrar//
    private void BotaoCadastrarFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCadastrarFuncionariosActionPerformed
        String nome= CapturarTXTNomeFuncionarios.getText();
        String cpf =CapturarTXTCPFFuncionarios.getText();
        String cargo=CapturarTXTCargoFuncionarios.getText();
        String telefone=CapturarTXTTelefoneFuncionarios.getText();
        String senha=CapturarTXTSenhaFuncionarios.getText();
        
        
        //Metodo validar campos de texto//
                
     if (validarCampos(nome, cpf, cargo, telefone, senha)) {
    Funcionarios f = new Funcionarios(nome, cpf, cargo, telefone, senha);
    this.model.CadastrarFuncionario(f);
    //metodo para limpar campos de comando apos a cri�a�o do objeto//
    limparTextos();
   } else {
    JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
    }

                 
      
                
            
                 
                 
    }
    
//______________________________________________________________________________________//
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
//_____________________________________Metodos________________________________________________//
    
    
    
    
   

    
    
//Metodo limpara campos de comando//
        public void limparTextos(){
        CapturarTXTNomeFuncionarios.setText("");
        CapturarTXTCPFFuncionarios.setText("");
        CapturarTXTCargoFuncionarios.setText("");
        CapturarTXTTelefoneFuncionarios.setText("");
        CapturarTXTSenhaFuncionarios.setText("");
    }//GEN-LAST:event_BotaoCadastrarFuncionariosActionPerformed
   
        
        //Metodo que clicamos na planilha para alterar modifica��es//
    private void PlanilhaGerenciaFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlanilhaGerenciaFuncionariosMouseClicked
                                                               
    int index = PlanilhaGerenciaFuncionarios.getSelectedRow();
    if (index >= 0) {
        Funcionarios f = this.model.returnFuncionario(index);
        CapturarTXTNomeFuncionarios.setText(f.getNome());
        CapturarTXTCPFFuncionarios.setText(f.getCpf());
        CapturarTXTCargoFuncionarios.setText(f.getCargo());
        CapturarTXTTelefoneFuncionarios.setText(f.getTelefone());
        CapturarTXTSenhaFuncionarios.setText(f.getSenha());
        
        // Define o índice do funcionário selecionado para edição
            indiceSelecionado = index;
    }


    }//GEN-LAST:event_PlanilhaGerenciaFuncionariosMouseClicked

    private void BotaoEditarFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoEditarFuncionariosActionPerformed
          if (indiceSelecionado >= 0) {
            String nome = CapturarTXTNomeFuncionarios.getText();
            String cpf = CapturarTXTCPFFuncionarios.getText();
            String cargo = CapturarTXTCargoFuncionarios.getText();
            String telefone = CapturarTXTTelefoneFuncionarios.getText();
            String senha = CapturarTXTSenhaFuncionarios.getText();

            if (validarCampos(nome, cpf, cargo, telefone, senha)) {
               if (validarCampos(nome, cpf, cargo, telefone, senha)) {
        // Obter o funcionário existente da lista
        Funcionarios f = model.returnFuncionario(indiceSelecionado);
        
        // Atualizar os dados desse funcionário
        f.setNome(nome);
        f.setCpf(cpf);
        f.setCargo(cargo);
        f.setTelefone(telefone);
        f.setSenha(senha);

        // Atualizar a tabela
         model.fireTableRowsUpdated(indiceSelecionado, indiceSelecionado);
        
        // Limpar os campos de entrada e resetar o índice
        limparTextos();
        indiceSelecionado = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um funcionário na tabela para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BotaoEditarFuncionariosActionPerformed

        
        //Metodos para validar campos de Funcionario completo//
    

 //______________________________________________________________________________________//
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
//______________________________________________________________________________________//
      
       
        /*
      
      metodo para verificar se existe objatos funcionarios iguais
      
      */
           
    public boolean validarCampos(String nome, String cpf, String cargo, String telefone, String senha) {
    return nome.trim().isEmpty() &&
           !cpf.trim().isEmpty() &&
           !cargo.trim().isEmpty() &&
           !telefone.trim().isEmpty() &&
           !senha.trim().isEmpty();
}

    
        
        
        
        
        
                
                
                
                
       
        
       
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gerencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoCadastrarFuncionarios;
    private javax.swing.JButton BotaoEditarFuncionarios;
    private javax.swing.JButton BotaoRemoverFuncionarios;
    private javax.swing.JTextField CapturarTXTCPFFuncionarios;
    private javax.swing.JTextField CapturarTXTCargoFuncionarios;
    private javax.swing.JTextField CapturarTXTNomeFuncionarios;
    private javax.swing.JTextField CapturarTXTSenhaFuncionarios;
    private javax.swing.JTextField CapturarTXTTelefoneFuncionarios;
    private javax.swing.JTable PlanilhaGerenciaFuncionarios;
    private javax.swing.JLabel TXTCPFFuncionarios;
    private javax.swing.JLabel TXTCargoFuncionarios;
    private javax.swing.JLabel TXTNomeFuncionarios;
    private javax.swing.JLabel TXTSenhaFuncionarios;
    private javax.swing.JLabel TXTTelefoneFuncionarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
