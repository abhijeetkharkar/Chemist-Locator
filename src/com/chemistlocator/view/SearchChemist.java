package com.chemistlocator.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.chemistlocator.dao.SearchChemistDAO;
import com.chemistlocator.model.ShopDetail;

@ManagedBean
@RequestScoped
public class SearchChemist
{
	private String location, shopName;
	private ArrayList<ShopDetail> shopList;
	
	public ArrayList<ShopDetail> getShopList()
	{
		return shopList;
	}

	public void setShopList(ArrayList<ShopDetail> shopList)
	{
		this.shopList = shopList;
	}

	public String getLocation() 
	{
		return location;
	}

	public void setLocation(String location) 
	{
		this.location = location;
	}

	public String getShopName() 
	{
		return shopName;
	}

	public void setShopName(String shopName) 
	{
		this.shopName = shopName;
	}
	
	public List<String> autocompleteLocation(String wildcard)
	{
		String autocompleteLocationQuery="select distinct shop_addr_lin_2 from shop_details where shop_addr_lin_2 like '%"+wildcard+"%';";
		return new SearchChemistDAO().getLocationsAutoCompleteDAO(autocompleteLocationQuery);
	}
	
	public List<String> autocompleteShopName(String wildcard)
	{
		if(shopName==null || "".equals(shopName))
		{
			String autocompleteShopNameQuery="select shop_name from shop_details where shop_name like '%"+wildcard+"%';";
			return new SearchChemistDAO().getShopsAutoCompleteDAO(autocompleteShopNameQuery);
		}
		else
		{
			String autocompleteShopNameQuery="select shop_name from shop_details where shop_name like '%"+wildcard+"%' and shop_addr_lin_2='"+shopName+"';";
			return new SearchChemistDAO().getShopsAutoCompleteDAO(autocompleteShopNameQuery);
		}
	}
	
	public String searchChemistShop()
	{
		if(location==null && shopName==null)
		{
			System.out.println("Inside IF searchChemistShop");
			shopList=new SearchChemistDAO().getShopsDAO("SELECT shop_id,shop_name,shop_addr_lin_1,shop_addr_lin_2,shop_city,shop_pincode,shop_country,shop_landline_number,shop_mobile_number,shop_email_id,shop_open_timings,shop_rating FROM mediguide.shop_details;");
		}
		else if(location==null && shopName!=null)
		{
			System.out.println("Inside ELSE-IF 1 searchChemistShop");
			shopList=new SearchChemistDAO().getShopsDAO("SELECT shop_id,shop_name,shop_addr_lin_1,shop_addr_lin_2,shop_city,shop_pincode,shop_country,shop_landline_number,shop_mobile_number,shop_email_id,shop_open_timings,shop_rating FROM mediguide.shop_details where shop_name like '%"+shopName+"%';");
		}
		else if(location!=null && shopName==null)
		{
			System.out.println("Inside ELSE-IF 2 searchChemistShop");
			shopList=new SearchChemistDAO().getShopsDAO("SELECT shop_id,shop_name,shop_addr_lin_1,shop_addr_lin_2,shop_city,shop_pincode,shop_country,shop_landline_number,shop_mobile_number,shop_email_id,shop_open_timings,shop_rating FROM mediguide.shop_details where shop_addr_lin_2 like '%"+location+"%' or shop_addr_lin_1 like '%"+location+"%';");
		}
		else
		{
			System.out.println("Inside ELSE searchChemistShop");
			shopList=new SearchChemistDAO().getShopsDAO("SELECT shop_id,shop_name,shop_addr_lin_1,shop_addr_lin_2,shop_city,shop_pincode,shop_country,shop_landline_number,shop_mobile_number,shop_email_id,shop_open_timings,shop_rating FROM mediguide.shop_details where (shop_addr_lin_2 like '%"+location+"%' or shop_addr_lin_1 like '%"+location+"%') and shop_name like '%"+shopName+"%';");
		}
		return "searchChemists";
	}
}
