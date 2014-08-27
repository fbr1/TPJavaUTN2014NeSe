package Entities;

public class Color extends Entity{
	
	// Constants
	
	static public final String defaultColor = "blanco";
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Color(){
		super();
		this.setNombre(Color.defaultColor);
	}
	public Color(String color){
		super();
		this.setNombre(color);
	}
}
