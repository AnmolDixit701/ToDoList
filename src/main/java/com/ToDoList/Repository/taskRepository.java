package com.ToDoList.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ToDoList.Entity.Tasks;
import com.ToDoList.Entity.User;

import jakarta.transaction.Transactional;
@Repository
public interface taskRepository extends JpaRepository<Tasks,Integer> {
    @Query("select count(t) from Tasks t where t.user.SerialNo=:userId")
	int getcount(@Param("userId")int userId);
    
    
    @Query("SELECT t FROM Tasks t WHERE t.user.SerialNo=:userId")
	ArrayList<Tasks> getAllTasks(@Param("userId")int userId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Tasks t where t.user.SerialNo=:userId and t.id=:task_id_to_delete")
	void deletetask(@Param("userId")int userId,@Param("task_id_to_delete") int task_id_to_delete);







     
	
	

}
