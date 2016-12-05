package com.myes.nbadata.service;

import java.util.List;

import com.myes.nbadata.po.User;

public interface UserService {

	List<User> queryUsers() throws Exception;
}
