package com.ToDoList.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity

public class WebConfig{
	
	
	@Autowired
	private Userdetailserviceimplmn usercustom;
	
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	   @Bean
	   public UserDetailsService getUserService() {
		   return new Userdetailserviceimplmn();
	   }
	 
	  
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
	    	
	    	System.out.println("AuthenticationManagerBuilder");
	    	System.out.println(usercustom);
	    	
	        auth.userDetailsService(usercustom).passwordEncoder(passwordEncoder());
	    } 
	    
	    
	    
	    @Bean
	    public DaoAuthenticationProvider authProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(getUserService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }
	    
	    
	    @Bean 
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
	    	http.csrf().disable().authorizeHttpRequests().requestMatchers("/","/home","/login","loginvalidation","/UserRegistration","/CSS/style.css").permitAll().requestMatchers("/tasks/**").authenticated()
	    	.and().formLogin().loginPage("/login").loginProcessingUrl("/loginvalidation").defaultSuccessUrl("/tasks/dashboards").permitAll();
	    	
	    	DefaultSecurityFilterChain   securitych = http.build();
	    	
	    	return securitych;
	    }
	
	 
	
	

	
	
	   
	

}
