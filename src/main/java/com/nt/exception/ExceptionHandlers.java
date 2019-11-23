package com.nt.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.exceptions.TemplateInputException;

@ControllerAdvice
public class ExceptionHandlers {
	
	
	@ExceptionHandler(value=NullPointerException.class)
	public String handleNullException(Model model) {
		model.addAttribute("errMsg","Some problem occured try again later");
		return "error";
	}


	@ExceptionHandler(value= {UserInsertionfailedException.class,IllegalStateException.class,TemplateInputException.class})
	public String handleNoBookfoundException(Model model) {
		ModelAndView mod=new ModelAndView();
		
		model.addAttribute("errMsg","Sorry problem occure ");
		//mod.setViewName("createauser");
		
	
		return "userinfo";
	}
}
