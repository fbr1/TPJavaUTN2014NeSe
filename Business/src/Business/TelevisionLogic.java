package Business;



import Data.TelevisionAdapter;
import Entities.ElectroDomestico;
import Entities.Television;


public class TelevisionLogic extends ElectroDomesticoLogic{	
	
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
	
}

