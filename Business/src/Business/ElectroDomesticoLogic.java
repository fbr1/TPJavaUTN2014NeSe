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

	public ElectroDomestico getOne(int id) throws Exception{
		return ElectroDomesticoData().getOne(id);
	}
	public ArrayList<ElectroDomestico> getTodos() throws Exception
	{
		return ElectroDomesticoData().getAll();
	}
	public ArrayList<ElectroDomestico> getTodos(char consumo) throws Exception{
		return ElectroDomesticoData().getAll(consumo);
	}
	public ArrayList<ElectroDomestico> getTodos(double precio_min, double precio_max) throws Exception
	{
		ArrayList<ElectroDomestico> electrodomesticos = new ArrayList<ElectroDomestico>();
		for(ElectroDomestico elecDom : ElectroDomesticoData().getAll()){
			double preciofinal = precioFinal(elecDom); 
			if(preciofinal >= precio_min && preciofinal <= precio_max ){
				electrodomesticos.add(elecDom);
			}
		}
		return electrodomesticos;
	}
	public ArrayList<ElectroDomestico> getTodos(double precio_min, double precio_max, char consumo) throws Exception
	{
		ArrayList<ElectroDomestico> electrodomesticos = new ArrayList<ElectroDomestico>();
		electrodomesticos.addAll(this.getTodos(precio_min,precio_max));
		electrodomesticos.retainAll(this.getTodos(consumo));
		return electrodomesticos;
	}	
	
	protected void validateInput(ElectroDomestico elecDom) throws Exception {
		States state = elecDom.getState();
		if( state == States.New || state == States.Modified){
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

			if(!correcto){ elecDom.setConsumoEnergetico(Entities.ConsumoEnergetico.defaultNombre);}	
		}
	}
	
	public void delete(int id) throws Exception{
		ElectroDomesticoData().delete(id);
	}

	public double precioFinal(int ID) throws Exception{
		ElectroDomestico elecDom = this.getOne(ID);
		double precioFinal = elecDom.getPrecio_base();

		//Consumos
		ConsumoEnergeticoLogic consumos = new ConsumoEnergeticoLogic();
		ConsumoEnergetico consumo= new ConsumoEnergetico();
		consumo = consumos.getOneByNombre(elecDom.getConsumoEnergetico());
		precioFinal += consumo.getPrecio();
		
		//Peso Precio
		PesoPrecioLogic pesosPrecios = new PesoPrecioLogic();	
		double pesoElecDom = elecDom.getPeso();
		for (PesoPrecio pp : pesosPrecios.getAll()){
			if(pesoElecDom >= pp.getPeso_min() && pesoElecDom <= pp.getPeso_max()){
				precioFinal += pp.getPrecio();
				break;
			}
		}
		return precioFinal;
	}
	public double precioFinal(ElectroDomestico elecDom) throws Exception{
		return this.precioFinal(elecDom.getId());
	}


	
	
	
}
