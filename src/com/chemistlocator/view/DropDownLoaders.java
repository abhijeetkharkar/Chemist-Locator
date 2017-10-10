package com.chemistlocator.view;

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

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

@ManagedBean(name="dropDownLoaders",eager=true)
public class DropDownLoaders
{
    private List<SelectItem> countries;
    private List<SelectItem> specialities;
    Connection con;
    Statement stmt;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String url = "jdbc:mysql://localhost:3306/mediguide";
    String user = "root";
    String password = "password";

    public List<SelectItem> getCountries() {
		return countries;
	}

	public void setCountries(List<SelectItem> countries) {
		this.countries = countries;
	}

	public List<SelectItem> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(List<SelectItem> specialities) {
		this.specialities = specialities;
	}

	@PostConstruct
    public void init()
    {
        String query1="select country_name from country_master;";
        String query2="select distinct shop_speciality from shop_details;";
        countries=new ArrayList<SelectItem>();
        specialities=new ArrayList<SelectItem>();
        SelectItem country, speciality;
        initDB();
        try
        {
            ps = (PreparedStatement) con.prepareStatement(query1);            
            rs = ps.executeQuery();
            while (rs.next())
            {
                country=new SelectItem();
                country.setLabel(rs.getString(1));
                country.setValue(rs.getString(1));
                countries.add(country);
            }
            
            ps = (PreparedStatement) con.prepareStatement(query2);            
            rs = ps.executeQuery();
            while (rs.next())
            {
            	speciality=new SelectItem();
            	speciality.setLabel(rs.getString(1));
            	speciality.setValue(rs.getString(1));
                specialities.add(speciality);
            }
            
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DropDownLoaders.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }
    
    public void initDB()
    {
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, user, password);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DropDownLoaders.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException e)
        {
			e.printStackTrace();
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
            Logger.getLogger(DropDownLoaders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}