package cinemax.backend.alimentos;

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
	
	public double getPreco() {
		return preco;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	protected void setNome(String nome) {
		this.nome = nome;
	}
	
	protected void setPreco(double preco) {
		this.preco = preco;
	}
	
	protected void setcodigo(int codigo) {
		this.codigo = codigo;
	}
}
