package cinemax.salas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoDeDadosSala implements IBancoDeDadosSala {
	private Map<Integer, ISala> salas;
	
	public BancoDeDadosSala() {
		this.salas = new HashMap<Integer, ISala>();
	}
	
	@Override
	public DadosSala[] obterTodasSalas() {
		List<DadosSala> todasSalas = new ArrayList<DadosSala>();
		
		for(ISala sala : salas.values()) {
			todasSalas.add(sala.obterCopiaDados());
		}
		
		return todasSalas.toArray(new DadosSala[todasSalas.size()]);
	}

	@Override
	public DadosSala obterSalaPorId(int id) {
		if(!salas.containsKey(id)) {
			return null;
		}
		return salas.get(id).obterCopiaDados();
	}

	@Override
	public boolean tentarBloquearLocal(int idSala, int linha, int coluna) {
		if(!salas.containsKey(idSala)) {
			return false;
		}
		
		ISala sala = salas.get(idSala);
		return sala.tentarBloquearLocal(linha, coluna);
	}

	@Override
	public boolean tentarDesbloquearLocal(int idSala, int linha, int coluna) {
		if(!salas.containsKey(idSala)) {
			return false;
		}
		
		ISala sala = salas.get(idSala);
		return sala.tentarDesbloquearLocal(linha, coluna);
	}
}
