

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.document.Document;

public class AzionePagination {
	public String esegui(HttpServletRequest request) throws ServletException{
		List<Document> risultati = (List<Document>)request.getSession().getAttribute("risultati");
		request.getSession().removeAttribute("risultati");
		request.getSession().setAttribute("risultati", risultati);
		List<String> snippets = (List<String>)request.getSession().getAttribute("snippets");
		request.getSession().removeAttribute("snippets");
		request.getSession().setAttribute("snippets", snippets);
		String suggestion = (String)request.getSession().getAttribute("suggestions");
		request.getSession().removeAttribute("suggestions");
		request.getSession().setAttribute("suggestions", suggestion);
		Double time = Double.valueOf(0.0D);
		request.setAttribute("time", time);
		int paginaCorrente = Integer.parseInt(request.getParameter("page"));
		request.setAttribute("page", Integer.valueOf(paginaCorrente));
		  
		return "true";
		
	}
}
