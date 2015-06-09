

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdvancedSearchServlet
 */
@WebServlet("/AdvancedSearchServlet")
public class AdvancedSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvancedSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AzioneAdvancedSearch as=new AzioneAdvancedSearch();
		ServletContext application = getServletContext();
		String prossimaPagina;
		if(as.esegui(request).equals("true")){
			prossimaPagina="/advancedResults.jsp";
			RequestDispatcher rd = application.getRequestDispatcher(prossimaPagina);
			rd.forward(request, response);
			}else{
				prossimaPagina="/errorAdvancedSearch.jsp";
				RequestDispatcher rd = application.getRequestDispatcher(prossimaPagina);
				rd.forward(request, response);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
