package com.myes.nbadata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myes.nbadata.dao.UserMapper;
import com.myes.nbadata.po.User;
import com.myes.nbadata.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> queryUsers() throws Exception {
		
		return userMapper.queryUsers();
	}

}
