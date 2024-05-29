package com.ToDoList.Controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ToDoList.Entity.Tasks;
import com.ToDoList.Entity.User;
import com.ToDoList.Entity.tasksOnly;
import com.ToDoList.Repository.UserRepository;
import com.ToDoList.Repository.taskRepository;
@Controller
@RequestMapping("/tasks")
public class tasks {
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private taskRepository taskrepo;
	
	@RequestMapping("/dashboards")
	public String dashboards(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loginuser=auth.getName();
		User u1 = this.userrepo.getUserName(loginuser);
		int userId=u1.getSerialNo();
		
		ArrayList<Tasks> container=taskrepo.getAllTasks(userId);
		ArrayList<tasksOnly> tasksend=new ArrayList<>();
		for(Tasks s: container) {
         tasksOnly to = new tasksOnly();
         to.setDdate(s.getDate());
         to.setDdescription(s.getDescription());
         to.setHheading(s.getHeading());
         to.setIid(s.getId());
         tasksend.add(to);
		}
		int totalcount = taskrepo.getcount(userId);
		String loggeduser  =u1.getFirstName();
	    model.addAttribute("tasksend",tasksend);
		model.addAttribute("loggeduser" ,loggeduser);
		model.addAttribute("totalcount",totalcount);
		return "tasks";
	}
	
	@RequestMapping("/delete/{taskid}")
	public String deletetask(Model model,@PathVariable String taskid) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int task_id_to_delete =Integer.parseInt(taskid);
		String loginuser=auth.getName();
		User u1 = this.userrepo.getUserName(loginuser);
		int userId=u1.getSerialNo();
		
		taskrepo.deletetask(userId,task_id_to_delete);
		
		ArrayList<Tasks> container=taskrepo.getAllTasks(userId);
		ArrayList<tasksOnly> tasksend=new ArrayList<>();
		for(Tasks s: container) {
         tasksOnly to = new tasksOnly();
         to.setDdate(s.getDate());
         to.setDdescription(s.getDescription());
         to.setHheading(s.getHeading());
         to.setIid(s.getId());
         tasksend.add(to);
		}
		int totalcount = taskrepo.getcount(userId);
		String loggeduser  =u1.getFirstName();
	    model.addAttribute("tasksend",tasksend);
		model.addAttribute("loggeduser" ,loggeduser);
		model.addAttribute("totalcount",totalcount);
		
		return "tasks";
		
	}
	
	@RequestMapping("/addtasks")
	public String taskadder(Model model) {
		 model.addAttribute("tasker",new Tasks());
		return "addtasks";
	}
	
	@RequestMapping("/taskadded")
	public String taskadded(@ModelAttribute("tasker") Tasks taskk,Principal principal){
		String username = principal.getName();
		User usertoAdd = userrepo.getUserName(username);
		taskk.setUser(usertoAdd);
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		 String str = ft.format(new Date());
		 taskk.setDate(str); 
		taskrepo.save(taskk);
		
		return "redirect:/tasks/addtasks";
	}
	
	@RequestMapping("/profile")
    public String userprofile(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loginuser=auth.getName();
		User u1 = this.userrepo.getUserName(loginuser);
		int userId=u1.getSerialNo();
		String useremail= u1.getEmail();
		String userfirstname= u1.getFirstName();
		String userlastname= u1.getLastName();
		String userdescription= u1.getDescription();
		String usergoal= u1.getGoal();
		model.addAttribute("useremail",useremail);
		model.addAttribute("userlastname",userlastname);
		model.addAttribute("userfirstname",userfirstname);
		model.addAttribute("userdescription",userdescription);
		model.addAttribute("usergoal",usergoal);
		return "profile";
		
	}
	
	@RequestMapping("/profileedit")
	public String profileditchannel(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loginuser=auth.getName();
		User UserInformaion = this.userrepo.getUserName(loginuser);
		model.addAttribute("UserInformaion",UserInformaion);
		return "Profileedit";
		
	}
	
	
	@RequestMapping("/profileeditsave")
	public String profileditsave(@ModelAttribute("UserInformaion") User user_modification_info,Model model) {
		String firstname1 = user_modification_info.getFirstName();
		String lastname1= user_modification_info.getLastName();
		String goal1= user_modification_info.getGoal();
		String description1 =user_modification_info.getDescription();
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loginuser=auth.getName();
		User usser = userrepo.getUserName(loginuser);
		
		
		String firstname = usser.getFirstName();
		String lastname = usser.getLastName();
		String goal = usser.getGoal();
		String description= usser.getDescription();
		
		if(firstname1!=firstname) {
			usser.setFirstName(firstname1);
			usser.setGoal(goal1);
			usser.setDescription(description1);
		}
		if(lastname!=lastname1) {
			usser.setLastName(lastname1);
		}
		if(goal!=goal1) {
			usser.setGoal(goal1);
		}
		if(description!= description1 ) {
			usser.setDescription(description1);
		}
		userrepo.save(usser);
		User UserInformaion = this.userrepo.getUserName(loginuser);
		model.addAttribute("UserInformaion",UserInformaion);
		return "redirect:/tasks/profileedit";
	}
}
