import java.time.LocalDateTime;
import java.util.ArrayList;

public class Socio {
	private String nome;
	private String apelido;
	private int idade;
	private int numSocio;
	private static int seguinte;
	private ArrayList<Cd> cdRequisitados;
	private ArrayList<Dvd> dvdRequisitados;
	private boolean checkPremium;
	private int numReferals;
	private boolean podeRequisitar;
	private LocalDateTime fimDePenaliza��o;
	
	//Construtor
	public Socio( String nome, String apelido, int idade,boolean checkPremium)
	{
		this.nome = nome;
		this.apelido = apelido;
		this.idade = idade;
		numReferals = 0;
		this.checkPremium = checkPremium;
		this.cdRequisitados = new ArrayList<Cd>();
		this.dvdRequisitados = new ArrayList<Dvd>();
		seguinte++;
		numSocio = seguinte;
		podeRequisitar = true;
	}
	
	/**
	 * Imprime as carateristicas de cada s�cio
	 */
	public String toString()
	{
		return "Numero Socio: "+ numSocio +"\n"+"Nome: " + nome +"\n"+ "Apelido: " + apelido +"\n"+ "Idade: " + idade +"\n"+ "Premium: " + checkPremium +"\n"+ "NumReferidos: " + numReferals+"\n";
	}

	/**
	 * M�todo que retorna o n�mero do s�cio
	 * @return
	 */
	public int getNumSocio()
	{
		return numSocio;
	}
	
	/**
	 * M�todo que retorna o nome do s�cio
	 * @return
	 */
	public String getNomeSocio()
	{
		return nome;
	}
	

	/**
	 * M�todo que adiciona um novo artigo aos artigos requisitados
	 * @param novoArtigo
	 */
	public void adicionaCdRequisitado(Cd novoCd)
	{
		cdRequisitados.add(novoCd);
	}
	
	/**
	 * M�todo que adiciona um novo artigo aos artigos requisitados
	 * @param novoArtigo
	 */
	public void adicionaDvdRequisitado(Dvd novoDvd)
	{
		dvdRequisitados.add(novoDvd);
	}
	
	/**
	 * M�todo que clacula o total de artigos requisitados pelo s�cio
	 * @return
	 */
	public int totalArtigosRequisitados(){
		return cdRequisitados.size() + dvdRequisitados.size();
	}
	
	/**
	 * M�todo que retorna a var�avel booleana que indica se o s�cio � premium ou n�o
	 * @return
	 */
	public boolean getcheckPremium()
	{
		return checkPremium;
	}
	/**
	 * M�todo que permite verificar o n�mero de referidos
	 * @return
	 */
	public int getnumReferals()
	{
		return numReferals;
	}
	
	/**
	 * M�todo que permite alterar o n�mero de referidos
	 * @param numReferals
	 */
	public void setnumReferals(int numReferals)
	{
		this.numReferals = numReferals;
	}
	
	/**
	 * M�todo que retorna a lsita de CD requisitado pelo s�cio
	 * @return the cdRequisitados
	 */
	public ArrayList<Cd> getCdRequisitados() {
		return cdRequisitados;
	}


	/**
	 * M�todo que retorna a lsita de DVD requisitado pelo s�cio
	 * @return the dvdRequisitados
	 */
	public ArrayList<Dvd> getDvdRequisitados() {
		return dvdRequisitados;
	}

	/**
	 * M�todo que retorna a variavel que indica se o s�cio pode ou n�o requisitar
	 * @return the podeRequisitar
	 */
	public boolean getPodeRequisitar() {
		return podeRequisitar;
	}

	/**
	 * M�todo que permite alterar a variavel que indica se o s�cio pode ou n�o requisitar
	 * @param podeRequisitar the podeRequisitar to set
	 */
	public void setPodeRequisitar(boolean podeRequisitar) {
		this.podeRequisitar = podeRequisitar;
	}

	/**
	 * M�todo que retorna a data do fim de penaliza��o do s�cio
	 * @return the fimDePenaliza��o
	 */
	public LocalDateTime getFimDePenaliza��o() {
		return fimDePenaliza��o;
	}
	
	/**
	 * M�todo que permite alterar a data do fim de penaliza��o
	 * @param fimDePenaliza��o
	 */
	public void setFimDePenaliza��o(LocalDateTime fimDePenaliza��o) {
		this.fimDePenaliza��o = fimDePenaliza��o;
	}
}
