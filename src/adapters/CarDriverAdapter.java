package adapters;

import java.sql.ResultSet;
import java.util.ArrayList;

import models.CarDriver;



public class CarDriverAdapter {
	
	public void insert(CarDriver driver)
	{
		String sql = "INSERT INTO driver_info VALUES (DEFAULT, '"+driver.getUsername()+"', '"+driver.getStatus()+"', '"+driver.getLicense_id()+"', '"+driver.getCar_type()+"')";
		DataAccess da = DataAccess.Singleton(); 
		da.executeQuery(sql);
	}
	
	public void update_status(CarDriver driver)
	{
		String sql = "UPDATE driver_info SET  status='"+driver.getStatus()+"' WHERE id="+driver.getId();
		DataAccess da = DataAccess.Singleton(); 
		da.executeQuery(sql);
	}
	
	public void update_car(CarDriver driver)
	{
		String sql = "UPDATE driver_info SET  car_type='"+driver.getCar_type()+"' WHERE username='"+driver.getUsername()+"'";
		DataAccess da = DataAccess.Singleton(); 
		da.executeQuery(sql);
	}
	
	public void update(CarDriver driver)
	{
		String sql = "UPDATE driver_info SET  car_type='"+driver.getCar_type()+"',status="+driver.getStatus()+", license_id='"+driver.getLicense_id()+"' WHERE id="+driver.getId();
		DataAccess da = DataAccess.Singleton(); 
		da.executeQuery(sql);
	}
	public void delete(int id)
	{
		String sql = "DELETE FROM driver_info WHERE id="+id;
		DataAccess da = DataAccess.Singleton(); 
		da.executeQuery(sql);
	}
	
	public ArrayList<CarDriver> getAll()
	{
		String sql = "SELECT * FROM driver_info";
		DataAccess da = DataAccess.Singleton(); 
		ResultSet rs = da.getResultSet(sql);
		ArrayList<CarDriver> driverlist = new ArrayList<CarDriver>();
		try {
			while(rs.next())
			{
				
				CarDriver cardriver = new CarDriver();
				cardriver.setId(Integer.parseInt(rs.getString("id")));
				cardriver.setUsername(rs.getString("username"));
				cardriver.setStatus(rs.getString("status"));
				cardriver.setLicense_id(rs.getString("license_id"));
				cardriver.setCar_type(rs.getString("car_type"));
				
				driverlist.add(cardriver);
			}
			return driverlist;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public CarDriver get(int id)
	{
		String sql = "SELECT * FROM driver_info WHERE id="+id;
		DataAccess da = DataAccess.Singleton(); 
		ResultSet rs = da.getResultSet(sql);
		CarDriver cardriver = new CarDriver();
		try {
			if(rs.next())
			{
				cardriver.setId(Integer.parseInt(rs.getString("id")));
				cardriver.setUsername(rs.getString("username"));
				cardriver.setStatus(rs.getString("status"));
				cardriver.setLicense_id(rs.getString("license_id"));
				cardriver.setLicense_id(rs.getString("car_type"));
				
				
				
							
				
				return cardriver;
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
	public CarDriver select(String sql) {
		DataAccess da = DataAccess.Singleton(); 
		ResultSet rs = da.getResultSet(sql);
		CarDriver cardriver = new CarDriver();
		try {
			if(rs.next())
			{
				cardriver.setId(Integer.parseInt(rs.getString("id")));
				cardriver.setUsername(rs.getString("username"));
				cardriver.setStatus(rs.getString("status"));
				cardriver.setLicense_id(rs.getString("license_id"));
				cardriver.setLicense_id(rs.getString("car_type"));
				
					return cardriver;
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
