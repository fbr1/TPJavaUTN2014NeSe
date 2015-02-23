package ElecDomListing;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Business.ElectroDomesticoLogic;
import Entities.ElectroDomestico;

/**
 * Servlet implementation class Listing
 */
@WebServlet("/Listing")
public class Listing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Listing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		String error = "";
		ArrayList<ElectroDomestico> elecDoms = null;
		try {
			elecDoms = new ElectroDomesticoLogic().getAll();	
			for(ElectroDomestico elecdom : elecDoms){		
				elecdom.setPrecioFinal(new ElectroDomesticoLogic().precioFinal(elecdom.getId()));			
			}
		} catch (Exception e1) {
			error = e1.getMessage();
		}
		if(!error.isEmpty()){
			request.setAttribute("error", error);	
		}
		request.setAttribute("elecDoms", elecDoms);
		
		getServletContext().getRequestDispatcher("/listado.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
