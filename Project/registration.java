package Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/reg")
public class registration extends HttpServlet 
{
		Connection con;
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    	{

    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		
    		String s1=request.getParameter("first");
    		String s2=request.getParameter("last");	
    		String s3=request.getParameter("uname");
    		String s4=request.getParameter("password");
    		String s5=request.getParameter("seatno");
    		
    		int r=0;
    		try
    		{
    			Class.forName("oracle.jdbc.driver.OracleDriver");
    			Connection  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");


    			PreparedStatement pst=con.prepareStatement("insert into infor values(?,?,?,?,?)");
    			pst.setString(1, s1);
    			pst.setString(2, s2);
    			pst.setString(3, s3);
    			pst.setString(4, s4);
    			pst.setString(5, s5);
    			
    			
    			r= pst.executeUpdate();
    			
    			PrintWriter pw=response.getWriter();
    			pw.println("<html><body bgcolor=green text=yellow><center>");
    			pw.println("<h1> You have Registered Succesfully</h1>");
    			pw.println("<a href=login.html>Login</a>");
    			pw.println("</center></body></html>");
    			
    			
    			if(r>0)
    			{
    				out.print("<h1> Data Added Sucessfully....!!</h1>");
    			}
    			else
    			{
    				out.print("<h1> Error....!!</h1>");

    			}
    			
    		}
    		catch ( SQLException | ClassNotFoundException e) 
    		{
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

	}
}


