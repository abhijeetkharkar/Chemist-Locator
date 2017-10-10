package com.chemistlocator.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;


import com.chemistlocator.model.ShopDetail;

public class SearchChemistDAO
{
	Connection con;
    Statement stmt;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String url = "jdbc:mysql://localhost:3306/mediguide";
    String user = "root";
    String password = "password";
    private ArrayList<ShopDetail> shoplist;
    private ArrayList<String> locationAutoCompleteList;
    private ArrayList<String> shopAutoCompleteList;

	public ArrayList<ShopDetail> getShoplist()
	{
		return shoplist;
	}

	public void setShoplist(ArrayList<ShopDetail> shoplist)
	{
		this.shoplist = shoplist;
	}

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
			Logger.getLogger(SearchChemistDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public ArrayList<ShopDetail> getShopsDAO(String query)
	{
		shoplist=new ArrayList<ShopDetail>();
		init();
		try
		{
			ShopDetail shopDetail;
			ps = (PreparedStatement) con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next())
            {
                shopDetail=new ShopDetail();
                shopDetail.setShop_id(rs.getString(1));
                shopDetail.setShop_name(rs.getString(2));
                String shop_addr_lin_1=rs.getString(3);
                String shop_addr_lin_2=rs.getString(4);
                String addr="";
                if(shop_addr_lin_1.charAt(shop_addr_lin_1.length()-1)==',')
                {
                	addr=shop_addr_lin_1+shop_addr_lin_2;
                }
                else
                {
                	addr=shop_addr_lin_1+","+shop_addr_lin_2;
                }
                if(addr.charAt(addr.length()-1)==',')
                {
                	addr=addr+rs.getString(5)+":"+rs.getString(6)+".";
                }
                else
                {
                	addr=addr+","+rs.getString(5)+":"+rs.getString(6)+".";
                }
                shopDetail.setShop_address(addr);
                
                String city=rs.getString(5);
                String country=rs.getString(7);
                String std_code="", isd_code="";
                
                ResultSet rs_city=null;
                PreparedStatement ps_city=con.prepareStatement("select city_std_code from city_master where city_name='"+city+"';");
                rs_city=ps_city.executeQuery();
                while(rs_city.next())
                {
                	std_code=rs_city.getString(1);
                }
                rs_city.close();
                ps_city.close();
                
                ResultSet rs_country=null;
                PreparedStatement ps_country=con.prepareStatement("select country_isd_code from country_master where country_name='"+country+"';");
                rs_country=ps_country.executeQuery();
                while(rs_country.next())
                {
                	isd_code=rs_country.getString(1);
                }
                rs_country.close();
                ps_country.close();
                
                String landline=rs.getString(8);
                String mobile=rs.getString(9);
                String contactNumber="";
                if(landline==null || "".equals(landline))
                	contactNumber=isd_code+" "+mobile;
                else if(mobile==null || "".equals(mobile))
                	contactNumber=std_code+"-"+landline;
                else if((landline==null || "".equals(landline)) && (mobile==null || "".equals(mobile)))
                	contactNumber="NA";
                else
                	contactNumber=std_code+"-"+landline+" | "+isd_code+" "+mobile;
                shopDetail.setShop_contact_number(contactNumber);
                String email=rs.getString(10);
                if(email==null || "".equals(email))
                	email="NA";
                shopDetail.setShop_email(email);
                shopDetail.setShop_open_timings(rs.getString(11));
                shopDetail.setShop_rating(rs.getString(12));
                double rating=Double.parseDouble(rs.getString(12));
                if(rating<=1)
                	shopDetail.setShop_rating_color("#CC3300");
                else if(rating>1 && rating<=2)
                	shopDetail.setShop_rating_color("#FF6600");
                else if(rating>2 && rating<=3)
                	shopDetail.setShop_rating_color("#FFCC00");
                else if(rating>3 && rating<=4)
                	shopDetail.setShop_rating_color("#66CC00");
                else
                	shopDetail.setShop_rating_color("#339900");
                shoplist.add(shopDetail);
                shopDetail=null;
            }
		}
		catch(SQLException ex)
		{
			Logger.getLogger(SearchChemistDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		close();
		return shoplist;
	}
	
	public List<String> getShopsAutoCompleteDAO(String query)
	{
		shopAutoCompleteList=new ArrayList<String>();
		init();
		try
		{
			ps = (PreparedStatement) con.prepareStatement(query);            
            rs = ps.executeQuery();            
            while (rs.next())
            {
                shopAutoCompleteList.add(rs.getString(1));
            }
		}
		catch(SQLException ex)
		{
			Logger.getLogger(SearchChemistDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		close();
		return shopAutoCompleteList;
	}
	
	public ArrayList<String> getLocationsAutoCompleteDAO(String query)
	{
		locationAutoCompleteList=new ArrayList<String>();
		init();
		try
		{
			ps = (PreparedStatement) con.prepareStatement(query);            
            rs = ps.executeQuery();            
            while (rs.next())
            {
            	locationAutoCompleteList.add(rs.getString(1));
            }            
            //FacesContext context = FacesContext.getCurrentInstance();          
            //context.addMessage(null, new FacesMessage("Successful", "ExtraMile Added!!!"));
		}
		catch(SQLException ex)
		{
			Logger.getLogger(SearchChemistDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		close();
		return locationAutoCompleteList;
	}
}
