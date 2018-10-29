package adapters;

import java.sql.ResultSet;
import java.util.ArrayList;
import models.Invoice;
public class InvoiceAdapter {
	
	public int insert(Invoice invoice)
	{
		String sql = "INSERT INTO invoice VALUES (DEFAULT, '"+invoice.getUid()+"',' "+invoice.getType()+"','"+invoice.getRider_username()+"', '"+invoice.getDriver_username()+"','"+invoice.getFrom_place()+"','"+invoice.getTo_place()+"','"+invoice.getPayment_status()+"','"+invoice.getDelivery_status()+"','"+invoice.getDistance()+"','"+invoice.getCost()+"','"+invoice.getAddress()+"','"+invoice.getDate()+"','"+invoice.getTime()+"','"+invoice.getCreate_date()+"', '"+invoice.getName()+"', '"+invoice.getCell()+"', '"+invoice.getEmail()+"')";
		DataAccess da = new DataAccess();
		return da.executeQuery(sql);
	}
	
	/*public void update(User user)
	{
		String sql = "UPDATE user SET name='"+user.getName()+"', salary="+emp.getSalary()+", email='"+emp.getEmail()+"' WHERE id="+emp.getId();
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}*/
	
	public void accept_ride(Invoice invoice, String driver) {
		String sql = "UPDATE invoice SET driver_username='"+driver+"', delivery_status='accepted' WHERE id='"+invoice.getId()+"'";
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}
	public void done_ride(Invoice invoice, String driver) {
		String sql = "UPDATE invoice SET payment_status='accepted' WHERE id='"+invoice.getId()+"' AND driver_username='"+driver+"'";
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}
	public void delete(String sql)
	{
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}
	
	public ArrayList<Invoice> getAll()
	{
		String sql = "SELECT * FROM invoice";
		DataAccess da = new DataAccess();
		ResultSet rs = da.getResultSet(sql);
		ArrayList<Invoice> invoicelist = new ArrayList<Invoice>();
		try {
			while(rs.next())
			{
				
				
				Invoice invoice = new Invoice();
				invoice.setId(Integer.parseInt(rs.getString("id")));
				invoice.setUserid(rs.getString("uid"));
				invoice.setType(rs.getString("type"));
                invoice.setRider_username(rs.getString("rider_username"));
                invoice.setDriver_username(rs.getString("driver_username"));
                invoice.setFrom_place(rs.getString("from_place"));
				invoice.setTo_place(rs.getString("to_place"));
			    invoice.setPayment_status(rs.getString("payment_status"));
                invoice.setDelivery_status(rs.getString("deliver_status"));
                invoice.setDistance(rs.getString("distance"));
                invoice.setCost(rs.getString("cost"));
                invoice.setAddress(rs.getString("address"));
                invoice.setDate(rs.getString("date"));
                invoice.setTime(rs.getString("time"));

                

				
	
				
				invoicelist.add(invoice);
			}
			return invoicelist;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Invoice get(int id)
	{
		String sql = "SELECT * FROM invoice WHERE id="+id;
		DataAccess da = new DataAccess();
		ResultSet rs = da.getResultSet(sql);
		
		Invoice invoice = new Invoice();

		
		try {
			if(rs.next())
			{
				
				
				invoice.setId(Integer.parseInt(rs.getString("id")));
				invoice.setUserid(rs.getString("uid"));
				invoice.setType(rs.getString("type"));
                invoice.setRider_username(rs.getString("rider_username"));
                invoice.setDriver_username(rs.getString("driver_username"));
                invoice.setFrom_place(rs.getString("from_place"));
				invoice.setTo_place(rs.getString("to_place"));
			    invoice.setPayment_status(rs.getString("payment_status"));
                invoice.setDelivery_status(rs.getString("delivery_status"));
                invoice.setDistance(rs.getString("distance"));
                invoice.setCost(rs.getString("cost"));
                invoice.setAddress(rs.getString("address"));
                invoice.setDate(rs.getString("date"));
                invoice.setTime(rs.getString("time"));
				invoice.setCreate_date(rs.getString("create_date"));
				invoice.setName(rs.getString("name"));
				invoice.setCell(rs.getString("cell"));
				invoice.setEmail(rs.getString("email"));



                

				
				
				return invoice;
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
	public Invoice select(String sql) {
		DataAccess da = new DataAccess();
		ResultSet rs = da.getResultSet(sql);
		Invoice invoice = new  Invoice();
		try {
			if(rs.next())
			{
				invoice.setId(Integer.parseInt(rs.getString("id")));
				invoice.setUserid(rs.getString("uid"));
				invoice.setType(rs.getString("type"));
                invoice.setRider_username(rs.getString("rider_username"));
                invoice.setDriver_username(rs.getString("driver_username"));
                invoice.setFrom_place(rs.getString("from_place"));
				invoice.setTo_place(rs.getString("to_place"));
			    invoice.setPayment_status(rs.getString("payment_status"));
                invoice.setDelivery_status(rs.getString("delivery_status"));
                invoice.setDistance(rs.getString("distance"));
                invoice.setCost(rs.getString("cost"));
                invoice.setAddress(rs.getString("address"));
                invoice.setDate(rs.getString("date"));
                invoice.setTime(rs.getString("time"));
				invoice.setCreate_date(rs.getString("create_date"));
				invoice.setName(rs.getString("name"));
				invoice.setCell(rs.getString("cell"));
				invoice.setEmail(rs.getString("email"));


                
                return invoice;
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



	
	public ArrayList<Invoice> select_query(String sql)
	{
		DataAccess da = new DataAccess();
		ResultSet rs = da.getResultSet(sql);
		ArrayList<Invoice> invoicelist = new ArrayList<Invoice>();
		try {
			while(rs.next())
			{
				Invoice invoice = new Invoice();
				invoice.setId(Integer.parseInt(rs.getString("id")));
				invoice.setUserid(rs.getString("uid"));
				invoice.setType(rs.getString("type"));
                invoice.setRider_username(rs.getString("rider_username"));
                invoice.setDriver_username(rs.getString("driver_username"));
                invoice.setFrom_place(rs.getString("from_place"));
				invoice.setTo_place(rs.getString("to_place"));
			    invoice.setPayment_status(rs.getString("payment_status"));
                invoice.setDelivery_status(rs.getString("delivery_status"));
                invoice.setDistance(rs.getString("distance"));
                invoice.setCost(rs.getString("cost"));
                invoice.setAddress(rs.getString("address"));
                invoice.setDate(rs.getString("date"));
                invoice.setTime(rs.getString("time"));
				invoice.setCreate_date(rs.getString("create_date"));
				invoice.setName(rs.getString("name"));
				invoice.setCell(rs.getString("cell"));
				invoice.setEmail(rs.getString("email"));

				invoicelist.add(invoice);
			}
			return invoicelist;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



}
