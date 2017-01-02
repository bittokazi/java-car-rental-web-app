package controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import adapters.CarDriverAdapter;
import adapters.UserAdapter;
import models.CarDriver;
import models.User;

public class confirm_reg extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "G:/atp-workspace/car_rental/WebContent/uploads";
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	UserAdapter ua1=new UserAdapter();
    	User user1=new User();
    	user1=ua1.select("SELECT * FROM user WHERE username='"+request.getParameter("un")+"' OR email='"+request.getParameter("email")+"'");
    	if(user1!=null) {
    		PrintWriter out = response.getWriter();
			out.print("exist");
    	}
    	else {
	    	//process only if its multipart content
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
	                        	UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"); 
	                        	String fname=uid.randomUUID().toString();
								user.setName(request.getParameter("n"));
	                        	user.setUsername(request.getParameter("un"));
	                        	user.setPassword(request.getParameter("pw"));
	                        	user.setEmail(request.getParameter("email"));
	                        	user.setCell(request.getParameter("phone"));
	                        	user.setDob(request.getParameter("dob"));
	                        	user.setGender(request.getParameter("gender"));
	                        	user.setRole(request.getParameter("type"));
	                        	user.setImage(user.getUsername()+"."+ext12[ext12.length-1]);
	                        	ua.insert(user);
	                        	
	                        	if(user.getRole().equals("driver")) {
	                        		CarDriverAdapter cda=new CarDriverAdapter();
	                        		CarDriver cardriver=new CarDriver();
	                        		cardriver.setCar_type(request.getParameter("car_type"));
	                        		cardriver.setLicense_id(request.getParameter("l"));
	                        		cardriver.setStatus("pending");
	                        		cardriver.setUsername(user.getUsername());
	                        		cda.insert(cardriver);
	                        	}
	                        	String relativeWebPath = "/uploads";
	                        	String absoluteFilePath = getServletContext().getRealPath(relativeWebPath);
								item.write( new File(absoluteFilePath, user.getUsername()+"."+ext12[ext12.length-1]));
								PrintWriter out = response.getWriter();
								out.print("ok");
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

     
    }
  
}