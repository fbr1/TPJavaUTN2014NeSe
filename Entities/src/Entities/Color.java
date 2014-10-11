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
	public Color(int id){
		super(id);
		this.setNombre(Color.defaultColor);
	}
	public Color(int id, String color){
		super(id);
		this.setNombre(color);
	}
	public Color(String color){
		this.setNombre(color);
	}
	public String toString(){	
		String nombre = Character.toUpperCase(this.nombre.charAt(0)) + this.nombre.substring(1);
		return nombre;
	}
	public boolean equals(Color color){
		if(this.getNombre().equalsIgnoreCase(color.getNombre())){
			return true;
		}else{
			return false;
		}
	}
	
}
