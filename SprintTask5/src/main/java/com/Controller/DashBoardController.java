package com.Controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Entity.SearchKeyWords;
import com.Service.SearchKeyWordsServiceImp;


@Controller

public class DashBoardController {
	
	@Autowired
	SearchKeyWordsServiceImp si;
	
	@RequestMapping("/dashBoard")
	public  String dashController(Map<String, Object> model1,Model model,Principal principal) {
		
		Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  String username="";
		    if (principal1 instanceof UserDetails) {
		       username = ((UserDetails)principal1).getUsername();
		    } else {
		       username = principal1.toString();
		    }
		    model.addAttribute("uName", username);
		    System.out.println(username);
		
		List<SearchKeyWords> searchesData = si.getAllSearch();
		
		 model1.put("searchesData", searchesData);
		    model1.put("count", "Hello from Spring MVC");
	        return "dashBoard";
	    
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public String
	thymeleafView( @RequestParam String q ,Map<String, Object> model,RedirectAttributes redirectAttributes, HttpServletResponse httpResponse) throws IOException {
		/*
		 * model.put("number", 1234); model.put("message", "Hello from Spring MVC");
		 */
	   // return new ModelAndView("thymeleaf/index");
	    System.out.println(q);
	    
	    	
	    	
			SearchKeyWords s = new SearchKeyWords((long) 0,q,0);
	    	
	    	SearchKeyWords te1= si.addSearch(s);//sending the model obtained from client to service to process, for adding tenant
			
			ResponseEntity<SearchKeyWords> rt=new ResponseEntity<SearchKeyWords>(te1,HttpStatus.OK); //response to client
			//return rt;
			
			httpResponse.sendRedirect("/dashBoard");
	    
	   return "redirect:/dashBoard";
	}
	

	
	

}
