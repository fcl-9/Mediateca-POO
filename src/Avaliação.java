
public class Avalia��o {
	
	public int avalCriticos;
	public int avalConsumidor;
	
	/**
	 * Construtor da classe Avalia��o
	 */
	public Avalia��o() {
		avalCriticos = 0;
		avalConsumidor = 0;
	}
	
	/**
	 *  M�todo respons�vel por atualizar o valor da variavel de inst�ncia avalCriticos
	 * @param avalCriticos
	 */
	public void setAvalCriticos(int avalCriticos){
		this.avalCriticos = avalCriticos;
	}
	/**
	 * M�todo getter que retorna avalia��o dada por um cr�tico
	 * @return
	 */
	public int getAvalCriticos(){
		return avalCriticos;
	}
	
	/**
	 * M�todo respons�vel por atualizar o valor da variavel de inst�ncia avalConsumidor
	 * @param novoAval
	 */
	public void setAvalConsumidor(int novoAval)
	{
		this.avalConsumidor = novoAval;
	}
	/**
	 * M�todo getter que retorna avalia��o dada por um consumidor
	 * @return
	 */
	public int getAvalConsumidor()
	{
		return avalConsumidor;
	}
}

