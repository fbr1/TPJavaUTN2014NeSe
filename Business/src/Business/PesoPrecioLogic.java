package Business;

import java.util.ArrayList;

import Data.PesoPrecioAdapter;
import Entities.PesoPrecio;


public class PesoPrecioLogic {	
	
	private PesoPrecioAdapter pesoPrecioData;
	
	public PesoPrecioLogic(){
		pesoPrecioData = new PesoPrecioAdapter();		
	}

	public PesoPrecioAdapter PesoPrecioData() {
		return pesoPrecioData;
	}

	public void setPesoPrecioData(PesoPrecioAdapter pesoPrecioData) {
		this.pesoPrecioData = pesoPrecioData;
	}
	
	public ArrayList<PesoPrecio> getAll()
	{
		return PesoPrecioData().getAll();
	}

	public PesoPrecio getOne(int id){
		return PesoPrecioData().getOne(id);
	}
	
	public void save(PesoPrecio color){
		PesoPrecioData().save(color);
	}	

	public void delete(int id){
		PesoPrecioData().delete(id);
	}	

}
