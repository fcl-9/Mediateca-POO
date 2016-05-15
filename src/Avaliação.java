
public class Avaliação {
	
	public int avalCriticos;
	public int avalConsumidor;
	
	/**
	 * Construtor da classe Avaliação
	 */
	public Avaliação() {
		avalCriticos = 0;
		avalConsumidor = 0;
	}
	
	/**
	 *  Método responsável por atualizar o valor da variavel de instância avalCriticos
	 * @param avalCriticos
	 */
	public void setAvalCriticos(int avalCriticos){
		this.avalCriticos = avalCriticos;
	}
	/**
	 * Método getter que retorna avaliação dada por um crítico
	 * @return
	 */
	public int getAvalCriticos(){
		return avalCriticos;
	}
	
	/**
	 * Método responsável por atualizar o valor da variavel de instância avalConsumidor
	 * @param novoAval
	 */
	public void setAvalConsumidor(int novoAval)
	{
		this.avalConsumidor = novoAval;
	}
	/**
	 * Método getter que retorna avaliação dada por um consumidor
	 * @return
	 */
	public int getAvalConsumidor()
	{
		return avalConsumidor;
	}
}

