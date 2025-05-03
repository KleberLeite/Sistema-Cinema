/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cinemax.frontend.vendaDeAlimentos;

import cinemax.backend.alimentos.Alimento;
import cinemax.backend.alimentos.BancoDeDadosAlimento;
import cinemax.frontend.paginasGerenteFuncionario.Gerente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author geral
 */
public class VendasDeAlimentos extends javax.swing.JFrame {
 private DefaultTableModel modeloTabela;
    private List<Alimento> listaAlimentos = new ArrayList<>();
    private Alimento alimentoSelecionado;
    private int quantidadeSelecionada = 0;
    private double custoTotal = 0.0;
    private BancoDeDadosAlimento banco;
    /**
     * Creates new form VendasDeAlimentos
     */
    public VendasDeAlimentos() {
       this.banco = banco;
       
        inicializarTabela();
        configurarListeners();
        atualizarTabela();
          initComponents();
       
        
    }

    private VendasDeAlimentos(BancoDeDadosAlimento bancoAlimentos) {
       
    }
     private void inicializarTabela() {
        modeloTabela = (DefaultTableModel) PlanilhaDeAlimentosDisponiveis.getModel();
        Alimento[] alimentos = banco.obterTodosAlimentos();
        listaAlimentos.clear();
        for (Alimento a : alimentos) {
            listaAlimentos.add(a);
        }
    }
    
    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Alimento alimento : listaAlimentos) {
            modeloTabela.addRow(new Object[]{
                alimento.getNome(),
                "R$ " + alimento.getPreco(),
                alimento.getCodigo()
            });
        }
    }

    private void configurarListeners() {
        PlanilhaDeAlimentosDisponiveis.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int linha = PlanilhaDeAlimentosDisponiveis.getSelectedRow();
                if (linha != -1) {
                    alimentoSelecionado = listaAlimentos.get(linha);
                    TXTItem.setText(alimentoSelecionado.getNome());
                    quantidadeSelecionada = 0;
                    custoTotal = 0.0;
                    atualizarCamposTotais();
                }
            }
        });

        TXTAcremento.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (alimentoSelecionado != null) {
                    quantidadeSelecionada++;
                    custoTotal += alimentoSelecionado.getPreco();
                    atualizarCamposTotais();
                }
            }
        });

        TXTDecremento.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (alimentoSelecionado != null && quantidadeSelecionada > 0) {
                    quantidadeSelecionada--;
                    custoTotal -= alimentoSelecionado.getPreco();
                    atualizarCamposTotais();
                }
            }
        });

        ButaoProcurarAlimentoNome.addActionListener(e -> {
            String nome = CapturaTXTProcurarNomeAlimento.getText();
            Alimento[] resultados = banco.obterAlimentoPorNome(nome);
            atualizarTabelaComResultado(resultados);
        });

        ButaoProcurarCodigoAlimento.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(CapturaTXTProcurarCodigoAlimento.getText());
                Alimento resultado = banco.obterAlimentoPorCodigo(codigo);
                if (resultado != null) {
                    atualizarTabelaComResultado(new Alimento[]{resultado});
                } else {
                    modeloTabela.setRowCount(0);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um código válido");
            }
        });
    }

    private void atualizarTabelaComResultado(Alimento[] alimentos) {
        modeloTabela.setRowCount(0);
        listaAlimentos.clear();
        for (Alimento a : alimentos) {
            listaAlimentos.add(a);
            modeloTabela.addRow(new Object[]{
                a.getNome(),
                "R$ " + a.getPreco(),
                a.getCodigo()
            });
        }
    }

    private void atualizarCamposTotais() {
        QuantidadeSelecionado.setText(String.valueOf(quantidadeSelecionada));
        QualtidadeTotalValor.setText(String.format("R$ %.2f", custoTotal));
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PlanilhaDeAlimentosDisponiveis = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        TXTQuantidadeTotalselecionado = new javax.swing.JLabel();
        TXTCustototal = new javax.swing.JLabel();
        QuantidadeSelecionado = new javax.swing.JLabel();
        QualtidadeTotalValor = new javax.swing.JLabel();
        CapturaTXTProcurarNomeAlimento = new javax.swing.JTextField();
        CapturaTXTProcurarCodigoAlimento = new javax.swing.JTextField();
        TXTProcurarNomeAlimento = new javax.swing.JLabel();
        TXTProcurarCodigoAlimento = new javax.swing.JLabel();
        ButaoProcurarAlimentoNome = new javax.swing.JButton();
        ButaoProcurarCodigoAlimento = new javax.swing.JButton();
        TXTItemselecionado = new javax.swing.JLabel();
        TXTItem = new javax.swing.JLabel();
        TXTAcremento = new javax.swing.JLabel();
        TXTDecremento = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        BotaoVoltar = new javax.swing.JButton();
        BotaovoltarInicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(2, 32, 64));

        jPanel2.setBackground(new java.awt.Color(0, 32, 64));

        PlanilhaDeAlimentosDisponiveis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                " Nome", "Preço", "Código"
            }
        ));
        jScrollPane1.setViewportView(PlanilhaDeAlimentosDisponiveis);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        TXTQuantidadeTotalselecionado.setText("Quanatidade de itens totais selecionados:");

        TXTCustototal.setText("Custo totas: ");

        QuantidadeSelecionado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        QuantidadeSelecionado.setText("0");

        QualtidadeTotalValor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        QualtidadeTotalValor.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TXTQuantidadeTotalselecionado)
                    .addComponent(TXTCustototal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(QuantidadeSelecionado)
                    .addComponent(QualtidadeTotalValor))
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTQuantidadeTotalselecionado)
                    .addComponent(QuantidadeSelecionado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(QualtidadeTotalValor)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(TXTCustototal)
                        .addGap(15, 15, 15))))
        );

        CapturaTXTProcurarNomeAlimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapturaTXTProcurarNomeAlimentoActionPerformed(evt);
            }
        });

        TXTProcurarNomeAlimento.setText("Procurar Nome:");

        TXTProcurarCodigoAlimento.setText("Procurar Código");

        ButaoProcurarAlimentoNome.setText("Procurar");

        ButaoProcurarCodigoAlimento.setText("Procurar");

        TXTItemselecionado.setText("Item selecionado:");

        TXTItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        TXTItem.setText("itemmmmm");

        TXTAcremento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TXTAcremento.setText("+");

        TXTDecremento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TXTDecremento.setText("-");

        jLabel11.setText("------------------------------------------------------------------------------");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addComponent(TXTItemselecionado)
                            .addGap(58, 58, 58)
                            .addComponent(TXTItem)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TXTAcremento)
                            .addGap(36, 36, 36)
                            .addComponent(TXTDecremento)
                            .addGap(11, 11, 11))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TXTProcurarCodigoAlimento)
                                .addComponent(TXTProcurarNomeAlimento))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(CapturaTXTProcurarCodigoAlimento, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                .addComponent(CapturaTXTProcurarNomeAlimento))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ButaoProcurarCodigoAlimento)
                                .addComponent(ButaoProcurarAlimentoNome)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CapturaTXTProcurarNomeAlimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTProcurarNomeAlimento)
                    .addComponent(ButaoProcurarAlimentoNome))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CapturaTXTProcurarCodigoAlimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTProcurarCodigoAlimento)
                    .addComponent(ButaoProcurarCodigoAlimento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(86, 86, 86)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTItemselecionado)
                    .addComponent(TXTItem)
                    .addComponent(TXTAcremento)
                    .addComponent(TXTDecremento))
                .addGap(18, 179, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        BotaoVoltar.setText("Voltar");
        BotaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoVoltarActionPerformed(evt);
            }
        });

        BotaovoltarInicio.setText("Voltar");
        BotaovoltarInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaovoltarInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoVoltar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BotaovoltarInicio)
                .addGap(72, 72, 72))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BotaovoltarInicio)
                .addGap(69, 69, 69)
                .addComponent(BotaoVoltar))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoVoltarActionPerformed
      
    }//GEN-LAST:event_BotaoVoltarActionPerformed

    private void CapturaTXTProcurarNomeAlimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapturaTXTProcurarNomeAlimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CapturaTXTProcurarNomeAlimentoActionPerformed

    private void BotaovoltarInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaovoltarInicioActionPerformed
      // Chamando a tela Gerente
             Gerente telaGerente = new Gerente("usuario", "senha");
            telaGerente.setVisible(true); // Torna a tela de Gerente visível
            dispose(); // Fecha a tela atual (VendasDeAlimentos)
    }//GEN-LAST:event_BotaovoltarInicioActionPerformed

    /**
     * @param args the command line arguments
     */
     public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            // Versão sem tratamento de erros (mais simples)
            new VendasDeAlimentos(new BancoDeDadosAlimento()).setVisible(true);
        }
    });
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoVoltar;
    private javax.swing.JButton BotaovoltarInicio;
    private javax.swing.JButton ButaoProcurarAlimentoNome;
    private javax.swing.JButton ButaoProcurarCodigoAlimento;
    private javax.swing.JTextField CapturaTXTProcurarCodigoAlimento;
    private javax.swing.JTextField CapturaTXTProcurarNomeAlimento;
    private javax.swing.JTable PlanilhaDeAlimentosDisponiveis;
    private javax.swing.JLabel QualtidadeTotalValor;
    private javax.swing.JLabel QuantidadeSelecionado;
    private javax.swing.JLabel TXTAcremento;
    private javax.swing.JLabel TXTCustototal;
    private javax.swing.JLabel TXTDecremento;
    private javax.swing.JLabel TXTItem;
    private javax.swing.JLabel TXTItemselecionado;
    private javax.swing.JLabel TXTProcurarCodigoAlimento;
    private javax.swing.JLabel TXTProcurarNomeAlimento;
    private javax.swing.JLabel TXTQuantidadeTotalselecionado;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
