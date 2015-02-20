package Entities;
import java.lang.String;

public class ElectroDomestico extends Entity {
	
	// Constants
	static final double defaultPeso=5.0;
	static final Color defaultColor = new Color();
	static final ConsumoEnergetico defaultConsumo = new ConsumoEnergetico();
	static final double defaultPrecio = 100.0;	
	
	// Instance Variables	
	protected double precio_base ; 
	protected Color color ;
	protected ConsumoEnergetico consumoEnergetico ;
	protected double peso; // en kg
	protected String descripcion;
	protected double precioFinal;
	
	// Instance Methods
	public double getPrecio_base() {
		return precio_base;
	}
	public void setPrecio_base(double precio_base) {
		this.precio_base = precio_base;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {	
		this.color = color;
	}
	public ConsumoEnergetico getConsumoEnergetico() {
		return consumoEnergetico;
	}
	public void setConsumoEnergetico(ConsumoEnergetico consumoEnergetico){
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
	public void setPrecioFinal(double precioFinal){
		this.precioFinal = precioFinal;
	}
	public double getPrecioFinal(){
		return this.precioFinal;
	}
	/** ElectroDomestico(String descripcion, double precioBase, double peso, ConsumoEnergetico idConsumoEnergetico, Colorcolor) */
	public ElectroDomestico(){
		super();
		this.setDescripcion("");
		this.setPrecio_base(ElectroDomestico.defaultPrecio);
		this.setColor(ElectroDomestico.defaultColor);
		this.setConsumoEnergetico(ElectroDomestico.defaultConsumo);
		this.setPeso(ElectroDomestico.defaultPeso);
	}
	/** ElectroDomestico(String descripcion, double precioBase, double peso, ConsumoEnergetico idConsumoEnergetico, Color color) */
	public ElectroDomestico(double precio,double peso){
		super();
		this.setPrecio_base(precio);
		this.setPeso(peso);
		this.setConsumoEnergetico(ElectroDomestico.defaultConsumo);
		this.setColor(ElectroDomestico.defaultColor);
	}
	/** ElectroDomestico(String descripcion, double precioBase, double peso, ConsumoEnergetico idConsumoEnergetico, Color color) */
	public ElectroDomestico(String descripcion,double precio, double peso, ConsumoEnergetico consumoEnergetico, Color color)
	{
		super();
		this.setDescripcion(descripcion);
		this.setPrecio_base(precio);
		this.setPeso(peso);
		this.setConsumoEnergetico(consumoEnergetico);
		this.setColor(color);
		
	}
	public double PrecioFinal(PesoPrecio pesoprecio){
		double precioFinal = this.getPrecio_base();
		
		// Consumos
		precioFinal += this.getConsumoEnergetico().getPrecio();
		
		//Peso Precio		
		precioFinal += pesoprecio.getPrecio();
		
		return precioFinal;
	}
	
}
