package edu.karazin.shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
 
	@GetMapping(value="/")
    public String homepage(){
        return "index";
    }
}
