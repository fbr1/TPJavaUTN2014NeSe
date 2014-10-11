package Entities;

public class ConsumoEnergetico extends Entity{	
	
		// Constants
		public static final char defaultNombre = 'A';
		public static final double defaultPrecio = 10.0 ;
		
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
			this.setPrecio(ConsumoEnergetico.defaultPrecio);
		}
		/** ConsumoEnergetico(char nombre, double precio) */
		public ConsumoEnergetico(int id){	
			super(id);
			this.setNombre(ConsumoEnergetico.defaultNombre);
			this.setPrecio(ConsumoEnergetico.defaultPrecio);
		}
		/** ConsumoEnergetico(char nombre, double precio) */
		public ConsumoEnergetico(int id,char nombre, double precio){
			super(id);
			this.setNombre(nombre);
			this.setPrecio(precio);
		}
		/** ConsumoEnergetico(char nombre, double precio) */
		public ConsumoEnergetico(char nombre, double precio){
			this.setNombre(nombre);
			this.setPrecio(precio);
		}
		public String toString(){		
			return String.valueOf(this.getNombre());		
		}
		public boolean equals(ConsumoEnergetico consumo){
			if(Character.compare(this.getNombre(), consumo.getNombre()) == 0){
				return true;
			}else{
				return false;
			}
		}
}
