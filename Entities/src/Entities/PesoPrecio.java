package Entities;

public class PesoPrecio extends Entity{
	
	// Variables
	private double peso_min;
	private double peso_max;
	private double precio;
	
	// Get & Set
	
	public double getPeso_min() {
		return peso_min;
	}
	private void setPeso_min(double peso_min) {
		this.peso_min = peso_min;
	}
	public double getPeso_max() {
		return peso_max;
	}
	private void setPeso_max(double peso_max) {
		this.peso_max = peso_max;
	}
	public double getPrecio() {
		return precio;
	}
	private void setPrecio(double precio) {
		this.precio = precio;
	}
	
	// Methods	
	//* public PesoPrecio(double peso_min, double peso_max, double precio) /
	public PesoPrecio(double peso_min, double peso_max, double precio) throws Exception{
		super();
		if(peso_min>peso_max) 
		{
			throw new Exception("Peso minimo es mayor que peso maximo");
		}
		else{
			this.setPeso_min(peso_min);
			this.setPeso_max(peso_max);
			this.setPrecio(precio);		
		}
	}
}
