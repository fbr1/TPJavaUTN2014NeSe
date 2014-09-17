package Data;
import java.util.ArrayList;

import Entities.ElectroDomestico;
import Entities.Entity.States;
import Entities.Lavarropas;
import Entities.Television;

public class ElectroDomesticoAdapter{
	
	private static ArrayList<ElectroDomestico> ElectroDomesticos() throws Exception{
		
		ArrayList<ElectroDomestico> electroDomesticos = new ArrayList<ElectroDomestico>();
		ArrayList<Lavarropas> lava= new LavarropasAdapter().getAll();
		ArrayList<Television> tele= new TelevisionAdapter().getAll();
		for(Lavarropas la : lava){
			electroDomesticos.add((ElectroDomestico)la);
		}
		for(Television te : tele){
			electroDomesticos.add((Television)te);
		}	
		return electroDomesticos;
	}

    public ArrayList<ElectroDomestico> getAll() throws Exception
    {
		return ElectroDomesticos();
    }
    public ArrayList<ElectroDomestico> getAll(char consumo) throws Exception{
    	ArrayList<ElectroDomestico> electroDomesticos = this.getAll();
    	ArrayList<ElectroDomestico> seleccionados = new ArrayList<ElectroDomestico>();
    	for(ElectroDomestico elec : electroDomesticos){
    		if(elec.getConsumoEnergetico() == consumo){
    			seleccionados.add(elec);
    		}
    	}
    	return seleccionados;
    }

    public ElectroDomestico getOne(int ID) throws Exception
    {    	
        for(ElectroDomestico e : ElectroDomesticos()){
        	if( e.getId() == ID) return e;
        }
        return null;
    }

    public void delete(int ID) throws Exception
    {
    	ElectroDomesticos().remove(this.getOne(ID));        
    }

    public void save(ElectroDomestico elecDom) throws Exception
    {
        if (elecDom.getState() == States.New)
        {
            int NextID = 0;
            for(ElectroDomestico e : ElectroDomesticos())
            {
                if (e.getId() > NextID)
                {
                    NextID = e.getId();
                }
            }
            elecDom.setId(NextID + 1);
            ElectroDomesticos().add(elecDom);
        }
        else if (elecDom.getState() == States.Deleted)
        {
            this.delete(elecDom.getId());
        }
        else if (elecDom.getState() == States.Modified)
        {
            for(ElectroDomestico e : ElectroDomesticos())
            {
            	if(e.getId() == elecDom.getId()){
            		e = elecDom;
            	}
            }
        }
        elecDom.setState(States.Unmodified);         
    }
}
