package hawk.springframework.blogapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping({"/","/admin"})
	public String mainPage (Model model) {
		model.addAttribute("template", "Blog app from mainPage Controller");
		return "index";
	}
	
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

}
