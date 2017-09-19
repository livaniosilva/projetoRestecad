package br.org.ecad.livanio.webService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.org.ecad.livanio.utils.JpaEntityManager;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Path("twitter")
public class TwitterService {
	private JpaEntityManager JpaEM = new JpaEntityManager();
	private EntityManager objEm = JpaEM.getEntityManager();
	
	public static Twitter getTwitterinstance() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("tuZcoyPC9azMvfZJnSou6Xd2H")
		  .setOAuthConsumerSecret("dAEF3LSqfvSjhaUTyGcj5hhO87yY0Cli938k1LK5762lwN8xti")
		  .setOAuthAccessToken("130821968-7fx8NHwN2ioQHd3rCoyvz5vEX1G4RFYCLMGsA0OT")
		  .setOAuthAccessTokenSecret("4Er0kxhfckq04sL70mzu2D63JISkEfqxXcXiE4bnlfgSx");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		return twitter;
		
	}

	/*
	 * Metodo para procurar os textos digitados 
	 * */
@POST
@Consumes("application/json; charset=UTF-8")
@Produces("application/json; charset=UTF-8")
@Path("/captar")
public  List<String> captar(String texto) throws TwitterException {
	List<String> textos = new ArrayList<String>();
	
		
	
	Twitter twitter = getTwitterinstance();
    Query query = new Query(texto);
    QueryResult result = twitter.search(query);
    
    List<Status> statuses = result.getTweets();
    for (Status status : statuses) {
    	System.out.println("Nome Usuario: " + status.getUser().getName());
    	System.out.println("Texto: " + status.getText());
    	textos.add(status.getText());
    }
    
    try {
	    objEm.getTransaction().begin();
		objEm.persist(textos);
		objEm.getTransaction().commit();
		objEm.close();
    }catch (Exception e) {
    		System.err.println("Erro ao efetuar a gravacao de dados" + e);
    }
    
	  return textos;
}
/*
 * Metodo responsavel para trazer um texto armazenado no banco de dados local
 * */
@POST
@Produces("application/json; charset=UTF-8")
@Path("/procurar/{texto}")
public Twitter procurar(@PathParam("texto") String texto) {
	
		Twitter twitter = objEm.find(Twitter.class, texto);
		objEm.close();
		return twitter;
}

/*
 * Metodo responsavel para contar ocorrencia de palavras
 * */
@GET
@Produces("application/json; charset=UTF-8")
@Path("/estatistica/{texto}")
public Twitter estatistica(@PathParam("texto") String texto) {
	List<Twitter> lista = new ArrayList<>();
	int contador = 0;
	Twitter twitter = objEm.find(Twitter.class, texto);
	
	if(texto!=null) {
	
	Pattern p = Pattern.compile(texto);
	Matcher m = p.matcher(texto);
	while(m.find()) 
		contador++;
	System.out.printf("O Texto" + texto + "contem"+ contador +"palavras");
	
	}
	lista = listarTamanhoPalavra();
	return twitter;
}

/*@SuppressWarnings("unlikely-arg-type")
@GET
@Path("mais_curto")
@Produces("application/json; charset=UTF-8")
public List<Twitter> lista_mais_curto(){
	int contador = 0;
	
			String query = "select t from tb_twitter tb";
			List<Twitter> textos = objEm.createQuery(query, Twitter.class).getResultList();
			Spliterator<Twitter> arrayTexto = textos.spliterator();
			for(int i=0; i<arrayTexto.estimateSize(); i++) {
				
				if(arrayTexto.equals(textos)) {
					contador++;
				}
				System.out.printf("O Texto" + textos + "contem"+ contador +"palavras");
			}
			return textos;
}*/


/*
 * METODO QUE RETORNA TAMANHO DA PALAVRA.
 * 
 * */
	public List<Twitter> listarTamanhoPalavra(){
		String query = "select t from tb_twitter tb";
		Twitter twitter = objEm.find(Twitter.class, query);
		List<Twitter> textoTwitter = new ArrayList<>();
		textoTwitter.add(twitter);
		
		return textoTwitter;
		
		
	}

public  void main(String[] args) {
	try {
		captar("ecad");
		procurar("ecad");
	} catch (TwitterException e) {
		e.printStackTrace();
	}
}
	

}
