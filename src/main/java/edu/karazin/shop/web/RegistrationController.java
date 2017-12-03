package edu.karazin.shop.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.karazin.shop.model.Role;
import edu.karazin.shop.model.User;
import edu.karazin.shop.service.UserService;

@Controller
@RequestMapping("register")
public class RegistrationController {
	
	private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);
	
	private final UserService userService;

	public RegistrationController(@Autowired UserService userService) {
		this.userService = userService;
	}

	 @RequestMapping(method = RequestMethod.GET)
	    public String newUser(Model model) {
		 log.info("Render form for registration");
	        User user = new User();
	        model.addAttribute("user", user);
	        return "registration";
	    }
	 
	 @RequestMapping(method = RequestMethod.POST)
	    public String saveUser(@Valid User user, BindingResult result, Model model) {
	 
//	        if (result.hasErrors()) {
//	            return "registration";
//	        }
		 	
		 	user.setRole(Role.ROLE_ADMIN);
//		 	user.setRole(Role.ROLE_USER);		 
	        userService.saveUser(user);
	 
	        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
	        
	        return "registration-success";
	    }

}
