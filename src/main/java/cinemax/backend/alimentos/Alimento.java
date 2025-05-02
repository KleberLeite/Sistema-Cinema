package cinemax.backend.alimentos;

/*
Resumo: Estrutura de dados para alimentos do cinema.
*/
public class Alimento {
	private String nome;
	private double preco;
	private int codigo;
	
	public Alimento(String nome, double preco, int codigo) {
		this.nome = nome;
		this.preco = preco;
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	protected void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getPreco() {
		return preco;
	}
	
	protected void setPreco(double preco) {
		this.preco = preco;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	protected void setcodigo(int codigo) {
		this.codigo = codigo;
	}
}
