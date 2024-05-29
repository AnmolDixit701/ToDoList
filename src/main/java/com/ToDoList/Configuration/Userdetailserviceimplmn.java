package com.ToDoList.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ToDoList.Entity.User;
import com.ToDoList.Repository.UserRepository;

import jakarta.transaction.Transactional;



@Service
public class Userdetailserviceimplmn implements UserDetailsService {
	
	@Autowired
	private UserRepository  userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user_information =userRepository.getUserName(username);

		
		if(user_information == null) {
			throw new UsernameNotFoundException("Could not found user !!!");
		}
		
		userdetailsservice userdetailservice = new userdetailsservice(user_information);
		return userdetailservice;
	}

}
