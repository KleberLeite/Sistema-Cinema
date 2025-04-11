package cinemax.backend.salas;

import java.util.HashMap;
import java.util.Map;

public class BancoDeDadosSala implements IBancoDeDadosSala {
	private Map<Integer, Sala> salas;
	
	public BancoDeDadosSala() {
		this.salas = new HashMap<Integer, Sala>();
	}
	
	@Override
	public Sala[] obterTodasSalas() {		
		return salas.values().toArray(new Sala[salas.size()]);
	}

	@Override
	public Sala obterSalaPorId(int id) {
		if(!salas.containsKey(id)) {
			return null;
		}
		return salas.get(id);
	}

	@Override
	public boolean tentarBloquearLocal(int idSala, int linha, int coluna) {
		if(!salas.containsKey(idSala)) {
			return false;
		}
		
		Sala sala = salas.get(idSala);
		return sala.tentarBloquearLocal(linha, coluna);
	}

	@Override
	public boolean tentarDesbloquearLocal(int idSala, int linha, int coluna) {
		if(!salas.containsKey(idSala)) {
			return false;
		}
		
		Sala sala = salas.get(idSala);
		return sala.tentarDesbloquearLocal(linha, coluna);
	}
}
