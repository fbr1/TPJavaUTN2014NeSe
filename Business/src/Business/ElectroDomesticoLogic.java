package Business;


import java.util.ArrayList;

import Data.ElectroDomesticoAdapter;
import Entities.ConsumoEnergetico;
import Entities.ElectroDomestico;
import Entities.Color;
import Entities.PesoPrecio;
import Entities.Entity.States;

public class ElectroDomesticoLogic extends BusinessLogic{	
	
	private ElectroDomesticoAdapter electroDomesticoData;
	
	public ElectroDomesticoLogic(){
		electroDomesticoData = new ElectroDomesticoAdapter();		
	}

	public ElectroDomesticoAdapter ElectroDomesticoData() {
		return electroDomesticoData;
	}

	public void setElectroDomesticoData(ElectroDomesticoAdapter electroDomesticoData) {
		this.electroDomesticoData = electroDomesticoData;
	}	

	public ElectroDomestico getOne(int id){
		return ElectroDomesticoData().getOne(id);
	}
	public ArrayList<ElectroDomestico> getTodos()
	{
		return ElectroDomesticoData().getAll();
	}
	public ArrayList<ElectroDomestico> getTodos(double precio_min, double precio_max)
	{
		return ElectroDomesticoData().getAll();
	}
	//public ArrayList<ElectroDomestico> getTodos(double precio_min, double precio_max)
	//{
	//	return ElectroDomesticoData().getAll();
	//}
	
	public void save(ElectroDomestico elecDom){
		States state = elecDom.getState();
		if( state == States.New || state == States.Modified){
			this.validateInput(elecDom);
		}
		ElectroDomesticoData().save(elecDom);
	}
	
	private void validateInput(ElectroDomestico elecDom) {
		String color = elecDom.getColor();
		char consumo = elecDom.getConsumoEnergetico();
		ConsumoEnergeticoLogic consumos = new ConsumoEnergeticoLogic();
		ColorLogic colores = new ColorLogic();
		boolean correcto = false;
		
		for ( Color col : colores.getAll()){
			if( color.equalsIgnoreCase(col.getNombre()) ){
				correcto = true;
				break;
			}
		}	
		if(!correcto){ elecDom.setColor(Entities.Color.defaultColor);}
		
		for ( ConsumoEnergetico con : consumos.getAll()){
			if( Character.toUpperCase(consumo) == Character.toUpperCase(con.getId()) ){
				correcto = true;
				break;
			}
		}
		if(!correcto){ elecDom.setConsumoEnergetico(Entities.ConsumoEnergetico.defaultId);}		
	}
	
	public void delete(int id){
		ElectroDomesticoData().delete(id);
	}

	public double precioFinal(int ID){
		ElectroDomestico elecDom = this.getOne(ID);
		double precioFinal =0.0;
		
		//Consumos
		ConsumoEnergeticoLogic consumos = new ConsumoEnergeticoLogic();
		ConsumoEnergetico consumo = consumos.getOne((int)elecDom.getConsumoEnergetico());		
		precioFinal =+ consumo.getPrecio();
		
		//Peso Precio
		PesoPrecioLogic pesosPrecios = new PesoPrecioLogic();	
		double pesoElecDom = elecDom.getPeso();
		for (PesoPrecio pp : pesosPrecios.getAll()){
			if(pesoElecDom >= pp.getPeso_min() && pesoElecDom <= pp.getPeso_max()){
				precioFinal =+ pp.getPrecio();
				break;
			}
		}
		return precioFinal;
	}
	public double precioFinal(ElectroDomestico elecDom){
		return this.precioFinal(elecDom.getId());
	}


	
	
	
}
