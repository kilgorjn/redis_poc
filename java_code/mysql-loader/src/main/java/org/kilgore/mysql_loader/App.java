package org.kilgore.mysql_loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
    	Class.forName("com.mysql.cj.jdbc.Driver"); 
    	Connection con=DriverManager.getConnection("jdbc:mysql://ilc-avtns-vsl02:3306/redispoc","mf_user","c@tsMe0w");      	
    	Statement stmt=con.createStatement();  
    	ResultSet rs=stmt.executeQuery("select fund_symbol from mutual_funds");  
    	while(rs.next())  
    	System.out.println(rs.getString("fund_symbol"));  
    	con.close(); 
    	System.out.println("Done!!!!");
//        System.out.println( "Hello World!" );
    }
}
