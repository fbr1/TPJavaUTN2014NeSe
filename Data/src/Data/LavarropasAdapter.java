package Data;

import java.util.ArrayList;

import Entities.Lavarropas;
import Entities.Entity.States;

public class LavarropasAdapter extends Adapter {
	
	private static ArrayList<Lavarropas> lavarropas;
	
	private static ArrayList<Lavarropas> Lavarropas() {
		if ( lavarropas == null){
			lavarropas = new ArrayList<Lavarropas>();
			Lavarropas lavarropa;
			try {
				lavarropa = new Lavarropas("Phillips",55,66,'D',"rojo",20);
				lavarropa.setState(States.Unmodified);
				lavarropa.setId(7);
				lavarropas.add(lavarropa);
				lavarropa = new Lavarropas("LG",69, 34, 'B', "gris",40);
				lavarropa.setState(States.Unmodified);
				lavarropa.setId(8);
				lavarropas.add(lavarropa);
				lavarropa = new Lavarropas("Whirpool",123,321,'E',"blanco",50);
				lavarropa.setState(States.Unmodified);
				lavarropa.setId(9);
				lavarropas.add(lavarropa);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}	
		}
		return lavarropas;
	}

    public ArrayList<Lavarropas> getAll()
    {
        return Lavarropas();
    }

    public Lavarropas getOne(int ID)
    {    	
        for(Lavarropas e : Lavarropas()){
        	if( e.getId() == ID) return e;
        }
        return null;
    }

    public void delete(int ID)
    {
    	Lavarropas().remove(this.getOne(ID));        
    }

    public void save(Lavarropas lavarropas)
    {
        if (lavarropas.getState() == States.New)
        {
            int NextID = 0;
            for(Lavarropas e : Lavarropas())
            {
                if (e.getId() > NextID)
                {
                    NextID = e.getId();
                }
            }
            lavarropas.setId(NextID + 1);
            Lavarropas().add(lavarropas);
        }
        else if (lavarropas.getState() == States.Deleted)
        {
            this.delete(lavarropas.getId());
        }
        else if (lavarropas.getState() == States.Modified)
        {
            for(Lavarropas e : Lavarropas())
            {
            	if(e.getId() == lavarropas.getId()){
            		e = lavarropas;
            	}
            }
        }
        lavarropas.setState(States.Unmodified);         
    }
}
