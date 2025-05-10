package cinemax.frontend.PaginasGeranteeFuncionario;

import cinemax.backend.funcionarios.CargoFuncionario;
import cinemax.frontend.controller.ControladorDeApp;

public class PaginaPrincipal {
	public static void abrirPaginaPrincipal() {
		ControladorDeApp app = ControladorDeApp.getInstancia();
		CargoFuncionario cargo = app.getCargo();

		// APENAS P/ DESENVOLVIMENTO
		if (cargo == null) {
			cargo = CargoFuncionario.Administrador;
			app.onLogin(cargo);
		}
		
		switch(cargo) {
		case Administrador:
		case Gerente:
			TelaGerente telaGerente = new TelaGerente();
			telaGerente.setVisible(true);
			telaGerente.setLocationRelativeTo(null);
			break;
		case Atendente:
			new TelaFuncionario().setVisible(true);
			break;
		}
	}
	
	public static void main(String[] args) {
		abrirPaginaPrincipal();
	}
}
