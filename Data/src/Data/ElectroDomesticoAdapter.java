package Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entities.Color;
import Entities.ConsumoEnergetico;
import Entities.ElectroDomestico;
import Entities.Lavarropas;
import Entities.Television;

public class ElectroDomesticoAdapter{
	
	public ArrayList<ElectroDomestico> getAll() throws Exception
    {
        ArrayList<ElectroDomestico> electroDomesticos = new ArrayList<ElectroDomestico>();
        Statement statement=null;
        ResultSet rs=null;
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.createStatement();
        	rs = statement.executeQuery("SELECT elec.id_electrodomestico, elec.descripcion, col.nombre_color,con.nombre, con.precio, elec.precio_base, "
        							  + "elec.id_color, elec.id_consumo, elec.peso, elec.resolucion, elec.TDT, elec.carga "
        							  + "FROM electrodomesticos elec "
        							  + "INNER JOIN colores col on col.id_color = elec.id_color "
        							  + "INNER JOIN consumosenergeticos con on con.id_consumos = elec.id_consumo ");

            while (rs.next())
            {
            	ElectroDomestico elecDom = null;
            	if(rs.getString("elec.carga") == null){
            		elecDom = new Television();
            		((Television) elecDom).setResolucion(rs.getDouble("elec.resolucion"));
                	((Television) elecDom).setSinTDT(rs.getBoolean("elec.TDT")); 
            	}else{
            		elecDom = new Lavarropas();
            		((Lavarropas) elecDom).setCarga(rs.getDouble("elec.carga"));     
            	}
            	elecDom.setId(rs.getInt("elec.id_electrodomestico"));
            	elecDom.setDescripcion(rs.getString("elec.descripcion"));
            	elecDom.setColor(new Color(rs.getInt("elec.id_color"),rs.getString("col.nombre_color")));
            	elecDom.setConsumoEnergetico(new ConsumoEnergetico(rs.getInt("elec.id_consumo"),rs.getString("con.nombre").charAt(0),rs.getDouble("con.precio")));
            	elecDom.setPrecio_base(rs.getDouble("elec.precio_base"));
            	elecDom.setPeso(rs.getDouble("elec.peso"));            	           	
            	electroDomesticos.add(elecDom);
            }
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al recuperar datos de las televisiones", Ex);
        }
        finally
        {
        	try{
        		if(rs!=null){rs.close();}
        		if(statement!=null && !statement.isClosed()){statement.close();}
        		DataConnectionManager.getInstancia().CloseConn();
        	}
        	catch (SQLException sqle){
        		sqle.printStackTrace();
        	}
        }
        return electroDomesticos;
    }
	
	public ArrayList<ElectroDomestico> getAll(char consumo) throws Exception
    {
        ArrayList<ElectroDomestico> electroDomesticos = new ArrayList<ElectroDomestico>();
        PreparedStatement statement=null;
        ResultSet rs=null;
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();        	
        	statement = conn.prepareStatement("SELECT elec.id_electrodomestico, elec.descripcion, col.nombre_color,con.nombre, con.precio, elec.precio_base, "
        							  + "elec.id_color, elec.id_consumo, elec.peso, elec.resolucion, elec.TDT, elec.carga "
        							  + "FROM electrodomesticos elec "
        							  + "INNER JOIN colores col on col.id_color = elec.id_color "
        							  + "INNER JOIN consumosenergeticos con on con.id_consumos = elec.id_consumo "
        							  + "WHERE con.nombre=?");
        	statement.setString(1, String.valueOf(consumo));
        	rs = statement.executeQuery();  
        	
            while (rs.next())
            {
            	ElectroDomestico elecDom = null;
            	if(rs.getString("elec.carga") == null){
            		elecDom = new Television();
            		((Television) elecDom).setResolucion(rs.getDouble("elec.resolucion"));
                	((Television) elecDom).setSinTDT(rs.getBoolean("elec.TDT")); 
            	}else{
            		elecDom = new Lavarropas();
            		((Lavarropas) elecDom).setCarga(rs.getDouble("elec.carga"));     
            	}
            	elecDom.setId(rs.getInt("elec.id_electrodomestico"));
            	elecDom.setDescripcion(rs.getString("elec.descripcion"));
            	elecDom.setColor(new Color(rs.getInt("elec.id_color"),rs.getString("col.nombre_color")));
            	elecDom.setConsumoEnergetico(new ConsumoEnergetico(rs.getInt("elec.id_consumo"),rs.getString("con.nombre").charAt(0),rs.getDouble("con.precio")));
            	elecDom.setPrecio_base(rs.getDouble("elec.precio_base"));
            	elecDom.setPeso(rs.getDouble("elec.peso"));            	           	
            	electroDomesticos.add(elecDom);
            }
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al recuperar datos de las televisiones", Ex);
        }
        finally
        {
        	try{
        		if(rs!=null){rs.close();}
        		if(statement!=null && !statement.isClosed()){statement.close();}
        		DataConnectionManager.getInstancia().CloseConn();
        	}
        	catch (SQLException sqle){
        		sqle.printStackTrace();
        	}
        }
        return electroDomesticos;
    }

    public ElectroDomestico getOne(int ID) throws Exception
    {    	
    	ElectroDomestico elecDom = null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("SELECT elec.id_electrodomestico, elec.descripcion, col.nombre_color, con.nombre, con.precio, elec.precio_base, "
        		   	  + "elec.id_color, elec.id_consumo, elec.peso, elec.resolucion, elec.TDT, elec.carga "
					  + "FROM electrodomesticos elec "
					  + "INNER JOIN colores col on col.id_color = elec.id_color "
					  + "INNER JOIN consumosenergeticos con on con.id_consumos = elec.id_consumo "
					  + "WHERE elec.id_electrodomestico = ?");  
        	statement.setInt(1, ID);
        	rs = statement.executeQuery();     	
        	
            while (rs.next())
            {
            	if(rs.getString("elec.carga") == null){
            		elecDom = new Television();
            		((Television) elecDom).setResolucion(rs.getDouble("elec.resolucion"));
                	((Television) elecDom).setSinTDT(rs.getBoolean("elec.TDT")); 
            	}else{
            		elecDom = new Lavarropas();
            		((Lavarropas) elecDom).setCarga(rs.getDouble("elec.carga"));     
            	}
            	elecDom.setId(rs.getInt("elec.id_electrodomestico"));
            	elecDom.setDescripcion(rs.getString("elec.descripcion"));
            	elecDom.setColor(new Color(rs.getInt("elec.id_color"),rs.getString("col.nombre_color")));
            	elecDom.setConsumoEnergetico(new ConsumoEnergetico(rs.getInt("elec.id_consumo"),rs.getString("con.nombre").charAt(0),rs.getDouble("con.precio")));
            	elecDom.setPrecio_base(rs.getDouble("elec.precio_base"));
            	elecDom.setPeso(rs.getDouble("elec.peso"));           	           	
            	
            }
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al recuperar la television por ID", Ex);
        }
        finally
        {
        	try{
        		if(rs!=null){rs.close();}
        		if(statement!=null && !statement.isClosed()){statement.close();}
        		DataConnectionManager.getInstancia().CloseConn();
        	}
        	catch (SQLException sqle){
        		sqle.printStackTrace();
        	}
        }
        return elecDom;
    } 




}
