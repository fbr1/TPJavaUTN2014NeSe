package Data;

import java.util.ArrayList;

import Entities.ConsumoEnergetico;
import Entities.Entity.States;

public class ConsumoEnergeticoAdapter extends Adapter{

private static ArrayList<ConsumoEnergetico> consumosEnergeticos;
	
	private static ArrayList<ConsumoEnergetico> ConsumoEnergeticos(){
		if ( consumosEnergeticos == null){
			consumosEnergeticos = new ArrayList<ConsumoEnergetico>();
			ConsumoEnergetico consumoEner;
			consumoEner = new ConsumoEnergetico('A',100.0);
			consumoEner.setState(States.Unmodified);
			consumosEnergeticos.add(consumoEner);
			consumoEner = new ConsumoEnergetico('B',80.0);
			consumoEner.setState(States.Unmodified);
			consumosEnergeticos.add(consumoEner);
			consumoEner = new ConsumoEnergetico('C',60.0);
			consumoEner.setState(States.Unmodified);
			consumosEnergeticos.add(consumoEner);
			consumoEner = new ConsumoEnergetico('D',50.0);
			consumoEner.setState(States.Unmodified);
			consumosEnergeticos.add(consumoEner);
			consumoEner = new ConsumoEnergetico('E',30.0);
			consumoEner.setState(States.Unmodified);
			consumosEnergeticos.add(consumoEner);
			consumoEner = new ConsumoEnergetico('F',10.0);
			consumoEner.setState(States.Unmodified);
			consumosEnergeticos.add(consumoEner);
		
		}
		return consumosEnergeticos;
	}

    public ArrayList<ConsumoEnergetico> getAll()
    {
        return ConsumoEnergeticos();
    }

    public ConsumoEnergetico getOne(int ID)
    {    	
        for(ConsumoEnergetico e : ConsumoEnergeticos()){
        	if( e.getId() == ID) return e;
        }
        return null;
    }

    public void delete(int ID)
    {
    	ConsumoEnergeticos().remove(this.getOne(ID));        
    }

    public void save(ConsumoEnergetico consumoEner)
    {
        if (consumoEner.getState() == States.New)
        {
            int NextID = 0;
            for(ConsumoEnergetico e : ConsumoEnergeticos())
            {
                if (e.getId() > NextID)
                {
                    NextID = e.getId();
                }
            }
            consumoEner.setId(NextID + 1);
            ConsumoEnergeticos().add(consumoEner);
        }
        else if (consumoEner.getState() == States.Deleted)
        {
            this.delete(consumoEner.getId());
        }
        else if (consumoEner.getState() == States.Modified)
        {
            for(ConsumoEnergetico e : ConsumoEnergeticos())
            {
            	if(e.getId() == consumoEner.getId()){
            		e = consumoEner;
            	}
            }
        }
        consumoEner.setState(States.Unmodified);         
    }
}
