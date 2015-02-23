package ElecDomListing;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Business.ColorLogic;
import Business.ElectroDomesticoLogic;
import Entities.ElectroDomestico;
import Entities.Entity;
import Entities.Television;
import Entities.Entity.States;

/**
 * Servlet implementation class ABM
 */
@WebServlet("/ABM")
public class ABM extends HttpServlet {	
	public static final int LAVARROPAS = 1;
	public static final int TELEVISION = 2;
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
		doPost(request,response);
		String action = request.getParameter("action");
		int ID = 0;
		boolean tiene_id = true;
		try {
			ID = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			tiene_id=false;
		}	
		if(action != null){
			if (action.equalsIgnoreCase("nuevo") && tiene_id==false){
				response.sendRedirect("/Web/Listing");
			}else{
				primerRequest(action,ID,request,response);
			}
			
		}
		

	} 
	private void primerRequest(String action,int ID,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		primerRequest( action, ID,request,  response,"");
	}
	private void primerRequest(String action,int ID,HttpServletRequest request, HttpServletResponse response,String error) throws IOException, ServletException{		
		if(action != null){		
			
			// Nuevo ABM
			if(action.equalsIgnoreCase("modificar") || action.equalsIgnoreCase("eliminar") || action.equalsIgnoreCase("nuevo")){			
				
				boolean elijio_algo=true;
				//Check Action
				try{
					this.addDropDownsToRequest(request);
				}catch (Exception e){
					error = error + e.getMessage();
				}
				if(action.equalsIgnoreCase("nuevo")){					
					request.setAttribute("action", action);					
				}else if(action.equalsIgnoreCase("modificar") || action.equalsIgnoreCase("eliminar")){								
					ElectroDomestico elecDom;
					try {
						elecDom = new ElectroDomesticoLogic().getOne(ID);
						elecDom.setPrecioFinal(new ElectroDomesticoLogic().precioFinal(elecDom.getId()));
						request.setAttribute("elecDom", elecDom);
						request.setAttribute("action", action);
						request.setAttribute("selectedColor", elecDom.getColor().getId());
						request.setAttribute("selectedConsumo", elecDom.getConsumoEnergetico().getId());
					}
					catch (Exception e) {
						error = error + e.getMessage();
					}		
				}
				request.setAttribute("error", error);
				if(elijio_algo==false){
					response.sendRedirect("/Web/Listing");
				}
				else {
					if(!error.isEmpty()){		
						request.setAttribute("error", error);
					}
					getServletContext().getRequestDispatcher("/ABM.jsp").forward(request, response);						
				
				}
				
				
			}
		}	

	}

	private void addDropDownsToRequest(HttpServletRequest request) throws Exception{
		// get colores & consumos list
		ArrayList<Entities.Color> colores=null;
		ArrayList<Entities.ConsumoEnergetico> consumos=null;
		try {
			colores = new ColorLogic().getAll();
			consumos = new Business.ConsumoEnergeticoLogic().getAll();
		} catch (Exception e) {
			throw e;
		}	
		
		request.setAttribute("colores", colores);
		request.setAttribute("consumos", consumos);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		String error = "";
		
		String confirmacion = request.getParameter("confirmacion");		
		
		if(confirmacion != null){
			if(confirmacion.equalsIgnoreCase("cancelar")){		
				response.sendRedirect("/Web/Listing");				
			}else{				
				try {
					this.addDropDownsToRequest(request);
					switch(confirmacion){
						case "Nuevo":{
							this.nuevo(request,response);
							break;
						} 
						case "Modificar":{							
							this.modificar(request,response);
							break;
						}
						case "Eliminar":{
							this.eliminar(request,response);
							break;
						}
					}					
				} catch (Exception e) {
					error=e.getMessage();
				}
				if(!error.isEmpty()){									
					primerRequest(confirmacion,Integer.parseInt(request.getParameter("id").equals("") ? "0" : request.getParameter("id"))  ,request,response,error);											
				}else{
					response.sendRedirect("/Web/Listing");						
				}				
				
			}			
						
		}
	}
	
	private void nuevo(HttpServletRequest request,HttpServletResponse response) throws Exception{		
		ElectroDomestico elecDom = null;		
		try {
			elecDom = this.getElectrodomesticoFromRequest(request);
			elecDom.setState(States.New);
			this.Save(elecDom);
		} catch (Exception e) {			
			throw e;
		}		
			
	}
	
	private void modificar(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		ElectroDomestico elecDom = null;		
		try {
			elecDom = this.getElectrodomesticoFromRequest(request);
			elecDom.setId(Integer.parseInt(request.getParameter("id")));
			elecDom.setState(States.Modified);
			this.Save(elecDom);			
		} catch (Exception e) {			
			throw e;
		}	
	}
	
	private void eliminar(HttpServletRequest request,HttpServletResponse response) throws Exception{		
		try {			
			int id = Integer.parseInt(request.getParameter("id"));
			ElectroDomestico elecDom = new ElectroDomesticoLogic().getOne(id);
			elecDom.setState(States.Deleted);
			this.Save(elecDom);
		} catch (Exception e) {			
			throw e;
		}		
	}
	
	private void Save(ElectroDomestico elecDom) throws Exception{
		
		try {
			if (elecDom instanceof Entities.Lavarropas){
				new Business.LavarropasLogic().save((Entities.Lavarropas)elecDom);
			}else if(elecDom instanceof Entities.Television){
				new Business.TelevisionLogic().save((Entities.Television)elecDom);
			}
		} catch (Exception e) {
			throw e;			
		}
	}
	
	private ElectroDomestico getElectrodomesticoFromRequest(HttpServletRequest request) throws Exception{
		
					
		ElectroDomestico elecDom = null;
		try {		
			elecDom = new ElectroDomestico();
			ArrayList<Entities.Color> colores = new Business.ColorLogic().getAll();
			ArrayList<Entities.ConsumoEnergetico> consumos = new Business.ConsumoEnergeticoLogic().getAll();		
			
			
			// Propiedades especificas
			
			int tipo = Integer.parseInt(request.getParameter("selecttipo"));
			if(tipo == ABM.LAVARROPAS){
				elecDom = new Entities.Lavarropas();
				((Entities.Lavarropas)elecDom).setCarga(Double.parseDouble(request.getParameter("Carga")));
			}else if(tipo == ABM.TELEVISION){
				elecDom = new Entities.Television();
				((Entities.Television)elecDom).setResolucion(Double.parseDouble(request.getParameter("Resolucion")));
				String sint = request.getParameter("Sintonizador");
				boolean sintonizador;
				if (sint == null){
					sintonizador = false;
				}else if (sint.equalsIgnoreCase("on")){
					sintonizador = true;
				}else{
					sintonizador = false;
				}				
				((Entities.Television)elecDom).setSinTDT(sintonizador);
			}else{
				throw new Exception("Tipo no existente");
			}
			
			// Propiedades generales
			
			Entities.Color color = (Entities.Color) findEntityInArrayById(colores,Integer.parseInt(request.getParameter("selectcolor")));
			Entities.ConsumoEnergetico consumo = (Entities.ConsumoEnergetico) findEntityInArrayById(consumos,Integer.parseInt(request.getParameter("selectconsumo")));
			elecDom.setDescripcion(request.getParameter("Descripcion"));
			elecDom.setPeso(Double.parseDouble(request.getParameter("Peso")));
			elecDom.setPrecio_base(Double.parseDouble(request.getParameter("Precio_Base")));
			elecDom.setColor(color);
			elecDom.setConsumoEnergetico(consumo);
			
		} catch( NumberFormatException e1){
			throw new Exception("Un numero era esperado");			
		}
		catch (Exception e) {
			throw e;
		}		
		
		return elecDom;
	}	

	public <T> T findEntityInArrayById(ArrayList<T> array,int id)
	{
	  for(T item : array){
		  if(((Entity) item).getId() == id){
			  return item;
		  }
	  }
	  return null;
	}

}
