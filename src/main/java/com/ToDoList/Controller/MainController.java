package com.ToDoList.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ToDoList.Entity.User;
import com.ToDoList.Repository.UserRepository;

@Controller
public class MainController {
	
	@Autowired
	public UserRepository userrepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@RequestMapping("/")
    public String director(Model model) {
		model.addAttribute("user",new User());
		return "home";
	}
	
	
	
	@RequestMapping("/home")
	public String HomePage(Model model) {
		model.addAttribute("user",new User());
		return "home";
	}
	
	
	@RequestMapping("/login")
	public String Login(Model model,@RequestParam(name = "error", required = false) String error) {
		 if (error != null) {
	            model.addAttribute("errorMessage", "Invalid username or password");
	        }	
		return "login";
	}
	
	
	@RequestMapping("/UserRegistration")
	public String UserRegistration(@ModelAttribute("user") User user,Model model) {
		
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userrepository.save(user);
			User usertest = userrepository.getUserName(user.getEmail());
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		model.addAttribute("user",new User());
		  return "redirect:" + "/home";
	}
	
	

}
