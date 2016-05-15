import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

public class Associado {
	private static ArrayList<Socio> sócios;
	private Scanner userInput;
	
	/**
	 * Contrutor da classe associado.
	 */
	public Associado(){
		//idSocio = 0;
		sócios = new ArrayList<Socio>();
		userInput  = new Scanner(System.in,"cp1252");
	}
	
	/**
	 * Adiciona um socio a lista de socios 
	 * @param novoSocio
	 */
	public void addSocio(Socio novoSocio)
	{
		sócios.add(novoSocio);
	}
	
	/**
	 * Lista todos os socios 
	 */
	public static void listaSocios()
	{
		if(sócios.size() == 0)
		{
			System.out.println("A mediateca não tem sócios.");
		}
		else
		{
			for(Socio soc :sócios)
			{
				System.out.println(soc);
			}
		}
	}
	
	/**
	 * Método que lsita todos os artigos requisitados por um sócio
	 */
	public void listaRequisicoes()
	{
		int idDeSocio;
		
		if(sócios.size() != 0)
		{
			System.out.println("De seguida apresentaremos todos os artigos que tem requisitados de momento");
			System.out.println("");
			idDeSocio = introduzIdSocio();
			
			if(idDeSocio != 0)
			{
				if(sócios.get(idDeSocio - 1).totalArtigosRequisitados() != 0)
				{
					listaCdRequisitado(idDeSocio - 1);
					listaDvdRequisitado(idDeSocio - 1);
				}
				else
				{
					System.out.println("Não possuí nenhum artigo requisitado!");	
				}
			}
		}
		else
		{
			System.out.println("Não podemos listar requisições uma vez que não existe sócios.");
		}
	}
	
	/**
	 * Método que retorna o conjunto de sócios.
	 * @return
	 */
	public static ArrayList<Socio> getSócios() {
		return sócios;
	}

	/**
	 * Método que lista os cd requisitado por um sócio
	 * @param idDeSocio
	 */
	private void listaCdRequisitado(int idDeSocio){
		String cdRequisitado;
		System.out.println("Listagem de CDs requisitados:");
		for(Cd cd: sócios.get(idDeSocio).getCdRequisitados())
		{
			cdRequisitado = "Id: " + cd.getIdArtigo() + "\n" + "Titulo:" + cd.getTitulo() + "\n" + "Ano: " + cd.getAno() + "\n" +
					"Data esperada de devolução " + cd.getDataDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "\n" + 
					"Artigo em atraso? " + cd.verificaAtraso() + "\n";
			System.out.println(cdRequisitado);
		}
	}
	
	/**
	 * Método que lista os dvd requisitado por um sócio
	 * @param idDeSocio
	 */
	private void listaDvdRequisitado(int idDeSocio){
		String dvdRequisitado;
		System.out.println("Listagem de DVDs requisitados:");
		for(Dvd dvd: sócios.get(idDeSocio).getDvdRequisitados())
		{
			dvdRequisitado = "Id: " + dvd.getIdArtigo() + "\n" + "Titulo:" + dvd.getTitulo() + "\n" + "Ano: " + dvd.getAno() + "\n" +
					"Data esperada de devolução " + dvd.getDataDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "\n" + 
					"Artigo em atraso? " + dvd.verificaAtraso() + "\n";
			System.out.println(dvdRequisitado);
		}
	}
	
	/**
	 * Método que apresenta o menu de devoluçaõ de Dvd's de sócios.
	 */
	public void fazDevolução()
	{
		int idDeSocio ;
		int escolha = 0;
		System.out.println("Bem vindo ao menu de devoluções da Mediateca.");
		try{
			if(sócios.size() != 0)
			{
				System.out.println("");
				System.out.println("Para devolver um artigo deverá introduzir:");
				System.out.println("O seu número de sócio e o numero do artigo que requisitou.");
				
				idDeSocio = introduzIdSocio();
				
				if(idDeSocio != 0)
				{
					if(sócios.get(idDeSocio - 1).totalArtigosRequisitados() != 0)
					{
						System.out.println("Bem Vindo : " + sócios.get(idDeSocio - 1).getNomeSocio());
						
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
									System.out.println("Escolheu uma opção errada.");
									System.out.println("Por favor introduza-a novamente.");
									break;
							}
							
						}while(escolha < 1 || escolha > 2);
					}
					else
					{
						System.out.println("Não possuí nenhum artigo requisitado!");
					}
				}
			}
			else
			{
				System.out.println("Não pode devolver um artigo uma vez que não existem sócios.");
			}
		}
		catch(InputMismatchException exp1)
		{
			System.out.println("O valor introduzido é inválido");
			userInput.nextLine();
		}
	}
		
	/**
	 * Método que devolve os CD de um determindo sócio
	 * @param idDeSocio
	 */
	private void devolveCd(int idDeSocio)
	{
		LocalDateTime today = LocalDateTime.now();
		int escolha ;
		int indexDosCds = -1;
		// Verifica se o CD está na lista de Cds da mediateca se estiver significa que este id pode ter sido requisitado
		try{
			if(sócios.get(idDeSocio).getCdRequisitados().size() != 0)
			{
				do{
					System.out.println("Insira o id do CD que deseja devolver. Caso deseje voltar ao menu anterior insira 0.");
					escolha = userInput.nextInt();
					
					if(escolha > Coleção.getColeçãoDeCds().get(Coleção.getColeçãoDeCds().size()-1).getIdArtigo())
					{
						System.out.println("O artigo solicitado não pode ser encontrado.");
					}
		
				}
				while(escolha < 0 || escolha > Coleção.getColeçãoDeCds().get(Coleção.getColeçãoDeCds().size()-1).getIdArtigo());
				
				if(escolha != 0)
				{
					for(Cd cd: sócios.get(idDeSocio).getCdRequisitados())
					{
						indexDosCds ++;
						if(cd.getIdArtigo() == escolha)
						{
							// Verifica se o dia de hoje ultrapassa o dia que era suposto devolver o Cd
							if(cd.verificaAtraso())
							{
								
								//Remove o objeto da lista de objetos requisitados
								sócios.get(idDeSocio).getCdRequisitados().remove(indexDosCds);
								//Coloca a variavel que permite requisitar a falso
								sócios.get(idDeSocio).setPodeRequisitar(false);
								//Data máxima de penalização
								sócios.get(idDeSocio).setFimDePenalização(today.plusMonths(1));
								//Coloca o cd disponivel para requisição
								cd.setRequisitado(false);
								System.out.println("O artigo "+ cd.getTitulo() +" foi devolvido. No entanto sofrerá uma penalização pois não o devolveu no tempo estipulado.");
								System.out.println("Ficará um mês sem poder efetuar requisições de qualquer artigo.");
							}
							else
							{
								// remove da lista de requisições de um socio o cd que deve ser removido
								sócios.get(idDeSocio).getCdRequisitados().remove(indexDosCds);
								//coloca o cd disponivel para requisição
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
				System.out.println("Não pode devolver CD's uma vez que não tem nenhum requisitado.");
			}
		}
		catch(InputMismatchException exp1)
		{
			System.out.println("O valor introduzido é inválido");
			userInput.nextLine();
		}
	}
	
	/**
	 * Método que devolve os Dvds de um determinado sócio
	 * @param idDeSocio
	 */
	private void devolveDvd(int idDeSocio)
	{
		LocalDateTime today = LocalDateTime.now();
		int escolha ;
		int indexDosDvds = -1;
		// Verifica se o DVD está na lista de DVDs da mediateca, se estiver significa que este id pode ter sido requisitado
		try{
			if(sócios.get(idDeSocio).getDvdRequisitados().size() != 0)
			{
				do{
					System.out.println("Insira o id do DVD que deseja devolver. Caso deseje voltar ao menu anterior insira -1.");
					escolha = userInput.nextInt();
					
					if(escolha > Coleção.getColeçãoDeDvds().get(Coleção.getColeçãoDeDvds().size()-1).getIdArtigo())
					{
						System.out.println("O artigo solicitado não pode ser encontrado.");
					}
		
				}
				while(escolha < -1 || escolha > Coleção.getColeçãoDeDvds().get(Coleção.getColeçãoDeDvds().size()-1).getIdArtigo());
				
				if(escolha != -1)
				{
					for(Dvd dvd: sócios.get(idDeSocio).getDvdRequisitados())
					{
						indexDosDvds ++;
						if(dvd.getIdArtigo() == escolha)
						{
							// Verifica se o dia de hoje ultrapassa o dia que era suposto devolver o Dvd
							if(dvd.verificaAtraso())
							{
								
								//Remove o objeto da lista de objetos requisitados
								sócios.get(idDeSocio).getDvdRequisitados().remove(indexDosDvds);
								//Coloca a variavel que permite requisitar a falso
								sócios.get(idDeSocio).setPodeRequisitar(false);
								//Data máxima de penalização
								sócios.get(idDeSocio).setFimDePenalização(today.plusMonths(1));
								//Coloca o dvd disponivel para requisição
								dvd.setRequisitado(false);
								System.out.println("O artigo "+ dvd.getTitulo() +" foi devolvido. No entanto sofrerá uma penalização pois não o devolveu no tempo estipulado.");
								System.out.println("Ficará um mês sem poder efetuar requisições de qualquer artigo.");
							}
							else
							{
								// remove da lista de requisições de um socio o dvd que deve ser removido
								sócios.get(idDeSocio).getDvdRequisitados().remove(indexDosDvds);
								//coloca o dvd disponivel para requisição
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
				System.out.println("Não pode devolver Dvd's uma vez que não tem nenhum requisitado.");
			}
			
		}
		catch(InputMismatchException exp1)
		{
			System.out.println("O valor introduzido é inválido");
			userInput.nextLine();
		}
	}
	
	/**
	 * Apresenta ao utilizador o menu que lhe permite requisitar um artigo
	 */
	public void fazRequisição()
	{
		System.out.println("Bem vindo ao menu de requisições da Mediateca.");
		int idDeSocio;
		int escolha = 0;
		//Ir buscar numero do ultimo sócio existente na database
		try{
			if(sócios.size() != 0)
			{
				idDeSocio = introduzIdSocio();
				
				if(idDeSocio  != 0)
				{
					System.out.println("Bem Vindo : " + sócios.get(idDeSocio - 1).getNomeSocio());
					if(sócios.get(idDeSocio - 1).getPodeRequisitar())
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
									System.out.println("Escolheu uma opção errada.");
									System.out.println("Por favor introduza-a novamente.");
									break;
							}
					
						}while(escolha < 1 || escolha > 2);
					}
					else
					{
						System.out.println("Lamentamos mas uma vez que recentemente entregou um artigo fora do prazo estipluado não poderá requisitar nenhum artigo.");
						System.out.println("Só poderá voltar a requisitar no dia: " + sócios.get(idDeSocio).getFimDePenalização().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
					}
				}
			}
			else
			{
				System.out.println("Infelizmente não pode continuar a operação de requisição uma vez que não existem sócios.");
			}
		}
		catch(InputMismatchException exp1)
		{
			System.out.println("O valor introduzido é inválido");
			userInput.nextLine();
		}
			
	}
	
	/**
	 * Método responsável por verificar se o utilizador inseriu bem o número de sócio
	 * @param idDeSocio
	 * @return
	 */
	private int introduzIdSocio()
	{
		int idDeSocio ;
		int lastSocioID = sócios.get(sócios.size() - 1).getNumSocio();
		
		do
		{
			System.out.println("Insira o seu numero de sócio.");
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
		if(Coleção.getColeçãoDeCds().size() != 0)
		{
			do{
				System.out.println("Insira o id do CD que deseja procurar. Caso deseje voltar ao menu anterior insira 0.");
				escolha = userInput.nextInt();
				
				if(escolha > Coleção.getColeçãoDeCds().get(Coleção.getColeçãoDeCds().size()-1).getIdArtigo())
				{
					System.out.println("O artigo solicitado não pode ser encontrado");
				}
				
			}
			while(escolha < 0 || escolha > Coleção.getColeçãoDeCds().get(Coleção.getColeçãoDeCds().size()-1).getIdArtigo());
			
			if(escolha != 0)
			{
				for(Cd cd: Coleção.getColeçãoDeCds())
				{
					if(cd.getIdArtigo() == escolha)
					{
						verificaCd = true;
						if(cd.getRequisitado())
						{
							System.out.println("O artigo que deseja requisitar já foi requisitado.");
							System.out.println("O artigo deverá ser devolvido em: ");
							System.out.println(cd.getDataDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
							break;
						}
						else
						{
							System.out.println("O artigo encontra-se disponivel para requisitar!");
							System.out.println("O nome do artigo que está a requisitar é: " + cd.getTitulo());
							
							if(sócios.get(idDeSocio).getcheckPremium())
							{
								if(sócios.get(idDeSocio).totalArtigosRequisitados() < 5)
								{
									
									if(sócios.get(idDeSocio).getnumReferals() == 0)
									{
										sócios.get(idDeSocio).adicionaCdRequisitado(cd);
										cd.setDataRequisicao(today);
										System.out.println("A data de requisição é: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										cd.setDataDevolucao(today.plusWeeks(1));
										System.out.println("O CD " +  cd.getTitulo()  + " deve ser devolvido em : " + (today.plusWeeks(1)).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										cd.setRequisitado(true);
									}
									else 
									{
										sócios.get(idDeSocio).adicionaCdRequisitado(cd);
										cd.setDataRequisicao(today);
										System.out.println("A data de requisição é: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										cd.setDataDevolucao((today.plusWeeks(1)).plusDays(sócios.get(idDeSocio).getnumReferals()));
										System.out.println("O CD " +  cd.getTitulo()  + " deve ser devolvido em : " + ((today.plusWeeks(1)).plusDays(sócios.get(idDeSocio).getnumReferals())).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										cd.setRequisitado(true);
									}
								
								}
								else 
								{
									System.out.println("Não pode efetuar mais requisições uma vez que, esgotou o numero de requisições");
								}
							}
							else
							{
								if(sócios.get(idDeSocio).getCdRequisitados().size() < 2 && sócios.get(idDeSocio).getDvdRequisitados().size() < 1)
								{
									if(sócios.get(idDeSocio).getnumReferals() == 0)
									{
										sócios.get(idDeSocio).adicionaCdRequisitado(cd);
	
										cd.setDataRequisicao(today);
										System.out.println("A data de requisição é: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										
										cd.setDataDevolucao(today.plusDays(3));
										System.out.println("O CD " +  cd.getTitulo()  + " deve ser devolvido em : " + (today.plusDays(3)).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										cd.setRequisitado(true);
									}
									else 
									{
										sócios.get(idDeSocio).adicionaCdRequisitado(cd);
										cd.setDataRequisicao(today);
										System.out.println("A data de requisição é: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										cd.setDataDevolucao(today.plusDays(3 + sócios.get(idDeSocio).getnumReferals()));
										System.out.println("O CD " +  cd.getTitulo()  + " deve ser devolvido em : " + (today.plusDays(3 + sócios.get(idDeSocio).getnumReferals())).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										cd.setRequisitado(true);
									}
								}
								else 
								{
									System.out.println("Não pode efetuar mais requisições uma vez que, esgotou o numero de requisições");
								}			
							}
						}
						break;
					}
				}
				if(!verificaCd)
					System.out.println("O artigo solicitado não pôde ser encontrado");
			}
		}
		else
		{
			System.out.println("Não pode requisitar CDs uma vez que não existem CDs.");
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
		if(Coleção.getColeçãoDeDvds().size() != 0)
		{
			do{
				System.out.println("Insira o id do DVD que deseja procurar. Caso deseje voltar ao menu anterior insira 0.");
				escolha = userInput.nextInt();
				
				if(escolha > Coleção.getColeçãoDeDvds().get(Coleção.getColeçãoDeDvds().size()-1).getIdArtigo())
				{
					System.out.println("O artigo solicitado não pôde ser encontrado");
				}
				
			}
			while(escolha < 0 || escolha > Coleção.getColeçãoDeDvds().get(Coleção.getColeçãoDeDvds().size()-1).getIdArtigo());
			
			if(escolha != 0)
			{
				for(Dvd dvd: Coleção.getColeçãoDeDvds())
				{
					if(dvd.getIdArtigo() == escolha)
					{
						verificaDvd = true;
						if(dvd.getRequisitado())
						{
							System.out.println("O artigo que deseja requisitar já foi requisitado.");
							System.out.println("O artigo deverá ser devolvido em: ");
							System.out.println(dvd.getDataDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
							break;
						}
						else
						{
							System.out.println("O artigo encontra-se disponivel para requisitar!");
							System.out.println("O nome do artigo que está a requisitar é: " + dvd.getTitulo());
							
							if(sócios.get(idDeSocio).getcheckPremium())
							{
								if(sócios.get(idDeSocio).totalArtigosRequisitados() < 5)
								{
									if(sócios.get(idDeSocio).getnumReferals() == 0)
									{
										sócios.get(idDeSocio).adicionaDvdRequisitado(dvd);
										dvd.setDataRequisicao(today);
										System.out.println("A data de requisição é: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										dvd.setDataDevolucao(today.plusWeeks(1));
										System.out.println("O DVD " +  dvd.getTitulo()  + " deve ser devolvido em : " + (today.plusWeeks(1)).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										dvd.setRequisitado(true);
									}
									else 
									{
										sócios.get(idDeSocio).adicionaDvdRequisitado(dvd);
										dvd.setDataRequisicao(today);
										System.out.println("A data de requisição é: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										dvd.setDataDevolucao((today.plusWeeks(1)).plusDays(sócios.get(idDeSocio).getnumReferals()));
										System.out.println("O DVD " +  dvd.getTitulo()  + " deve ser devolvido em : " + ((today.plusWeeks(1)).plusDays(sócios.get(idDeSocio).getnumReferals())).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										dvd.setRequisitado(true);
									}
								}
								else 
								{
									System.out.println("Não pode efetuar mais requisições uma vez que, esgotou o numero de requisições");
								}
							}
							else
							{
								if(sócios.get(idDeSocio).getDvdRequisitados().size() < 2 && sócios.get(idDeSocio).getDvdRequisitados().size() < 1)
								{
									if(sócios.get(idDeSocio).getnumReferals() == 0)
									{
										sócios.get(idDeSocio).adicionaDvdRequisitado(dvd);
	
										dvd.setDataRequisicao(today);
										System.out.println("A data de requisição é: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										
										dvd.setDataDevolucao(today.plusDays(3));
										System.out.println("O DVD " +  dvd.getTitulo()  + " deve ser devolvido em : " + (today.plusDays(3)).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										dvd.setRequisitado(true);
									}
									else 
									{
										sócios.get(idDeSocio).adicionaDvdRequisitado(dvd);
										dvd.setDataRequisicao(today);
										System.out.println("A data de requisição é: " + today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										dvd.setDataDevolucao(today.plusDays(3 + sócios.get(idDeSocio).getnumReferals()));
										System.out.println("O DVD " +  dvd.getTitulo()  + " deve ser devolvido em : " + (today.plusDays(3 + sócios.get(idDeSocio).getnumReferals())).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
										dvd.setRequisitado(true);
									}
								}
								else 
								{
									System.out.println("Não pode efetuar mais requisições uma vez que, esgotou o numero de requisições");
								}			
							}
						}
						break;
					}
				}
				if(!verificaDvd)
					System.out.println("O artigo solicitado não pôde ser encontrado");
			}
		}
		else
		{
			System.out.println("Não pode requisitar Dvds uma vez que não existem Dvds.");
		}
	}
}

