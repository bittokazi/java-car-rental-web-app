package adapters;

import java.sql.ResultSet;
import java.util.ArrayList;

import models.Cost;

public class CostAdapter {

	public void insert(Cost cost)
	{
		String sql = "INSERT INTO cost VALUES (DEFAULT, '"+cost.getType()+"', '"+cost.getCost()+"', '"+cost.getName()+"')";
		DataAccess da = DataAccess.Singleton();
		da.executeQuery(sql);
	}

	public void update(Cost cost)
	{
		String sql = "UPDATE cost SET name='"+cost.getName()+"', type='"+cost.getType()+"', cost='"+cost.getCost()+"' WHERE id="+cost.getId();
		DataAccess da = DataAccess.Singleton();
		da.executeQuery(sql);
	}
	public void delete(int id)
	{
		String sql = "DELETE FROM cost WHERE id="+id;
		DataAccess da = DataAccess.Singleton();
		da.executeQuery(sql);
	}

	public ArrayList<Cost> getAll()
	{
		String sql = "SELECT * FROM cost";
		DataAccess da = DataAccess.Singleton();
		ResultSet rs = da.getResultSet(sql);
		ArrayList<Cost> costlist = new ArrayList<Cost>();
		try {
			while(rs.next())
			{

				Cost cost = new Cost();
				cost.setId(Integer.parseInt(rs.getString("id")));
				cost.setType(rs.getString("type"));
				cost.setCost(rs.getString("cost"));
				cost.setName(rs.getString("name"));



				costlist.add(cost);
			}
			return costlist;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Cost get(int id)
	{
		String sql = "SELECT * FROM cost WHERE id="+id;
		DataAccess da = DataAccess.Singleton();
		ResultSet rs = da.getResultSet(sql);
		 Cost cost = new  Cost();
		try {
			if(rs.next())
			{




				cost.setId(Integer.parseInt(rs.getString("id")));
				cost.setName(rs.getString("name"));
				cost.setType(rs.getString("type"));
				cost.setCost(rs.getString("cost"));



				return cost;
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
	public Cost select(String sql) {
		DataAccess da = DataAccess.Singleton();
		ResultSet rs = da.getResultSet(sql);
		Cost cost = new  Cost();
		try {
			if(rs.next())
			{
				cost.setId(Integer.parseInt(rs.getString("id")));
				cost.setName(rs.getString("name"));
				cost.setType(rs.getString("type"));
				cost.setCost(rs.getString("cost"));
				return cost;
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
