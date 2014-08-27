import Business.ElectroDomesticoLogic;

public class ElectroDomesticos {
	
	private ElectroDomesticoLogic electroDomesticos;
	
	public ElectroDomesticos(){
		electroDomesticos = new ElectroDomesticoLogic();
		for (Entities.ElectroDomestico e : electroDomesticos.getAll()){
			mostrar(e);
		}
	}	
	private void mostrar(Entities.ElectroDomestico ed){
		System.out.println(ed.getColor());
		System.out.println(ed.getConsumoEnergetico());
		System.out.println(ed.getPeso());
		System.out.println(ed.getPrecio_base());
	}
}
