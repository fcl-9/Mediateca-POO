import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Collections;

public class SubMenu {

	private Scanner userInput;
	private Coleção novaColeção;
	private Avaliação novoAval;
	private OrdenaAvalCritic ordenaCr;
	private OrdenaAvalConsum ordenaCon;
	private Associado novoAss;

	/**
	 * Construtor da classe submenu.
	 */
	public SubMenu(){
		userInput = new Scanner(System.in,"cp1252");
		novaColeção = new Coleção();
		novoAval = new Avaliação();
		ordenaCr = new OrdenaAvalCritic();
		ordenaCon = new OrdenaAvalConsum();
		novoAss = new Associado();
	}

	/**
	 * Método que permite criar um novo Sócio
	 */
	public void criaSocio(){
		int opcao;
		String escolhaRef;
		System.out.println("############# Bem vindo ao menu de criação de sócio. #############");
		try{
			do {
				System.out.println("Irá adicionar um novo sócio.");
				System.out.println("Deseja continuar? S/N");
				escolhaRef = userInput.next();

				switch (escolhaRef.charAt(0))
				{
				case 'N':
					break;
				case 'S':

					if(Associado.getSócios().size() != 0)
					{
						do {
							System.out.println("Sistema de referidos.");
							System.out.println("Deseja indicar o número de um sócio que lhe tenha indicado esta mediateca. S/N");
							escolhaRef = userInput.next();

							switch (escolhaRef.charAt(0))
							{
							case 'N':
								break;
							case 'S':
								do{

									System.out.println("Insira o numero de socio que lhe recomendou a mediateca");
									opcao = userInput.nextInt();

								}while(opcao < 0 || opcao > Associado.getSócios().get(Associado.getSócios().size()-1).getNumSocio());

								int numReferals = Associado.getSócios().get(opcao - 1).getnumReferals();
								Associado.getSócios().get(opcao - 1).setnumReferals( numReferals  + 1);
								break;

							}
						}while(escolhaRef.charAt(0) != 'N' && escolhaRef.charAt(0) != 'S');
					}
					System.out.println("Primeiro nome:");
					String nome = userInput.next();
					userInput.nextLine();

					System.out.println("Apelido:");
					String apelido = userInput.next();
					userInput.nextLine();




					System.out.println("Idade:");
					int idade = userInput.nextInt();
					userInput.nextLine();

					boolean tipo = false;

					do {
						System.out.println("Que tipo de sócio é?");
						System.out.println("1 - Standard");
						System.out.println("2 - Premium");

						System.out.println("Escolha um dos tipos apresentados.");
						opcao = userInput.nextInt();

						switch (opcao)
						{
						case 1:
							tipo  = false;
							break;
						case 2:
							tipo  = true;
							break;

						}
					}while(opcao <0 || opcao > 2);
					Socio novoSocio = new Socio(nome, apelido, idade, tipo);
					novoAss.addSocio(novoSocio);
				}
			}while(escolhaRef.charAt(0) != 'N' && escolhaRef.charAt(0) != 'S');
		}
		catch(InputMismatchException exp1)
		{
			System.out.println("Erro o valor introduzido não é válido.");
			userInput.nextLine();
		}
	}

	/**
	 * Método responsável por atribuir aos artigos as avaliações dos consumidores
	 */
	public void novoAvalConsumidores()
	{
		//int escolha = 0;
		int indexCD = -1;
		int indexDVD = -1;
		int avaliaçãoConsumer = 0;
		try{
			System.out.println("Caro consumidor insira o id do artigo que deseja avaliar:");
			int escolhaArtigo = userInput.nextInt();

			for(int i = 0; i < Coleção.getColeçãoDeCds().size(); i++)
			{
				if(Coleção.getColeçãoDeCds().get(i).getIdArtigo() == escolhaArtigo)
				{
					indexCD = i;
					break;
				}
			}

			for(int i = 0; i < Coleção.getColeçãoDeDvds().size(); i++)
			{
				if(Coleção.getColeçãoDeDvds().get(i).getIdArtigo() == escolhaArtigo)
				{
					indexDVD = i;
					break;
				}
			}


			if(indexCD == -1 && indexDVD == -1)
			{
				System.out.println("O artigo que não foi encontrado.");
			}
			if(indexCD > -1)
			{
				do{
					System.out.println("Está a avaliar o CD : " + Coleção.getColeçãoDeCds().get(indexCD).getTitulo());
					System.out.println("Insira a sua avaliação (1 - 5)");
					avaliaçãoConsumer = userInput.nextInt();
				}
				while(avaliaçãoConsumer <= 0 || avaliaçãoConsumer >5);
				Coleção.getColeçãoDeCds().get(indexCD).getAvalConsumidores().add(avaliaçãoConsumer);
				Coleção.getColeçãoDeCds().get(indexCD).calculaAvalConsumidores();
			}

			if(indexDVD > -1)
			{
				do{
					System.out.println("Está a avaliar o Dvd : " + Coleção.getColeçãoDeDvds().get(indexDVD).getTitulo());
					System.out.println("Insira a sua avaliação (1 - 5)");
					avaliaçãoConsumer = userInput.nextInt();
				}
				while(avaliaçãoConsumer <= 0 || avaliaçãoConsumer >5);
				Coleção.getColeçãoDeDvds().get(indexDVD).getAvalConsumidores().add(avaliaçãoConsumer);
				Coleção.getColeçãoDeDvds().get(indexDVD).calculaAvalConsumidores();
			}
		}
		catch(InputMismatchException exp)
		{
			System.out.println("Erro o valor introduzido não é válido.");
			userInput.nextLine();
		}
	}

	/**
	 * Método responsável por apresentar o menu que permite escolher que tipo de avaliação se pretende
	 */
	public void menuClassificacoes(){
		int opcao = 0;
		if(Coleção.getColeçãoDeCds().size() == 0 && Coleção.getColeçãoDeDvds().size() == 0)
		{
			System.out.println("Não pode efetuar classificações, pois a mediateca não possui itens");
		}
		else{
			System.out.println("ATENÇÃO:");
			System.out.println("Para avaliar os artigos necessita do seu ID,");
			System.out.println("caso não o saiba consulte a opção lista produtos.");

			char escolhaContinua;

			do{
				System.out.println("Deseja coninuar ? S/N");
				escolhaContinua = userInput.next().charAt(0);
				userInput.nextLine();

				switch(escolhaContinua)
				{
				case 'N': 							
					break;
				case 'S':
					do{
						System.out.println("Em que tipo de avaliador se enquandra?");
						System.out.println("1 - Crítico");
						System.out.println("2 - Consumidor");
						System.out.println("");
						System.out.println("Introduza a sua escolha:");

						opcao = userInput.nextInt();
						switch(opcao)
						{
						case 1:
							avalPorCritico();
							break;
						case 2:
							novoAvalConsumidores();
							break;
						default:
							System.out.println("Não escolheu nenhuma das opções expectáveis.");
							System.out.println("Por favor volte a introduzir uma das opções apresentadas.");
							break;
						}
					}while(opcao < 1 || opcao > 2);
				}
			}while(escolhaContinua != 'N' && escolhaContinua != 'S' );
		}
	}


	/**
	 * Método responsável por apresentar o menu que permite ao user escolher a listagem que quer realizar
	 */
	public void menuListagens()
	{
		int escolha = 0;
		int ano = 0;
		String intReal = null;

		do {
			System.out.println("Deseja listar os artigos presentes na mediateca por:");
			System.out.println("1 - Listar todos os artigos.");
			System.out.println("2 - Listar por CDs.");
			System.out.println("3 - Listar por DVDs");
			System.out.println("4 - Listar por ano.");
			System.out.println("5 - Listar por interprete/realizador.");
			System.out.println("6 - Listar por classificação de críticos CDs.");
			System.out.println("7 - Listar por classificação de críticos DVDs.");
			System.out.println("8 - Listar por classificação de consumidores CDs.");
			System.out.println("9 - Listar por classificação de consumidores DVDs.");
			System.out.println("0 - Para voltar ao menu inicial.");
			System.out.println("");
			System.out.println("Introduza a sua escolha:");
			escolha = userInput.nextInt();			
			try{
				switch(escolha)
				{
				case 0:
					break;
				case 1:
					Coleção.listarCd();
					Coleção.listarDvd();
					break;
				case 2: 
					Coleção.listarCd();
					break;
				case 3: 
					Coleção.listarDvd();
					break;
				case 4:
					try
					{
						System.out.println("Qual é o ano que deseja listar?");
						ano = userInput.nextInt();
						userInput.nextLine();
						Coleção.listaCdAno(ano);
						Coleção.listaDvdAno(ano);
					}
					catch(InputMismatchException exp)
					{
						System.out.println("Erro o valor introduzido não é válido.");
						userInput.nextLine();

					}
					break;
				case 5:
					System.out.println("Qual é o intérprete/realizador que deseja listar?");
					intReal = userInput.next();
					userInput.nextLine();
					Coleção.listaRealEInt(intReal);
					break;
				case 6:
					listByCriticClassCd();
					break;
				case 7:
					listByCriticClassDvd();
					break;
				case 8:
					listByConsumerClassCd();
					break;
				case 9:
					listByConsumerClassDvd();
					break;
				default:
					System.out.println("Não escolheu nenhuma das opções expectáveis.");
					System.out.println("Por favor volte a introduzir uma das opções apresentadas.");
					break;
				}
			} 
			catch(InputMismatchException exp1)
			{
				System.out.println("Erro o valor introduzido não é válido.");
				userInput.nextLine();
			}
		}while(escolha < 0 || escolha > 8);

	}



	/**
	 * Lista por ordem de classificações dada por críticos
	 */
	public void listByCriticClassCd(){

		Collections.sort(Coleção.getColeçãoDeCds(), ordenaCr);

		for(Cd cdzinho: Coleção.getColeçãoDeCds())
		{
			System.out.println(cdzinho);
		}
	}

	/**
	 * Lista os dvds por ordem de classificações dada por criticos
	 */
	public void listByCriticClassDvd(){

		Collections.sort(Coleção.getColeçãoDeDvds(), ordenaCr);

		for(Dvd dvds: Coleção.getColeçãoDeDvds())
		{
			System.out.println(dvds);
		}
	}

	/**
	 * Lista por CD ordem de classificação de consumidor
	 */
	public void listByConsumerClassCd(){
		Collections.sort(Coleção.getColeçãoDeCds(), ordenaCon);

		for(Cd cdzinho: Coleção.getColeçãoDeCds())
		{
			System.out.println(cdzinho);
			System.out.println("O número de votos do " + cdzinho.getTitulo() +" é de " + cdzinho.getAvalConsumidores().size() + "\n");
		}
	}

	/**
	 * Lista Dvd por ordem de classificação de consumidor
	 */
	public void listByConsumerClassDvd(){
		Collections.sort(Coleção.getColeçãoDeDvds(), ordenaCon);

		for(Dvd dvds: Coleção.getColeçãoDeDvds())
		{
			System.out.println(dvds);
			System.out.println("O número de votos do " + dvds.getTitulo() +" é de " + dvds.getAvalConsumidores().size() + "\n");
		}
	}

	/**
	 * Método que permite selecionar se se quer introduzir um cd ou um dvd
	 */
	public void menuArtigoInserir()
	{
		int escolha = 0;


		do {
			System.out.println("Que artigo deseja adicionar a coleção da mediateca:");
			System.out.println("1 - Introduzir  CD ");
			System.out.println("2 - Introduzir DVD ");
			System.out.println("0 - Voltar ao Menu Inicial");
			System.out.println("");
			System.out.println("Introduza a sua escolha:");
			try{
				escolha = userInput.nextInt();
				userInput.nextLine();

				switch(escolha)
				{
				case 0: 

					break;
				case 1: 
					criaCd();
					break;
				case 2: 
					criaDvd();
					break;
				}
			}
			catch(InputMismatchException exp){
				System.out.println("ERRO! Não introduziu um número inteiro, ao contrário do que era esperado.");
				userInput.nextLine();
			}
		}while(escolha < 0 || escolha > 2);
	}


	/**
	 * Método que permite criar um CD
	 */
	private void criaCd()
	{
		try{	
			int ano;
			System.out.println("Título Do CD: ");
			String titulo = userInput.next();
			userInput.nextLine();

			do{
				System.out.println("Ano Do CD:");
				ano= userInput.nextInt();
			}while(ano < 0 && (ano != (int)ano));

			System.out.println("Interprete: ");
			String interprete = userInput.next();
			userInput.nextLine();	


			int genderChoose;
			String estilo = null ;
			do{
				System.out.println("######################### Estilo #########################");
				System.out.println("1 - Pop");
				System.out.println("2 - Rock");
				System.out.println("3 - Blues");
				System.out.println("4 - Folk");
				System.out.println("5 - Dance");
				System.out.println("6 - Classic");
				System.out.println("7 - Jazz");
				System.out.println("8 - Country");
				System.out.println("9 - Outro");
				System.out.println("###########################################################");

				System.out.println("Introduza a sua opção:");
				genderChoose = userInput.nextInt();
				switch(genderChoose)
				{
				case 1:
					estilo = "Pop";
					break;
				case 2:
					estilo  = "Rock";
					break;
				case 3:
					estilo = "Blues";
					break;
				case 4:
					estilo = "Folk";
					break;
				case 5:
					estilo = "Dance";
					break;
				case 6:
					estilo = "Classic";
					break;
				case 7:
					estilo = "Jazz";
					break;
				case 8:
					estilo = "Country";
					break;
				case 9:
					System.out.println("Insira um estilo que não se encontra na lista.");
					estilo = userInput.next();
					userInput.nextLine();
					break;
				}		

			}while(genderChoose <= 0 || genderChoose > 9);

			Cd novoCD = new Cd(titulo, ano, interprete, estilo);	
			novaColeção.addCd(novoCD);
		}
		catch(InputMismatchException exp)
		{
			System.out.println("Erro o valor introduzido não é válido.");

		}

	}

	/**
	 * Método que permite criar um DVD
	 */
	private void criaDvd(){
		try{
			int ano;
			System.out.println("Título do DVD:");
			String titulo = userInput.next();
			userInput.nextLine();

			do{
				System.out.println("Ano do DVD:");
				ano = userInput.nextInt();
			}while(ano < 0 );

			System.out.println("Realizador do DVD:");
			String realizador = userInput.next();
			userInput.nextLine();

			String genero = null;
			int opcao;
			do {
				System.out.println("############################# Géneros #############################");
				System.out.println("1 - Comédia");
				System.out.println("2 - Ação");
				System.out.println("3 - Romance");
				System.out.println("4 - Drama");
				System.out.println("5 - Aventura");
				System.out.println("6 - Terror");
				System.out.println("7 - Animação");
				System.out.println("8 - Suspense");
				System.out.println("9 - Outros");
				System.out.println("###################################################################");


				System.out.println("Escolha um dos géneros apresentados.");
				opcao = userInput.nextInt();

				switch (opcao)
				{
				case 1:
					genero = "Comédia";
					break;
				case 2:
					genero = "Ação";
					break;
				case 3: 
					genero = "Romance";
					break;
				case 4:
					genero = "Drama";
					break;
				case 5:
					genero = "Aventura";
					break;
				case 6:
					genero = "Terror";
					break;
				case 7:
					genero = "Animação";
					break;
				case 8:
					genero = "Suspense";
					break;
				case 9:
					System.out.println("Insira o género que pretende.");
					genero = userInput.next();
					userInput.nextLine();
					break;
				}
			}while(opcao <0 || opcao > 9);
			Dvd novoDVD = new Dvd(titulo, ano, realizador, genero);	
			novaColeção.addDvd(novoDVD);
		}
		catch(InputMismatchException exp)
		{
			System.out.println("Erro o valor introduzido não é válido.");

		}
	}

	/**
	 * Método que permite a um juri avaliar um determinado artigo
	 */
	public void avalPorCritico(){
		int opção = 0;
		int darNota = 0;
		int indexArtigoCD = 0;
		int indexArtigoDVD = 0;
		try{
			do{

				System.out.println("Escolha o artigo que deseja avaliar. Insira o seu numero.");
				opção = userInput.nextInt();

				for(int i = 0; i <  Coleção.getColeçãoDeCds().size(); i++)
				{
					if(Coleção.getColeçãoDeCds().get(i).getIdArtigo() == opção )
					{
						indexArtigoCD = i + 1;
						break;
					}	
				}

				for(int i = 0; i <  Coleção.getColeçãoDeDvds().size(); i++)
				{
					if(Coleção.getColeçãoDeDvds().get(i).getIdArtigo() == opção )
					{
						indexArtigoDVD = i + 1;
						break;
					}
				}

			}while(opção < 1 || opção > ((Coleção.getColeçãoDeCds().size())+(Coleção.getColeçãoDeDvds().size())));

			do{
				if(indexArtigoDVD != 0)
				{
					if((Coleção.getColeçãoDeDvds().get(indexArtigoDVD - 1).getAvalCriticosDone()))
					{
						System.out.println("O item já foi avaliado por um crítico.");
						break;
					}
					else
					{
						System.out.println("Vai avaliar o seguinte DVD: " + Coleção.getColeçãoDeDvds().get(indexArtigoDVD - 1).getTitulo());
						System.out.println("Introduza a sua classificação:");
						darNota = userInput.nextInt();
						novoAval.setAvalCriticos(darNota); // Coloca no objeto a nota dada pelo critico
						Coleção.getColeçãoDeDvds().get(indexArtigoDVD - 1).setAvalCriticos(novoAval.getAvalCriticos()); // Busca avaliação dos criticos
						Coleção.getColeçãoDeDvds().get(indexArtigoDVD - 1).setAvalCriticosDone(true);
						System.out.println("Obrigado pela sua avaliação!");
					}
				}
				else if(indexArtigoCD != 0)
				{
					if(Coleção.getColeçãoDeCds().get(indexArtigoCD - 1).getAvalCriticosDone())
					{
						System.out.println("O item já foi avaliado por um crítico.");
						break;
					}
					else
					{
						System.out.println("Vai avaliar o seguinte CD: " + Coleção.getColeçãoDeCds().get(indexArtigoCD - 1).getTitulo());
						System.out.println("Introduza a sua classificação:");
						darNota = userInput.nextInt();
						novoAval.setAvalCriticos(darNota);

						Coleção.getColeçãoDeCds().get(indexArtigoCD - 1).setAvalCriticos(novoAval.getAvalCriticos()); // Busca avaliação dos criticos
						Coleção.getColeçãoDeCds().get(indexArtigoCD - 1).setAvalCriticosDone(true);
						System.out.println("Obrigado pela sua avaliação!");
					}
				}
				else
				{
					System.out.println("O item não existe.");
					break;
				}

			}while(darNota < 1 || darNota > 5);
		}
		catch(InputMismatchException exp)
		{
			System.out.println("Erro o valor introduzido não é válido.");
			userInput.nextLine();
		}


	}
	
	/**
	 * Método que junta os métodos auxiliares de carregamento e trata das execeções que podem surgir desses métodos.
	 */
	public void carregaFicheiros()
	{
		boolean carSucessCd = false;
		boolean carSucessDvd = false;
		boolean carSucessSocio = false;
		System.out.println("Irá proceder ao carregamento de ficheiros de texto.");
		System.out.println("A carregar coleção de CDs....");
		try{
			carregaColeçãoCDs();
			carSucessCd = true;
		}catch(IOException e1)
		{
			System.out.print("Não foi possível carregar o ficheiro.");

		}
		System.out.println("A carregar coleção de Dvds....");
		try{
			carregaColeçãoDvds();
			carSucessDvd = true;
		}catch(IOException e2)
		{
			System.out.print("Não foi possível carregar o ficheiro de Dvds.");
		}
		System.out.println("A carregar Sócios....");
		try{
			carregaSócios();
			carSucessSocio = true;
		}catch(IOException e3)
		{
			System.out.print("Não foi possível carregar o ficheiro de sócios.");

		}
		if(carSucessSocio &&  carSucessDvd && carSucessCd)
		{
			System.out.println("Todos os ficheiro foram carregados!");
		}
	}
	/**
	 * Método auxiliar que carrega do fichiero ColeçãoCD.txt
	 * @throws IOException
	 */
	private void carregaColeçãoCDs() throws IOException
	{

		FileReader streamEntradaCd= new FileReader("ColeçãoCD.txt");
		BufferedReader saveOnMemory=  new BufferedReader(streamEntradaCd);

		String titulo;
		String ano ;		
		String interprete ;
		String estilo;
		String garbage ;

		garbage = saveOnMemory.readLine();
		while(garbage != null)
		{
			titulo = saveOnMemory.readLine();
			ano =  saveOnMemory.readLine();
			interprete =  saveOnMemory.readLine();
			estilo =  saveOnMemory.readLine();
			// Consome os carateres que separam os Cds
			garbage = saveOnMemory.readLine();

			// Converte a string ano para um inteiro
			int newAno = Integer.parseInt(ano);
			// Cria novo Cd
			Cd novoCd = new Cd(titulo, newAno, interprete, estilo);
			// Adiciona o cd a coleção
			novaColeção.addCd(novoCd);
		}
		saveOnMemory.close();
	}
	/**
	 * Método auxiliar que carrega do ficheiro ColeçãoDvd.txt
	 * @throws IOException
	 */
	private void carregaColeçãoDvds() throws IOException
	{
		String titulo;
		String ano ;		
		String realizador ;
		String genero;
		String garbage ;
		FileReader streamEntradaCd= new FileReader("ColeçãoDvd.txt");
		BufferedReader saveOnMemory=  new BufferedReader(streamEntradaCd);


		garbage = saveOnMemory.readLine();
		while(garbage != null)
		{
			titulo = saveOnMemory.readLine();
			ano =  saveOnMemory.readLine();
			realizador =  saveOnMemory.readLine();
			genero =  saveOnMemory.readLine();
			// Consome os carateres que separam os Cds
			garbage = saveOnMemory.readLine();
			// Converte a string ano para um inteiro
			int newAno = Integer.parseInt(ano);
			//Cria novo Dvd
			Dvd novoDvd = new Dvd(titulo, newAno, realizador, genero);
			// Adiciona a coleção de dvds o novo cd
			novaColeção.addDvd(novoDvd);
		}
		saveOnMemory.close();
	}

	/**
	 * Método auxiliar que carrega o do ficheiro de text Associados.txt
	 * @throws IOException
	 */
	private void carregaSócios() throws IOException
	{	
		String nome;
		String apelido;
		String idade;
		String checkPremium;
		String garbage ;
		FileReader streamEntradaCd= new FileReader("Associados.txt");
		BufferedReader saveOnMemory=  new BufferedReader(streamEntradaCd);

		garbage = saveOnMemory.readLine();
		while(garbage != null)
		{
			nome = saveOnMemory.readLine();
			apelido =  saveOnMemory.readLine();
			idade =  saveOnMemory.readLine();
			checkPremium =  saveOnMemory.readLine();
			// Consome os carateres que separam os Cds
			garbage = saveOnMemory.readLine();

			int convIdade = Integer.parseInt(idade);
			boolean convBool = Boolean.parseBoolean(checkPremium);

			Socio novoSocio = new Socio(nome,apelido,convIdade,convBool);
			novoAss.addSocio(novoSocio);

		}
		saveOnMemory.close();
	}

}