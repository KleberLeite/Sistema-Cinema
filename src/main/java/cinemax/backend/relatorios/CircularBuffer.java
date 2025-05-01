package cinemax.backend.relatorios;

import java.util.ArrayList;
import java.util.List;

public class CircularBuffer<T> {
	private final Object[] buffer;
	private int tamanho;
	
	public CircularBuffer(int length) {
		this.buffer = new Object[length];
	}
	
	public void push(T obj) {
		for(int i = buffer.length - 1; i > 0; i--) {
			buffer[i] = buffer[i-1];
		}		
		buffer[0] = obj;

		if(tamanho < buffer.length) {
			tamanho++;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		List<T> result = new ArrayList<T>(tamanho);
		for(int i = tamanho - 1; i >= 0; i--) {
			result.add((T)buffer[i]);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public T obterAtual() {
		return (T)buffer[0];
	}
}
