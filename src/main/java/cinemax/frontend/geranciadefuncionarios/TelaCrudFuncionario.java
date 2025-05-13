
package cinemax.frontend.geranciadefuncionarios;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import cinemax.backend.filmes.ClassificacaoIndicativa;
import cinemax.backend.funcionarios.CargoFuncionario;
import cinemax.backend.funcionarios.Funcionario;
import cinemax.backend.funcionarios.IBancoDeDadosFuncionario;
import cinemax.frontend.PaginasGeranteeFuncionario.PaginaPrincipal;
import cinemax.frontend.estilizacao.Estilizador;
import cinemax.frontend.estilizacao.EstiloBotao;
import cinemax.frontend.estilizacao.JTextFieldEstilizado;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

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
 *          Captura dados dos campos de texto Valida campos com validarCampos()
 *          Cria novo objeto Funcionarios Adiciona ao modelo Limpa campos com
 *          limparTextos() BotaoEditarFuncionariosActionPerformed(ActionEvent
 *          evt) BotaoRemoverFuncionariosActionPerformed(ActionEvent evt)
 *          PlanilhaGerenciaFuncionariosMouseClicked(MouseEvent evt) ( ALEM, DE
 *          VALIDAÇÕES: Verifica campos vazios Formato do CPF (11 dígitos)
 *          Formato do telefone (10-11 dígitos) Tamanho mínimo da senha (6
 *          caracteres)
 */

//_______________________________________________________//

@SuppressWarnings("serial")
public class TelaCrudFuncionario extends javax.swing.JFrame {
	private IBancoDeDadosFuncionario bancoDeDados;
	private int indiceSelecionado = -1;
	private Funcionario[] funcionariosLista;

	public TelaCrudFuncionario(IBancoDeDadosFuncionario bancoDeDados) {
		getContentPane().setBackground(new Color(2, 17, 28));
		this.bancoDeDados = bancoDeDados;
		initComponents();
		DefaultTableModel model = (DefaultTableModel) PlanilhaGerenciaFuncionarios.getModel();
		model.setRowCount(0);
		atualizarTabela();
	}

	private void atualizarTabela() {
		//System.out.println("Iniciando atualização da tabela...");

		try {
			funcionariosLista = bancoDeDados.obterTodosFuncionarios();
			
			//System.out.println("Número de funcionários encontrados: " + funcionariosLista.length);
			Color corFundoCabelho =  new Color(2, 17, 28);
			Estilizador.estilizarTabela(PlanilhaGerenciaFuncionarios,corFundoCabelho);
			
			DefaultTableModel model = (DefaultTableModel) PlanilhaGerenciaFuncionarios.getModel();
			model.setRowCount(0);

			for (Funcionario funcionario : funcionariosLista) {
				//System.out.println("Adicionando: " + funcionario.getNome());
				model.addRow(new Object[] { funcionario.getNome(), funcionario.getCpf(),
						funcionario.getCargo().toString(), funcionario.getTelefone(), funcionario.getSenha() });
			}

			model.fireTableDataChanged();
			//System.out.println("Tabela atualizada com sucesso!");

		} catch (Exception e) {
			//System.err.println("Erro ao atualizar tabela: " + e.getMessage());
			e.printStackTrace();
		}
	}

	//@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		BotaoCadastrarFuncionarios = new javax.swing.JButton();
		BotaoEditarFuncionarios = new javax.swing.JButton();
		BotaoRemoverFuncionarios = new javax.swing.JButton();
		TXTNomeFuncionarios = new javax.swing.JLabel();
		TXTNomeFuncionarios.setFont(new Font("Tahoma", Font.BOLD, 13));
		TXTNomeFuncionarios.setForeground(new Color(255, 255, 255));
		TXTCPFFuncionarios = new javax.swing.JLabel();
		TXTCPFFuncionarios.setFont(new Font("Tahoma", Font.BOLD, 13));
		TXTCPFFuncionarios.setForeground(new Color(255, 255, 255));
		TXTCargoFuncionarios = new javax.swing.JLabel();
		TXTCargoFuncionarios.setFont(new Font("Tahoma", Font.BOLD, 13));
		TXTCargoFuncionarios.setForeground(new Color(255, 255, 255));
		TXTTelefoneFuncionarios = new javax.swing.JLabel();
		TXTTelefoneFuncionarios.setFont(new Font("Tahoma", Font.BOLD, 13));
		TXTTelefoneFuncionarios.setForeground(new Color(255, 255, 255));
		TXTSenhaFuncionarios = new javax.swing.JLabel();
		TXTSenhaFuncionarios.setForeground(new Color(255, 255, 255));
		TXTSenhaFuncionarios.setFont(new Font("Tahoma", Font.BOLD, 13));
		CapturarTXTNomeFuncionarios = new JTextFieldEstilizado(null);
		CapturarTXTCPFFuncionarios = new JTextFieldEstilizado(null);
		comboBoxCargoFuncionario =  new JComboBox<>();
		for (CargoFuncionario cargo : CargoFuncionario.values()) {
		    if(cargo!=CargoFuncionario.Administrador)comboBoxCargoFuncionario.addItem(cargo);
		}
		CapturarTXTTelefoneFuncionarios = new JTextFieldEstilizado(null);
		CapturarTXTSenhaFuncionarios = new JTextFieldEstilizado(null);
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel1.setBackground(new Color(0, 0, 0));
		ButaoVoltar = new javax.swing.JButton();
		
		Estilizador.estilizarComboBoxClassificacaoIndicativa(comboBoxCargoFuncionario);
		comboBoxCargoFuncionario.setSelectedIndex(-1);
		
		Estilizador.aplicarEstiloBotao(ButaoVoltar, EstiloBotao.CLARO_UNIFICADO);
		Estilizador.aplicarEstiloBotao(BotaoCadastrarFuncionarios, EstiloBotao.PADRAO_ESCURECIDO);
		Estilizador.aplicarEstiloBotao(BotaoEditarFuncionarios, EstiloBotao.PADRAO_ESCURECIDO);
		Estilizador.aplicarEstiloBotao(BotaoRemoverFuncionarios, EstiloBotao.PADRAO_ESCURECIDO);
		
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Cinemax");
		
		jScrollPane1 = Estilizador.estilizarScrollPane(jScrollPane1);

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

		TXTNomeFuncionarios.setText("Nome:");

		TXTCPFFuncionarios.setText("CPF:");

		TXTCargoFuncionarios.setText("Cargo:");

		TXTTelefoneFuncionarios.setText("Telefone:");

		TXTSenhaFuncionarios.setText("Senha:");

		CapturarTXTNomeFuncionarios.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CapturarTXTNomeFuncionariosActionPerformed(evt);
			}
		});

		jPanel1.setBackground(new Color(255, 255, 255));

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel1.setForeground(new Color(0, 0, 0));
		jLabel1.setText("Gerenciamento de Funcionários");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addGap(416)
					.addComponent(jLabel1)
					.addContainerGap(419, Short.MAX_VALUE))
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jLabel1)
					.addContainerGap())
		);
		jPanel1.setLayout(jPanel1Layout);

		ButaoVoltar.setText("  Voltar  ");
		ButaoVoltar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ButaoVoltarActionPerformed(evt);
			}
		});
		
		scrollPane = new JScrollPane();
		Estilizador.estilizandoScrollBarVertEHori(jScrollPane1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 1104, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(BotaoCadastrarFuncionarios))
						.addGroup(layout.createSequentialGroup()
							.addGap(37)
							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(TXTNomeFuncionarios)
										.addComponent(TXTCargoFuncionarios)
										.addComponent(TXTCPFFuncionarios)
										.addComponent(ButaoVoltar)
										.addComponent(TXTSenhaFuncionarios))
									.addGap(4)
									.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(CapturarTXTNomeFuncionarios)
										.addComponent(CapturarTXTCPFFuncionarios)
										.addComponent(comboBoxCargoFuncionario, 0, 221, Short.MAX_VALUE)
										.addComponent(CapturarTXTSenhaFuncionarios, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup()
									.addComponent(TXTTelefoneFuncionarios)
									.addGap(18)
									.addComponent(CapturarTXTTelefoneFuncionarios, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
					.addGap(180)
					.addComponent(BotaoEditarFuncionarios)
					.addGap(224)
					.addComponent(BotaoRemoverFuncionarios)
					.addGap(229))
				.addGroup(layout.createSequentialGroup()
					.addContainerGap(159, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 828, GroupLayout.PREFERRED_SIZE)
					.addGap(117))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(TXTNomeFuncionarios)
								.addComponent(CapturarTXTNomeFuncionarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(31)
							.addComponent(TXTCPFFuncionarios))
						.addComponent(CapturarTXTCPFFuncionarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(TXTCargoFuncionarios)
						.addComponent(comboBoxCargoFuncionario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(TXTTelefoneFuncionarios)
						.addComponent(CapturarTXTTelefoneFuncionarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(TXTSenhaFuncionarios)
						.addComponent(CapturarTXTSenhaFuncionarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(BotaoRemoverFuncionarios)
						.addComponent(BotaoEditarFuncionarios)
						.addComponent(BotaoCadastrarFuncionarios))
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(311)
							.addComponent(ButaoVoltar))
						.addGroup(layout.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		PlanilhaGerenciaFuncionarios = new javax.swing.JTable();
		scrollPane.setViewportView(PlanilhaGerenciaFuncionarios);
		
				PlanilhaGerenciaFuncionarios.setModel(
					new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null, null }, { null, null, null, null, null },
								{ null, null, null, null, null }, { null, null, null, null, null } },
						new String[] { "Nome", "CPF", "Cargo", "Telefone", "Senha" }
					) {	
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return false;
						}
				});
				Color corFundoCabelho =  new Color(2, 17, 28);
				Estilizador.estilizarTabela(PlanilhaGerenciaFuncionarios,corFundoCabelho);
				
		PlanilhaGerenciaFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				PlanilhaGerenciaFuncionariosMouseClicked(evt);
			}
		});
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void CapturarTXTNomeFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CapturarTXTNomeFuncionariosActionPerformed

	}// GEN-LAST:event_CapturarTXTNomeFuncionariosActionPerformed

// ===================== BOTÂO DE AÇÃO, CADASTRAR FUNCIONARIO ===================== //
//                    Responsáveis pelas operações CRUD (CRIAR)
// -------------------------------------------------------------------------------- //

	private void BotaoCadastrarFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BotaoCadastrarFuncionariosActionPerformed
		String nome = CapturarTXTNomeFuncionarios.getText();
		String cpf = CapturarTXTCPFFuncionarios.getText();
		CargoFuncionario cargo = (CargoFuncionario)comboBoxCargoFuncionario.getSelectedItem();
		String telefone = CapturarTXTTelefoneFuncionarios.getText();
		String senha = CapturarTXTSenhaFuncionarios.getText();

		if (!validarCampos(nome, cpf, cargo, telefone, senha)) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		
			if (bancoDeDados.tentarAdicionarFuncionario(nome, cpf, cargo, telefone, senha)) {
				JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
				limparTextos();
				atualizarTabela();
			} else {
				JOptionPane.showMessageDialog(this,
						"Erro ao cadastrar funcionário. Verifique se o CPF já existe ou se o dia está aberto.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		} 
	

// ===================== MEDODOS DE FUNCIONALIDADES GERAIS ===================== //
//              1- lIMPARA CAMPOS DE TEXTO;
//              2- METODO MAUSE CLICK
// -------------------------------------------------------------------------------//

	// 01 //
	public void limparTextos() {
		CapturarTXTNomeFuncionarios.setText("");
		CapturarTXTCPFFuncionarios.setText("");
		comboBoxCargoFuncionario.setSelectedIndex(-1);
		CapturarTXTTelefoneFuncionarios.setText("");
		CapturarTXTSenhaFuncionarios.setText("");
		BotaoCadastrarFuncionarios.setEnabled(true);
	}// GEN-LAST:event_BotaoCadastrarFuncionariosActionPerformed

	// 02 //
	private void PlanilhaGerenciaFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_PlanilhaGerenciaFuncionariosMouseClicked
		int row = PlanilhaGerenciaFuncionarios.getSelectedRow();
		if (row >= 0 && row < funcionariosLista.length) {
			indiceSelecionado = row;
			Funcionario f = funcionariosLista[row];

			CapturarTXTNomeFuncionarios.setText(f.getNome());
			CapturarTXTCPFFuncionarios.setText(f.getCpf());
			comboBoxCargoFuncionario.setSelectedItem(f.getCargo()); 
			CapturarTXTTelefoneFuncionarios.setText(f.getTelefone());
			CapturarTXTSenhaFuncionarios.setText(f.getSenha());

			BotaoCadastrarFuncionarios.setEnabled(false);
		}

	}// GEN-LAST:event_PlanilhaGerenciaFuncionariosMouseClicked

// ===================== BOTÂO DE AÇÃO, EDITAR FUNCIONARIO ===================== //
//                    Responsáveis pelas operações CRUD (EDITAR)
// -------------------------------------------------------------------------------- //

	private void BotaoEditarFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BotaoEditarFuncionariosActionPerformed
		BotaoCadastrarFuncionarios.setEnabled(false);
		BotaoRemoverFuncionarios.setEnabled(false);
		if (indiceSelecionado < 0) {
			JOptionPane.showMessageDialog(this, "Selecione um funcionário para editar.", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		String nome = CapturarTXTNomeFuncionarios.getText();
		String cpf = CapturarTXTCPFFuncionarios.getText();
		CargoFuncionario cargo = (CargoFuncionario) comboBoxCargoFuncionario.getSelectedItem();
		String telefone = CapturarTXTTelefoneFuncionarios.getText();
		String senha = CapturarTXTSenhaFuncionarios.getText();

		if (!validarCampos(nome, cpf, cargo, telefone, senha)) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

			String cpfOriginal = funcionariosLista[indiceSelecionado].getCpf();

			boolean sucesso = true;
			sucesso &= bancoDeDados.tentarAlterarNome(cpfOriginal, nome);
			sucesso &= bancoDeDados.tentarAlterarCargo(cpfOriginal, cargo);
			sucesso &= bancoDeDados.tentarAlterarTelefone(cpfOriginal, telefone);
			sucesso &= bancoDeDados.tentarAlterarSenha(cpfOriginal, senha);

			if (!cpf.equals(cpfOriginal)) {
				sucesso &= bancoDeDados.tentarAlterarCPF(cpfOriginal, cpf);
			}

			if (sucesso) {
				JOptionPane.showMessageDialog(this, "Funcionário atualizado com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
				limparTextos();
				atualizarTabela();
				indiceSelecionado = -1;
			} else {
				JOptionPane.showMessageDialog(this, "Erro ao atualizar funcionário. Verifique se o dia está aberto.",
						"Erro", JOptionPane.ERROR_MESSAGE);
			}

		BotaoCadastrarFuncionarios.setEnabled(true);
		BotaoRemoverFuncionarios.setEnabled(true);
	}// GEN-LAST:event_BotaoEditarFuncionariosActionPerformed

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

	private void BotaoRemoverFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BotaoRemoverFuncionariosActionPerformed
		if (indiceSelecionado < 0) {
			JOptionPane.showMessageDialog(this, "Selecione um funcionário para remover.", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Funcionario funcionario = funcionariosLista[indiceSelecionado];
		int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente remover o funcionário?\nNome: "
				+ funcionario.getNome() + "\nCPF: " + funcionario.getCpf(), "Confirmar Remoção",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			if (bancoDeDados.tentarRemoverFuncionarioPorCPF(funcionario.getCpf())) {
				JOptionPane.showMessageDialog(this, "Funcionário removido com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
				limparTextos();
				atualizarTabela();
				indiceSelecionado = -1;
			} else {
				JOptionPane.showMessageDialog(this, "Erro ao remover funcionário. Verifique se o dia está aberto.",
						"Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}// GEN-LAST:event_BotaoRemoverFuncionariosActionPerformed

	private void ButaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ButaoVoltarActionPerformed
		// Chamando a tela Gerente
		PaginaPrincipal.abrirPaginaPrincipal();
		dispose(); // Fecha a tela atual (VendasDeAlimentos)
	}// GEN-LAST:event_ButaoVoltarActionPerformed

// ===================== METODO RESPONSAVEL POR VALIDAÇÕE ===================== //
//   * Valida se todos os campos obrigatórios foram preenchidos corretamente*
// Verifica se algum campo obrigatório está vazio;
// -------------------------------------------------------------------------------//

	private boolean validarCampos(String nome, String cpf, CargoFuncionario cargo, String telefone, String senha) {
		if (nome.trim().isEmpty() || cpf.trim().isEmpty() || cargo==null || telefone.trim().isEmpty()
				|| senha.trim().isEmpty()) {
			return false;
		}

		if (!validarCPF(cpf)) {
			JOptionPane.showMessageDialog(this, "CPF inválido! Deve conter 11 dígitos.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!validarTelefone(telefone)) {
			JOptionPane.showMessageDialog(this, "Telefone inválido! Deve conter 10 ou 11 dígitos.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (senha.length() < 6) {
			JOptionPane.showMessageDialog(this, "Senha deve ter no mínimo 6 caracteres!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private boolean validarCPF(String cpf) {
		cpf = cpf.replaceAll("[^0-9]", "");
		return cpf.length() == 11;
	}

	private boolean validarTelefone(String telefone) {
		telefone = telefone.replaceAll("[^0-9]", "");
		return telefone.length() == 10 || telefone.length() == 11;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		throw new UnsupportedOperationException("Não pode iniciar nessa tela!");
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton BotaoCadastrarFuncionarios;
	private javax.swing.JButton BotaoEditarFuncionarios;
	private javax.swing.JButton BotaoRemoverFuncionarios;
	private javax.swing.JButton ButaoVoltar;
	private javax.swing.JTextField CapturarTXTCPFFuncionarios;
	private JComboBox<CargoFuncionario> comboBoxCargoFuncionario;
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
	private JScrollPane scrollPane;
}
