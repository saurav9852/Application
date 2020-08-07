package org.oar.Application;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class LoginServlet extends GenericServlet 
{
	Connection con = null;
	PreparedStatement pstmt=null;
	String qry = "select * from application.login where user=? & password=?";
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException 
	{
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		PrintWriter out = resp.getWriter();
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3307?user=root&password=root");
				pstmt=con.prepareStatement("qry");
				pstmt.setString(1, user);
				pstmt.setString(2, pass);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next())
				{
					out.println("<html><body><h1>success</h1></body></html>");
					return;
				}
				out.println("<html><body><h1>Error</h1></body></html>");
				return;
			} 
			catch (ClassNotFoundException|SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 		
	}
}
