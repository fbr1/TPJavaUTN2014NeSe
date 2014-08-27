package Business;

import java.util.ArrayList;

import Data.ConsumoEnergeticoAdapter;
import Entities.ConsumoEnergetico;

public class ConsumoEnergeticoLogic {
private ConsumoEnergeticoAdapter consumoEnergeticoData;
	
	public ConsumoEnergeticoLogic(){
		consumoEnergeticoData = new ConsumoEnergeticoAdapter();		
	}

	public ConsumoEnergeticoAdapter ConsumoEnergeticoData() {
		return consumoEnergeticoData;
	}

	public void setConsumoEnergeticoData(ConsumoEnergeticoAdapter consumoEnergeticoData) {
		this.consumoEnergeticoData = consumoEnergeticoData;
	}
	
	public ArrayList<ConsumoEnergetico> getAll()
	{
		return ConsumoEnergeticoData().getAll();
	}

	public ConsumoEnergetico getOne(int id){
		return ConsumoEnergeticoData().getOne(id);
	}
	
	public void save(ConsumoEnergetico elecDom){
		ConsumoEnergeticoData().save(elecDom);
	}	

	public void delete(int id){
		ConsumoEnergeticoData().delete(id);
	}
}
