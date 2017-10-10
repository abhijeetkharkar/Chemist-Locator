package com.chemistlocator.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator
{
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
	{
		String emailID=value.toString();
		String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		if(!emailID.matches(EMAIL_REGEX))
		{
			FacesMessage msg = new FacesMessage("E-Mail ID validation failed","Invalid E-Mail ID");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}		
	}	
}
