package cinemax.frontend.gerenciamentofilmes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinemax.backend.core.Backend;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.Sessao;
import cinemax.backend.salas.Sala;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.estilizacao.Estilizador;
import cinemax.frontend.estilizacao.EstiloBotao;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

public class TelaEditarSessao extends JFrame {

	private static final long serialVersionUID = 1L;
	private ControladorDeApp app = ControladorDeApp.getInstancia();
	private JPanel contentPane;
	private JTextField textFieldMes;
	private JTextField textFieldDia;
	private JTextField textFieldHora;
	private JTextField textFieldMinuto;
	Sessao sessao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEditarSessao frame = new TelaEditarSessao(null, null);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setTitle("Cinemax");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Methods utils
	// -----------------------------------------------------------------------------

	private boolean atualizaSessao(int idSala, Sessao sessao, String diaTexto, String MesTexto, String HoraTexto,
			String MinutoTexto) {
		int anoNovo = LocalDateTime.now().getYear();
		int diaNovo;
		int mesNovo;
		int horaNovo;
		int minutoNovo;

		LocalDateTime novaData;

		if (validaNumero(diaTexto)) {
			diaNovo = Integer.parseInt(diaTexto);
		} else {
			JOptionPane.showMessageDialog(null, "Entrada inválida! Digite somente números", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (validaNumero(MesTexto)) {
			mesNovo = Integer.parseInt(MesTexto);
		} else {
			JOptionPane.showMessageDialog(null, "Entrada inválida! Digite somente números", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (validaNumero(HoraTexto)) {
			horaNovo = Integer.parseInt(HoraTexto);
		} else {
			JOptionPane.showMessageDialog(null, "Entrada inválida! Digite somente números", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (validaNumero(MinutoTexto)) {
			minutoNovo = Integer.parseInt(MinutoTexto);
		} else {
			JOptionPane.showMessageDialog(null, "Entrada inválida! Digite somente números", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			novaData = LocalDateTime.of(anoNovo, mesNovo, diaNovo, horaNovo, minutoNovo);
		} catch (DateTimeException e) {
			JOptionPane.showMessageDialog(null, "Entrada inválida! Digite uma data e/ou hora válida!", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		boolean alterarIn = app.getBackend().getBancoFilmes().tentarAlterarInicioSessao(sessao.getFilme().getId(), sessao.getId(), novaData);
		boolean alterarSala = app.getBackend().getBancoFilmes().tentarAlterarSalaSessao(sessao.getFilme().getId(), sessao.getId(), idSala);
		if (alterarIn && alterarSala) {
			JOptionPane.showMessageDialog(null, "Sessão atualizada com sucesso!");
			return true;
		}
		JOptionPane.showMessageDialog(null, "Data inválida! Por favor, coloque uma data válida!!", "Erro", JOptionPane.ERROR_MESSAGE);
		return false;
		
	}

	private boolean validaNumero(String textoNumero) {

		int numeroTexto = 0;
		try {
			numeroTexto = Integer.parseInt(textoNumero);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}

	}

	// -------------------------------------------------------------------------------------------

	/**
	 * Create the frame.
	 */
	public TelaEditarSessao(Sessao sessaoAtual, TelaManutencaoFilme telaManutencaoFilme) {
		if (sessaoAtual != null) {
			this.sessao = sessaoAtual;
		}
		Sessao sessao = this.sessao;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(2, 18, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		int dia = sessao.getInicio().getDayOfMonth();
		int mes = sessao.getInicio().getMonthValue();
		int hora = sessao.getInicio().getHour();
		int minuto = sessao.getInicio().getMinute();

		String diaAtual = Integer.toString(dia);
		String mesAtual = Integer.toString(mes);
		String horaAtual = Integer.toString(hora);
		String minutoAtual = Integer.toString(minuto);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 564, 239);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Editar Sessão:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 379, 44);
		panel.add(lblNewLabel);

		textFieldDia = new JTextField(diaAtual);
		textFieldDia.setBounds(24, 102, 86, 20);
		panel.add(textFieldDia);
		textFieldDia.setColumns(10);

		textFieldMes = new JTextField(mesAtual);
		textFieldMes.setBounds(132, 102, 86, 20);
		panel.add(textFieldMes);
		textFieldMes.setColumns(10);

		textFieldHora = new JTextField(horaAtual);
		textFieldHora.setBounds(238, 102, 86, 20);
		panel.add(textFieldHora);
		textFieldHora.setColumns(10);

		textFieldMinuto = new JTextField(minutoAtual);
		textFieldMinuto.setBounds(344, 102, 86, 20);
		panel.add(textFieldMinuto);
		textFieldMinuto.setColumns(10);

		JLabel lblMes = new JLabel("Mês:");
		lblMes.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMes.setBounds(132, 83, 46, 14);
		panel.add(lblMes);

		JLabel lblDia = new JLabel("Dia:");
		lblDia.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDia.setBounds(24, 83, 46, 14);
		panel.add(lblDia);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHora.setBounds(238, 83, 46, 14);
		panel.add(lblHora);

		JLabel lblMinuto = new JLabel("Minuto:");
		lblMinuto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMinuto.setBounds(344, 83, 64, 14);
		panel.add(lblMinuto);
		
		JComboBox comboBoxSala = new JComboBox();
		Estilizador.estilizarComboBoxClassificacaoIndicativa(comboBoxSala);
		int i = 0;
		for(Sala sala : app.getBackend().getBancoSalas().obterTodasSalas()) {
			comboBoxSala.addItem(sala.getIdSala());
			if(sessao.getSala().getIdSala() == sala.getIdSala()) {
				comboBoxSala.setSelectedIndex(i);
			}
			i++;
		}
		comboBoxSala.setBounds(456, 101, 59, 22);
		
		panel.add(comboBoxSala);
		
		JLabel lblSala = new JLabel("Salas:");
		lblSala.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSala.setBounds(456, 83, 46, 14);
		panel.add(lblSala);

		JButton btnAtualizar = new JButton("Atualizar");
		Estilizador.aplicarEstiloBotao(btnAtualizar, EstiloBotao.PADRAO_ESCURECIDO);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String diaTexto = textFieldDia.getText();
				String MesTexto = textFieldMes.getText();
				String HoraTexto = textFieldHora.getText();
				String MinutoTexto = textFieldMinuto.getText();
				int idSala = (int) comboBoxSala.getSelectedItem();

				if (atualizaSessao(idSala,sessao, diaTexto, MesTexto, HoraTexto, MinutoTexto)) {
					
					telaManutencaoFilme.atualizarListaDeSessoesPosEdicaoOuAdicao();
					dispose();
				}

			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtualizar.setBounds(207, 168, 161, 35);
		panel.add(btnAtualizar);
	}
}
