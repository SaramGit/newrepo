package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.nt.exception.UserInsertionfailedException;
import com.nt.model.User;


@Service
public class UserService {
	private List<User> userList = new ArrayList<>();
	public List<User> getAllUsers(){
		return userList;
	}
	public void addUser(User user)  {
		
			  if(user.getUserName()==null||user.getMarried()==null||user.getGender()==null||user.getProfile()==null) {
					throw new UserInsertionfailedException("sorry user not added");
			  }
			  userList.add(user);
		
	}
} 