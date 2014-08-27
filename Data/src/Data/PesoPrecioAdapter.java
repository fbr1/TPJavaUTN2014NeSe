package Data;

import java.util.ArrayList;

import Entities.PesoPrecio;
import Entities.Entity.States;

public class PesoPrecioAdapter extends Adapter{
	
	private static ArrayList<PesoPrecio> pesoPrecios;
	
	private static ArrayList<PesoPrecio> PesoPrecios() {
		if ( pesoPrecios == null){
			pesoPrecios = new ArrayList<PesoPrecio>();
			PesoPrecio pesoPrecio;
			try {
				pesoPrecio = new PesoPrecio(0, 19, 10);
				pesoPrecio.setState(States.Unmodified);
				pesoPrecio.setId(0);
				pesoPrecios.add(pesoPrecio);
				pesoPrecio = new PesoPrecio(20, 49, 50);
				pesoPrecio.setState(States.Unmodified);
				pesoPrecio.setId(1);
				pesoPrecios.add(pesoPrecio);
				pesoPrecio = new PesoPrecio(50, 79, 80);
				pesoPrecio.setState(States.Unmodified);
				pesoPrecio.setId(2);
				pesoPrecios.add(pesoPrecio);
				pesoPrecio = new PesoPrecio(80, Double.MAX_VALUE, 100);
				pesoPrecio.setState(States.Unmodified);
				pesoPrecio.setId(3);
				pesoPrecios.add(pesoPrecio);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}	
		}
		return pesoPrecios;
	}

    public ArrayList<PesoPrecio> getAll()
    {
        return PesoPrecios();
    }

    public PesoPrecio getOne(int ID)
    {    	
        for(PesoPrecio e : PesoPrecios()){
        	if( e.getId() == ID) return e;
        }
        return null;
    }

    public void delete(int ID)
    {
    	PesoPrecios().remove(this.getOne(ID));        
    }

    public void save(PesoPrecio pesoPrecio)
    {
        if (pesoPrecio.getState() == States.New)
        {
            int NextID = 0;
            for(PesoPrecio e : PesoPrecios())
            {
                if (e.getId() > NextID)
                {
                    NextID = e.getId();
                }
            }
            pesoPrecio.setId(NextID + 1);
            PesoPrecios().add(pesoPrecio);
        }
        else if (pesoPrecio.getState() == States.Deleted)
        {
            this.delete(pesoPrecio.getId());
        }
        else if (pesoPrecio.getState() == States.Modified)
        {
            for(PesoPrecio e : PesoPrecios())
            {
            	if(e.getId() == pesoPrecio.getId()){
            		e = pesoPrecio;
            	}
            }
        }
        pesoPrecio.setState(States.Unmodified);         
    }
}
