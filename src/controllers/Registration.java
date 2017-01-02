package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameterMap().containsKey("select")) {
			if(request.getParameter("select").equals("driver")) {
				request.setAttribute("driver", true);
			}
			else {
				request.setAttribute("driver", false);
			}
		}
		else {
			request.setAttribute("driver", false);
		}
		RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/frontend/registration.jsp");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
