package Entities;

public class ConsumoEnergetico extends Entity{	
	
		// Constants
		public static final char defaultNombre = 'F';
		
		// Variables
		private double precio;	
		private char nombre;
		
		// Get & Set
		public double getPrecio() {
			return precio;
		}
		public void setPrecio(double precio) {
			this.precio = precio;
		}
		
		public char getNombre() {
			return nombre;
		}
		public void setNombre(char nombre) {
			this.nombre = nombre;
		}
		// Methods
		/** ConsumoEnergetico(char nombre, double precio) */
		public ConsumoEnergetico(){	
			super();
			this.setNombre(ConsumoEnergetico.defaultNombre);
		}
		/** ConsumoEnergetico(char nombre, double precio) */
		public ConsumoEnergetico(char nombre, double precio){
			super();
			this.setNombre(nombre);
			this.setPrecio(precio);
		}
}
