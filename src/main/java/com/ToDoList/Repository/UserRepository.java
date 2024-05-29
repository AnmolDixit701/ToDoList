package com.ToDoList.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ToDoList.Entity.User;


@Repository
public interface UserRepository  extends JpaRepository<User,String> {
	
	@Query("SELECT u FROM User u  WHERE u.Email=:Email")
	User getUserName(@Param("Email") String Email);
	
	
	
	
	

	

}
