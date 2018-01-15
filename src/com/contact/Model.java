package com.contact;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



public class Model {
	public String register(RegBean bean) {
		System.out.println("Model-->register()");
		// Input validation
		String msg=bean.validate();
		if(msg.equals("SUCCESS")) {
			System.out.println("In model Validation succeeded");
			// Business validation
			Connection con=null;
			PreparedStatement ps_sql=null, ps_ins=null;
			ResultSet rs=null;
			try {
				con=JDBCHelper.getConnection();
				if(con==null) {
					return "cannot connect to DB. Contact admin!!!";
				}
				else {
					ps_sql=con.prepareStatement("select * from ContactAppUsers where email=?");
					ps_sql.setString(1, bean.getEmail());
					ps_sql.execute();
					
					rs=ps_sql.getResultSet();
				
					if(rs.next()) {
						return "Email Id is alredy taken by users!! Please register with new email id";
					}
					else {
						// business validation succeeded
						// store data into DB
						ps_ins=con.prepareStatement("insert into ContactAppUsers (name,email,pass) values(?,?,?)");
						ps_ins.setString(1, bean.getName());
						ps_ins.setString(2, bean.getEmail());
						ps_ins.setString(3, bean.getPass());
						ps_ins.execute();
						
						return "SUCCESS";		
					}
				}	
			}
			catch (SQLException e) {
				e.printStackTrace();
				return "Oops something went wrong!"+e.getMessage();
			}
		}
		else {
			System.out.println("In model regiateration failed");
			return msg;
		}
	}
	
	public String login(LoginBean bean) {
		System.out.println("In model login.do...");	
		/*
		 * 1. Input validate the bean. if it fails return the msg 
		 * which is to be displayed to user
		 * 2. Business validate connection to DB. if it fails return the msg to user
		 * 3. Apply business logic and store the data to DB
		 * 4. return Success 
		 */
		
		// Input validation
		String msg=bean.validate();
		if(msg.equals("SUCCESS")) {
			System.out.println("In model Validation succeeded");
			// Business validation
			Connection con=null;
			PreparedStatement ps_sql=null, ps_ins=null;
			ResultSet rs=null;						
			try {
				con=JDBCHelper.getConnection();
				if(con==null) {
					return "cannot connect to DB. Contact admin!!!";
				}
				else {
					ps_sql=con.prepareStatement("select * from ContactAppUsers where email=? and pass=?");
					ps_sql.setString(1, bean.getEmail());
					ps_sql.setString(2, bean.getPass());
					ps_sql.execute();
					rs=ps_sql.getResultSet();		
					if(!rs.next()) {
						return "Email id or Password is incorrect..";
					}
					else {
					// business validation succeeded
					// forward to menu.jsp
					return "SUCCESS";
					}			
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				return "Oops something went wrong!"+e.getMessage();
			}
		}
		else {
		System.out.println("In model login failed");
		return msg;
		}
	}
	
	public List<RegBean> getUsers(){
		List<RegBean> list=new ArrayList<RegBean>();
		Connection con = null;
		PreparedStatement ps_sql = null;
		ResultSet rs = null;
		try
		{
			con = JDBCHelper.getConnection();
			ps_sql = con.prepareStatement("select * from ContactAppUsers");
			ps_sql.execute();
			rs = ps_sql.getResultSet();
			
			while(rs.next())
			{
				String name = rs.getString("name");
				String email = rs.getString("email");
				String pass = rs.getString("pass");
				
				RegBean bean = new RegBean();
				
				bean.setName(name);
				bean.setEmail(email);
				bean.setPass(pass);
				
				list.add(bean);
			}
			return list;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sql);
			JDBCHelper.close(con);
		}
		
	}
	
	public List<ContactBean> getContacts(String email){
		List<ContactBean> list=new ArrayList<ContactBean>();
		Connection con = null;
		PreparedStatement ps_sql = null;
		ResultSet rs = null;
		try
		{
			con = JDBCHelper.getConnection();
			ContactBean bean = new ContactBean();
			ps_sql = con.prepareStatement("select * from ContactAppUsers where email=?");
			ps_sql.setString(1, email);
			rs=ps_sql.executeQuery();
			int i=0;
			while (rs.next()) {
				i=rs.getInt("Sl.no");
				System.out.println("Sl num : "+i);
			}
			
			ps_sql = con.prepareStatement("select * from CONTACTS_FINAL where UserSlno=?");
			ps_sql.setInt(1, i);
			ps_sql.execute();
			rs = ps_sql.getResultSet();
			
			while(rs.next())
			{
				String name = rs.getString("Name");
				Date dob = rs.getDate("Dob");
				String gender = rs.getString("Gender");
				String date=dob.toString();				
				bean.setName(name);
				bean.setDob(date);
				bean.setGender(gender);	
			}
			
			/*ps_sql = con.prepareStatement("select * from CONTACTS_FINAL where UserSlno=?");
			ps_sql.setInt(1, i);*/
			rs=ps_sql.executeQuery();
			int j=0;
			while (rs.next()) {
				j=rs.getInt("Sl.no");
				System.out.println("Sl num : "+i);
			}
			
			ps_sql = con.prepareStatement("select * from CONTACTS_EMAILS where ContactSlno=?");
			ps_sql.setInt(1, j);
			ps_sql.execute();
			rs = ps_sql.getResultSet();
			StringBuilder sb=new StringBuilder();
			while(rs.next())
			{
				String conEmail = rs.getString("Email");
				sb.append(conEmail+",");								
			}
			bean.setEmail(sb.toString());	
			
			ps_sql = con.prepareStatement("select * from CONTACTS_PHNOS where ContactSlno=?");
			ps_sql.setInt(1, j);
			ps_sql.execute();
			rs = ps_sql.getResultSet();
			sb=new StringBuilder();
			while(rs.next())
			{
				String phno = rs.getString("Phno");
				sb.append(phno+",");
			}
			bean.setPhno(sb.toString());

			
			ps_sql = con.prepareStatement("select * from CONTACTS_TAGS where ContactSlno=?");
			ps_sql.setInt(1, j);
			ps_sql.execute();
			rs = ps_sql.getResultSet();
			sb=new StringBuilder();
			while(rs.next())
			{
				String tag = rs.getString("Tags");
				sb.append(tag+",");
			}
			bean.setTag(sb.toString());	
			return list;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_sql);
			JDBCHelper.close(con);
		}
	}
		
	
	public String editAccount(RegBean bean) {
		System.out.println("Model-->editAccount bean-->"+bean);
		String msg=bean.validate();
		if(msg.equals("SUCCESS")) {
			System.out.println("In model editing InputValidation succeeded");
			// Business validation
			Connection con=null;
			PreparedStatement ps_sql=null, ps_ins=null;
			ResultSet rs=null;
			try {
				con=JDBCHelper.getConnection();
				if(con==null) {
					return "cannot connect to DB. Contact admin!!!";
				}
				else {
					ps_sql=con.prepareStatement("select * from ContactAppUsers where email=?");
					ps_sql.setString(1, bean.getEmail());
					ps_sql.execute();
					
					rs=ps_sql.getResultSet();
				
					if(!rs.next()) {
						return "Enter the correct email Id you want to edit";
					}
					else {
						// business validation succeeded
						// store data into DB
						ps_ins=con.prepareStatement("update ContactAppUsers set name=?, pass=? where email=?");
						ps_ins.setString(1, bean.getName());
						ps_ins.setString(2, bean.getPass());
						ps_ins.setString(3, bean.getEmail());
						ps_ins.execute();
						
						return "SUCCESS";		
					}
				}	
			}
			catch (SQLException e) {
				e.printStackTrace();
				return "Oops something went wrong!"+e.getMessage();
			}
		}
		else {
			System.out.println("In model editing process failed");
			return msg;
		}
	}
	
	public String addContact(ContactBean bean, String email) {
		System.out.println("Model-->addContact() bean-->"+bean);
		String msg=bean.validate();
		if(msg.equals("SUCCESS")) {
			System.out.println("In model addContact() InputValidation succeeded");
			Connection con=null;
			PreparedStatement ps_sql=null, ps_ins=null;
			ResultSet rs=null;
			try {
				con=JDBCHelper.getConnection();
				if(con==null) {
					return "cannot connect to DB. Contact admin!!!";
				}
					else {
						// business validation succeeded
						// store data into DB
						//int userSlno=
						ps_sql=con.prepareStatement("select * from ContactAppUsers where email=?");
						ps_sql.setString(1, email);
						rs=ps_sql.executeQuery();
						int i=0;
						while (rs.next()) {
							i=rs.getInt("Sl.no");
							System.out.println("Sl num : "+i);
						}
						
						//${sessionScope.user};

						ps_ins=con.prepareStatement("insert into CONTACTS_FINAL (userSlno,name,dob,gender) values(?,?,?,?)");
						ps_ins.setInt(1,i);
						ps_ins.setString(2, bean.getName());
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
						java.util.Date date = sdf1.parse(bean.getDob());
						 //date = sdf1.parse(source)
				        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

						System.out.println("java"+date);
						//java.sql.Date sqlDate =(Date) (date);

						System.out.println("sql"+sqlDate);
						
						//System.out.println("java"+date.toString());
						//java.sql.Date sqlStartDate = new Date(date.getTime()); 
						//System.out.println("sql"+sqlStartDate.toString());
						ps_ins.setDate(3, sqlDate);
						ps_ins.setString(4, bean.getGender());
						ps_ins.execute();
						//adding email to the table
						String[] emails=(bean.getEmail()).split(",");
						ps_sql=con.prepareStatement("select * from CONTACTS_FINAL where name=?");
						ps_sql.setString(1, bean.getName());
						ps_sql.execute();
						rs=ps_sql.getResultSet();
						int fno=0;
						while(rs.next()) {
							fno=rs.getInt("Sl.no");
						}
						
						for(int k=0;k<emails.length;k++) {
							ps_ins=con.prepareStatement("insert into CONTACTS_EMAILS (contactSlno,email) values(?,?)");
							ps_ins.setInt(1,fno);
							ps_ins.setString(2, emails[k]);
							ps_ins.execute();
						}
						
						// adding phone nos
						String[] nos=(bean.getPhno()).split(",");
						/*ps_sql=con.prepareStatement("select * from CONTACTS_NEW where name=?");
						ps_sql.setString(1, bean.getName());
						ps_sql.execute();
						rs=ps_sql.getResultSet();*/
						
						for(int k=0;k<nos.length;k++) {
							ps_ins=con.prepareStatement("insert into CONTACTS_PHNOS (contactSlno,phno) values(?,?)");
							ps_ins.setInt(1,fno);
							ps_ins.setString(2, nos[k]);
							ps_ins.execute();
						}
						
						// adding tags
						String[] tag=(bean.getTag()).split(",");
						for(int k=0;k<tag.length;k++) {
							ps_ins=con.prepareStatement("insert into CONTACTS_TAGS (contactSlno,tags) values(?,?)");
							ps_ins.setInt(1,fno);
							ps_ins.setString(2, tag[k]);
							ps_ins.execute();
						}
						
						
						return "SUCCESS";		
					}
					
				}
			catch (SQLException e) {
				e.printStackTrace();
				return "Oops something went wrong!"+e.getMessage();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("In model Adding contact process failed");
			return msg;
		}
		return msg;
	}
			
}
