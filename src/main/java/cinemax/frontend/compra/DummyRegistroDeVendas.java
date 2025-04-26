package cinemax.frontend.compra;

import java.util.ArrayList;
import java.util.List;

import cinemax.backend.alimentos.Alimento;
import cinemax.backend.filmes.Filme;

public class DummyRegistroDeVendas {
	
	private double valorTotal;
	private List<VendasFilme> listaVendasFilme = new ArrayList<>();
	private List<VendasAlimento> listaVendasAlimento = new ArrayList<>();

	
	
	public double getValorTotal() {
		return valorTotal;
	}
	
	/*Metodo responsável por registrar a venda do filme na lista e somar no valor total
	 * @param: Filme, int, int;
	 * return: void
	 * */
	public void registrarVendaFilme(Filme filme, int ingressosInteiros, int ingressosMeios) {
		for (VendasFilme venda : listaVendasFilme) {
			if (venda.getFilme().equals(filme)) {
				venda.adicionarIngressos(ingressosInteiros, ingressosMeios);
				this.valorTotal=venda.calcularTotal(ingressosInteiros, ingressosMeios);
				return;
			};
		}

		VendasFilme venda = new VendasFilme(filme, ingressosInteiros, ingressosMeios);
		listaVendasFilme.add(venda);
		this.valorTotal=venda.calcularTotal(ingressosInteiros, ingressosMeios);
	}
	
	/*Metodo responsável por registrar a venda do alimento na lista e somar no valor total
	 * @param: Ailmento, int, int;
	 * return: void
	 * */
	public void registrarVendaAlimento(Alimento alimento, double preco,int quantidade) {
		for (VendasAlimento venda : listaVendasAlimento) {
			if (venda.getAlimento().equals(alimento)) {
				venda.adicionarAlimentos(quantidade);
				return;
			}
		}
		// Se não encontrou, adiciona novo
		VendasAlimento venda = new VendasAlimento(alimento, preco,quantidade);
		listaVendasAlimento.add(venda);
		this.valorTotal=venda.calcularTotal();
	}

	/*Metodo responsável por exibir o relatorio Geral de compras
	 * @param: double, double;
	 * return: void
	 * */
	public void exibirRelatorio(double precoInteiro, double precoMeia) {
		System.out.println("Vendas Filmes --------------------------------------------");
		for (VendasFilme vendasFilme : listaVendasFilme) {
			System.out.println(vendasFilme + " | Total R$: " + vendasFilme.calcularTotal(precoInteiro, precoMeia));
		}
		
		System.out.println("Vendas Alimentos --------------------------------------------");
		
		for (VendasAlimento vendasAlimento : listaVendasAlimento) {
			System.out.println(vendasAlimento + " | Total R$: " + vendasAlimento.calcularTotal());
		}
	}

	private List<VendasFilme> getListaVendasFilme() {
		return listaVendasFilme;
	}

	private List<VendasAlimento> getListaVendasAlimento() {
		return listaVendasAlimento;
	}

	


}
