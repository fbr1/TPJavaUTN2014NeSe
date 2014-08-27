package Business;

import java.util.ArrayList;

import Data.TelevisionAdapter;
import Entities.Television;


public class TelevisionLogic extends ElectroDomesticoLogic{	
	//Constants
	static final double MIN_PULGADAS = 40;
	static final double PORCENTAJE_PULGADAS_RECARGO = 30;
	static final double RECARGO_TDT = 50;
	
	private TelevisionAdapter televisionData;
	
	public TelevisionLogic(){
		televisionData = new TelevisionAdapter();		
	}

	public TelevisionAdapter TelevisionData() {
		return televisionData;
	}

	public void setTelevisionData(TelevisionAdapter televisionData) {
		this.televisionData = televisionData;
	}
	
	public ArrayList<Television> getAll()
	{
		return TelevisionData().getAll();
	}

	public Television getOne(int id){
		return TelevisionData().getOne(id);
	}
	
	public void save(Television television){
		TelevisionData().save(television);
		System.out.println("Se agrego un nuevo televisor en negocio");
	}		

	public void delete(int id){
		TelevisionData().delete(id);
	}	
	
	public double precioFinal(int ID){
		double precioFinal = super.precioFinal(ID);
		Television television = this.getOne(ID);
		if(television.getResolucion() > TelevisionLogic.MIN_PULGADAS){
			precioFinal =+ precioFinal * TelevisionLogic.PORCENTAJE_PULGADAS_RECARGO / 100; 
		}
		if(television.tieneSinTDT()){
			precioFinal =+ TelevisionLogic.RECARGO_TDT;
		}
		return precioFinal;
	}
	
	public double precioFinal(Television television){
		return this.precioFinal(television.getId());
	}
	
	

}

