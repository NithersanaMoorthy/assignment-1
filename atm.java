package Assignment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class atm {

	public static void main(String[] args) throws SQLException {
		try{
			Scanner sc=new Scanner(System.in);
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/nithersan","root","root");
		Statement s=conn.createStatement();
	   ResultSet rs;
	   System.out.println("Load cash to ATM:");
	   System.out.println("Maintain Customer Details:");
	   System.out.println("Handle ATM Process:");
	   int ch=sc.nextInt();
	   switch(ch) {
	   case 1:System.out.println("denom:");
	          System.out.println("no.of 2000:");
	          int th=sc.nextInt();
	          System.out.println("no.of 500:");
	          int fi=sc.nextInt();
	          System.out.println("no.of 100:");
	          int hu=sc.nextInt();
	          rs=s.executeQuery("Select count(*) from atm");
	          int c=0;
	          while(rs.next()) {
	        	  c=rs.getInt(1);
	          }
	          if(c==0) {
	        	  s.executeUpdate("insert into atm values(2000,"+th+2000*th+")");
	        	  s.executeUpdate("insert into atm values(500,"+fi+500*fi+")");
	        	  s.executeUpdate("insert into atm values(100,"+hu+100*hu+")");
	        	  
	        	 }else {
	        		 s.executeUpdate("update atm set num=num+"+th+" where denom=2000");
	        		 s.executeUpdate("update atm set num=num+"+fi+" where denom=500");
	        		 s.executeUpdate("update atm set num=num+"+hu+" where denom=100");
	        		 
	        		 s.executeUpdate("update atm set val=val+"+(th*2000)+" where denom=2000");
	        		 s.executeUpdate("update atm set val=val+"+(fi*500)+" where denom=500");
	        		 s.executeUpdate("update atm set val=val+"+(hu*100)+" where denom=100");
	        	 }
	          System.out.println("completed...");
	          break;
	   case 2:rs=s.executeQuery("select * from customers");
	           System.out.println("accno");
	           System.out.println("accholder");
	           System.out.println("pin");
	           System.out.println("accbal");
	           System.out.println();
	           while(rs.next()) {
	        	   System.out.println(rs.getInt(1)+" "+rs.getString(2)+""+rs.getInt(3)+""+rs.getInt(4));
	           }
	           break;
	   case 3:int total=0;
		      rs=s.executeQuery("select * from atm");
		       System.out.println("denom val");
		       System.out.println();
		       while(rs.next()) {
		    	System.out.println(rs.getInt(1)+""+rs.getInt(2)+""+rs.getInt(3));
		    	total=total+rs.getInt(3);
		       }System.out.println();
		       System.out.println("total amount:"+total);
		       break;
	   default:System.out.println("choice correct one...");
		   
	   }
	   s.close();
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}	

}	
	
	
	
	

