
package cinemax.frontend.geranciadefuncionarios;

import cinemax.frontend.model.Funcionarios;
import cinemax.frontend.model.FuncionariosModel;



/**
 *
 * @author Geraldo Luiz
 */
public class Gerencia extends javax.swing.JFrame {
 FuncionariosModel model=new FuncionariosModel();
    /**
     * Criado interface de GerÃªncia de funcionarios;
     * Ate o momento foram implementados apenas botÃµes e comandos se texto alem de euma planilha.
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
        BotãoCadastrarFuncionarios = new javax.swing.JButton();
        BotãoEditarFuncionarios = new javax.swing.JButton();
        BotãoRemoverFuncionarios = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
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
                "Nome", "CPF", "Cargo", "Telefone", "senha"
            }
        ));
        jScrollPane1.setViewportView(PlanilhaGerenciaFuncionarios);

        BotãoCadastrarFuncionarios.setText("Cadastrar");
        BotãoCadastrarFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotãoCadastrarFuncionariosActionPerformed(evt);
            }
        });

        BotãoEditarFuncionarios.setText("Editar");

        BotãoRemoverFuncionarios.setText("Remover");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Gerência de Funcionários");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addComponent(jLabel1))
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
                                .addComponent(BotãoCadastrarFuncionarios)
                                .addGap(124, 124, 124)
                                .addComponent(BotãoEditarFuncionarios)
                                .addGap(126, 126, 126)
                                .addComponent(BotãoRemoverFuncionarios)))
                        .addGap(0, 151, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
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
                        .addGap(263, 263, 263)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotãoCadastrarFuncionarios)
                            .addComponent(BotãoEditarFuncionarios)
                            .addComponent(BotãoRemoverFuncionarios))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CapturarTXTNomeFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapturarTXTNomeFuncionariosActionPerformed
     
    }//GEN-LAST:event_CapturarTXTNomeFuncionariosActionPerformed

    
                          //Botão cadastrar//
    private void BotãoCadastrarFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotãoCadastrarFuncionariosActionPerformed
        String nome= CapturarTXTNomeFuncionarios.getText();
        String cpf =CapturarTXTCPFFuncionarios.getText();
        String cargo=CapturarTXTCargoFuncionarios.getText();
        String telefone=CapturarTXTTelefoneFuncionarios.getText();
        String senha=CapturarTXTSenhaFuncionarios.getText();
        
        Funcionarios f =new Funcionarios(nome,cpf,cargo,telefone,senha);
                this.model.CadastrarFuncionario(f);
        

        
        
        
        
    }//GEN-LAST:event_BotãoCadastrarFuncionariosActionPerformed

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
    private javax.swing.JButton BotãoCadastrarFuncionarios;
    private javax.swing.JButton BotãoEditarFuncionarios;
    private javax.swing.JButton BotãoRemoverFuncionarios;
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
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
