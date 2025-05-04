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
			new Gerente().setVisible(true);
			break;
		case Atendente:
			new Funcionarios().setVisible(true);
			break;
		}
	}
	
	public static void main(String[] args) {
		abrirPaginaPrincipal();
	}
}
