import java.util.Comparator;
public class OrdenaAvalConsum implements Comparator<Artigo> {

	/**
	 * M�todo que permite comprar dois artigos com base na avalia��o dos consumidores
	 */
	@Override
	public int compare(Artigo artigo1, Artigo artigo2) {
		return  artigo2.getMediaAval() - artigo1.getMediaAval();
	}

}
