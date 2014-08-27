package Data;

import java.util.ArrayList;

import Entities.Television;
import Entities.Entity.States;

public class TelevisionAdapter extends Adapter{
	
	private static ArrayList<Television> televisores;
	
	private static ArrayList<Television> Television() {
		if ( televisores == null){
			televisores = new ArrayList<Television>();
			Television television;
			try {
				television = new Television(60,120,'B',"rojo",19,false);
				television.setState(States.Unmodified);
				television.setId(4);
				televisores.add(television);
				television = new Television(30, 180, 'F', "negro", 21, true);
				television.setState(States.Unmodified);
				television.setId(5);
				televisores.add(television);
				television = new Television(150,130,'A',"azul",23,false);
				television.setState(States.Unmodified);
				television.setId(6);
				televisores.add(television);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}	
		}
		return televisores;
	}

    public ArrayList<Television> getAll()
    {
        return Television();
    }

    public Television getOne(int ID)
    {    	
        for(Television e : Television()){
        	if( e.getId() == ID) return e;
        }
        return null;
    }

    public void delete(int ID)
    {
    	Television().remove(this.getOne(ID));        
    }

    public void save(Television television)
    {
        if (television.getState() == States.New)
        {
            int NextID = 0;
            for(Television e : Television())
            {
                if (e.getId() > NextID)
                {
                    NextID = e.getId();
                }
            }
            television.setId(NextID + 1);
            System.out.println("Se agrego un nuevo televisor en datos");
            Television().add(television);            
        }
        else if (television.getState() == States.Deleted)
        {
            this.delete(television.getId());
        }
        else if (television.getState() == States.Modified)
        {
            for(Television e : Television())
            {
            	if(e.getId() == television.getId()){
            		e = television;
            	}
            }
        }
        television.setState(States.Unmodified);         
    }
}