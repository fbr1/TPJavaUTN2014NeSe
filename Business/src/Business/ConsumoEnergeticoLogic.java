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
	
	public ArrayList<ConsumoEnergetico> getAll() throws Exception
	{
		return ConsumoEnergeticoData().getAll();
	}

	public ConsumoEnergetico getOne(int id) throws Exception{
		return ConsumoEnergeticoData().getOne(id);
	}
	
	public void save(ConsumoEnergetico elecDom) throws Exception{
		ConsumoEnergeticoData().save(elecDom);
	}	

	public void delete(int id) throws Exception{
		ConsumoEnergeticoData().delete(id);
	}
}
