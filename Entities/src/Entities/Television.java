package Entities;

public class Television extends ElectroDomestico{
	
	// Constants
	static final double defaultResolucion = 20.0;
	static final boolean defaultSinTDT = false;
	static final int PORCENTAJE_RECARGO = 30;
	
	//Get & Set
	public double getResolucion() {
		return resolucion;
	}

	private void setResolucion(double resolucion) {
		this.resolucion = resolucion;
	}

	public boolean tieneSinTDT() {
		return sinTDT;
	}

	private void setSinTDT(boolean sinTDT) {
		this.sinTDT = sinTDT;
	}
	
	// Variables
	private double resolucion;
	private boolean sinTDT;
	
	// Methods	
	//*Television(double precio, double peso, char consumoEnergetico, String color, double resolucion, boolean sinTDT) /
	public Television(){
		super();
		this.setResolucion(Television.defaultResolucion);
		this.setSinTDT(Television.defaultSinTDT);
	}
	//*Television(double precio, double peso, char consumoEnergetico, String color, double resolucion, boolean sinTDT) /
	public Television(double precio, double peso){
		super(precio,peso);
		this.setResolucion(Television.defaultResolucion);
		this.setSinTDT(Television.defaultSinTDT);
	}
	//*Television(double precio, double peso, char consumoEnergetico, String color, double resolucion, boolean sinTDT) /
	public Television(double precio, double peso, char consumoEnergetico,
			String color, double resolucion, boolean sinTDT) 
	{
		super(precio, peso, consumoEnergetico, color); 
		this.setResolucion(resolucion);
		this.setSinTDT(sinTDT);		
	}



	
}
