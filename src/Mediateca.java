
import java.util.InputMismatchException;
import java.util.Scanner;
public class Mediateca {

	private static Scanner scan = new Scanner(System.in,"cp1252");	
	private static SubMenu submenu = new SubMenu();
	private static Associado associado = new Associado();

	public static void main (String args[]){

		apresentaMenu();
	}

	/**
	 * M�todo respons�vel por imprimir o menu e pedir ao utilizador que op��o deseja.
	 */
	private static void apresentaMenu(){
		int escolha ;

		do {
			System.out.println("######################### Menu mediateca #########################");
			System.out.println("1 - Criar novo Artigo.");
			System.out.println("2 - Listar Artigos.");
			System.out.println("3 - Atribuir avalia��o a artigos.");
			System.out.println("4 - Criar novo s�cio.");
			System.out.println("5 - Listar s�cios.");
			System.out.println("6 - Fazer requisi��o por parte do s�cio.");
			System.out.println("7 - Fazer devolu��o por parte de s�cio.");
			System.out.println("8 - Listar produtos requisitados por s�cio.");
			System.out.println("9 - Carregar dados a partir de ficheiro de texto.");
			System.out.println("10 - Sair.");
			System.out.println("##################################################################");


			System.out.println("Insira a op��o que deseja selecionar: ");
			try{
				escolha = scan.nextInt();
				scan.nextLine();

				switch (escolha)
				{
				case 1:
					submenu.menuArtigoInserir();
					break;
				case 2:
					submenu.menuListagens();
					break;
				case 3:
					submenu.menuClassificacoes();
					break;
				case 4:
					submenu.criaSocio();
					break;
				case 5:
					Associado.listaSocios();
					break;
				case 6:
					associado.fazRequisi��o();
					break;
				case 7:
					associado.fazDevolu��o();
					break;
				case 8:
					associado.listaRequisicoes();
					break;
				case 9:
					submenu.carregaFicheiros();
					break;
				case 10:
					System.out.println("Escolheu a op��o sair.");
					System.exit(0);
					break;
				default:
					System.out.println("N�o escolheu nenhuma das op��es expect�veis.");
					System.out.println("Por favor volte a introduzir uma das op��es apresentadas.");
					break;
				}
			}
			catch(InputMismatchException exp){
				System.out.println("ERRO! N�o introduziu um n�mero inteiro, ao contr�rio do que era esperado.");
				scan.nextLine();
			}
		}while(true);
	}

}