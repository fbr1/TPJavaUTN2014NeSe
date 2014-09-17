package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entities.Lavarropas;
import Entities.Entity.States;

public class LavarropasAdapter{

	public ArrayList<Lavarropas> getAll() throws Exception
    {
        ArrayList<Lavarropas> lavarropas = new ArrayList<Lavarropas>();
        Statement statement=null;
        ResultSet rs=null;
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.createStatement();
        	rs = statement.executeQuery("SELECT elec.id_electrodomestico, elec.descripcion, col.nombre_color,con.nombre, elec.precio_base, elec.peso, elec.carga "
					  + "FROM electrodomesticos elec "
					  + "INNER JOIN colores col on (col.id_color = elec.id_color) "
					  + "INNER JOIN consumosenergeticos con on con.id_consumos = elec.id_consumo "
					  + "WHERE elec.carga IS NOT NULL");       	


            while (rs.next())
            {
            	Lavarropas lavarropa = new Lavarropas();
            	lavarropa.setId(rs.getInt("elec.id_electrodomestico"));
            	lavarropa.setDescripcion(rs.getString("elec.descripcion"));
            	lavarropa.setColor(rs.getString("col.nombre_color"));
            	lavarropa.setConsumoEnergetico(rs.getString("con.nombre").charAt(0));
            	lavarropa.setPrecio_base(rs.getDouble("elec.precio_base"));
            	lavarropa.setPeso(rs.getDouble("elec.peso"));
            	lavarropa.setCarga(rs.getDouble("elec.carga"));
            	lavarropas.add(lavarropa);
            }
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al recuperar datos de las lavarropas", Ex);
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
        return lavarropas;
    }

    public Lavarropas getOne(int ID) throws Exception
    {    	
        Lavarropas lavarropa = null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("SELECT elec.id_electrodomestico, elec.descripcion, col.nombre_color,con.nombre, con.nombre,elec.precio_base, elec.peso, elec.carga "
					  + "FROM electrodomesticos elec "
					  + "INNER JOIN colores col on col.id_color = elec.id_color "
					  + "INNER JOIN consumosenergeticos con on con.id_consumos = elec.id_consumo "
					  + "WHERE elec.carga IS NOT NULL AND elec.id_electrodomestico = ?");  
        	statement.setInt(1, ID);
        	rs = statement.executeQuery();     	
        	
        	if(rs.next()){
        		lavarropa = new Lavarropas();
            	lavarropa.setId(rs.getInt("elec.id_electrodomestico"));
            	lavarropa.setDescripcion(rs.getString("elec.descripcion"));
            	lavarropa.setColor(rs.getString("col.nombre_color"));
            	lavarropa.setConsumoEnergetico(rs.getString("con.nombre").charAt(0));
            	lavarropa.setPrecio_base(rs.getDouble("elec.precio_base"));
            	lavarropa.setPeso(rs.getDouble("elec.peso"));
            	lavarropa.setCarga(rs.getDouble("elec.carga"));

        	}
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al recuperar el lavarropa por ID", Ex);
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
        return lavarropa;
    } 

    public void delete(int ID) throws Exception
    {
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("DELETE FROM electrodomesticos WHERE id_electrodomestico=?");
        	statement.setInt(1, ID);
        	statement.executeUpdate();  	
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al eliminar el lavarropa", Ex);
        }
        finally
        {
        	try{
        		if(statement!=null && !statement.isClosed()){statement.close();}
        		DataConnectionManager.getInstancia().CloseConn();
        	}
        	catch (SQLException sqle){
        		sqle.printStackTrace();
        	}
        }  
    }

    public void save(Lavarropas lavarropa) throws Exception
    {
        if (lavarropa.getState() == States.New)
        {
        	this.insert(lavarropa);
        }
        else if (lavarropa.getState() == States.Deleted)
        {
            this.delete(lavarropa.getId());
        }
        else if (lavarropa.getState() == States.Modified)
        {
        	this.update(lavarropa);
        }
        lavarropa.setState(States.Unmodified);         
    }
    
    public void update(Lavarropas lavarropa) throws Exception{
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("UPDATE electrodomesticos SET descripcion=?, id_color=(SELECT id_color FROM colores WHERE nombre_color=?), "
        									+ "id_consumo =( SELECT id_consumos FROM consumosenergeticos WHERE nombre=?), precio_base=?,peso=?,carga=? WHERE id_electrodomestico=?");
        	statement.setString(1, lavarropa.getDescripcion());
        	statement.setString(2, lavarropa.getColor());
        	statement.setString(3, String.valueOf(lavarropa.getConsumoEnergetico()));
        	statement.setDouble(4, lavarropa.getPrecio_base());
        	statement.setDouble(5, lavarropa.getPeso());
        	statement.setDouble(6, lavarropa.getCarga());
        	statement.setInt(7, lavarropa.getId());        	
        	statement.executeUpdate();  	
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al modificar el lavarropa", Ex);
        }
        finally
        {
        	try{
        		if(statement!=null && !statement.isClosed()){statement.close();}
        		DataConnectionManager.getInstancia().CloseConn();
        	}
        	catch (SQLException sqle){
        		sqle.printStackTrace();
        	}
        }      
    }
    
    public void insert(Lavarropas lavarropa) throws Exception{  	
        PreparedStatement statement=null;
        
        try
        {
        	Connection conn = DataConnectionManager.getInstancia().getConn();
        	statement = conn.prepareStatement("INSERT INTO electrodomesticos(descripcion,id_color,id_consumo,precio_base,peso,carga) "
        									+ "values(?,(SELECT id_color FROM colores WHERE nombre_color=?), (SELECT id_consumos FROM consumosenergeticos WHERE nombre=?),?,?,?) ", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, lavarropa.getDescripcion());
			statement.setString(2, lavarropa.getColor());
			statement.setString(3, String.valueOf(lavarropa.getConsumoEnergetico()));
			statement.setDouble(4, lavarropa.getPrecio_base());
			statement.setDouble(5, lavarropa.getPeso());
			statement.setDouble(6, lavarropa.getCarga());
        	statement.executeUpdate();  	
        	ResultSet rs = statement.getGeneratedKeys();
        	if(rs.next()){
        		lavarropa.setId(rs.getInt(1));
        	}
        }
        catch (Exception Ex)
        {                
            throw new Exception("Error al crear el lavarropa", Ex);
        }
        finally
        {
        	try{
        		if(statement!=null && !statement.isClosed()){statement.close();}
        		DataConnectionManager.getInstancia().CloseConn();
        	}
        	catch (SQLException sqle){
        		sqle.printStackTrace();
        	}
        }  
    }
}
