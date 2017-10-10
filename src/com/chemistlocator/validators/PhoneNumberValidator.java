package com.chemistlocator.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("phoneNumberValidator")
public class PhoneNumberValidator implements Validator
{
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
	{
		String phoneNumber=value.toString();
		try
		{
			Long.parseLong(phoneNumber);
		}
		catch(NumberFormatException e)
		{
			FacesMessage msg = new FacesMessage("Phone Number validation failed","Invalid Phone Number");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		
		if(phoneNumber.startsWith("0"))
		{
			FacesMessage msg = new FacesMessage("Phone Number validation failed","Phone Number should not start with zero");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		
		if(phoneNumber.length()!=8)
		{
			FacesMessage msg = new FacesMessage("Phone Number validation failed","Phone Number should be 8-Digits long");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}	
}
