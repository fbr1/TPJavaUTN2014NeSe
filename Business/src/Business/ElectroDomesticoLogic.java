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
	public ArrayList<ElectroDomestico> getTodos(char consumo){
		return ElectroDomesticoData().getAll(consumo);
	}
	public ArrayList<ElectroDomestico> getTodos(double precio_min, double precio_max)
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
	public ArrayList<ElectroDomestico> getTodos(double precio_min, double precio_max, char consumo)
	{
		ArrayList<ElectroDomestico> electrodomesticos = new ArrayList<ElectroDomestico>();
		electrodomesticos.addAll(this.getTodos(precio_min,precio_max));
		electrodomesticos.retainAll(this.getTodos(consumo));
		return electrodomesticos;
	}	
	
	protected void validateInput(ElectroDomestico elecDom) {
		States state = elecDom.getState();
		if( state == States.New || state == States.Modified){
			String color = elecDom.getColor();
			char consumo = elecDom.getConsumoEnergetico();
			ConsumoEnergeticoLogic consumos = new ConsumoEnergeticoLogic();
			ColorLogic colores = new ColorLogic();
			boolean correcto = false;
			
			try {
				for ( Color col : colores.getAll()){
					if( color.equalsIgnoreCase(col.getNombre()) ){
						correcto = true;
						break;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			if(!correcto){ elecDom.setColor(Entities.Color.defaultColor);}
			
			try {
				for ( ConsumoEnergetico con : consumos.getAll()){
					if( Character.toUpperCase(consumo) == Character.toUpperCase(con.getId()) ){
						correcto = true;
						break;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!correcto){ elecDom.setConsumoEnergetico(Entities.ConsumoEnergetico.defaultId);}	
		}
	}
	
	public void delete(int id){
		ElectroDomesticoData().delete(id);
	}

	public double precioFinal(int ID){
		ElectroDomestico elecDom = this.getOne(ID);
		double precioFinal = elecDom.getPrecio_base();

		//Consumos
		ConsumoEnergeticoLogic consumos = new ConsumoEnergeticoLogic();
		ConsumoEnergetico consumo= new ConsumoEnergetico();
		try {
			consumo = consumos.getOne((int)elecDom.getConsumoEnergetico());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		precioFinal += consumo.getPrecio();
		
		//Peso Precio
		PesoPrecioLogic pesosPrecios = new PesoPrecioLogic();	
		double pesoElecDom = elecDom.getPeso();
		try {
			for (PesoPrecio pp : pesosPrecios.getAll()){
				if(pesoElecDom >= pp.getPeso_min() && pesoElecDom <= pp.getPeso_max()){
					precioFinal += pp.getPrecio();
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return precioFinal;
	}
	public double precioFinal(ElectroDomestico elecDom){
		return this.precioFinal(elecDom.getId());
	}


	
	
	
}
