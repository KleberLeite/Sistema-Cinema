package cinemax.frontend.gerenciamentofilmes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinemax.backend.core.Backend;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.Sessao;
import cinemax.frontend.controller.ControladorDeApp;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
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
	Sessao sessao = app.getBackend().getBancoFilmes().obterFilmePorId(0).obterSessao(0);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEditarSessao frame = new TelaEditarSessao(null);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Methods utils
	// -----------------------------------------------------------------------------

	private boolean atualizaSesao(Sessao sessao, String diaTexto, String MesTexto, String HoraTexto, String MinutoTexto) {
		int anoNovo = LocalDateTime.now().getYear();
		int diaNovo;
		int mesNovo;
		int horaNovo;
		int minutoNovo; 
		
		LocalDateTime novaData;
		
		if(validaNumero(diaTexto)) {
			diaNovo = Integer.parseInt(diaTexto);
		}else {
			JOptionPane.showMessageDialog(null, "Entrada inválida! Digite somente números", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(validaNumero(MesTexto)) {
			mesNovo = Integer.parseInt(MesTexto);
		}else {
			JOptionPane.showMessageDialog(null, "Entrada inválida! Digite somente números", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(validaNumero(HoraTexto)) {
			horaNovo = Integer.parseInt(HoraTexto);
		}else {
			JOptionPane.showMessageDialog(null, "Entrada inválida! Digite somente números", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(validaNumero(MinutoTexto)) {
			minutoNovo = Integer.parseInt(MinutoTexto);
		}else {
			JOptionPane.showMessageDialog(null, "Entrada inválida! Digite somente números", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		
		try {
			novaData = LocalDateTime.of(anoNovo, mesNovo, diaNovo, horaNovo, minutoNovo);
			JOptionPane.showMessageDialog(null, "Sessão atualizada com sucesso!");
        } catch (DateTimeException e) {
        	JOptionPane.showMessageDialog(null, "Entrada inválida! Digite uma data e/ou hora válida!", "Erro",
					JOptionPane.ERROR_MESSAGE);
        	return false;
        }
		
		//ToDo: @Kleber tentarAdicionarDataSessao(int idSessao, int idFilme, LocalDateTime)
		app.getBackend().getBancoFilmes().tentarAdicionarSessao(sessao.getId(), sessao.getFilme().getId(), novaData);
		return true;
		
		
			
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
	public TelaEditarSessao(Sessao sessaoAtual) {
		if (sessaoAtual != null) {
			this.sessao = sessaoAtual;
		}
		Sessao sessao = this.sessao;
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		int dia = sessao.getInicio().getDayOfMonth();
		int mes = sessao.getInicio().getMonthValue(); // 1 a 12
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
		textFieldDia.setBounds(183, 102, 86, 20);
		panel.add(textFieldDia);
		textFieldDia.setColumns(10);

		textFieldMes = new JTextField(mesAtual);
		textFieldMes.setBounds(65, 102, 86, 20);
		panel.add(textFieldMes);
		textFieldMes.setColumns(10);

		textFieldHora = new JTextField(horaAtual);
		textFieldHora.setBounds(298, 102, 86, 20);
		panel.add(textFieldHora);
		textFieldHora.setColumns(10);

		textFieldMinuto = new JTextField(minutoAtual);
		textFieldMinuto.setBounds(414, 102, 86, 20);
		panel.add(textFieldMinuto);
		textFieldMinuto.setColumns(10);

		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(65, 83, 46, 14);
		panel.add(lblMes);

		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(183, 83, 46, 14);
		panel.add(lblDia);

		JLabel lblNewLabel_1_1_1 = new JLabel("Hora");
		lblNewLabel_1_1_1.setBounds(298, 83, 46, 14);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Minuto");
		lblNewLabel_1_1_2.setBounds(414, 83, 46, 14);
		panel.add(lblNewLabel_1_1_2);

		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String diaTexto = textFieldDia.getText();
				String MesTexto = textFieldMes.getText();
				String HoraTexto = textFieldHora.getText();
				String MinutoTexto = textFieldMinuto.getText();
				
				if(atualizaSesao(sessao,diaTexto, MesTexto, HoraTexto, MinutoTexto)) {
					TelaEditarFilme telaEditarFilme = new TelaEditarFilme(app.getBackend().getBancoFilmes().obterFilmePorId(sessao.getFilme().getId()));
					telaEditarFilme.setLocationRelativeTo(null);
					telaEditarFilme.setVisible(true);
					
					dispose();
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(207, 168, 161, 35);
		panel.add(btnNewButton);
	}
}
