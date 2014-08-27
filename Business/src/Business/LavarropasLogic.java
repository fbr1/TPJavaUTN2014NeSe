package Business;

import java.util.ArrayList;

import Data.LavarropasAdapter;
import Entities.Lavarropas;

public class LavarropasLogic extends ElectroDomesticoLogic{	
	//Constants
	static final double MIN_CARGA = 30; 
	static final double RECARGO_CARGA = 50;
	
	private LavarropasAdapter lavarropasData;
	
	public LavarropasLogic(){
		lavarropasData = new LavarropasAdapter();		
	}

	public LavarropasAdapter LavarropasData() {
		return lavarropasData;
	}

	public void setLavarropasData(LavarropasAdapter lavarropasData) {
		this.lavarropasData = lavarropasData;
	}
	public ArrayList<Lavarropas> getAll()
	{
		return LavarropasData().getAll();
	}

	public Lavarropas getOne(int id){
		return LavarropasData().getOne(id);
	}
	
	public void save(Lavarropas lavarropas){
		LavarropasData().save(lavarropas);
	}	

	public void delete(int id){
		LavarropasData().delete(id);
	}	
	 
	public double precioFinal(int ID){
		double precioFinal = super.precioFinal(ID);
		if(this.getOne(ID).getCarga() > LavarropasLogic.MIN_CARGA ) { precioFinal =+ LavarropasLogic.RECARGO_CARGA ;}
		return precioFinal;
	}
	
	public double precioFinal(Lavarropas lavarropa){
		return this.precioFinal(lavarropa.getId());
	}
}

