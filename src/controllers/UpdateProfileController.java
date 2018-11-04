package controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import adapters.CarDriverAdapter;
import adapters.UserAdapter;
import models.CarDriver;
import models.User;


public class UpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		Map<String, Object> rest = new HashMap<>();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("rider") || ac.get_role(request).equals("driver") || ac.get_role(request).equals("administrator")) {
				String menu=um.menu(ac.get_role(request).toString());
				request.setAttribute("menu", menu);
				
				UserAdapter ua=new UserAdapter();
				User user = new User();
				user=ua.select("SELECT * FROM public.user WHERE username='"+ac.get_username(request)+"' ORDER BY id DESC");
				
				if(request.getHeader("type")!=null && request.getHeader("type").contains("rest")) {
					if(request.getParameter("token")!=null && !request.getParameter("token").equals("")) {
						user.setFcm_token(request.getParameter("token"));
						ua.update_fcm(user);
					}
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.print(new Gson().toJson(user));
				} else {
					request.setAttribute("user", user);
					RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/update_profile.jsp");
					disp.forward(request, response);
				}
				

			}
			else {
				if(request.getHeader("type")!=null &&  request.getHeader("type").contains("rest")) {
					response.setStatus(401);
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					rest.put("success", false);
					rest.put("message", "Username or Password Do not match");
					out.print(new Gson().toJson(rest));
				} else {
					response.sendRedirect("login?msg=nomatch");
				}
			}
		}
		else {
			if(request.getHeader("type")!=null &&  request.getHeader("type").contains("rest")) {
				response.setStatus(401);
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				rest.put("success", false);
				rest.put("message", "Username or Password Do not match");
				out.print(new Gson().toJson(rest));
			} else {
				response.sendRedirect("login?msg=nomatch");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("rider") || ac.get_role(request).equals("driver") || ac.get_role(request).equals("administrator")) {
				if(request.getParameterMap().containsKey("action")) {
						if(request.getParameter("action").equals("edit_info_action")) {
							
							if(!request.getParameter("n").equals("") || !request.getParameter("dob").equals("") || !request.getParameter("email").equals("") || !request.getParameter("mn").equals("")) {
								User user=new User();
								user.setName(request.getParameter("n"));
								user.setDob(request.getParameter("dob"));
								user.setCell(request.getParameter("mn"));
								user.setGender(request.getParameter("gender"));
								user.setEmail(request.getParameter("email"));
								user.setUsername(ac.get_username(request));
								UserAdapter ua=new UserAdapter();
								ua.update_info(user);
								PrintWriter out = response.getWriter();
								out.println("Update Successful");
							}
							else {
								PrintWriter out = response.getWriter();
								out.println("Empty Fields");
							}
						}
						else if(request.getParameter("action").equals("edit_password")) {
							if(!request.getParameter("pw").equals("")) {
								User user=new User();
								user.setUsername(ac.get_username(request));
								user.setPassword(request.getParameter("pw"));
								UserAdapter ua=new UserAdapter();
								ua.update_password(user);
								PrintWriter out = response.getWriter();
								out.println("Password Update Successful");
							}
							
						}
						if(request.getParameter("action").equals("edit_image")) {
							 if(ServletFileUpload.isMultipartContent(request)){
						            try {
						                List<FileItem> multiparts = new ServletFileUpload(
						                                         new DiskFileItemFactory()).parseRequest(request);
						              
						                for(FileItem item : multiparts){
						                    if(!item.isFormField()){
						                        String name = new File(item.getName()).getName();
						                        String[] ext12=name.split("\\.");
						                 
												if(ext12[ext12.length-1].equals("jpg") || ext12[ext12.length-1].equals("png") || ext12[ext12.length-1].equals("bmp")) {
													UserAdapter ua=new UserAdapter();
						                        	User user=new User();
						                        	user.setUsername(ac.get_username(request));
						                        	user.setImage(user.getUsername()+"."+ext12[ext12.length-1]);
						                        	
						                        	ua.update_image(user);
						                        	
						                        	String relativeWebPath = "/uploads";
						                        	String absoluteFilePath = getServletContext().getRealPath(relativeWebPath);
													item.write( new File(absoluteFilePath, user.getUsername()+"."+ext12[ext12.length-1]));
													PrintWriter out = response.getWriter();
													out.print("Updated Profile Picture Successfully");
												}
						                    }
						                }
						            } catch (Exception ex) {
						            	PrintWriter out = response.getWriter();
										out.print("error");
						            }          
						         
						        }else{
						        	PrintWriter out = response.getWriter();
									out.print("error");
						        }
					    	}
							
						else {
							response.sendRedirect("login?msg=nomatch");
						}
				}
				else {
					response.sendRedirect("login?msg=nomatch");
				}
			}
			else {
				response.sendRedirect("login?msg=nomatch");
			}
		}
		else {
			response.sendRedirect("login?msg=nomatch");
		}
	}

}
