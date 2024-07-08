package com.stackroute.githubloginservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class GithubLoginerviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubLoginerviceApplication.class, args);
	}


	@GetMapping("/")
	public String message(Principal principal) {
		return "Your login is successful!! Welcome to Connect India";
	}



}
