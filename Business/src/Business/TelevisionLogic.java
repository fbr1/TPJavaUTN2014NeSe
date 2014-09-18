package Business;



import Data.TelevisionAdapter;
import Entities.ElectroDomestico;
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

	public Television getOne(int id) throws Exception{
		return TelevisionData().getOne(id);
	}
	
	public void save(Television television) throws Exception{
		super.validateInput((ElectroDomestico)television);
		TelevisionData().save(television);
	}		

	public void delete(int id) throws Exception{
		TelevisionData().delete(id);
	}	
	
	public double precioFinal(int ID) throws Exception{
		double precioFinal = super.precioFinal(ID);
		Television television = this.getOne(ID);
		if(television.getResolucion() > TelevisionLogic.MIN_PULGADAS){
			precioFinal += precioFinal * TelevisionLogic.PORCENTAJE_PULGADAS_RECARGO / 100; 
		}
		if(television.tieneSinTDT()){
			precioFinal += TelevisionLogic.RECARGO_TDT;
		}
		return precioFinal;
	}
	
	public double precioFinal(Television television) throws Exception{
		return this.precioFinal(television.getId());
	}
	
	

}

