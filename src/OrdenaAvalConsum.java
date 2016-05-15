import java.util.Comparator;
public class OrdenaAvalConsum implements Comparator<Artigo> {

	/**
	 * Método que permite comprar dois artigos com base na avaliação dos consumidores
	 */
	@Override
	public int compare(Artigo artigo1, Artigo artigo2) {
		return  artigo2.getMediaAval() - artigo1.getMediaAval();
	}

}
