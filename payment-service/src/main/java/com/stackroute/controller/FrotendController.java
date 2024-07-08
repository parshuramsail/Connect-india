package com.stackroute.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrotendController {

	@RequestMapping(path = "/home")
	public String home(ModelMap model) {
		System.out.println("In Home Controller Method");
		String email =  "Shuvo@gmail.com";
		double amount = 2000;

		model.addAttribute("email", email);
		model.addAttribute("amount", amount);

		return "payment";
	}

	@GetMapping("/action")
	public String index() {
		return "index";
	}

}
