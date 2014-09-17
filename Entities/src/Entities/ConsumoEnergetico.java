package Entities;

public class ConsumoEnergetico extends Entity{	
	
		// Constants
		public static final char defaultId = 'F';
		
		// Variables
		private double precio;	
		
		// Get & Set
		public double getPrecio() {
			return precio;
		}
		public void setPrecio(double precio) {
			this.precio = precio;
		}
		// Methods
		/** ConsumoEnergetico(char id, double precio) */
		public ConsumoEnergetico(){	
			super();
			this.setId(ConsumoEnergetico.defaultId);
		}
		/** ConsumoEnergetico(char id, double precio) */
		public ConsumoEnergetico(char id, double precio){
			super();
			this.setId(id);
			this.setPrecio(precio);
		}
}
