package cinemax.backend.salas;

import java.util.HashMap;
import java.util.Map;

import cinemax.utilities.ConversorDeCoordenadas;

public class BancoDeDadosSala implements IBancoDeDadosSala {	
	private Map<Integer, Sala> salas;
	
	public BancoDeDadosSala() {
		this.salas = new HashMap<Integer, Sala>();
		preencherSalas();
	}
	
	private TipoDeEstrutura[] gerarEstruturaDasSalas() {
		TipoDeEstrutura[] estruturaDasSalas = new TipoDeEstrutura[16 * 16];
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				int index = ConversorDeCoordenadas.obter1dPor2d(i, j, 16);
				
				if(i == 0 || i == 15) {
					estruturaDasSalas[index] = TipoDeEstrutura.Poltrona;
				} else if(i == 1 || i == 14) {
					estruturaDasSalas[index] = TipoDeEstrutura.Vazio;
				} else {
					if(j == 3 || j == 4 || j == 11 || j == 12) {
						estruturaDasSalas[index] = TipoDeEstrutura.Vazio;
					} else {
						estruturaDasSalas[index] = TipoDeEstrutura.Poltrona;
					}
				}
			}
		}
		
		int indexLocalCadeirante1 = ConversorDeCoordenadas.obter1dPor2d(13, 7, 16);
		int indexLocalCadeirante2 = ConversorDeCoordenadas.obter1dPor2d(13, 8, 16);
		estruturaDasSalas[indexLocalCadeirante1] = TipoDeEstrutura.LocalCadeirantes;
		estruturaDasSalas[indexLocalCadeirante2] = TipoDeEstrutura.LocalCadeirantes;
		
		int indexLocalObesos1 = ConversorDeCoordenadas.obter1dPor2d(0, 0, 16);
		int indexLocalObesos2 = ConversorDeCoordenadas.obter1dPor2d(0, 15, 16);
		estruturaDasSalas[indexLocalObesos1] = TipoDeEstrutura.PoltronaObesos;
		estruturaDasSalas[indexLocalObesos2] = TipoDeEstrutura.PoltronaObesos;
		
		/*System.out.println("Estrutura Criada: ");
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				int index = ConversorDeCoordenadas.obter1dPor2d(i, j, 16);
				TipoDeEstrutura e = estruturaDasSalas[index];
				//System.out.println(e);
				if(e == TipoDeEstrutura.Poltrona)
					System.out.print("P ");
				else if(e == TipoDeEstrutura.PoltronaObesos)
					System.out.print("O ");
				else if(e == TipoDeEstrutura.LocalCadeirantes)
					System.out.print("C ");
				else
					System.out.print("  ");
			}
			System.out.print("\n");
		}*/
		
		return estruturaDasSalas;
	}
	
	private void preencherSalas() {
		TipoDeEstrutura[] estrutura = gerarEstruturaDasSalas();
		for(int i = 0; i < 2; i++) {
			Sala sala = new Sala(
				i,
				16,
				16,
				new boolean[16 * 16],
				estrutura
			);
			salas.put(i, sala);	
		}
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
