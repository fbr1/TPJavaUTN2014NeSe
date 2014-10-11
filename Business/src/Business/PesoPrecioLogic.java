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
	
	public ArrayList<PesoPrecio> getAll() throws Exception
	{
		return PesoPrecioData().getAll();
	}

	public PesoPrecio getOne(int id) throws Exception{
		return PesoPrecioData().getOne(id);
	}
	
	public void save(PesoPrecio pesoprecio) throws Exception{
		PesoPrecioData().save(pesoprecio);
	}	

	public void delete(int id) throws Exception{
		PesoPrecioData().delete(id);
	}
	public PesoPrecio getOneByPeso(double peso) throws Exception{
		return PesoPrecioData().getOneByPeso(peso);
	}
}
