/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cinemax.frontend.alimentos;

import cinemax.frontend.PaginasGeranteeFuncionario.PaginaPrincipal;
import cinemax.backend.alimentos.Alimento;
import cinemax.backend.alimentos.IBancoDeDadosAlimento;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.estilizacao.Estilizador;
import cinemax.frontend.estilizacao.EstiloBotao;
import cinemax.frontend.estilizacao.JTextFieldEstilizado;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author geral
 */
public class TelaVendaDeAlimento extends javax.swing.JFrame {
	private Map<Alimento, Integer> carrinho = new HashMap<>();

	private ControladorDeApp app = ControladorDeApp.getInstancia();
	private IBancoDeDadosAlimento bancoDados = app.getBackend().getBancoAlimentos();
	private DefaultTableModel modeloTabela;

	/**
	 * Creates new form VendaDealimentoss
	 */
	public TelaVendaDeAlimento() {
		initComponents();
                
                PlanilhaDeAlimentos.setModel(new javax.swing.table.DefaultTableModel(
		new Object[][] {},
		new String[] { "Nome", "Preço", "Código" }
	) {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false; // Impede a edição da célula
		}
	});

                
                
          
		inicializarTabela();
		configurarListeners();
		TXTAcrementoDeItem.setText("+");
		TXTDecrementoDeItem.setText("-");

		// Aqui está o local certo para adicionar os listeners aos "+" e "-"
		TXTAcrementoDeItem.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				incrementarItem();
			}
		});

		TXTDecrementoDeItem.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				decrementarItem();
			}
		});
		/*
		 * BotaoComprar.addActionListener(new java.awt.event.ActionListener() { public
		 * void actionPerformed(java.awt.event.ActionEvent evt) {
		 * BotaoComprarActionPerformed(evt); } });
		 * 
		 * bntRecarregarLista.addActionListener(new java.awt.event.ActionListener() {
		 * public void actionPerformed(java.awt.event.ActionEvent evt) {
		 * bntRecarregarListaActionPerformed(evt); } });
		 */
	}

	// ___________________________________________///
	private void inicializarTabela() {
		modeloTabela = (DefaultTableModel) PlanilhaDeAlimentos.getModel();
		atualizarTabela();
		Color corFundoCabelho =  new Color(2, 17, 28);
        Estilizador.estilizarTabela(PlanilhaDeAlimentos,corFundoCabelho);
	}

	private void atualizarTabela() {
		modeloTabela.setRowCount(0);
		Alimento[] alimentos = bancoDados.obterTodosAlimentos();
		for (Alimento alimento : alimentos) {
			modeloTabela.addRow(new Object[] { alimento.getNome(), alimento.getPreco(), alimento.getCodigo() });
		}
		Color corFundoCabelho =  new Color(2, 17, 28);
        Estilizador.estilizarTabela(PlanilhaDeAlimentos,corFundoCabelho);
	}

	private void configurarListeners() {
		PlanilhaDeAlimentos.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int linhaSelecionada = PlanilhaDeAlimentos.getSelectedRow(); // CORRIGIDO AQUI
				if (linhaSelecionada != -1) {
					preencherCampos(linhaSelecionada);
				}
			}
		});
	}

        
        public void limparCamposs() {        	
    // Limpar os campos internos diretamente
    TXTitemSelecionado.setText("");
    TXTQuantidadeDeitemTotaisSelecionados.setText("0");
    TXTPrecoTotalDeTodosOsItems.setText("0");
    carrinho.clear();
}

	private void limparCampos() {
		CapturaTXTProcurarNomeAlimento.setText("");
		CapturaTXTProcurarCodigoAlimento.setText("");
	}

	private void preencherCampos(int linhaSelecionada) {
		Object valor = PlanilhaDeAlimentos.getValueAt(linhaSelecionada, 0);
		String nome = valor != null ? valor.toString() : "Desconhecido";
		TXTitemSelecionado.setText(nome);
	}

	private void incrementarItem() {
		int linhaSelecionada = PlanilhaDeAlimentos.getSelectedRow();
		if (linhaSelecionada != -1) {
			int codigo = (int) PlanilhaDeAlimentos.getValueAt(linhaSelecionada, 2);
			Alimento a = bancoDados.obterAlimentoPorCodigo(codigo);
			if (a != null) {
				// Incrementar quantidade
				carrinho.put(a, carrinho.getOrDefault(a, 0) + 1);

				// Atualizar contadores
				atualizarQuantidadeTotal();
				atualizarPrecoTotal();
			}
		}
	}

	private void decrementarItem() {
		int linhaSelecionada = PlanilhaDeAlimentos.getSelectedRow();
		if (linhaSelecionada != -1) {
			int codigo = (int) PlanilhaDeAlimentos.getValueAt(linhaSelecionada, 2);
			Alimento a = bancoDados.obterAlimentoPorCodigo(codigo);

			if (a == null) {
				return;
			}

			if (carrinho.containsKey(a)) {
				int qtdAtual = carrinho.get(a);
				if (qtdAtual > 1) {
					carrinho.put(a, qtdAtual - 1);
				} else {
					carrinho.remove(a); // Remove se quantidade for 1
				}

				atualizarQuantidadeTotal();
				atualizarPrecoTotal();
			}
		}
	}

	private void atualizarQuantidadeTotal() {
		int total = 0;
		for (int qtd : carrinho.values()) {
			total += qtd;
		}

		// Atualiza o texto no JLabel com a quantidade total de itens
		TXTQuantidadeDeitemTotaisSelecionados.setText(String.valueOf(total));

		// Opcional: imprime no console para depuração
		// System.out.println("Total de itens no carrinho: " + total);
	}

      
        
        
        
        
        
        
        
	private void atualizarPrecoTotal() {
		double total = 0.0;
		for (Map.Entry<Alimento, Integer> entry : carrinho.entrySet()) {
			total += entry.getKey().getPreco() * entry.getValue();
		}
		TXTPrecoTotalDeTodosOsItems.setText(String.format("R$ %.2f", total));
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	//@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PlanilhaDeAlimentos = new javax.swing.JTable();
        jPanelPrincipal = new javax.swing.JPanel();
        TxtnomeDoalimento = new javax.swing.JLabel();
        TxtnomeDoalimento.setFont(new Font("Tahoma", Font.BOLD, 13));
        TXTcodigodoalimento = new javax.swing.JLabel();
        TXTcodigodoalimento.setFont(new Font("Tahoma", Font.BOLD, 13));
        CapturaTXTProcurarNomeAlimento = new JTextFieldEstilizado(null);
        CapturaTXTProcurarCodigoAlimento = new JTextFieldEstilizado(null);
        bntProcurarnomeAliemnto = new javax.swing.JButton();
        bntProcurarnomeAliemnto.setFont(new Font("Tahoma", Font.BOLD, 12));
        bntProvurarcodigo = new javax.swing.JButton();
        bntProvurarcodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel4.setFont(new Font("Tahoma", Font.BOLD, 13));
        TXTitemSelecionado = new javax.swing.JLabel();
        TXTAcrementoDeItem = new javax.swing.JLabel();
        TXTAcrementoDeItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        TXTDecrementoDeItem = new javax.swing.JLabel();
        TXTDecrementoDeItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jPanelTotalEItens = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel8.setFont(new Font("Tahoma", Font.BOLD, 13));
        jLabel9 = new javax.swing.JLabel();
        jLabel9.setFont(new Font("Tahoma", Font.BOLD, 13));
        TXTQuantidadeDeitemTotaisSelecionados = new javax.swing.JLabel();
        TXTPrecoTotalDeTodosOsItems = new javax.swing.JLabel();
        bntRecarregarLista = new javax.swing.JButton();
        bntRecarregarLista.setFont(new Font("Tahoma", Font.BOLD, 12));
        bntVoltar = new javax.swing.JButton();
        bntVoltar.setFont(new Font("Tahoma", Font.BOLD, 13));
        BotaoAvancar = new javax.swing.JButton();
        BotaoAvancar.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        jPanelPrincipal = Estilizador.criarPainelArredondado(new Color(255, 255, 255),10);
        jPanelTotalEItens = Estilizador.criarPainelArredondadoComBorda(new Color(240, 240, 240),Color.BLACK, 10, 1);
        
        Estilizador.aplicarEstiloBotao(BotaoAvancar, EstiloBotao.CLARO_UNIFICADO);
        Estilizador.aplicarEstiloBotao(bntProcurarnomeAliemnto, EstiloBotao.PADRAO_ESCURECIDO);
        Estilizador.aplicarEstiloBotao(bntProvurarcodigo, EstiloBotao.PADRAO_ESCURECIDO);
        Estilizador.aplicarEstiloBotao(bntRecarregarLista, EstiloBotao.PADRAO_ESCURECIDO);
        Estilizador.aplicarEstiloBotao(bntVoltar, EstiloBotao.CLARO_UNIFICADO);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cinemax");
        setBackground(new java.awt.Color(2, 32, 64));

        jPanel1.setBackground(new Color(2, 18, 27));

        PlanilhaDeAlimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Preço", "Código"
            }
        ));
        
        PlanilhaDeAlimentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlanilhaDeAlimentosMouseClicked(evt);
            }
        });
        
        jScrollPane1.setViewportView(PlanilhaDeAlimentos);
        jScrollPane1 = Estilizador.estilizandoScrollBarVertEHori(jScrollPane1);

        TxtnomeDoalimento.setText("Procurar Nome:");

        TXTcodigodoalimento.setText("Procurar Código:");

        bntProcurarnomeAliemnto.setText("Procurar");
        bntProcurarnomeAliemnto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntProcurarnomeAliemntoActionPerformed(evt);
            }
        });

        bntProvurarcodigo.setText("Procurar");
        bntProvurarcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntProvurarcodigoActionPerformed(evt);
            }
        });

        jLabel3.setText("__________________________________________________________");

        jLabel4.setText("Item Selecionado:");

        TXTitemSelecionado.setFont(new Font("Segoe UI", Font.BOLD, 13)); // NOI18N

        TXTAcrementoDeItem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TXTAcrementoDeItem.setText("+");

        TXTDecrementoDeItem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TXTDecrementoDeItem.setText("-");

        jPanelTotalEItens.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setText("Total de Item selecionados:");

        jLabel9.setText("Total:");

        TXTQuantidadeDeitemTotaisSelecionados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TXTQuantidadeDeitemTotaisSelecionados.setText("0");

        TXTPrecoTotalDeTodosOsItems.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TXTPrecoTotalDeTodosOsItems.setText("0");

        javax.swing.GroupLayout gl_jPanelTotalEItens = new javax.swing.GroupLayout(jPanelTotalEItens);
        gl_jPanelTotalEItens.setHorizontalGroup(
        	gl_jPanelTotalEItens.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanelTotalEItens.createSequentialGroup()
        			.addGap(17)
        			.addGroup(gl_jPanelTotalEItens.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_jPanelTotalEItens.createSequentialGroup()
        					.addComponent(jLabel8)
        					.addPreferredGap(ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
        					.addComponent(TXTQuantidadeDeitemTotaisSelecionados))
        				.addGroup(Alignment.TRAILING, gl_jPanelTotalEItens.createSequentialGroup()
        					.addComponent(jLabel9)
        					.addPreferredGap(ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
        					.addComponent(TXTPrecoTotalDeTodosOsItems)))
        			.addGap(22))
        );
        gl_jPanelTotalEItens.setVerticalGroup(
        	gl_jPanelTotalEItens.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanelTotalEItens.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_jPanelTotalEItens.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel8)
        				.addComponent(TXTQuantidadeDeitemTotaisSelecionados))
        			.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
        			.addGroup(gl_jPanelTotalEItens.createParallelGroup(Alignment.BASELINE)
        				.addComponent(TXTPrecoTotalDeTodosOsItems)
        				.addComponent(jLabel9))
        			.addGap(19))
        );
        jPanelTotalEItens.setLayout(gl_jPanelTotalEItens);

        bntRecarregarLista.setText("Recarregar Lista");
        bntRecarregarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntRecarregarListaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gl_jPanelPrincipal = new javax.swing.GroupLayout(jPanelPrincipal);
        gl_jPanelPrincipal.setHorizontalGroup(
        	gl_jPanelPrincipal.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_jPanelPrincipal.createSequentialGroup()
        			.addGroup(gl_jPanelPrincipal.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_jPanelPrincipal.createSequentialGroup()
        					.addGap(20)
        					.addGroup(gl_jPanelPrincipal.createParallelGroup(Alignment.TRAILING)
        						.addComponent(bntRecarregarLista)
        						.addGroup(gl_jPanelPrincipal.createSequentialGroup()
        							.addGroup(gl_jPanelPrincipal.createParallelGroup(Alignment.TRAILING)
        								.addGroup(gl_jPanelPrincipal.createSequentialGroup()
        									.addComponent(TxtnomeDoalimento)
        									.addGap(12))
        								.addGroup(gl_jPanelPrincipal.createSequentialGroup()
        									.addComponent(TXTcodigodoalimento)
        									.addGap(18)))
        							.addGroup(gl_jPanelPrincipal.createParallelGroup(Alignment.LEADING, false)
        								.addComponent(CapturaTXTProcurarNomeAlimento, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
        								.addComponent(CapturaTXTProcurarCodigoAlimento))
        							.addGap(18)
        							.addGroup(gl_jPanelPrincipal.createParallelGroup(Alignment.LEADING)
        								.addComponent(bntProvurarcodigo)
        								.addComponent(bntProcurarnomeAliemnto)))))
        				.addGroup(gl_jPanelPrincipal.createSequentialGroup()
        					.addGap(138)
        					.addComponent(TXTitemSelecionado)))
        			.addContainerGap(44, Short.MAX_VALUE))
        		.addGroup(gl_jPanelPrincipal.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_jPanelPrincipal.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_jPanelPrincipal.createSequentialGroup()
        					.addGap(316, 316, Short.MAX_VALUE)
        					.addComponent(TXTAcrementoDeItem)
        					.addGap(32)
        					.addComponent(TXTDecrementoDeItem)
        					.addGap(28))
        				.addGroup(gl_jPanelPrincipal.createSequentialGroup()
        					.addComponent(jLabel4)
        					.addGap(188, 280, Short.MAX_VALUE))))
        		.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 406, Short.MAX_VALUE)
        		.addGroup(gl_jPanelPrincipal.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jPanelTotalEItens, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_jPanelPrincipal.setVerticalGroup(
        	gl_jPanelPrincipal.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanelPrincipal.createSequentialGroup()
        			.addGap(25)
        			.addGroup(gl_jPanelPrincipal.createParallelGroup(Alignment.BASELINE)
        				.addComponent(TxtnomeDoalimento)
        				.addComponent(CapturaTXTProcurarNomeAlimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(bntProcurarnomeAliemnto))
        			.addGap(38)
        			.addGroup(gl_jPanelPrincipal.createParallelGroup(Alignment.BASELINE)
        				.addComponent(TXTcodigodoalimento)
        				.addComponent(CapturaTXTProcurarCodigoAlimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(bntProvurarcodigo))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(bntRecarregarLista)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
        			.addComponent(jLabel4)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(TXTitemSelecionado)
        			.addGap(10)
        			.addGroup(gl_jPanelPrincipal.createParallelGroup(Alignment.BASELINE)
        				.addComponent(TXTAcrementoDeItem)
        				.addComponent(TXTDecrementoDeItem))
        			.addGap(41)
        			.addComponent(jPanelTotalEItens, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(75))
        );
        jPanelPrincipal.setLayout(gl_jPanelPrincipal);

        bntVoltar.setText("  Voltar  ");
        bntVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntVoltarActionPerformed(evt);
            }
        });
        
        
        BotaoAvancar.setText(" Avançar ");
        BotaoAvancar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BotaoComprarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(26)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
        			.addGap(39)
        			.addComponent(jPanelPrincipal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(57, Short.MAX_VALUE))
        		.addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup()
        			.addGap(18)
        			.addComponent(bntVoltar)
        			.addPreferredGap(ComponentPlacement.RELATED, 770, Short.MAX_VALUE)
        			.addComponent(BotaoAvancar)
        			.addGap(20))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(44)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jPanelPrincipal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 503, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(bntVoltar)
        				.addComponent(BotaoAvancar))
        			.addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1.setLayout(jPanel1Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void bntVoltarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bntVoltarActionPerformed
		// Chamando a tela Gerente//
		PaginaPrincipal.abrirPaginaPrincipal();
		dispose();
		
		if(telaCarrinhoAlimentos != null) {
			telaCarrinhoAlimentos.dispose();
		}
	}// GEN-LAST:event_bntVoltarActionPerformed

	private void PlanilhaDeAlimentosMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_PlanilhaDeAlimentosMouseClicked

	}// GEN-LAST:event_PlanilhaDeAlimentosMouseClicked

	private void bntProcurarnomeAliemntoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bntProcurarnomeAliemntoActionPerformed
		String nomeProcurado = CapturaTXTProcurarNomeAlimento.getText().trim();

		if (nomeProcurado.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Digite um nome para procurar!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		modeloTabela.setRowCount(0);
		Alimento[] resultados = bancoDados.obterAlimentoPorNome(nomeProcurado);

		if (resultados.length == 0) {
			JOptionPane.showMessageDialog(this, "Nenhum alimento encontrado com este nome!", "Informação",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			for (Alimento alimento : resultados) {
				modeloTabela.addRow(new Object[] { alimento.getNome(), alimento.getPreco(), alimento.getCodigo() });
			}
		}
		limparCampos();
	}// GEN-LAST:event_bntProcurarnomeAliemntoActionPerformed

	private void bntProvurarcodigoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bntProvurarcodigoActionPerformed
		String codigoText = CapturaTXTProcurarCodigoAlimento.getText().trim();

		if (codigoText.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Digite um código para procurar!", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			int codigo = Integer.parseInt(codigoText);
			Alimento alimento = bancoDados.obterAlimentoPorCodigo(codigo);

			if (alimento == null) {
				JOptionPane.showMessageDialog(this, "Nenhum alimento encontrado com este código!", "Informação",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				modeloTabela.setRowCount(0);
				modeloTabela.addRow(new Object[] { alimento.getNome(), alimento.getPreco(), alimento.getCodigo() });
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "O código deve ser um número válido!", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		limparCampos();
	}// GEN-LAST:event_bntProvurarcodigoActionPerformed

	private void BotaoComprarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BotaoComprarActionPerformed
		if (carrinho.isEmpty()) {
	        JOptionPane.showMessageDialog(this, 
	            "Carrinho está vazio!", 
	            "Aviso", 
	            JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	
		if(telaCarrinhoAlimentos != null) {
			return;
		}
	    
	    telaCarrinhoAlimentos = new TelaCarrinhoAlimentos(this);
	    telaCarrinhoAlimentos.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            	telaCarrinhoAlimentos = null;
            }
        });
	    
	    telaCarrinhoAlimentos.adicionarItens(new HashMap<>(carrinho)); // Passa cópia do carrinho
	    telaCarrinhoAlimentos.setVisible(true);
	    telaCarrinhoAlimentos.setLocationRelativeTo(null);
	}// GEN-LAST:event_BotaoComprarActionPerformed

	private void bntRecarregarListaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bntRecarregarListaActionPerformed
		atualizarTabela();
		limparCampos();
	}// GEN-LAST:event_bntRecarregarListaActionPerformed

	private void salvarCompraNoHistorico() {
		app.getBackend().getGerenciadorRelatorios().obterRelatorioDoDia().getRelatorioAlimentos()
				.adicionarVendas(carrinho);
		/*
		 * for (Map.Entry<String, Integer> entry : carrinho.entrySet()) { String
		 * nomeProduto = entry.getKey(); int quantidade = entry.getValue();
		 * 
		 * // Aqui você pode chamar seu backend para salvar no banco
		 * System.out.println("Salvando compra: " + nomeProduto + " - Qtd: " +
		 * quantidade);
		 * 
		 * // Exemplo fictício (substitua com o real): //
		 * app.getBackend().getHistoricoCompras().registrarCompra(nomeProduto, //
		 * quantidade); }
		 */

		// Posteriormente o RelatorioGeral pode consultar esse histórico
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TelaVendaDeAlimento.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaVendaDeAlimento.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaVendaDeAlimento.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaVendaDeAlimento.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TelaVendaDeAlimento().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
	private TelaCarrinhoAlimentos telaCarrinhoAlimentos;
    private javax.swing.JButton BotaoAvancar;
    private javax.swing.JTextField CapturaTXTProcurarCodigoAlimento;
    private javax.swing.JTextField CapturaTXTProcurarNomeAlimento;
    private javax.swing.JTable PlanilhaDeAlimentos;
    private javax.swing.JLabel TXTAcrementoDeItem;
    private javax.swing.JLabel TXTDecrementoDeItem;
    private javax.swing.JLabel TXTPrecoTotalDeTodosOsItems;
    private javax.swing.JLabel TXTQuantidadeDeitemTotaisSelecionados;
    private javax.swing.JLabel TXTcodigodoalimento;
    private javax.swing.JLabel TXTitemSelecionado;
    private javax.swing.JLabel TxtnomeDoalimento;
    private javax.swing.JButton bntProcurarnomeAliemnto;
    private javax.swing.JButton bntProvurarcodigo;
    private javax.swing.JButton bntRecarregarLista;
    private javax.swing.JButton bntVoltar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelTotalEItens;
    private javax.swing.JScrollPane jScrollPane1;
}
