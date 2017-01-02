package adapters;

import java.sql.ResultSet;
import java.util.ArrayList;

import models.Places;

public class PlacesAdapter {
	
	public void insert(Places place)
	{
		String sql = "INSERT INTO places VALUES (null, '"+place.getName()+"')";
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}
	
	public void update(Places place)
	{
		String sql = "UPDATE places SET name='"+place.getName()+"' WHERE id="+place.getId();
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}
	public void delete(int id)
	{
		String sql = "DELETE FROM places WHERE id="+id;
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}
	
	public ArrayList<Places> getAll()
	{
		String sql = "SELECT * FROM places";
		DataAccess da = new DataAccess();
		ResultSet rs = da.getResultSet(sql);
		ArrayList<Places> placelist = new ArrayList<Places>();
		try {
			while(rs.next())
			{
				Places place = new Places();
				place.setId((rs.getInt("id")));
				place.setName((rs.getString("name")));
				
				
	
				
				placelist.add(place);
			}
			return placelist;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Places get(int id)
	{
		String sql = "SELECT * FROM places WHERE id="+id;
		DataAccess da = new DataAccess();
		ResultSet rs = da.getResultSet(sql);
		
		
		Places place = new Places();
		try {
			if(rs.next())
			{
				
				
				place.setId(rs.getInt("id"));
				place.setName(rs.getString("name"));
							
				
				return place;
			}
			else
			{
				return null;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Places select(String sql) {
		DataAccess da = new DataAccess();
		ResultSet rs = da.getResultSet(sql);
		Places place = new Places();

		
		try {
			if(rs.next())
			{
				place.setId(rs.getInt("id"));
				place.setName(rs.getString("name"));
					return place;
			}
			else
			{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
