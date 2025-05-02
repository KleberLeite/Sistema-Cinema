
package cinemax.frontend.geranciadefuncionarios;

import cinemax.frontend.model.Funcionarios;
import cinemax.frontend.model.FuncionariosModel;
import javax.swing.JOptionPane;


/**
 * @author Geraldo Luiz
 *
 */

//_______________________________________________________//

 /**
     * @VISÃO GERAL DA JAVAFX gERANCIA
     * 
     *Tabela para exibição dos funcionários
     *Campos de formulário para inserção de dados
     * Botões de ação (Cadastrar, Editar, Remover)
     */

//_______________________________________________________//

/**
     * @METODOS IMPLEMENTADOS
     * 
     * Captura dados dos campos de texto
     *Valida campos com validarCampos()
     *Cria novo objeto Funcionarios
     *Adiciona ao modelo
     *Limpa campos com limparTextos()
     *BotaoEditarFuncionariosActionPerformed(ActionEvent evt)
     *BotaoRemoverFuncionariosActionPerformed(ActionEvent evt)
     *PlanilhaGerenciaFuncionariosMouseClicked(MouseEvent evt)
     * ( ALEM, DE VALIDAÇÕES:
     * Verifica campos vazios
     * Formato do CPF (11 dígitos)
     * Formato do telefone (10-11 dígitos)
     * Tamanho mínimo da senha (6 caracteres)
 */

//_______________________________________________________//






public class Gerencia extends javax.swing.JFrame {
 FuncionariosModel model=new FuncionariosModel();
  private int indiceSelecionado = -1;
    
  
  
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
        BotaoRemoverFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoRemoverFuncionariosActionPerformed(evt);
            }
        });

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
                        .addGap(278, 278, 278)
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

    
    
// ===================== BOTÂO DE AÇÃO, CADASTRAR FUNCIONARIO ===================== //
//                    Responsáveis pelas operações CRUD (CRIAR)
// -------------------------------------------------------------------------------- //
    
    
                        
    private void BotaoCadastrarFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCadastrarFuncionariosActionPerformed
         BotaoCadastrarFuncionarios.setEnabled(true);  
        String nome= CapturarTXTNomeFuncionarios.getText();
        String cpf =CapturarTXTCPFFuncionarios.getText();
        String cargo=CapturarTXTCargoFuncionarios.getText();
        String telefone=CapturarTXTTelefoneFuncionarios.getText();
        String senha=CapturarTXTSenhaFuncionarios.getText();
        
      
     if (validarCampos(nome, cpf, cargo, telefone, senha)) {
    Funcionarios f = new Funcionarios(nome, cpf, cargo, telefone, senha);
    this.model.CadastrarFuncionario(f);
   
    limparTextos();
   } else {
    JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
     }          
              
    }
    

    
    
    
// ===================== MEDODOS DE FUNCIONALIDADES GERAIS ===================== //
//              1- lIMPARA CAMPOS DE TEXTO;
//              2- METODO MAUSE CLICK
// -------------------------------------------------------------------------------//
    

    
        // 01 //
        public void limparTextos(){
        CapturarTXTNomeFuncionarios.setText("");
        CapturarTXTCPFFuncionarios.setText("");
        CapturarTXTCargoFuncionarios.setText("");
        CapturarTXTTelefoneFuncionarios.setText("");
        CapturarTXTSenhaFuncionarios.setText("");
    }//GEN-LAST:event_BotaoCadastrarFuncionariosActionPerformed
   
        
        // 02 //
    private void PlanilhaGerenciaFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlanilhaGerenciaFuncionariosMouseClicked
                                           
    int index = PlanilhaGerenciaFuncionarios.getSelectedRow();
    if (index >= 0) {
        Funcionarios f = this.model.returnFuncionario(index);
        CapturarTXTNomeFuncionarios.setText(f.getNome());
        CapturarTXTCPFFuncionarios.setText(f.getCpf());
        CapturarTXTCargoFuncionarios.setText(f.getCargo());
        CapturarTXTTelefoneFuncionarios.setText(f.getTelefone());
        CapturarTXTSenhaFuncionarios.setText(f.getSenha());
        
        // Define o índice do funcionário selecionado para edição//
            indiceSelecionado = index;
             BotaoCadastrarFuncionarios.setEnabled(false);
             
             
    }
             

    }//GEN-LAST:event_PlanilhaGerenciaFuncionariosMouseClicked

    
// ===================== BOTÂO DE AÇÃO, EDITAR FUNCIONARIO ===================== //
//                    Responsáveis pelas operações CRUD (EDITAR)
// -------------------------------------------------------------------------------- //
    
    
    
    private void BotaoEditarFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoEditarFuncionariosActionPerformed
           if (indiceSelecionado >= 0) {
        String nome = CapturarTXTNomeFuncionarios.getText();
        String cpf = CapturarTXTCPFFuncionarios.getText();
        String cargo = CapturarTXTCargoFuncionarios.getText();
        String telefone = CapturarTXTTelefoneFuncionarios.getText();
        String senha = CapturarTXTSenhaFuncionarios.getText();

        if (!validarCampos(nome, cpf, cargo, telefone, senha)) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

      
        Funcionarios f = model.returnFuncionario(indiceSelecionado);
        
        // Atualizar os dados desse funcionário//
        f.setNome(nome);
        f.setCpf(cpf);
        f.setCargo(cargo);
        f.setTelefone(telefone);
        f.setSenha(senha);

        // Atualizar a tabela//
        model.fireTableRowsUpdated(indiceSelecionado, indiceSelecionado);
        
      
        limparTextos();
        indiceSelecionado = -1;
        BotaoCadastrarFuncionarios.setEnabled(true);
         BotaoRemoverFuncionarios.setEnabled(true);
         
         
    } else {
        JOptionPane.showMessageDialog(this, 
            "Selecione um funcionário na tabela para editar.", 
            "Aviso", 
            JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_BotaoEditarFuncionariosActionPerformed

    
// ===================== BOTÂO DE AÇÃO, EXCLUIR FUNCIONARIO ===================== //
//                    Responsáveis pelas operações CRUD (EXCLUIR)
//1. Verifica se há um item selecionado na tabela;
// 2. Obtém o funcionário selecionado para mostrar detalhes na confirmação;
// 3. Diálogo de confirmação com detalhes do funcionário;
// 4. Se usuário confirmar, procede com a remoção;
// 4.1 Remove do modelo de dados;
// 4.2 Atualiza a visualização da tabela;
// 4.4 FIEED 
    
// -------------------------------------------------------------------------------- //
    
    
    
    private void BotaoRemoverFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRemoverFuncionariosActionPerformed
        //1. //
    if (indiceSelecionado < 0) {
        JOptionPane.showMessageDialog(
            this, 
            "Por favor, selecione um funcionário na tabela para remover.", 
            "Nenhum Selecionado", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // 2. //
    Funcionarios funcionario = model.returnFuncionario(indiceSelecionado);
    
    // 3. //
    int confirmacao = JOptionPane.showConfirmDialog(
        this,
        "Deseja realmente remover o funcionário?\n\n" +
        "Nome: " + funcionario.getNome() + "\n" +
        "CPF: " + funcionario.getCpf() + "\n" +
        "Cargo: " + funcionario.getCargo(),
        "Confirmar Remoção",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);
    
    // 4. //
    if (confirmacao == JOptionPane.YES_OPTION) {
        try {
            // 4.1 //
            model.removerFuncionario(indiceSelecionado);
            
            // 4.2 //
            model.fireTableDataChanged();
            
            // 4.3 //
            limparTextos();
            indiceSelecionado = -1;
           
            
            // 4.4 //
            JOptionPane.showMessageDialog(
                this,
                "Funcionário removido com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(
                this,
                "Erro ao remover funcionário:\n" + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
        BotaoCadastrarFuncionarios.setEnabled(true);
    }
    }//GEN-LAST:event_BotaoRemoverFuncionariosActionPerformed

    
    
    
    
// ===================== METODO RESPONSAVEL POR VALIDAÇÕE ===================== //
//   * Valida se todos os campos obrigatórios foram preenchidos corretamente*
// Verifica se algum campo obrigatório está vazio;
// -------------------------------------------------------------------------------//
    
    

public boolean validarCampos(String nome, String cpf, String cargo, String telefone, String senha) {
    
    if (nome.trim().isEmpty() || 
        cpf.trim().isEmpty() || 
        cargo.trim().isEmpty() || 
        telefone.trim().isEmpty() || 
        senha.trim().isEmpty()) {
        return false;
    }
    
    // Validações específicas para cada campo//
    
    
    
    if (!validarCPF(cpf)) {
        JOptionPane.showMessageDialog(this, "CPF inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if (!validarTelefone(telefone)) {
        JOptionPane.showMessageDialog(this, "Telefone inválido! Formato esperado: (XX) XXXX-XXXX", "Erro", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if (senha.length() < 6) {
        JOptionPane.showMessageDialog(this, "Senha deve ter no mínimo 6 caracteres!", "Erro", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    return true;
}

/**
 * Valida o formato do CPF (apenas estrutura, não calcula dígitos verificadores)
 * @param cpf CPF a ser validado
 * @return true se o CPF estiver no formato correto
 */
private boolean validarCPF(String cpf) {
    // Remove caracteres não numéricos
    cpf = cpf.replaceAll("[^0-9]", "");
    // Verifica se tem 11 dígitos
    return cpf.length() == 11;
}

/**
 * Valida o formato do telefone
 * @param telefone Telefone a ser validado
 * @return true se o telefone estiver em um formato aceitável
 */
private boolean validarTelefone(String telefone) {
    // Remove caracteres não numéricos
    telefone = telefone.replaceAll("[^0-9]", "");
    // Verifica se tem entre 10 e 11 dígitos (incluindo DDD)
    return telefone.length() >= 10 && telefone.length() <= 11;
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
