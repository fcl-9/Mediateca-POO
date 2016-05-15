import java.util.Comparator;


public class OrdenaAvalCritic implements Comparator<Artigo> {
	
	/**
	 * M�todo que permite comparar dois artigos com base na avalia��o dos cr�ticos
	 */
	@Override
	public int compare(Artigo artigo1, Artigo artigo2) {
		return artigo2.getAvalCriticos() - artigo1.getAvalCriticos();
	}
}
