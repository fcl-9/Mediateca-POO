import java.time.LocalDateTime;
import java.util.ArrayList;

public class Artigo {
	private int idArtigo;
	private static int seguinte;
	private String titulo;
	private int ano;
	private int avalCriticos;
	private ArrayList<Integer> avalConsumidores;
	private LocalDateTime dataDevolucao;
	private LocalDateTime dataRequisicao;
	private double mediaAvalConsumidores;
	private boolean avalCriticosDone;
	private boolean requisitado;
	private boolean atrasado;
	
	/**
	 * Cosntrutor da cLasse artigo
	 * @param titulo do artigo
	 * @param ano do artigo
	 */
	public Artigo(String titulo, int ano)
	{
		this.titulo = titulo;
		this.ano = ano;
		requisitado = false;
		this.avalConsumidores = new ArrayList<Integer>();
		avalCriticosDone = false;
		mediaAvalConsumidores = 0;
		seguinte++;
		idArtigo = seguinte;
		atrasado = false;
		
	}
	
	/**
	 * Metodo que retorna  o id do artigo
	 * @return idArtigo do artigo
	 */
	public int getIdArtigo(){
		return idArtigo;
	}
	
	/**
	 * Metodo que retorna  o titulo do artigo
	 * @return titilo do artigo
	 */
	public String getTitulo(){
		return titulo;
	}
	
	/**
	 * Método que retorna o ano doa rtigo
	 * @return ano do artigo
	 */
	public int getAno(){
		return ano;
	}

	/**
	 * Método setter que permite alterar o valor da variavel que verifica se um artigo se encontra ou não requisitado
	 * @param requisitado
	 */
	public void setRequisitado(boolean requisitado)
	{
		this.requisitado = requisitado;
	}
	
	/**
	 * Método getter que permite aceder ao valor da variavel que diz se o item foi ou não criado
	 * @return
	 */
	public boolean getRequisitado()
	{
		return requisitado ;
	}
	
	/**
	 * @return a avaliacao dos criticos
	 */
	public int getAvalCriticos() {
		return avalCriticos;
	}
	
	/**
	 * Atribui uma avaliação dos criticos ao artigo
	 * @param avalCriticos
	 */
	public void setAvalCriticos(int avalCriticos) {
		this.avalCriticos = avalCriticos;
	}
	
	/**
	 * 
	 * Método que retorna o array de avaliação dada pelos consumidores
	 * @return a avaliacao dos consumidores
	 */
	public ArrayList<Integer> getAvalConsumidores() {
		return avalConsumidores;
	}
	

	/**
	 * Método que a variavel que verifica se os criticos já atribuiram classificação
	 * @return the avalCriticosDone
	 */
	public boolean getAvalCriticosDone() {
		return avalCriticosDone;
	}

	/**
	 * Método setter que altera a val booleana que verifica se os criticos fizeram ou não avaliação
	 * @param avalCriticosDone the avalCriticosDone to set
	 */
	public void setAvalCriticosDone(boolean avalCriticosDone) {
		this.avalCriticosDone = avalCriticosDone;
	}
	
	/**
	 * Método que calcula a média de avaliações dos consumidores
	 * @return média de avaliacoes dos consumidores
	 */
	public void calculaAvalConsumidores(){
		int soma = 0;
		for(int i = 0; i < avalConsumidores.size(); i++){
			soma = soma + avalConsumidores.get(i);
		}
		mediaAvalConsumidores = soma/avalConsumidores.size();
	}
	/**
	 * Retorna a media das classificações da avaliação de consumidores
	 * @return
	 */
	public int getMediaAval()
	{
		return (int)mediaAvalConsumidores;
	}
	/**
	 * Método que permite atribuir uma data de devolucao ao CD
	 * @param dataDevolucao a atribuir
	 */
	public void setDataDevolucao(LocalDateTime dataDevolucao){
		this.dataDevolucao = dataDevolucao;
	}
	
	/**
	 * Método que devolve a data de devolução 
	 * @return
	 */
	public LocalDateTime getDataDevolucao(){
		return dataDevolucao;
	}
	
	/**
	 * Método que permite atribuir uma data de requisicao ao CD
	 * @param  dataRquisicao a atribuir
	 */
	public void setDataRequisicao(LocalDateTime dataRequisicao){
		this.dataRequisicao = dataRequisicao;
	}

	
	/**
	 * Método que verifica se o artigo está em atraso
	 */
	public boolean verificaAtraso(){
		LocalDateTime today = LocalDateTime.now();
		if(today.compareTo(dataDevolucao) > 0){
			atrasado = true;
		}
		else
			atrasado = false;
		return atrasado;
	}
	
}