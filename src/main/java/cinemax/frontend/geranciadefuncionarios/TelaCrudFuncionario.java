
package cinemax.frontend.geranciadefuncionarios;

import cinemax.frontend.PaginasGeranteeFuncionario.PaginaPrincipal;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.backend.funcionarios.CargoFuncionario;
import cinemax.backend.funcionarios.Funcionario;
import cinemax.backend.funcionarios.IBancoDeDadosFuncionario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
		ButaoVoltar = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Funcionários");

		PlanilhaGerenciaFuncionarios.setModel(
			new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, { null, null, null, null, null } },
				new String[] { "Nome", "CPF", "Cargo", "Teléfone", "Senha" }
			) {	
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return false;
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
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(280, 280, 280).addComponent(jLabel1)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addGap(0, 11, Short.MAX_VALUE).addComponent(jLabel1)));

		ButaoVoltar.setText("Voltar");
		ButaoVoltar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ButaoVoltarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup().addGap(37, 37, 37).addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
										.addComponent(TXTNomeFuncionarios).addComponent(TXTCPFFuncionarios)
										.addComponent(TXTCargoFuncionarios))
								.addGap(34, 34, 34)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(CapturarTXTNomeFuncionarios)
										.addComponent(CapturarTXTCPFFuncionarios)
										.addComponent(CapturarTXTCargoFuncionarios,
												javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))))
						.addGap(0, 475, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(161, 161, 161)
										.addComponent(BotaoCadastrarFuncionarios).addGap(128, 128, 128)
										.addComponent(BotaoEditarFuncionarios).addGap(122, 122, 122)
										.addComponent(BotaoRemoverFuncionarios))
								.addGroup(layout.createSequentialGroup().addGap(22, 22, 22).addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE, 753,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE).addComponent(ButaoVoltar).addGap(38, 38, 38)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(29, 29, 29)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(layout
								.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(TXTNomeFuncionarios).addComponent(CapturarTXTNomeFuncionarios,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(31, 31, 31).addComponent(TXTCPFFuncionarios))
								.addComponent(CapturarTXTCPFFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(TXTCargoFuncionarios)
								.addComponent(CapturarTXTCargoFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(22, 22, 22)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(TXTTelefoneFuncionarios)
								.addComponent(CapturarTXTTelefoneFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(23, 23, 23)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(TXTSenhaFuncionarios)
								.addComponent(CapturarTXTSenhaFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(22, 22, 22)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(BotaoCadastrarFuncionarios).addComponent(BotaoEditarFuncionarios)
								.addComponent(BotaoRemoverFuncionarios))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(ButaoVoltar)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

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
		String cargoStr = CapturarTXTCargoFuncionarios.getText();
		String telefone = CapturarTXTTelefoneFuncionarios.getText();
		String senha = CapturarTXTSenhaFuncionarios.getText();

		if (!validarCampos(nome, cpf, cargoStr, telefone, senha)) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			CargoFuncionario cargo = CargoFuncionario.valueOf(cargoStr);
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
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "Cargo inválido! Valores aceitos: Administrador, Gerente, Atendente",
					"Erro", JOptionPane.ERROR_MESSAGE);
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
		CapturarTXTCargoFuncionarios.setText("");
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
			CapturarTXTCargoFuncionarios.setText(f.getCargo().toString());
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
		String cargoStr = CapturarTXTCargoFuncionarios.getText();
		String telefone = CapturarTXTTelefoneFuncionarios.getText();
		String senha = CapturarTXTSenhaFuncionarios.getText();

		if (!validarCampos(nome, cpf, cargoStr, telefone, senha)) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			CargoFuncionario cargo = CargoFuncionario.valueOf(cargoStr);
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
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "Cargo inválido! Valores aceitos: Administrador, Gerente, Atendente",
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

	private boolean validarCampos(String nome, String cpf, String cargoStr, String telefone, String senha) {
		if (nome.trim().isEmpty() || cpf.trim().isEmpty() || cargoStr.trim().isEmpty() || telefone.trim().isEmpty()
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

		try {
			CargoFuncionario.valueOf(cargoStr);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "Cargo inválido! Valores aceitos: Administrador, Gerente, Atendente",
					"Erro", JOptionPane.ERROR_MESSAGE);
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
