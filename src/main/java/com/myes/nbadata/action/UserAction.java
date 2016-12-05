package com.myes.nbadata.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myes.nbadata.po.User;
import com.myes.nbadata.service.UserService;

@Controller
public class UserAction {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("user/queryUsers.shtml")
	public String queryUsers(ModelMap model) throws Exception{
		
		List<User> list = userService.queryUsers();
		
		System.out.println(list);
		
		return "user/queryUsers";
	}
}
