package controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import adapters.UserAdapter;
import models.User;

public class AccessControl {
	public boolean login_check(HttpServletRequest request ,String un, String pw) {
		UserAdapter ua=new UserAdapter();
		String sql = "SELECT * FROM user WHERE username='"+un+"' AND password='"+pw+"'";
		User user=new User();
		user=ua.select(sql);
		if(user!=null) {
			this.create_session(request, un, user.getRole());
			return true;
		}
		else{
			return false;
		}
	}
	public String get_username(HttpServletRequest request) {
		HttpSession session=request.getSession();
		if(session.getAttribute("username")!=null) return (String) session.getAttribute("username");
		else return null;
	}
	public String get_role(HttpServletRequest request) {
		HttpSession session=request.getSession();
		if(session.getAttribute("role")!=null) return (String) session.getAttribute("role");
		else return null;
	}
	public void create_session(HttpServletRequest request, String un, String role) {
		HttpSession session=request.getSession();
		session.setAttribute("username", un);
		session.setAttribute("role", role);
	}
}
