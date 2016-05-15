
public class Dvd extends Artigo{
	private String realizador;
	private String genero;

	public Dvd(String titulo, int ano, String realizador, String genero)
	{
		super(titulo,ano);
		this.realizador = realizador;
		this.genero = genero;
	}

	/** 
	 * @return the realizador
	 */
	public String getRealizador() {
		return realizador;
	}

	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * Imprime as caraterisitcas do CD
	 */
	public String toString(){
		return"Id: " + getIdArtigo() + "\n" + "Titulo:" + getTitulo() + "\n" + "Ano: " + getAno() + "\n" + "Realizador: " + realizador +
				"\n" + "Género: " + genero + "\n";
	}

}
