package edu.karazin.shop.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.karazin.shop.dao.UserRepository;
import edu.karazin.shop.model.User;

@Controller
@RequestMapping("profile")
public class UserProfileController {
	
	private static final Logger log = LoggerFactory.getLogger(UserProfileController.class);

	private final UserRepository userRepository;
	
	public UserProfileController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getProfile(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = this.userRepository.findByLogin(auth.getName());
		model.addAttribute("userDetails", user);
		return "user-details";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String updateProfile(Model model, User userDetails) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = this.userRepository.findByLogin(auth.getName());
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		user.setAddress(userDetails.getAddress());
		user.setPhone(userDetails.getPhone());
		
		this.userRepository.save(user);
		
		return "redirect:/profile";
	}

}
