package com.Controller;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Configuration.LoginSuccessHandler;
import com.Service.CustomUserDetails;

//@RestController
@Controller
//@ResponseBody
public class HomeController {
	
	 
	    LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
	    
	
	 
	  @RequestMapping("/")
	    public String root(){
	        return "home";
	    }

	  @RequestMapping("/user")
	    public String user(Principal principal,Model model){
		//  System.out.println(loginSuccessHandler.);
		   
		  Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  String username="";
		    if (principal1 instanceof UserDetails) {
		       username = ((UserDetails)principal1).getUsername();
		    } else {
		       username = principal1.toString();
		    }
		    model.addAttribute("uName", username);
		    System.out.println(username);
		  
	        return "user";
	    }

	  @RequestMapping("/admin")
	    public String admin(Principal principal,Model model){
		  Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  String username="";
		    if (principal1 instanceof UserDetails) {
		       username = ((UserDetails)principal1).getUsername();
		    } else {
		       username = principal1.toString();
		    }
		    model.addAttribute("uName", username);
		    System.out.println(username);
	        return "admin";
	    }
  
	  
}
