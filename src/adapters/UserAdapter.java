package adapters;

import java.sql.ResultSet;
import java.util.ArrayList;

import models.User;

public class UserAdapter {
	public void insert(User user)
	{
		String sql = "INSERT INTO public.user VALUES (DEFAULT, '"+user.getUsername()+"', '"+user.getPassword()+"', '"+user.getName()+"', '"+user.getDob()+"','"+user.getCell()+"','"+user.getEmail()+"','"+user.getRole()+"','"+user.getImage()+"','"+user.getGender()+"')";
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}

	/*public void update(User user)
	{
		String sql = "UPDATE user SET name='"+user.getName()+"', salary="+emp.getSalary()+", email='"+emp.getEmail()+"' WHERE id="+emp.getId();
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}*/

	public void update_info(User user)
	{
		String sql = "UPDATE public.user SET name='"+user.getName()+"', email='"+user.getEmail()+"', cell='"+user.getCell()+"', dob='"+user.getDob()+"', gender='"+user.getGender()+"' WHERE username='"+user.getUsername()+"'";
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}
	public void update_password(User user)
	{
		String sql = "UPDATE public.user SET password='"+user.getPassword()+"' WHERE username='"+user.getUsername()+"'";
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}
	public void update_image(User user)
	{
		String sql = "UPDATE public.user SET image='"+user.getImage()+"' WHERE username='"+user.getUsername()+"'";
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}

	public void delete(int id)
	{
		String sql = "DELETE FROM public.user WHERE id="+id;
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
	}

	public ArrayList<User> getAll()
	{
		String sql = "SELECT * FROM public.user";
		DataAccess da = new DataAccess();
		ResultSet rs = da.getResultSet(sql);
		ArrayList<User> userlist = new ArrayList<User>();
		try {
			while(rs.next())
			{
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setCell(rs.getString("cell"));
				user.setDob(rs.getString("dob"));
				user.setRole(rs.getString("role"));
				user.setGender(rs.getString("gender"));
			    user.setEmail(rs.getString("email"));
			    user.setImage(rs.getString("image"));



				userlist.add(user);
			}
			return userlist;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<User> select_users(String sql)
	{
		DataAccess da = new DataAccess();
		ResultSet rs = da.getResultSet(sql);
		ArrayList<User> userlist = new ArrayList<User>();
		try {
			while(rs.next())
			{
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setCell(rs.getString("cell"));
				user.setDob(rs.getString("dob"));
				user.setRole(rs.getString("role"));
				user.setGender(rs.getString("gender"));
			    user.setEmail(rs.getString("email"));
			    user.setImage(rs.getString("image"));



				userlist.add(user);
			}
			return userlist;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User get(int id)
	{
		String sql = "SELECT * FROM public.user WHERE id="+id;
		DataAccess da = new DataAccess();
		ResultSet rs = da.getResultSet(sql);
		User user = new  User();
		try {
			if(rs.next())
			{



				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setCell(rs.getString("cell"));
				user.setDob(rs.getString("dob"));
				user.setRole(rs.getString("role"));
				user.setGender(rs.getString("gender"));
			    user.setEmail(rs.getString("email"));
			    user.setImage(rs.getString("image"));




				return user;
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
	public User select(String sql) {
		DataAccess da = new DataAccess();
		ResultSet rs = da.getResultSet(sql);
		User user = new  User();
		try {
			if(rs.next())
			{
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setCell(rs.getString("cell"));
				user.setDob(rs.getString("dob"));
				user.setRole(rs.getString("role"));
				user.setGender(rs.getString("gender"));
			    user.setEmail(rs.getString("email"));
			    user.setImage(rs.getString("image"));
				return user;
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
