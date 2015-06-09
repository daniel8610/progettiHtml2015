import org.apache.lucene.analysis.CharFilter;
import org.jsoup.Jsoup;


public class ParserHtml {
public static boolean hasBody(String html){
	return Jsoup.parse(html).getElementsByTag("body").hasText();
}
public static String getBody(String html){
	return Jsoup.parse(html).body().text();
}
public static boolean hasTitle(String html){
	return Jsoup.parse(html).getElementsByTag("title").hasText();
}
public static String getTitle(String html){
	return Jsoup.parse(html).title();
}
}
