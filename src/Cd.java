
public class Cd extends Artigo{
	private String interprete;
	private String estilo;
	
	/**
	 * Construtor da classe Cd
	 * @param titulo
	 * @param ano
	 * @param interprete
	 * @param estilo
	 */
	public Cd(String titulo, int ano,String interprete, String estilo){
		super(titulo,ano);
		this.interprete = interprete;
		this.estilo = estilo;
	}
	

	/**
	 * M�todo que retorna o estilo do CD
	 * @return estilo
	 */
	public String getEstilo(){
		return estilo;
	}
	
	/**
	 * M�todo que retorna o in�rprete do CD
	 * @return interprete
	 */
	public String getInterprete(){
		return interprete;
	}
		
	
	/**
	 * Imprime as caraterisitcas do CD
	 * @Override
	 */
	public String toString(){
		return"Id: " + getIdArtigo() + "\n" + "Titulo:" + getTitulo() + "\n" + "Ano: " + getAno() + "\n" + "Interprete: " + interprete +
				"\n" + "Estilo: " + estilo + "\n" + "Estado Requisi��o: " + getRequisitado() +"\n";
	}
}
