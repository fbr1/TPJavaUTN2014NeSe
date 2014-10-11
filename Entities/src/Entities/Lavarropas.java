package Entities;


public class Lavarropas extends ElectroDomestico{
	// Constants
	static final double defaultCarga = 5.0;
	static final double MIN_CARGA = 30; 
	static final double RECARGO_CARGA = 50;
	private double carga;
	// Get & Set
	public double getCarga() {
		return carga;
	}
	public void setCarga(double carga) {
		this.carga = carga;
	}
	
	// Methods
	//* Lavarropas(double precio, double peso,ConsumoEnergetico consumoEnergetico, Color color, double carga) /
	public Lavarropas(){
		super();
		this.setCarga(Lavarropas.defaultCarga);
	}
	//* Lavarropas(double precio, double peso,ConsumoEnergetico consumoEnergetico, Color color, double carga) /
	public Lavarropas(double precio, double peso) {
		super(precio, peso);
		this.setCarga(Lavarropas.defaultCarga);
	}
	//* Lavarropas(double precio, double peso,char consumoEnergetico, Color color, double carga) /
	public Lavarropas(String descripcion,double precio, double peso,
			ConsumoEnergetico consumoEnergetico, Color color, double carga) {
		super(descripcion, precio, peso, consumoEnergetico, color);		
		this.setCarga(carga);
	}
	public double PrecioFinal(PesoPrecio pesoprecio){
		double precioFinal = super.PrecioFinal(pesoprecio);
		if(this.getCarga() > Lavarropas.MIN_CARGA ) { precioFinal += Lavarropas.RECARGO_CARGA ;}
		return precioFinal;
	}
}
