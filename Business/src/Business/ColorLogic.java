package Business;

import java.util.ArrayList;

import Data.ColorAdapter;
import Entities.Color;

public class ColorLogic {
	
	private ColorAdapter colorData;
	
	public ColorLogic(){
		colorData = new ColorAdapter();		
	}

	public ColorAdapter ColorData() {
		return colorData;
	}

	public void setColorData(ColorAdapter colorData) {
		this.colorData = colorData;
	}
	
	public ArrayList<Color> getAll() throws Exception
	{
		return ColorData().getAll();
	}

	public Color getOne(int id) throws Exception{
		return ColorData().getOne(id);
	}
	
	public void save(Color color) throws Exception{
		ColorData().save(color);
	}	

	public void delete(int id) throws Exception{
		ColorData().delete(id);
	}	

}
