package Business;


import Data.LavarropasAdapter;
import Entities.ElectroDomestico;
import Entities.Lavarropas;

public class LavarropasLogic extends ElectroDomesticoLogic{	
	
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

	public Lavarropas getOne(int id) throws Exception{
		return LavarropasData().getOne(id);
	}
	
	public void save(Lavarropas lavarropas) throws Exception{
		super.validateInput((ElectroDomestico)lavarropas);
		LavarropasData().save(lavarropas);
	}	

	public void delete(int id) throws Exception{
		LavarropasData().delete(id);
	}	
	 
}


