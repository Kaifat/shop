package edu.karazin.shop.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.karazin.shop.model.User;
import edu.karazin.shop.service.UserService;

@Controller
@RequestMapping("login")
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	private final UserService userService;

	public LoginController(@Autowired UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		log.info("View LOGIN form");
		model.addAttribute("loginForm", new LoginForm());
		return "login";	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("loginForm") LoginForm form) {
		log.info("11111111111111");
		User user = userService.validateUser(form.getEmail(), form.getPassword());
	
		if (user != null) {
			log.info(user.getEmail());
			return "forward:/";
		} else {
			return "redirect:/login";
		}
		
	}
}
