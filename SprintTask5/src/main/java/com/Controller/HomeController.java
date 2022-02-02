package com.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestController
@Controller
//@ResponseBody
public class HomeController {
	 
	  @RequestMapping("/")
	    public String root(){
	        return "home";
	    }

	  @RequestMapping("/user")
	    public String home(){
	        return "user";
	    }

	  @RequestMapping("/admin")
	    public String admin(){
	        return "admin";
	    }
  
}
