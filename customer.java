package Assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class customer {
@SuppressWarnings({ "resource", "unused" })
public static void main(String[] args) throws ClassNotFoundException {
	try {
	Scanner s=new Scanner(System.in);
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/nithersan","root","root");
	Statement st=c.createStatement();
	ResultSet rs;
	int total=0;
	rs=st.executeQuery("Select val from atm");
	while(rs.next()) total+=rs.getInt(1);
	System.out.println("1.Check your Balance");
	System.out.println("2.Withdraw your Money");
	System.out.println("3.Transfer your Money");
	int ch=s.nextInt();
	int accno=0;
	int pin=0;
	int accbal=0;
	int amount=0;
	int acno=0;
	int withamount=0;
	int holderno=0;
	String name="";
	boolean b=true;
		switch(ch) {
		case 1:	System.out.println("accno:");
		     	acno=s.nextInt();
		     	rs=st.executeQuery("Select count(*) from customers where accno="+acno);
		     	while(rs.next()) {
				if(rs.getInt(1)==0)
					b=false;
		     	}
		     	if(!b) System.out.println("accno incorrect...");
		     		else {
		     				rs=st.executeQuery("select * from customers where accno="+acno);
		     					while(rs.next()) {
		     						accno=rs.getInt(1);
		     						name=rs.getString(2);
		     						pin=rs.getInt(3);
		     						accbal=rs.getInt(4);
		     					}
		     					System.out.println("pin:");
		     					int p=s.nextInt();
		     					if(p==pin) {
		     						System.out.println("acc details...");
		     						System.out.println("accno: "+accno);
		     						System.out.println("accholder: "+name);
		     						System.out.println("accbal: Rs."+accbal);
		     						}
		     					else {
		     						System.out.println("accno and pin checkit...");
		     					}
		     			}
		     	break;
		case 2:	System.out.println("accno:");
				acno=s.nextInt();
				rs=st.executeQuery("Select count(*) from customers where accno="+acno);
				while(rs.next()) {
					if(rs.getInt(1)==0)
						b=false;
				}
				if(!b) System.out.println("accno incorrect...");
				else {
				rs=st.executeQuery("select * from customers where accno="+acno);
				while(rs.next()) {
					accno=rs.getInt(1);
					name=rs.getString(2);
					pin=rs.getInt(3);
					accbal=rs.getInt(4);
				}
				System.out.println("pin: ");
				int p=s.nextInt();
				if(p==pin) {
					System.out.println("amount withdrawn ");
					amount=s.nextInt();
					int t=0,temp=amount;
					if((amount>10000 || amount<100) && amount%100==0 )
						System.out.println("amount to be withdrawn between 100 and 10000");
					else if(amount>accbal)
						System.out.println("accbal less then given amount");
					else if(amount>total)
						System.out.println("atm balance is less,come again...");
					else {
							while(amount>3000) {
								withamount=withamount+2000;
								st.executeUpdate("update customers set accbal=accbal-2000 where accno="+accno);
								st.executeUpdate("update atm set num=num-1 where denom=2000");
								st.executeUpdate("update atm set val=val-2000 where denom=2000");
								amount-=2000;
								t=t+2000;
							}
							while(amount>1000) {
								withamount=withamount+500;
								st.executeUpdate("update customers set accbal=accbal-500 where accno="+accno);
								st.executeUpdate("update atm set num=num-1 where denom=500");
								st.executeUpdate("update atm set val=val-500 where denom=500");
								amount=amount-500;
								t=t+500;
							}
							while(amount>0) {
								holderno=holderno+100;
								amount=amount-100;
								t=t+100;
							}
							withamount=withamount+holderno;
							st.executeUpdate("update customers set accbal=accbal-"+holderno+" where accno="+accno);
							st.executeUpdate("update atm set num=num-"+(holderno/100) +" where denom=100");
							st.executeUpdate("update atm set val=val-"+holderno +" where denom=100");
							
					}
					System.out.println("Amount "+t+" is withdrawn...");
					if(t!=temp) {
						System.out.println(""+(temp-t)+" not available at the movement...");
					}
				}
				else {
					System.out.println("accno and pin checkit...");
				}
			}
			break;
		case 3:	System.out.println("accno:");
				acno=s.nextInt();
				rs=st.executeQuery("Select count(*) from customers where accno="+acno);
				while(rs.next()) {
					if(rs.getInt(1)==0)
						b=false;
					}
				if(!b) System.out.println("accno wrong...");
				else {
					rs=st.executeQuery("select * from customers where accno="+acno);
						while(rs.next()) {
							accno=rs.getInt(1);
							name=rs.getString(2);
							pin=rs.getInt(3);
							accbal=rs.getInt(4);
						}
						System.out.println("pin: ");
						int p=s.nextInt();
						if(p==pin) {
							System.out.println("accno to be transfer your money");
							int aacno=s.nextInt();
							boolean bool=true;
							rs=st.executeQuery("Select count(*) from customers where accno="+aacno);
							while(rs.next()) {
								if(rs.getInt(1)==0)
									bool=false;
							}
							if(!b) System.out.println("accno invalid...");
								else {
									System.out.println("amount: ");
									int temp3=s.nextInt();
									if(temp3>accbal) {
										System.out.println("accbal less...");
									}
									else {
								st.executeUpdate("update accbal=accbal-"+temp3+" where accno="+accno);
								st.executeUpdate("update accbal=accbal+"+temp3+" where accno="+acno);
								System.out.println("money transfered...");
									}
								}
						}
						else {
							System.out.println("accno and pin checkit...");
						}
					}
						break;
				default:System.out.println("choich correct one...");
		
			}
	s.close();
	
	
}catch(Exception e) {
	System.out.println(e);
}
}
}
