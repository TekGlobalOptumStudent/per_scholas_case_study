package com.PatchworkNovels.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {
	
	@RequestMapping("/error")
	public ModelAndView errorHandler(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("error");
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    String message = "There was an error.";
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	        	message = "We could not find your page, please return to home.";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            message = "Something went wrong, please return to home.";
	        }
	    }
	    mav.addObject("error_message", message);
	    return mav;
	}

	@Override
	public String getErrorPath() {
		return null;
	}
}
