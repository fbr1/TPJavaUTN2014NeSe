package Business;


import java.util.ArrayList;

import Data.ElectroDomesticoAdapter;
import Entities.ConsumoEnergetico;
import Entities.ElectroDomestico;
import Entities.Color;
import Entities.Lavarropas;
import Entities.Entity.States;
import Entities.PesoPrecio;
import Entities.Television;

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
	
	public ArrayList<ElectroDomestico> getAll() throws Exception{
		return ElectroDomesticoData().getAll();
	}

	public ArrayList<ElectroDomestico> getAll(char consumo) throws Exception{
		return ElectroDomesticoData().getAll(consumo);
	}
	public ArrayList<ElectroDomestico> getAll(double precio_min, double precio_max) throws Exception
	{
		ArrayList<ElectroDomestico> electrodomesticos = new ArrayList<ElectroDomestico>();
		for(ElectroDomestico elecDom : ElectroDomesticoData().getAll()){
			double preciofinal = 0;
			if(elecDom instanceof Television){     
				preciofinal = new TelevisionLogic().precioFinal((Television)elecDom);
			}else if(elecDom instanceof Lavarropas){
				preciofinal = new LavarropasLogic().precioFinal((Lavarropas)elecDom);
			}				
			if(preciofinal != 0){
				if(preciofinal >= precio_min && preciofinal <= precio_max ){
					electrodomesticos.add(elecDom);
				}
			}else{
				throw new Exception("Hubo un error en el filtrado de datos");
			}
		}
		return electrodomesticos;
	}
	public ArrayList<ElectroDomestico> getAll(double precio_min, double precio_max, char consumo) throws Exception
	{
		ArrayList<ElectroDomestico> electrodomesticos = new ArrayList<ElectroDomestico>();
		electrodomesticos.addAll(this.getAll(precio_min,precio_max));
		electrodomesticos.retainAll(this.getAll(consumo));
		return electrodomesticos;
	}	
	
	protected void validateInput(ElectroDomestico elecDom) throws Exception {
		States state = elecDom.getState();
		if( state == States.New || state == States.Modified){
			String color = elecDom.getColor().getNombre();
			char consumo = elecDom.getConsumoEnergetico().getNombre();
			ConsumoEnergeticoLogic consumos = new ConsumoEnergeticoLogic();
			ColorLogic colores = new ColorLogic();
			boolean correcto = false;
			

			for ( Color col : colores.getAll()){
				if( color.equalsIgnoreCase(col.getNombre()) ){
					correcto = true;
					break;
				}
			}

			if(!correcto){ elecDom.setColor(new Color());}			

			for ( ConsumoEnergetico con : consumos.getAll()){
				if( Character.toUpperCase(consumo) == Character.toUpperCase(con.getId()) ){
					correcto = true;
					break;
				}
			}

			if(!correcto){ elecDom.setConsumoEnergetico(new ConsumoEnergetico());}	
			
			if(elecDom.getDescripcion().isEmpty()){
				throw new Exception("La descripcion esta vacia");
			}			
		}
	}


	public double precioFinal(int ID) throws Exception{		
		ElectroDomestico elecDom = this.getOne(ID);		
		return this.precioFinal(elecDom);
	}
	
	public double precioFinal(ElectroDomestico elecDom) throws Exception{
		PesoPrecioLogic pesosPrecios = new PesoPrecioLogic();	
		PesoPrecio pesoprecio = pesosPrecios.getOneByPeso(elecDom.getPeso());
		return elecDom.PrecioFinal(pesoprecio);
	}


	
	
	
}
