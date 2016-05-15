import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

public class Associado {
	private static ArrayList<Socio> s�cios;
	private Scanner userInput;
	
	/**
	 * Contrutor da classe associado.
	 */
	public Associado(){
		//idSocio = 0;
		s�cios = new ArrayList<Socio>();
		userInput  = new Scanner(System.in,"cp1252");
	}
	
	/**
	 * Adiciona um socio a lista de socios 
	 * @param novoSocio
	 */
	public void addSocio(Socio novoSocio)
	{
		s�cios.add(novoSocio);
	}
	
	/**
	 * Lista todos os socios 
	 */
	public static void listaSocios()
	{
		if(s�cios.size() == 0)
		{
			System.out.println("A mediateca n�o tem s�cios.");
		}
		else
		{
			for(Socio soc :s�cios)
			{
				System.out.println(soc);
			}
		}
	}
	
	/**
	 * M�todo que lsita todos os artigos requisitados por um s�cio
	 */
	public void listaRequisicoes()
	{
		int idDeSocio;
		
		if(s�cios.size() != 0)
		{
			System.out.println("De seguida apresentaremos todos os artigos que tem requisitados de momento");
			System.out.println("");
			idDeSocio = introduzIdSocio();
			
			if(idDeSocio != 0)
			{
				if(s�cios.get(idDeSocio - 1).totalArtigosRequisitados() != 0)
				{
					listaCdRequisitado(idDeSocio - 1);
					listaDvdRequisitado(idDeSocio - 1);
				}
				else
				{
					System.out.println("N�o possu� nenhum artigo requisitado!");	
				}
			}
		}
		else
		{
			System.out.println("N�o podemos listar requisi��es uma vez que n�o existe s�cios.");
		}
	}
	
	/**
	 * M�todo que retorna o conjunto de s�cios.
	 * @return
	 */
	public static ArrayList<Socio> getS�cios() {
		return s�cios;
	}

	/**
	 * M�todo que lista os cd requisitado por um s�cio
	 * @param idDeSocio
	 */
	private void listaCdRequisitado(int idDeSocio){
		String cdRequisitado;
		System.out.println("Listagem de CDs requisitados:");
		for(Cd cd: s�cios.get(idDeSocio).getCdRequisitados())
		{
			cdRequisitado = "Id: " + cd.getIdArtigo() + "\n" + "Titulo:" + cd.getTitulo() + "\n" + "Ano: " + cd.getAno() + "\n" +
					"Data esperada de devolu��o " + cd.getDataDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "\n" + 
					"Artigo em atraso? " + cd.verificaAtraso() + "\n";
			System.out.println(cdRequisitado);
		}
	}
	
	/**
	 * M�todo que lista os dvd requisitado por um s�cio
	 * @param idDeSocio
	 */
	private void listaDvdRequisitado(int idDeSocio){
		String dvdRequisitado;
		System.out.println("Listagem de DVDs requisitados:");
		for(Dvd dvd: s�cios.get(idDeSocio).getDvdRequisitados())
		{
			dvdRequisitado = "Id: " + dvd.getIdArtigo() + "\n" + "Titulo:" + dvd.getTitulo() + "\n" + "Ano: " + dvd.getAno() + "\n" +
					"Data esperada de devolu��o " + dvd.getDataDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "\n" + 
					"Artigo em atraso? " + dvd.verificaAtraso() + "\n";
			System.out.println(dvdRequisitado);
		}
	}
	
	/**
	 * M�todo que apresenta o menu de devolu�a� de Dvd's de s�cios.
	 */
	public void fazDevolu��o()
	{
		int idDeSocio ;
		int escolha = 0;
		System.out.println("Bem vindo ao menu de devolu��es da Mediateca.");
		try{
			if(s�cios.size() != 0)
			{
				System.out.println("");
				System.out.println("Para devolver um artigo dever� introduzir:");
				System.out.println("O seu n�mero de s�cio e o numero do artigo que requisitou.");
				
				idDeSocio = introduzIdSocio();
				
				if(idDeSocio != 0)
				{
					if(s�cios.get(idDeSocio - 1).totalArtigosRequisitados() != 0)
					{
						System.out.println("Bem Vindo : " + s�cios.get(idDeSocio - 1).getNomeSocio());
						
						do
						{
							System.out.println("Deseja devolver:");
							System.out.println("1 - CD");
							System.out.println("2 - DVD");
							escolha = userInput.nextInt();
							
							switch(escolha)
							{
								case 1:
									devolveCd(idDeSocio - 1);
									break;
								case 2:
									devolveDvd(idDeSocio - 1);
									break;
								default:
									System.out.println("Escolheu uma op��o errada.");
									System.out.println("Por favor introduza-a novamente.");
									break;
							}
							
						}while(escolha < 1 || escolha > 2);
					}
					else
					{
						System.out.println("N�o possu� nenhum artigo requisitado!");
					}
				}
			}
			else
			{
				System.out.println("N�o pode devolver um artigo uma vez que n�o existem s�cios.");
			}
		}
		catch(InputMismatchException exp1)
		{
			System.out.println("O valor introduzido � inv�lido");
			userInput.nextLine();
		}
	}
		
	/**
	 * M�todo que devolve os CD de um determindo s�cio
	 * @param idDeSocio
	 */
	private void devolveCd(int idDeSocio)
	{
		LocalDateTime today = LocalDateTime.now();
		int escolha ;
		int indexDosCds = -1;
		// Verifica se o CD est� na lista de Cds da mediateca se estiver significa que este id pode ter sido requisitado
		try{
			if(s�cios.get(idDeSocio).getCdRequisitados().size() != 0)
			{
				do{
					System.out.println("Insira o id do CD que deseja devolver. Caso deseje voltar ao menu anterior insira 0.");
					escolha = userInput.nextInt();
					
					if(escolha > Cole��o.getCole��oDeCds().get(Cole��o.getCole��oDeCds().size()-1).getIdArtigo())
					{
						System.out.println("O artigo solicitado n�o pode ser encontrado.");
					}
		
				}
				while(escolha < 0 || escolha > Cole��o.getCole��oDeCds().get(Cole��o.getCole��oDeCds().size()-1).getIdArtigo());
				
				if(escolha != 0)
				{
					for(Cd cd: s�cios.get(idDeSocio).getCdRequisitados())
					{
						indexDosCds ++;
						if(cd.getIdArtigo() == escolha)
						{
							// Verifica se o dia de hoje ultrapassa o dia que era suposto devolver o Cd
							if(cd.verificaAtraso())
							{
								
								//Remove o objeto da lista de objetos requisitados
								s�cios.get(idDeSocio).getCdRequisitados().remove(indexDosCds);
								//Coloca a variavel que permite requisitar a falso
								s�cios.get(idDeSocio).setPodeRequisitar(false);
								//Data m�xima de penaliza��o
								s�cios.get(idDeSocio).setFimDePenaliza��o(today.plusMonths(1));
								//Coloca o cd disponivel para requisi��o
								cd.setRequisitado(false);
								System.out.println("O artigo "+ cd.getTitulo() +" foi devolvido. No entanto sofrer� uma penaliza��o pois n�o o devolveu no tempo estipulado.");
								System.out.println("Ficar� um m�s sem poder efetuar requisi��es de qualquer artigo.");
							}
							else
							{
								// remove da lista de requisi��es de um socio o cd que deve ser removido
								s�cios.get(idDeSocio).getCdRequisitados().remove(indexDosCds);
								//coloca o cd disponivel para requisi��o
								cd.setRequisitado(false);
								System.out.println("O artigo "+ cd.getTitulo() +" foi devolvido.");
							}
							break;
						}
					}
				}
			}
			else
			{
				System.out.println("N�o pode devolver CD's uma vez que n�o tem nenhum requisitado.");
			}
		}
		catch(InputMismatchException exp1)
		{
			System.out.println("O valor introduzido � inv�lido");
			userInput.nextLine();
		}
	}
	
	/**
	 * M�todo que devolve os Dvds de um determinado s�cio
	 * @param idDeSocio
	 */
	private void devolveDvd(int idDeSocio)
	{
		LocalDateTime today = LocalDateTime.now();
		int escolha ;
		int indexDosDvds = -1;
		// Verifica se o DVD est� na lista de DVDs da mediateca, se estiver significa que este id pode ter sido requisitado
		try{
			if(s�cios.get(idDeSocio).getDvdRequisitados().size() != 0)
			{
				do{
					System.out.println("Insira o id do DVD que deseja devolver. Caso deseje voltar ao menu anterior insira -1.");
					escolha = userInput.nextInt();
					
					if(escolha > Cole��o.getCole��oDeDvds().get(Cole��o.getCole��oDeDvds().size()-1).getIdArtigo())
					{
						System.out.println("O artigo solicitado n�o pode ser encontrado.");
					}
		
				}
				while(escolha < -1 || escolha > Cole��o.getCole��oDeDvds().get(Cole��o.getCole��oDeDvds().size()-1).getIdArtigo());
				
				if(escolha != -1)
				{
					for(Dvd dvd: s�cios.get(idDeSocio).getDvdRequisitados())
					{
						indexDosDvds ++;
						if(dvd.getIdArtigo() == escolha)
						{
							// Verifica se o dia de hoje ultrapassa o dia que era suposto devolver o Dvd
							if(dvd.verificaAtraso())
							{
								
								//Remove o objeto da lista de objetos requisitados
								s�cios.get(idDeSocio).getDvdRequisitados().remove(indexDosDvds);
								//Coloca a variavel que permite requisitar a falso
								s�cios.get(idDeSocio).setPodeRequisitar(false);
								//Data m�xima de penaliza��o
								s�cios.get(idDeSocio).setFimDePenaliza��o(today.plusMonths(1));
								//Coloca o dvd disponivel para requisi��o
								dvd.setRequisitado(false);
								System.out.println("O artigo "+ dvd.getTitulo() +" foi devolvido. No entanto sofrer� uma penaliza��o pois n�o o devolveu no tempo estipulado.");
								System.out.println("Ficar� um m�s sem poder efetuar requisi��es de qualquer artigo.");
							}
							else
							{
								// remove da lista de requisi��es de um socio o dvd que deve ser removido
								s�cios.get(idDeSocio).getDvdRequisitados().remove(indexDosDvds);
								//coloca o dvd disponivel para requisi��o
								dvd.setRequisitado(false);
								System.out.println("O artigo "+ dvd.getTitulo() +" foi devolvido.");
							}
							break;
						}
					}
				}
			}
			else
			{
				System.out.println("N�o pode devolver Dvd's uma vez que n�o tem nenhum requisitado.");
			}
			
		}
		catch(InputMismatchException exp1)
		{
			System.out.println("O valor introduzido � inv�lido");
			userInput.nextLine();
		}
	}
	
	/**
	 * Apresenta ao utilizador o menu que lhe permite requisitar um artigo
	 */
	public void fazRequisi��o()
	{
		System.out.println("Bem vindo ao menu de requisi��es da Mediateca.");
		int idDeSocio;
		int escolha = 0;
		//Ir buscar numero do ultimo s�cio existente na database
		try{
			if(s�cios.size() != 0)
			{
				idDeSocio = introduzIdSocio();
				
				if(idDeSocio  != 0)
				{
					System.out.println("Bem Vindo : " + s�cios.get(idDeSocio - 1).getNomeSocio());
					if(s�cios.get(idDeSocio - 1).getPodeRequisitar())
					{
						do
						{
							System.out.println("Deseja requisitar:");
							System.out.println("1 - CD");
							System.out.println("2 - DVD");
							escolha = userInput.nextInt();
							
							switch(escolha)
							{
								case 1:
									escolheuCD(idDeSocio - 1);
									break;
								case 2:
									escolheuDVD(idDeSocio - 1);
									break;
								default:
									System.out.println("Escolheu uma op��o errada.");
									System.out.println("Por favor introduza-a novamente.");
									break;
							}
					
						}while(escolha < 1 || escolha > 2);
					}
					else
					{
						System.out.println("Lamentamos mas uma vez que recentemente entregou um artigo fora do prazo estipluado n�o poder� requisitar nenhum artigo.");
						System.out.println("S� poder� voltar a requisitar no dia: " + s�cios.get(idDeSocio).getFimDePenaliza��o().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
					}
				}
			}
			else
			{
				System.out.println("Infelizmente n�o pode continuar a opera��o de requisi��o uma vez que n�o existem s�cios.");
			}
		}
		catch(InputMismatchException exp1)
		{
			System.out.println("O valor introduzido � inv�lido");
			userInput.nextLine();
		}
			
	}
	
	/**
	 * M�todo respons�vel por verificar se o utilizador inseriu bem o n�mero de s�cio
	 * @param idDeSocio
	 * @return
	 */
	private int introduzIdSocio()
	{
		int idDeSocio ;
		int lastSocioID = s�cios.get(s�cios.size() - 1).getNumSocio();
		
		do
		{
			System.out.println("Insira o seu numero de s�cio.");
			System.out.println("Ou introduza 0 para sair.");
			idDeSocio = userInput.nextInt();
			
		}while(idDeSocio < 0 || idDeSocio > lastSocioID);
		
		return idDeSocio;
	}
	
	/**
	 * Requisita Cd
	 */
	private void escolheuCD(int idDeSocio)
	{
		LocalDateTime today = LocalDateTime.now();
		int escolha;
		boolean verificaCd = false;
		if(Cole��o.getCole��oDeCds().size() != 0)
		{
			do{
				System.out.println("Insira o id do CD que deseja procurar. Caso deseje voltar ao menu anterior insira 0.");
				escolha = userInput.nextInt();
				
				if(escolha > Cole��o.getCole��oDeCds().get(Cole��o.getCole��oDeCds().size()-1).getIdArtigo())
				{
					System.out.println("O artigo solicitado n�o pode ser encontrado");
				}
				
			}
			while(escolha < 0 || escolha > Cole��o.getCole��oDeCds().get(Cole��o.getCole��oDeCds().size()-1).getIdArtigo());
			
			if(escolha != 0)
			{
				for(Cd cd: Cole��o.getCole��oDeCds())
				{
					if(cd.getIdArtigo() == escolha)
					{
						verificaCd = true;
						if(cd.getRequisitado())
						{
							System.out.println("O artigo que deseja requisitar j� foi requisitado.");
							System.out.println("O artigo dever� ser devolvido em: ");
							System.out.println(cd.getDataDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
							break;
						}
						else
						{
							System.out.println("O artigo encontra-se disponivel para requisitar!");
							System.out.println("O nome do artigo que est� a requisitar �: " + cd.getTitulo());
							
							if(s�cios.get(idDeSocio).getcheckPremium())
							{
								if(s�cios.get(idDeSocio).totalArtigosRequisitados() < 5)
								{
									
									if(s�cios.get(idDeSocio).getnumReferals() == 0)
									{
										s�cios.get(idDeSocio).adicionaCdRequisitado(cd);
										cd.setDataRequisicao(today);
										System.out.println("A data de requisi��o �: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										cd.setDataDevolucao(today.plusWeeks(1));
										System.out.println("O CD " +  cd.getTitulo()  + " deve ser devolvido em : " + (today.plusWeeks(1)).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										cd.setRequisitado(true);
									}
									else 
									{
										s�cios.get(idDeSocio).adicionaCdRequisitado(cd);
										cd.setDataRequisicao(today);
										System.out.println("A data de requisi��o �: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										cd.setDataDevolucao((today.plusWeeks(1)).plusDays(s�cios.get(idDeSocio).getnumReferals()));
										System.out.println("O CD " +  cd.getTitulo()  + " deve ser devolvido em : " + ((today.plusWeeks(1)).plusDays(s�cios.get(idDeSocio).getnumReferals())).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										cd.setRequisitado(true);
									}
								
								}
								else 
								{
									System.out.println("N�o pode efetuar mais requisi��es uma vez que, esgotou o numero de requisi��es");
								}
							}
							else
							{
								if(s�cios.get(idDeSocio).getCdRequisitados().size() < 2 && s�cios.get(idDeSocio).getDvdRequisitados().size() < 1)
								{
									if(s�cios.get(idDeSocio).getnumReferals() == 0)
									{
										s�cios.get(idDeSocio).adicionaCdRequisitado(cd);
	
										cd.setDataRequisicao(today);
										System.out.println("A data de requisi��o �: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										
										cd.setDataDevolucao(today.plusDays(3));
										System.out.println("O CD " +  cd.getTitulo()  + " deve ser devolvido em : " + (today.plusDays(3)).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										cd.setRequisitado(true);
									}
									else 
									{
										s�cios.get(idDeSocio).adicionaCdRequisitado(cd);
										cd.setDataRequisicao(today);
										System.out.println("A data de requisi��o �: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										cd.setDataDevolucao(today.plusDays(3 + s�cios.get(idDeSocio).getnumReferals()));
										System.out.println("O CD " +  cd.getTitulo()  + " deve ser devolvido em : " + (today.plusDays(3 + s�cios.get(idDeSocio).getnumReferals())).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										cd.setRequisitado(true);
									}
								}
								else 
								{
									System.out.println("N�o pode efetuar mais requisi��es uma vez que, esgotou o numero de requisi��es");
								}			
							}
						}
						break;
					}
				}
				if(!verificaCd)
					System.out.println("O artigo solicitado n�o p�de ser encontrado");
			}
		}
		else
		{
			System.out.println("N�o pode requisitar CDs uma vez que n�o existem CDs.");
		}
	}

	/**
	 * Requisita DVD
	 * @param idDeSocio
	 */
	private void escolheuDVD(int idDeSocio)
	{
		LocalDateTime today = LocalDateTime.now();
		int escolha ;
		boolean verificaDvd = false;
		if(Cole��o.getCole��oDeDvds().size() != 0)
		{
			do{
				System.out.println("Insira o id do DVD que deseja procurar. Caso deseje voltar ao menu anterior insira 0.");
				escolha = userInput.nextInt();
				
				if(escolha > Cole��o.getCole��oDeDvds().get(Cole��o.getCole��oDeDvds().size()-1).getIdArtigo())
				{
					System.out.println("O artigo solicitado n�o p�de ser encontrado");
				}
				
			}
			while(escolha < 0 || escolha > Cole��o.getCole��oDeDvds().get(Cole��o.getCole��oDeDvds().size()-1).getIdArtigo());
			
			if(escolha != 0)
			{
				for(Dvd dvd: Cole��o.getCole��oDeDvds())
				{
					if(dvd.getIdArtigo() == escolha)
					{
						verificaDvd = true;
						if(dvd.getRequisitado())
						{
							System.out.println("O artigo que deseja requisitar j� foi requisitado.");
							System.out.println("O artigo dever� ser devolvido em: ");
							System.out.println(dvd.getDataDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
							break;
						}
						else
						{
							System.out.println("O artigo encontra-se disponivel para requisitar!");
							System.out.println("O nome do artigo que est� a requisitar �: " + dvd.getTitulo());
							
							if(s�cios.get(idDeSocio).getcheckPremium())
							{
								if(s�cios.get(idDeSocio).totalArtigosRequisitados() < 5)
								{
									if(s�cios.get(idDeSocio).getnumReferals() == 0)
									{
										s�cios.get(idDeSocio).adicionaDvdRequisitado(dvd);
										dvd.setDataRequisicao(today);
										System.out.println("A data de requisi��o �: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										dvd.setDataDevolucao(today.plusWeeks(1));
										System.out.println("O DVD " +  dvd.getTitulo()  + " deve ser devolvido em : " + (today.plusWeeks(1)).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										dvd.setRequisitado(true);
									}
									else 
									{
										s�cios.get(idDeSocio).adicionaDvdRequisitado(dvd);
										dvd.setDataRequisicao(today);
										System.out.println("A data de requisi��o �: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										dvd.setDataDevolucao((today.plusWeeks(1)).plusDays(s�cios.get(idDeSocio).getnumReferals()));
										System.out.println("O DVD " +  dvd.getTitulo()  + " deve ser devolvido em : " + ((today.plusWeeks(1)).plusDays(s�cios.get(idDeSocio).getnumReferals())).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										dvd.setRequisitado(true);
									}
								}
								else 
								{
									System.out.println("N�o pode efetuar mais requisi��es uma vez que, esgotou o numero de requisi��es");
								}
							}
							else
							{
								if(s�cios.get(idDeSocio).getDvdRequisitados().size() < 2 && s�cios.get(idDeSocio).getDvdRequisitados().size() < 1)
								{
									if(s�cios.get(idDeSocio).getnumReferals() == 0)
									{
										s�cios.get(idDeSocio).adicionaDvdRequisitado(dvd);
	
										dvd.setDataRequisicao(today);
										System.out.println("A data de requisi��o �: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										
										dvd.setDataDevolucao(today.plusDays(3));
										System.out.println("O DVD " +  dvd.getTitulo()  + " deve ser devolvido em : " + (today.plusDays(3)).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										dvd.setRequisitado(true);
									}
									else 
									{
										s�cios.get(idDeSocio).adicionaDvdRequisitado(dvd);
										dvd.setDataRequisicao(today);
										System.out.println("A data de requisi��o �: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										dvd.setDataDevolucao(today.plusDays(3 + s�cios.get(idDeSocio).getnumReferals()));
										System.out.println("O DVD " +  dvd.getTitulo()  + " deve ser devolvido em : " + (today.plusDays(3 + s�cios.get(idDeSocio).getnumReferals())).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										dvd.setRequisitado(true);
									}
								}
								else 
								{
									System.out.println("N�o pode efetuar mais requisi��es uma vez que, esgotou o numero de requisi��es");
								}			
							}
						}
						break;
					}
				}
				if(!verificaDvd)
					System.out.println("O artigo solicitado n�o p�de ser encontrado");
			}
		}
		else
		{
			System.out.println("N�o pode requisitar Dvds uma vez que n�o existem Dvds.");
		}
	}
}

