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
	 * M�todo que retorna o ano doa rtigo
	 * @return ano do artigo
	 */
	public int getAno(){
		return ano;
	}

	/**
	 * M�todo setter que permite alterar o valor da variavel que verifica se um artigo se encontra ou n�o requisitado
	 * @param requisitado
	 */
	public void setRequisitado(boolean requisitado)
	{
		this.requisitado = requisitado;
	}
	
	/**
	 * M�todo getter que permite aceder ao valor da variavel que diz se o item foi ou n�o criado
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
	 * Atribui uma avalia��o dos criticos ao artigo
	 * @param avalCriticos
	 */
	public void setAvalCriticos(int avalCriticos) {
		this.avalCriticos = avalCriticos;
	}
	
	/**
	 * 
	 * M�todo que retorna o array de avalia��o dada pelos consumidores
	 * @return a avaliacao dos consumidores
	 */
	public ArrayList<Integer> getAvalConsumidores() {
		return avalConsumidores;
	}
	

	/**
	 * M�todo que a variavel que verifica se os criticos j� atribuiram classifica��o
	 * @return the avalCriticosDone
	 */
	public boolean getAvalCriticosDone() {
		return avalCriticosDone;
	}

	/**
	 * M�todo setter que altera a val booleana que verifica se os criticos fizeram ou n�o avalia��o
	 * @param avalCriticosDone the avalCriticosDone to set
	 */
	public void setAvalCriticosDone(boolean avalCriticosDone) {
		this.avalCriticosDone = avalCriticosDone;
	}
	
	/**
	 * M�todo que calcula a m�dia de avalia��es dos consumidores
	 * @return m�dia de avaliacoes dos consumidores
	 */
	public void calculaAvalConsumidores(){
		int soma = 0;
		for(int i = 0; i < avalConsumidores.size(); i++){
			soma = soma + avalConsumidores.get(i);
		}
		mediaAvalConsumidores = soma/avalConsumidores.size();
	}
	/**
	 * Retorna a media das classifica��es da avalia��o de consumidores
	 * @return
	 */
	public int getMediaAval()
	{
		return (int)mediaAvalConsumidores;
	}
	/**
	 * M�todo que permite atribuir uma data de devolucao ao CD
	 * @param dataDevolucao a atribuir
	 */
	public void setDataDevolucao(LocalDateTime dataDevolucao){
		this.dataDevolucao = dataDevolucao;
	}
	
	/**
	 * M�todo que devolve a data de devolu��o 
	 * @return
	 */
	public LocalDateTime getDataDevolucao(){
		return dataDevolucao;
	}
	
	/**
	 * M�todo que permite atribuir uma data de requisicao ao CD
	 * @param  dataRquisicao a atribuir
	 */
	public void setDataRequisicao(LocalDateTime dataRequisicao){
		this.dataRequisicao = dataRequisicao;
	}

	
	/**
	 * M�todo que verifica se o artigo est� em atraso
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