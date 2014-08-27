package Entities;

public class Lavarropas extends ElectroDomestico{
	// Constants
	static final double defaultCarga = 5.0;
	private double carga;
	// Get & Set
	public double getCarga() {
		return carga;
	}
	private void setCarga(double carga) {
		this.carga = carga;
	}
	
	// Methods
	//* Lavarropas(double precio, double peso,char consumoEnergetico, String color, double carga) /
	public Lavarropas(){
		super();
		this.setCarga(Lavarropas.defaultCarga);
	}
	//* Lavarropas(double precio, double peso,char consumoEnergetico, String color, double carga) /
	public Lavarropas(double precio, double peso) {
		super(precio, peso);
		this.setCarga(Lavarropas.defaultCarga);
	}
	//* Lavarropas(double precio, double peso,char consumoEnergetico, String color, double carga) /
	public Lavarropas(double precio, double peso,
			char consumoEnergetico, String color, double carga) {
		super(precio, peso, consumoEnergetico, color);		
		this.setCarga(carga);
	}		
}