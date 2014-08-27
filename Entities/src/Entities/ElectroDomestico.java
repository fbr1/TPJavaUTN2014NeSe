package Entities;
import java.lang.String;

public class ElectroDomestico extends Entity{
	
	// Constants
	static final double defaultPeso=5;
	static final String defaultColor = "blanco";
	static final char defaultConsumo = 'F';
	static final double defaultPrecio = 100.0;	
	
	// Instance Variables	
	protected double precio_base ; 
	protected String color ;
	protected char consumoEnergetico ;
	protected double peso; // en kg
	protected String descripcion;
	
	// Instance Methods
	public double getPrecio_base() {
		return precio_base;
	}
	public void setPrecio_base(double precio_base) {
		this.precio_base = precio_base;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {	
		this.color = color;
	}
	public char getConsumoEnergetico() {
		return consumoEnergetico;
	}
	public void setConsumoEnergetico(char consumoEnergetico){
		this.consumoEnergetico = consumoEnergetico; 
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
	public String getDescripcion(){
		return this.descripcion;
	}
	/** ElectroDomestico(double precioBase, double peso, char idConsumoEnergetico, String color) */
	public ElectroDomestico(){
		super();
		this.setPrecio_base(ElectroDomestico.defaultPrecio);
		this.setColor(ElectroDomestico.defaultColor);
		this.setConsumoEnergetico(ElectroDomestico.defaultConsumo);
		this.setPeso(ElectroDomestico.defaultPeso);
	}
	/** ElectroDomestico(double precioBase, double peso, char idConsumoEnergetico, String color) */
	public ElectroDomestico(double precio,double peso){
		super();
		this.setPrecio_base(precio);
		this.setPeso(peso);
		this.setConsumoEnergetico(ElectroDomestico.defaultConsumo);
		this.setColor(ElectroDomestico.defaultColor);
	}
	/** ElectroDomestico(double precioBase, double peso, char idConsumoEnergetico, String color) */
	public ElectroDomestico(double precio, double peso, char consumoEnergetico, String color)
	{
		super();
		this.setPrecio_base(precio);
		this.setPeso(peso);
		this.setConsumoEnergetico(consumoEnergetico);
		this.setColor(color);
		
	}	

	
	
}
