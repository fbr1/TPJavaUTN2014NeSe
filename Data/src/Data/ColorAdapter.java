package Data;

import java.util.ArrayList;

import Entities.Color;
import Entities.Entity.States;

public class ColorAdapter extends Adapter{
	
	private static ArrayList<Color> colores;
	
	private static ArrayList<Color> Colors(){
		if ( colores == null){
			colores = new ArrayList<Color>();
			Color color;
			color = new Color("blanco");
			color.setId(0);
			color.setState(States.Unmodified);
			colores.add(color);
			color = new Color("Nnegro");
			color.setId(1);
			color.setState(States.Unmodified);
			colores.add(color);
			color = new Color("rojo");
			color.setId(2);
			color.setState(States.Unmodified);
			colores.add(color);			
			color = new Color("azul");
			color.setId(3);
			color.setState(States.Unmodified);
			colores.add(color);	
			color = new Color("gris");
			color.setId(4);
			color.setState(States.Unmodified);
			colores.add(color);	
		}
		return colores;
	}

    public ArrayList<Color> getAll()
    {
        return Colors();
    }

    public Color getOne(int ID)
    {    	
        for(Color e : Colors()){
        	if( e.getId() == ID) return e;
        }
        return null;
    }

    public void delete(int ID)
    {
    	Colors().remove(this.getOne(ID));        
    }

    public void save(Color color)
    {
        if (color.getState() == States.New)
        {
            int NextID = 0;
            for(Color e : Colors())
            {
                if (e.getId() > NextID)
                {
                    NextID = e.getId();
                }
            }
            color.setId(NextID + 1);
            Colors().add(color);
        }
        else if (color.getState() == States.Deleted)
        {
            this.delete(color.getId());
        }
        else if (color.getState() == States.Modified)
        {
            for(Color e : Colors())
            {
            	if(e.getId() == color.getId()){
            		e = color;
            	}
            }
        }
        color.setState(States.Unmodified);         
    }
}
