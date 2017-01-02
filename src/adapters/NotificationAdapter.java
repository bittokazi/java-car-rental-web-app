package adapters;

import models.Invoice;
import models.Notification;

import java.sql.ResultSet;
import java.util.ArrayList;

public class NotificationAdapter {
	public void insert(Notification nf)
	{
		String sql = "INSERT INTO notification VALUES (null, '"+nf.getUsername()+"', '"+nf.getDescription()+"', '"+nf.getDate()+"', '"+nf.getTime()+"')";
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}
	public void delete(int id)
	{
		String sql = "DELETE FROM notification WHERE id="+id;
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}
	public ArrayList<Notification> select_query(String sql)
	{
		DataAccess da = new DataAccess();
		ResultSet rs = da.getResultSet(sql);
		ArrayList<Notification> nf = new ArrayList<Notification>();
		try {
			while(rs.next())
			{
				Notification n = new Notification();
				n.setId(Integer.parseInt(rs.getString("id")));
                n.setUsername(rs.getString("username"));
                n.setDescription(rs.getString("description"));
                n.setDate(rs.getString("date"));
				n.setTime(rs.getString("time"));

				nf.add(n);
			}
			return nf;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
