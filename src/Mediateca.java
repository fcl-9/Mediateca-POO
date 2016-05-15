
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
	 * Método responsável por imprimir o menu e pedir ao utilizador que opção deseja.
	 */
	private static void apresentaMenu(){
		int escolha ;

		do {
			System.out.println("######################### Menu mediateca #########################");
			System.out.println("1 - Criar novo Artigo.");
			System.out.println("2 - Listar Artigos.");
			System.out.println("3 - Atribuir avaliação a artigos.");
			System.out.println("4 - Criar novo sócio.");
			System.out.println("5 - Listar sócios.");
			System.out.println("6 - Fazer requisição por parte do sócio.");
			System.out.println("7 - Fazer devolução por parte de sócio.");
			System.out.println("8 - Listar produtos requisitados por sócio.");
			System.out.println("9 - Carregar dados a partir de ficheiro de texto.");
			System.out.println("10 - Sair.");
			System.out.println("##################################################################");


			System.out.println("Insira a opção que deseja selecionar: ");
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
					associado.fazRequisição();
					break;
				case 7:
					associado.fazDevolução();
					break;
				case 8:
					associado.listaRequisicoes();
					break;
				case 9:
					submenu.carregaFicheiros();
					break;
				case 10:
					System.out.println("Escolheu a opção sair.");
					System.exit(0);
					break;
				default:
					System.out.println("Não escolheu nenhuma das opções expectáveis.");
					System.out.println("Por favor volte a introduzir uma das opções apresentadas.");
					break;
				}
			}
			catch(InputMismatchException exp){
				System.out.println("ERRO! Não introduziu um número inteiro, ao contrário do que era esperado.");
				scan.nextLine();
			}
		}while(true);
	}

}