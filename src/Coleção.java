import java.util.ArrayList;

public class Coleção {
	private static ArrayList<Cd> coleçãoDeCds;
	private static ArrayList<Dvd> coleçãoDeDvds;

	/**
	 * Construtor da classe Coleção
	 */
	public Coleção(){
		coleçãoDeCds =  new ArrayList<Cd>();
		coleçãoDeDvds =  new ArrayList<Dvd>();

	}
	/**
	 * Método que adiciona um CD ao ArrayList colecao de CDs
	 * @param Objeto da Classe Cd que queremos adicionar
	 */
	public void addCd(Cd novoCd){
		coleçãoDeCds.add(novoCd);
	}

	/**
	 * Método que adiciona umDVD ao ArrayList colecao de DVDs
	 * @param Objetdo da Classe Dvd que queremos adicionar
	 */
	public void addDvd(Dvd novoDvd){
		coleçãoDeDvds.add(novoDvd);
	}

	/**
	 * Método que lista todos os CD existentes na mediateca
	 */
	public static void listarCd(){
		if(coleçãoDeCds.size() == 0)
		{
			System.out.println("Não existem CD's a apresentar.");
		}
		else
		{
			System.out.println("Listagem de CDs");
			for(Cd cd: coleçãoDeCds)
			{
				System.out.println(cd);
			}
		}
	}

	/**
	 * Método que lista todos os DVD existentes na mediateca
	 */
	public static void listarDvd(){
		if(coleçãoDeDvds.size() == 0)
		{
			System.out.println("Não existem Dvd's a apresentar.");
		}
		else
		{
			System.out.println("Listagem de DVDs");
			for(Dvd dvd: coleçãoDeDvds)
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
		if(coleçãoDeCds.size() == 0)
		{
			System.out.println("Não existem CD's a apresentar.");
		}
		else
		{
			System.out.println("Listagem de CDs por ano:");
			System.out.println("");
			for(int i  = 0; i < coleçãoDeCds.size(); i++)
			{
				if (ano == (coleçãoDeCds.get(i)).getAno() )
				{
					System.out.println(coleçãoDeCds.get(i));
					existCD = true;
				}
			}
		}
		if(!existCD)
		{
			System.out.println("Não existem CDs desse ano.");
		}
	}	

	/**
	 * Lista DVDs por ano.
	 * @param ano que pretendemos listar
	 */
	public static void listaDvdAno(int ano){
		boolean existDVD = false;
		if(coleçãoDeDvds.size() == 0)
		{
			System.out.println("Não existem Dvd's a apresentar.");
		}
		else
		{
			System.out.println("Listagem de DVDs por ano:");
			System.out.println("");
			for(int i  = 0; i < coleçãoDeDvds.size(); i++)
			{
				if(ano == (coleçãoDeDvds.get(i)).getAno() )
				{
					System.out.println(coleçãoDeDvds.get(i));
					existDVD = true;
				}
			}
		}

		if(!existDVD)
		{
			System.out.println("Não existem Dvds desse ano.");
		}
	}

	/**
	 * Lista por interprete e realizador
	 * @param nome do intérprete/realizador
	 */
	public static void listaRealEInt(String IntReal)
	{
		boolean existArtigo = false;
		System.out.println("Listagem de CDs e DVDs por realizador/Interprete: ");
		System.out.println("CDs:");
		System.out.println("");
		for(int i  = 0; i < coleçãoDeCds.size(); i++)
		{
			if(((coleçãoDeCds.get(i)).getInterprete()).equals(IntReal))
			{
				System.out.println(coleçãoDeCds.get(i));
				existArtigo = true;
			}
		}
		System.out.println("DVDs:");
		System.out.println("");
		for(int i  = 0; i < coleçãoDeDvds.size(); i++)
		{
			if(((coleçãoDeDvds.get(i)).getRealizador()).equals(IntReal) )
			{
				System.out.println(coleçãoDeDvds.get(i));
				existArtigo = true;
			}
		}
		if(!existArtigo)
		{
			System.out.println("Não existem artigos desse intérprete/realizador.");
		}
	}
	
	/** Método que retorna o ArrayList que contém os CD existentes na mediateca
	 * 
	 * @return
	 */
	public static ArrayList<Cd> getColeçãoDeCds()
	{
		return coleçãoDeCds;
	}
	
	/** Método que retorna o ArrayList que contém os DVD existentes na mediateca
	 * 
	 * @return
	 */
	public static ArrayList<Dvd> getColeçãoDeDvds()
	{
		return coleçãoDeDvds;
	}
}
