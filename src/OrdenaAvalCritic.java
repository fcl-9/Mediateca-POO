import java.util.Comparator;


public class OrdenaAvalCritic implements Comparator<Artigo> {
	
	/**
	 * Método que permite comparar dois artigos com base na avaliação dos críticos
	 */
	@Override
	public int compare(Artigo artigo1, Artigo artigo2) {
		return artigo2.getAvalCriticos() - artigo1.getAvalCriticos();
	}
}
