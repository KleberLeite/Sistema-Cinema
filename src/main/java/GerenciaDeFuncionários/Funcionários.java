
package GerenciaDeFuncionários;

/**
 *
 * @author Geraldo Luiz
 */
public class Funcionários extends javax.swing.JFrame {

    /*
     * Criado interface de Gerencia de  Funcionários;
     *  Até o momento estão implementados apenas comandos de texto, botões e uma planilha...
    */
    
    public Funcionários() {
        initComponents();
       
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        TXTNomeFuncionario = new javax.swing.JLabel();
        TXTCPFFuncionarios = new javax.swing.JLabel();
        TXTTelefoneFuncionarios = new javax.swing.JLabel();
        TXTCargoFuncionarios = new javax.swing.JLabel();
        CapturadeTXTNomeFuncionario = new javax.swing.JTextField();
        CapturadeTXTCPFuncionario = new javax.swing.JTextField();
        CapturadeTXTSalarioFuncionarios = new javax.swing.JTextField();
        CapturadeTXTCargoFuncionarios = new javax.swing.JTextField();
        BotãoCadastrarFuncionarios = new javax.swing.JToggleButton();
        BotãoAlterarFuncionarios = new javax.swing.JToggleButton();
        BotãoRemoverFuncionarios = new javax.swing.JToggleButton();
        Senha = new javax.swing.JLabel();
        CapturadeTXTSenhaFuncionario = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaFuncionarios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Funcionários");

        TXTNomeFuncionario.setText("Nome");

        TXTCPFFuncionarios.setText("CPF");

        TXTTelefoneFuncionarios.setText("Teléfone");

        TXTCargoFuncionarios.setText("Cargo");

        CapturadeTXTNomeFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapturadeTXTNomeFuncionarioActionPerformed(evt);
            }
        });

        BotãoCadastrarFuncionarios.setText("Cadastrar");
        BotãoCadastrarFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotãoCadastrarFuncionariosActionPerformed(evt);
            }
        });

        BotãoAlterarFuncionarios.setText("Alterar");

        BotãoRemoverFuncionarios.setText("Remover");

        Senha.setText("Senha");

        CapturadeTXTSenhaFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapturadeTXTSenhaFuncionarioActionPerformed(evt);
            }
        });

        TabelaFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "CPF", "Teléfone", "Senha"
            }
        ));
        jScrollPane1.setViewportView(TabelaFuncionarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXTCPFFuncionarios)
                                    .addComponent(TXTNomeFuncionario)
                                    .addComponent(TXTCargoFuncionarios)
                                    .addComponent(Senha)
                                    .addComponent(TXTTelefoneFuncionarios))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CapturadeTXTNomeFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                    .addComponent(CapturadeTXTCPFuncionario)
                                    .addComponent(CapturadeTXTSalarioFuncionarios)
                                    .addComponent(CapturadeTXTCargoFuncionarios, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                    .addComponent(CapturadeTXTSenhaFuncionario)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(BotãoCadastrarFuncionarios)
                        .addGap(116, 116, 116)
                        .addComponent(BotãoAlterarFuncionarios)
                        .addGap(101, 101, 101)
                        .addComponent(BotãoRemoverFuncionarios)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CapturadeTXTNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTNomeFuncionario))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXTCPFFuncionarios)
                            .addComponent(CapturadeTXTCPFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CapturadeTXTCargoFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTCargoFuncionarios))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CapturadeTXTSalarioFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTTelefoneFuncionarios))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Senha)
                            .addComponent(CapturadeTXTSenhaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotãoCadastrarFuncionarios)
                            .addComponent(BotãoAlterarFuncionarios)
                            .addComponent(BotãoRemoverFuncionarios))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    ///--------------------------------------------------------------------------////
    ///|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||////
    ///-------------------------------------------------------------------------////
    
   
    
   //Comandos de texto//
    private void CapturadeTXTNomeFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapturadeTXTNomeFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CapturadeTXTNomeFuncionarioActionPerformed

    private void BotãoCadastrarFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotãoCadastrarFuncionariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotãoCadastrarFuncionariosActionPerformed

    private void CapturadeTXTSenhaFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapturadeTXTSenhaFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CapturadeTXTSenhaFuncionarioActionPerformed

     ///--------------------------------------------------------------------------////
    ///|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||////
    ///-------------------------------------------------------------------------////
   
    
   
    public static void main(String args[]) {
        
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Funcionários().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BotãoAlterarFuncionarios;
    private javax.swing.JToggleButton BotãoCadastrarFuncionarios;
    private javax.swing.JToggleButton BotãoRemoverFuncionarios;
    private javax.swing.JTextField CapturadeTXTCPFuncionario;
    private javax.swing.JTextField CapturadeTXTCargoFuncionarios;
    private javax.swing.JTextField CapturadeTXTNomeFuncionario;
    private javax.swing.JTextField CapturadeTXTSalarioFuncionarios;
    private javax.swing.JTextField CapturadeTXTSenhaFuncionario;
    private javax.swing.JLabel Senha;
    private javax.swing.JLabel TXTCPFFuncionarios;
    private javax.swing.JLabel TXTCargoFuncionarios;
    private javax.swing.JLabel TXTNomeFuncionario;
    private javax.swing.JLabel TXTTelefoneFuncionarios;
    private javax.swing.JTable TabelaFuncionarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
