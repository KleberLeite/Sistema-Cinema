package cinemax.consoleFrontend.vendaDeAlimentos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cinemax.backend.alimentos.*;
import cinemax.consoleFrontend.PaginaBase;

public class PaginaCarrinhoAlimentos extends PaginaBase {
	private class PedidoAlimento {
		public int quantidade;
		public final Alimento alimento;
		
		public PedidoAlimento(Alimento alimento, int quantidade) {
			this.alimento = alimento;
			this.quantidade = quantidade;
		}
		
		@Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	        	return true;
	        if (!(obj instanceof PedidoAlimento))
	        	return false;
	        
	        PedidoAlimento outro = (PedidoAlimento)obj;
	        return alimento.getCodigo() == outro.alimento.getCodigo();
	    }

	    @Override
	    public int hashCode() {
	        return alimento.getCodigo();
	    }
	}
	
	private IBancoDeDadosAlimento bancoDeDadosAlimentos;
	private List<PedidoAlimento> pedidos;
	
	public PaginaCarrinhoAlimentos(IBancoDeDadosAlimento bancoDeDadosAlimentos) {
		this.bancoDeDadosAlimentos = bancoDeDadosAlimentos;
	}
	
	@Override
	public void abrir() {
		pedidos = new ArrayList<PedidoAlimento>();
		menuPrincipal();
	}
	
	private void menuPrincipal() {
		Scanner sc = super.getScanner();
		while(true) {
			super.limparConsole();
			System.out.println("----- Carrinho de Alimentos -----");
			
			printarAlimentosNoCarrinho();
			System.out.print("\n");
			
			System.out.println(
				"Opções:\n" +
				"1. Adicionar Alimento\n" +
				"2. BuscarAlimento\n" +
				"3. Remover Alimento\n" +
				"4. Concluir pedido\n" +
				"5. Cancelar"
			);
			int opcao = sc.nextInt();
			switch(opcao) {
			case 1:
				aoSelecionarAdicionarAlimento();
				break;
			case 2:
				aoSelecionarBuscarAlimento();
				break;
			case 3:
				aoSelecionarRemoverAlimento();
				break;
			case 4:
				if(aoSelecionarConcluirPedido()) {
					return;
				}
				break;
			case 5:
				return;
			}
		}		
	}
	
	private void aoSelecionarAdicionarAlimento() {
		Scanner sc = super.getScanner();
		
		while(true) {
			super.limparConsole();
			System.out.print(
				"----- Carrinho de Alimentos -----\n\n" +
				"Insira o Código (ou -1 para cancelar): "
			);
			int codigo = sc.nextInt();
			if(codigo == -1) {
				return;
			}
			if(!bancoDeDadosAlimentos.existeAlimentoComCodigo(codigo)) {
				System.out.println("Não encontrado!");
				if(desejaSair("Deseja adicionar outro alimento?")) {
					return;
				} else {
					continue;
				}
			}
			Alimento alimento = bancoDeDadosAlimentos.obterAlimentoPorCodigo(codigo);
			System.out.println("Alimento Selecionado: " + alimento.getNome());

			System.out.print("Insira a Quantidade (ou -1 para cancelar): ");
			int quantidade = sc.nextInt(); 
			if(quantidade == -1) {
				return;
			}
			if(quantidade <= 0) {
				System.out.println("Entrada inválida!");
				if(desejaSair("Deseja adicionar outro alimento?")) {
					return;
				} else {
					continue;
				}
			}

			PedidoAlimento pedido = new PedidoAlimento(alimento, quantidade);
			pedidos.add(pedido);
			System.out.println("Pedido adicionado com sucesso!");
			
			if(desejaSair("Deseja adicionar outro alimento?")) {
				return;
			}
		}
	}
	
	private void aoSelecionarBuscarAlimento() {
		Scanner sc = super.getScanner();
		
		while(true) {
			super.limparConsole();
			System.out.println(
				"----- Carrinho de Alimentos -----\n\n" +
				"Insira o nome (ou -1 para listar todos): "
			);
			
			String nome = sc.nextLine().trim();
			if(nome.equals("")) {
				continue;
			}
			
			if(nome.equals("-1")) {
				nome = "";
			}
			
			Alimento[] alimentos = bancoDeDadosAlimentos.obterAlimentoPorNome(nome);
			if(alimentos.length == 0) {
				System.out.println("\n\nNenhum alimento encontrado!");
			} else {
				System.out.println("Alimentos encontrados:");
				printarAlimentos(alimentos);
				System.out.print("\n");
			}
			
			if(desejaSair("Deseja buscar novamente?")) {
				return;
			}
		}
	}
	
	private void aoSelecionarRemoverAlimento() {
		Scanner sc = super.getScanner();
		
		while(true) {
			super.limparConsole();
			System.out.println("----- Carrinho de Alimentos -----");
			printarAlimentosNoCarrinho();
			System.out.print("\n");
			
			System.out.print("Insira o código (ou -1 para cancelar): ");
			int codigo = sc.nextInt();
			if(codigo == -1) {
				return;
			}
			
			PedidoAlimento pedido = obterPedidoPorCodigoAlimento(codigo);
			if(pedido == null) {
				System.out.println("Não encontrado!");
				if(desejaSair("Deseja remover outro alimento?")) {
					return;
				} else {
					continue;
				}
			}
			
			System.out.println("Insira a quantidade (0 para tudo, -1 para cancelar): ");
			int quantidade = sc.nextInt();
			if(quantidade == -1) {
				return;
			} else if(quantidade < 0) {
				System.out.println("Entrada inválida!");
				continue;
			}
			
			if(quantidade == 0 || pedido.quantidade - quantidade <= 0) {
				pedidos.remove(pedido);
			} else {
				pedido.quantidade -= quantidade;
			}			
			System.out.println("Removido com sucesso!");
			
			if(desejaSair("Deseja remover outro alimento?")) {
				return;
			}
		}		
	}
	
	private boolean aoSelecionarConcluirPedido() {
		System.out.println("Deseja realmente finalizar o pedido? Digite S para confirmar, ou qualquer outro caractere para sair.");
		String opcao = super.getScanner().next();
		return opcao.toLowerCase().trim().equals("s");
	}
	
	private void printarAlimentosNoCarrinho() {
		if(pedidos.size() == 0) {
			System.out.println("Não há alimentos no carrinho!");
			return;
		}
			
		System.out.println("Carrinho:");
		for(int i = 0; i < pedidos.size(); i++) {
			PedidoAlimento pedido = pedidos.get(i);
			System.out.println(
				(i + 1) +
				":  " +
				pedido.quantidade + 
				"un | " +
				pedido.alimento.getCodigo() +
				" | " +
				pedido.alimento.getNome()
			);
		}
	}
	
	private void printarAlimentos(Alimento[] alimentos) {
		for(int i = 0; i < alimentos.length; i++) {
			System.out.println(alimentos[i].getCodigo() + " | " + alimentos[i].getNome());
		}
	}
	
	private boolean desejaSair(String mensagem) {
		System.out.println(mensagem + " Digite 'S' (ou 's'), ou qualquer outro caractere para sair.");
		String opcao = super.getScanner().next();
		return !opcao.toLowerCase().trim().equals("s");
	}
	
	private PedidoAlimento obterPedidoPorCodigoAlimento(int codigo) {
		return pedidos.stream()
			.filter(p -> p.alimento.getCodigo() == codigo)
			.findFirst()
			.orElse(null);
	}

	@Override
	public void reabrir() {
		menuPrincipal();
	}	
}
