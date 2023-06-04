package Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public loginservlet() 
    {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String s1=request.getParameter("uname");
		String s2=request.getParameter("password");
		
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");


			PreparedStatement pst=con.prepareStatement("select * from infor where uname=? and password=?");
			pst.setString(1, s1);
			pst.setString(2, s2);
			ResultSet rs=pst.executeQuery();
			
			PrintWriter pw=response.getWriter();
			pw.println("<html><body bgcolor=green text=yellow><center>");
			
			
			if(rs.next())
			{
				pw.println("Welcome");
				pw.print("<h1>"+s1+" ...!!</h1>");
			}
			else
			{
				pw.println("<h>Invalid UserName/Password</h1>");

			}
			pw.println("</center></body></html>");
			
		}
		catch ( SQLException | ClassNotFoundException e) 
		{
			
			e.printStackTrace();
		}

		
		
		



}
}
