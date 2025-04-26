package cinemax.frontend.controller;

import cinemax.backend.core.Backend;

public class ControladorDeApp {

	 private static ControladorDeApp instancia;

	    private Backend backend;


	    private ControladorDeApp() {
	        this.backend = Backend.dummy(); // ou 
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
	
}
