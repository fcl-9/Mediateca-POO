import java.util.ArrayList;

public class Cole��o {
	private static ArrayList<Cd> cole��oDeCds;
	private static ArrayList<Dvd> cole��oDeDvds;

	/**
	 * Construtor da classe Cole��o
	 */
	public Cole��o(){
		cole��oDeCds =  new ArrayList<Cd>();
		cole��oDeDvds =  new ArrayList<Dvd>();

	}
	/**
	 * M�todo que adiciona um CD ao ArrayList colecao de CDs
	 * @param Objeto da Classe Cd que queremos adicionar
	 */
	public void addCd(Cd novoCd){
		cole��oDeCds.add(novoCd);
	}

	/**
	 * M�todo que adiciona umDVD ao ArrayList colecao de DVDs
	 * @param Objetdo da Classe Dvd que queremos adicionar
	 */
	public void addDvd(Dvd novoDvd){
		cole��oDeDvds.add(novoDvd);
	}

	/**
	 * M�todo que lista todos os CD existentes na mediateca
	 */
	public static void listarCd(){
		if(cole��oDeCds.size() == 0)
		{
			System.out.println("N�o existem CD's a apresentar.");
		}
		else
		{
			System.out.println("Listagem de CDs");
			for(Cd cd: cole��oDeCds)
			{
				System.out.println(cd);
			}
		}
	}

	/**
	 * M�todo que lista todos os DVD existentes na mediateca
	 */
	public static void listarDvd(){
		if(cole��oDeDvds.size() == 0)
		{
			System.out.println("N�o existem Dvd's a apresentar.");
		}
		else
		{
			System.out.println("Listagem de DVDs");
			for(Dvd dvd: cole��oDeDvds)
			{
				System.out.println(dvd);
			}
		}
	}

	/**
	 * Lista CDs por ano.
	 * @param ano que pretendemos listar
	 */
	public static void listaCdAno(int ano)
	{
		boolean existCD = false;
		if(cole��oDeCds.size() == 0)
		{
			System.out.println("N�o existem CD's a apresentar.");
		}
		else
		{
			System.out.println("Listagem de CDs por ano:");
			System.out.println("");
			for(int i  = 0; i < cole��oDeCds.size(); i++)
			{
				if (ano == (cole��oDeCds.get(i)).getAno() )
				{
					System.out.println(cole��oDeCds.get(i));
					existCD = true;
				}
			}
		}
		if(!existCD)
		{
			System.out.println("N�o existem CDs desse ano.");
		}
	}	

	/**
	 * Lista DVDs por ano.
	 * @param ano que pretendemos listar
	 */
	public static void listaDvdAno(int ano){
		boolean existDVD = false;
		if(cole��oDeDvds.size() == 0)
		{
			System.out.println("N�o existem Dvd's a apresentar.");
		}
		else
		{
			System.out.println("Listagem de DVDs por ano:");
			System.out.println("");
			for(int i  = 0; i < cole��oDeDvds.size(); i++)
			{
				if(ano == (cole��oDeDvds.get(i)).getAno() )
				{
					System.out.println(cole��oDeDvds.get(i));
					existDVD = true;
				}
			}
		}

		if(!existDVD)
		{
			System.out.println("N�o existem Dvds desse ano.");
		}
	}

	/**
	 * Lista por interprete e realizador
	 * @param nome do int�rprete/realizador
	 */
	public static void listaRealEInt(String IntReal)
	{
		boolean existArtigo = false;
		System.out.println("Listagem de CDs e DVDs por realizador/Interprete: ");
		System.out.println("CDs:");
		System.out.println("");
		for(int i  = 0; i < cole��oDeCds.size(); i++)
		{
			if(((cole��oDeCds.get(i)).getInterprete()).equals(IntReal))
			{
				System.out.println(cole��oDeCds.get(i));
				existArtigo = true;
			}
		}
		System.out.println("DVDs:");
		System.out.println("");
		for(int i  = 0; i < cole��oDeDvds.size(); i++)
		{
			if(((cole��oDeDvds.get(i)).getRealizador()).equals(IntReal) )
			{
				System.out.println(cole��oDeDvds.get(i));
				existArtigo = true;
			}
		}
		if(!existArtigo)
		{
			System.out.println("N�o existem artigos desse int�rprete/realizador.");
		}
	}
	
	/** M�todo que retorna o ArrayList que cont�m os CD existentes na mediateca
	 * 
	 * @return
	 */
	public static ArrayList<Cd> getCole��oDeCds()
	{
		return cole��oDeCds;
	}
	
	/** M�todo que retorna o ArrayList que cont�m os DVD existentes na mediateca
	 * 
	 * @return
	 */
	public static ArrayList<Dvd> getCole��oDeDvds()
	{
		return cole��oDeDvds;
	}
}
