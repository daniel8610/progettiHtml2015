import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.util.Version;


public class AzioneAdvancedSearch {
	public String esegui(HttpServletRequest request) throws ServletException{
		String and,not;
		double time = 0.0D;
		and = request.getParameter("and");
		not=request.getParameter("not");
		
		if(not==""&&and=="")
			return "false";
		List<Document> results = new LinkedList<Document>();
		List<String> snippets = new LinkedList<String>();
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_47,StopAnalyzer.ENGLISH_STOP_WORDS_SET);
		double startTime = System.currentTimeMillis();
		SearchIndex si = new SearchIndex(analyzer);
		results = si.advancedQuery(and, not);
		for (Document d:results){
			String description = d.get("content");
			HighlighterUtil hi = new HighlighterUtil(analyzer);
			String desc;
			try {
				desc = hi.getHighlightedString(and, description);
				snippets.add(desc);
			} catch (IOException | InvalidTokenOffsetsException | ParseException e) {
				e.printStackTrace();
			}
			}
			double endTime = System.currentTimeMillis();
			time = (endTime - startTime) / 1000.0D;
			request.getSession().setAttribute("risultati", results);
			request.getSession().setAttribute("snippets", snippets);
			request.getSession().setAttribute("time", time);
			if (request.getParameter("page") != null) {
				int paginaCorrente = Integer.parseInt(request.getParameter("page"));
				request.setAttribute("page", Integer.valueOf(paginaCorrente));
			} else {
				int paginaCorrente = 1;
				request.setAttribute("page", Integer.valueOf(paginaCorrente));
			}
			return "true";
	}
}
