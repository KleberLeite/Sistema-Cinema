package cinemax.frontend.controller;

import cinemax.backend.core.Backend;
import cinemax.backend.funcionarios.CargoFuncionario;

public class ControladorDeApp {
	private static ControladorDeApp instancia;
    private Backend backend;
    private CargoFuncionario cargoLogin = null;

    private ControladorDeApp() {
        this.backend = Backend.dummy(); 
    }

    public static ControladorDeApp getInstancia() {
        if (instancia == null) {
            instancia = new ControladorDeApp();
        }
        return instancia;
    }

    public Backend getBackend() {
        return backend;
    }
    
    public void onLogin(CargoFuncionario cargo) {
    	this.cargoLogin = cargo;
    }
    
    public void onLogout() {
    	this.cargoLogin = null;
    }
    
    public CargoFuncionario getCargo() {
    	return cargoLogin;
    }
}
