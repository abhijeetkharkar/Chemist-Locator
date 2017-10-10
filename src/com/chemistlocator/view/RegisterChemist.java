package com.chemistlocator.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.chemistlocator.dao.RegisterChemistDAO;

@ManagedBean(name="registerChemist")
@ViewScoped
public class RegisterChemist
{
	private String shopName, shopOwnerFName, shopOwnerLName;
	//private String shopOwnerMName;
	private String shopAddr1, shopAddr2, shopCity, shopState, shopPinCode, shopCountry;
	private String shopEMail, shopPhone, shopMobile;
	private Date shopOpenTime, shopCloseTime;
	private List<String> shopClosedOn, shopMedicineType, shopPaymentMode;
	private String shop24HRS, shopSpeciality, shopHomeDelivery;
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopOwnerFName() {
		return shopOwnerFName;
	}
	public void setShopOwnerFName(String shopOwnerFName) {
		this.shopOwnerFName = shopOwnerFName;
	}
	/*public String getShopOwnerMName() {
		return shopOwnerMName;
	}
	public void setShopOwnerMName(String shopOwnerMName) {
		this.shopOwnerMName = shopOwnerMName;
	}*/
	public String getShopOwnerLName() {
		return shopOwnerLName;
	}
	public void setShopOwnerLName(String shopOwnerLName) {
		this.shopOwnerLName = shopOwnerLName;
	}
	public String getShopAddr1() {
		return shopAddr1;
	}
	public void setShopAddr1(String shopAddr1) {
		this.shopAddr1 = shopAddr1;
	}
	public String getShopAddr2() {
		return shopAddr2;
	}
	public void setShopAddr2(String shopAddr2) {
		this.shopAddr2 = shopAddr2;
	}
	public String getShopCity() {
		return shopCity;
	}
	public void setShopCity(String shopCity) {
		this.shopCity = shopCity;
	}
	public String getShopState() {
		return shopState;
	}
	public void setShopState(String shopState) {
		this.shopState = shopState;
	}
	public String getShopPinCode() {
		return shopPinCode;
	}
	public void setShopPinCode(String shopPinCode) {
		this.shopPinCode = shopPinCode;
	}
	public String getShopCountry() {
		return shopCountry;
	}
	public void setShopCountry(String shopCountry) {
		this.shopCountry = shopCountry;
	}
	public String getShopEMail() {
		return shopEMail;
	}
	public void setShopEMail(String shopEMail) {
		this.shopEMail = shopEMail;
	}
	public String getShopPhone() {
		return shopPhone;
	}
	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}
	public String getShopMobile() {
		return shopMobile;
	}
	public void setShopMobile(String shopMobile) {
		this.shopMobile = shopMobile;
	}
	public Date getShopOpenTime() {
		return shopOpenTime;
	}
	public void setShopOpenTime(Date shopOpenTime) {
		this.shopOpenTime = shopOpenTime;
	}
	public Date getShopCloseTime() {
		return shopCloseTime;
	}
	public void setShopCloseTime(Date shopCloseTime) {
		this.shopCloseTime = shopCloseTime;
	}
	public String getShop24HRS() {
		return shop24HRS;
	}
	public void setShop24HRS(String shop24hrs) {
		shop24HRS = shop24hrs;
	}
	public List<String> getShopClosedOn() {
		return shopClosedOn;
	}
	public void setShopClosedOn(List<String> shopClosedOn) {
		this.shopClosedOn = shopClosedOn;
	}
	public List<String> getShopMedicineType() {
		return shopMedicineType;
	}
	public void setShopMedicineType(List<String> shopMedicineType) {
		this.shopMedicineType = shopMedicineType;
	}
	public List<String> getShopPaymentMode() {
		return shopPaymentMode;
	}
	public void setShopPaymentMode(List<String> shopPaymentMode) {
		this.shopPaymentMode = shopPaymentMode;
	}
	public String getShopSpeciality() {
		return shopSpeciality;
	}
	public void setShopSpeciality(String shopSpeciality) {
		this.shopSpeciality = shopSpeciality;
	}
	public String getShopHomeDelivery() {
		return shopHomeDelivery;
	}
	public void setShopHomeDelivery(String shopHomeDelivery) {
		this.shopHomeDelivery = shopHomeDelivery;
	}
	
	public void registerMyChemist()
	{
		String shopTimings;
		String medicineType, paymentMode;
		String remoteIP=Utils.getRequest().getRemoteAddr();
		
		if("N".equals(shop24HRS))
		{
			if(shopClosedOn==null || shopClosedOn.size()==0)
			{
				shopTimings=new SimpleDateFormat("hh:mm a").format(shopOpenTime)+" TO "+new SimpleDateFormat("hh:mm a").format(shopCloseTime)+" | Open All 7 Days a Week";
			}
			else
			{
				shopTimings=new SimpleDateFormat("hh:mm a").format(shopOpenTime)+" TO "+new SimpleDateFormat("hh:mm a").format(shopCloseTime)+" | ";
				for(int i=0;i<shopClosedOn.size();i++)
				{
					if(i<shopClosedOn.size()-1)
					{
						shopTimings+=shopClosedOn.get(i)+", ";
					}
					else
					{
						shopTimings+=shopClosedOn.get(i)+".";
					}
				}
			}
		}
		else
		{
			if(shopClosedOn==null || shopClosedOn.size()==0)
				shopTimings="24 X 7 Open";
			else
			{
				shopTimings="24 HRS Open | ";
				for(int i=0;i<shopClosedOn.size();i++)
				{
					if(i<shopClosedOn.size()-1)
					{
						shopTimings+=shopClosedOn.get(i)+", ";
					}
					else
					{
						shopTimings+=shopClosedOn.get(i)+".";
					}
				}
			}			
		}
		
		
		if(shopMedicineType==null || shopMedicineType.size()==0)
		{
			medicineType=null;
		}
		else
		{
			medicineType="";
			for(int i=0;i<shopMedicineType.size();i++)
			{
				if(i<shopMedicineType.size()-1)
				{
					medicineType+=shopMedicineType.get(i)+", ";
				}
				else
				{
					medicineType+=shopMedicineType.get(i)+".";
				}
			}
		}
		
		
		if(shopPaymentMode==null || shopPaymentMode.size()==0)
		{
			paymentMode=null;
		}
		else
		{
			paymentMode="";
			for(int i=0;i<shopPaymentMode.size();i++)
			{
				if(i<shopPaymentMode.size()-1)
				{
					paymentMode+=shopPaymentMode.get(i)+", ";
				}
				else
				{
					paymentMode+=shopPaymentMode.get(i)+".";
				}
			}
		}
		
		//String shopName, String shopOwnerFName, String shopOwnerLName, String addrLin1, 
		//String addrLin2, String shopCity, String shopState, String shopPin, String shopCountry, 
		//String shopEmail, String shopPhone, String shopMobile, String shopTimings, 
		//String shopMedicineType, String shopSpeciality, String shopPaymentMode, String shopHomeDelivery, String remoteIP
		
		new RegisterChemistDAO().registerChemistInsert(shopName, shopOwnerFName, shopOwnerLName, shopAddr1, shopAddr2, shopCity, shopState, shopPinCode, shopCountry, shopEMail, shopPhone, shopMobile, shopTimings, medicineType, shopSpeciality, paymentMode, shopHomeDelivery, remoteIP);
	}
}
