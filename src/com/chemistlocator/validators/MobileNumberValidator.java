package com.chemistlocator.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("mobileNumberValidator")
public class MobileNumberValidator implements Validator
{
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
	{
		String mobileNumber=value.toString();
		try
		{
			Long.parseLong(mobileNumber);
		}
		catch(NumberFormatException e)
		{
			FacesMessage msg = new FacesMessage("Mobile Number validation failed","Invalid Mobile Number");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		
		if(mobileNumber.startsWith("0"))
		{
			FacesMessage msg = new FacesMessage("Mobile Number validation failed","Mobile Number should not start with zero");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		
		if(mobileNumber.length()!=10)
		{
			FacesMessage msg = new FacesMessage("Mobile Number validation failed","Mobile Number should be 10-Digits long");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}	
}
