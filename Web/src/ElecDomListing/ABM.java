package ElecDomListing;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Business.ElectroDomesticoLogic;
import Entities.ElectroDomestico;

/**
 * Servlet implementation class ABM
 */
@WebServlet("/ABM")
public class ABM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABM() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action != null){
			if(action.equalsIgnoreCase("nuevo")){
				request.setAttribute("action", action);					
			}else if(action.equalsIgnoreCase("modificar") || action.equalsIgnoreCase("eliminar")){
				int ID=0;
				try {
					ID = Integer. parseInt(request.getParameter("id"));
				} catch (NumberFormatException e1) {
					getServletContext().getRequestDispatcher("/404.jsp");
				}
				ElectroDomestico elecDom;
				try {
					elecDom = new ElectroDomesticoLogic().getOne(ID);
					elecDom.setPrecioFinal(new ElectroDomesticoLogic().precioFinal(elecDom.getId()));
					request.setAttribute("elecDom", elecDom);
					request.setAttribute("action", action);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}	
			getServletContext().getRequestDispatcher("/ABM.jsp").forward(request, response);
		}	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
