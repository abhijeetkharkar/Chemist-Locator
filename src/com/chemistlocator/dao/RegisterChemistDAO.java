package com.chemistlocator.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class RegisterChemistDAO
{
	Connection con;
    Statement stmt;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String url = "jdbc:mysql://localhost:3306/mediguide";
    String user = "root";
    String password = "password";
    
    public void init()
	{		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(url, user, password);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(SearchChemistDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (ClassNotFoundException ex)
		{
			System.out.println("Class Not Found");
			//ex.printStackTrace();
		}
	}

	public void close()
	{
		try
		{
			if(rs != null)
			{
				rs.close();
			}
			if(ps != null)
			{
				ps.close();
			}
			if(con != null)
			{
				con.close();
			}
		}
		catch (SQLException ex)
		{
			Logger.getLogger(RegisterChemistDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void registerChemistInsert(String shopName, String shopOwnerFName, String shopOwnerLName, String addrLin1, String addrLin2, String shopCity, String shopState, String shopPin, String shopCountry, String shopEmail, String shopPhone, String shopMobile, String shopTimings, String shopMedicineType, String shopSpeciality, String shopPaymentMode, String shopHomeDelivery, String remoteIP)
	{
        FacesContext context = FacesContext.getCurrentInstance();
		init();
		try
		{
			ps=(PreparedStatement) con.prepareStatement("select max(shop_id) from shop_details;");
			rs=ps.executeQuery();
			String max_shop_id=null;
			while(rs.next())
			{
				max_shop_id=rs.getString(1);
			}
			long current_number=Long.parseLong(max_shop_id.substring(1))+1;
			String current_shop_id="S";
			for(int i=0;i<(8-(current_number+"").length());i++)
			{
				current_shop_id+="0";
			}
			current_shop_id+=current_number;
			ps=(PreparedStatement) con.prepareStatement("insert into shop_details values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, current_shop_id);
			ps.setString(2, shopName);
			ps.setString(3, shopOwnerLName+", "+shopOwnerFName);
			ps.setString(4, addrLin1);
			ps.setString(5, addrLin2);
			ps.setString(6, shopCity);
			ps.setString(7, shopState);
			ps.setString(8, shopPin);
			ps.setString(9, shopCountry);
			ps.setString(10, shopEmail);
			ps.setString(11, shopPhone);
			ps.setString(12, shopMobile);
			ps.setString(13, shopTimings);
			ps.setString(14, shopMedicineType);
			ps.setString(15, shopSpeciality);
			ps.setString(16, shopPaymentMode);
			ps.setString(17, shopHomeDelivery);
			ps.setString(18, "Admin");
			//ps.setString(19, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
			ps.setDate(19, new java.sql.Date(new Date().getTime()));
			ps.setString(20, remoteIP);
			ps.executeUpdate();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Chemist Registration Successful!!!"));
		}
		catch(SQLException ex)
		{
			Logger.getLogger(RegisterChemistDAO.class.getName()).log(Level.SEVERE, null, ex);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Chemist Registration Failed!!!"));
		}
		close();
	}
}
