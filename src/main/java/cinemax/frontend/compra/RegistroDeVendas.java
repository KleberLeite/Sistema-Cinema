package cinemax.frontend.compra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cinemax.backend.alimentos.Alimento;
import cinemax.backend.filmes.Filme;

public class RegistroDeVendas {
	
	private double valorTotal;
	private List<VendasFilme> listaVendasFilme = new ArrayList<>();
	private Map<Alimento, Integer> mapVendasAlimento = new HashMap<>();

	
	
	public double getValorTotal() {
		return valorTotal;
	}
	
	/*Metodo responsável por registrar a venda do filme na lista e somar no valor total
	 * @param: Filme, int, int;
	 * return: void
	 * */
	public void registrarVendaFilme(Filme filme, Carrinho carrinho) {
		for (VendasFilme venda : listaVendasFilme) {
			if (venda.getFilme().equals(filme)) {
				venda.adicionarIngressos(carrinho.qtdeDeInteiras(), carrinho.qtdeDeMeias());
				this.valorTotal += carrinho.totalCompraFilme();
				return;
			};
		}

		VendasFilme venda = new VendasFilme(filme, carrinho.qtdeDeInteiras(), carrinho.qtdeDeMeias());
		listaVendasFilme.add(venda);
		this.valorTotal += carrinho.totalCompraFilme();

	}

	/*Metodo responsável por exibir o relatorio Geral de compras
	 * @param: double, double;
	 * return: void
	 * */
	public void exibirRelatorio() {
		System.out.println("Vendas Filmes --------------------------------------------");
		for (VendasFilme vendasFilme : listaVendasFilme) {
			System.out.println(vendasFilme + " | Total R$: " 
		+ vendasFilme.calcularTotal(Ingresso.getPrecoIngresso(), Ingresso.getPrecoIngresso()/2));
		}

	}

	private List<VendasFilme> getListaVendasFilme() {
		return listaVendasFilme;
	}

	public Map<Alimento, Integer> getMapVendasAlimento() {
		return mapVendasAlimento;
	}



	


}
