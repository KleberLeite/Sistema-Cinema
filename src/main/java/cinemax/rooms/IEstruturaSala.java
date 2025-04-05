package cinemax.rooms;

public interface IEstruturaSala {
	public boolean tentarBloquearLocal(byte linha, byte coluna);
	public boolean tentarDesbloquearLocal(byte linha, byte coluna);
	public int obterIdSala();
}
