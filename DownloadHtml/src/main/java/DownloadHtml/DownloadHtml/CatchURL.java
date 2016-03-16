package DownloadHtml.DownloadHtml;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

//recupera da una pagina html tutti i link che ci sono in esso
public class CatchURL 
{
    public static void main( String[] args ) throws IOException, BoilerpipeProcessingException
    {
    	Document doc = Jsoup.connect("http://romanews.eu/2016/roma-inter-sfida-spigolosa-a-colpi-di-angoli/").get();

    	 //System.out.println(doc.html());
    	//System.out.println(doc.getElementsByClass("left").text());
    	//seleziono i nodi di nome a e con attributo href e poi dopo prendo i link assoluti
    	Elements questions = doc.select("a[href]");
		for(Element link: questions){
			System.out.println(link.attr("abs:href"));
			
		}
    	//System.out.println(doc.getElementsByClass("plat pc").text());
    }
}
