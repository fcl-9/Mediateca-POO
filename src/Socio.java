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
	private LocalDateTime fimDePenalização;
	
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
	 * Imprime as carateristicas de cada sócio
	 */
	public String toString()
	{
		return "Numero Socio: "+ numSocio +"\n"+"Nome: " + nome +"\n"+ "Apelido: " + apelido +"\n"+ "Idade: " + idade +"\n"+ "Premium: " + checkPremium +"\n"+ "NumReferidos: " + numReferals+"\n";
	}

	/**
	 * Método que retorna o número do sócio
	 * @return
	 */
	public int getNumSocio()
	{
		return numSocio;
	}
	
	/**
	 * Método que retorna o nome do sócio
	 * @return
	 */
	public String getNomeSocio()
	{
		return nome;
	}
	

	/**
	 * Método que adiciona um novo artigo aos artigos requisitados
	 * @param novoArtigo
	 */
	public void adicionaCdRequisitado(Cd novoCd)
	{
		cdRequisitados.add(novoCd);
	}
	
	/**
	 * Método que adiciona um novo artigo aos artigos requisitados
	 * @param novoArtigo
	 */
	public void adicionaDvdRequisitado(Dvd novoDvd)
	{
		dvdRequisitados.add(novoDvd);
	}
	
	/**
	 * Método que clacula o total de artigos requisitados pelo sócio
	 * @return
	 */
	public int totalArtigosRequisitados(){
		return cdRequisitados.size() + dvdRequisitados.size();
	}
	
	/**
	 * Método que retorna a varíavel booleana que indica se o sócio é premium ou não
	 * @return
	 */
	public boolean getcheckPremium()
	{
		return checkPremium;
	}
	/**
	 * Método que permite verificar o número de referidos
	 * @return
	 */
	public int getnumReferals()
	{
		return numReferals;
	}
	
	/**
	 * Método que permite alterar o número de referidos
	 * @param numReferals
	 */
	public void setnumReferals(int numReferals)
	{
		this.numReferals = numReferals;
	}
	
	/**
	 * Método que retorna a lsita de CD requisitado pelo sócio
	 * @return the cdRequisitados
	 */
	public ArrayList<Cd> getCdRequisitados() {
		return cdRequisitados;
	}


	/**
	 * Método que retorna a lsita de DVD requisitado pelo sócio
	 * @return the dvdRequisitados
	 */
	public ArrayList<Dvd> getDvdRequisitados() {
		return dvdRequisitados;
	}

	/**
	 * Método que retorna a variavel que indica se o sócio pode ou não requisitar
	 * @return the podeRequisitar
	 */
	public boolean getPodeRequisitar() {
		return podeRequisitar;
	}

	/**
	 * Método que permite alterar a variavel que indica se o sócio pode ou não requisitar
	 * @param podeRequisitar the podeRequisitar to set
	 */
	public void setPodeRequisitar(boolean podeRequisitar) {
		this.podeRequisitar = podeRequisitar;
	}

	/**
	 * Método que retorna a data do fim de penalização do sócio
	 * @return the fimDePenalização
	 */
	public LocalDateTime getFimDePenalização() {
		return fimDePenalização;
	}
	
	/**
	 * Método que permite alterar a data do fim de penalização
	 * @param fimDePenalização
	 */
	public void setFimDePenalização(LocalDateTime fimDePenalização) {
		this.fimDePenalização = fimDePenalização;
	}
}
