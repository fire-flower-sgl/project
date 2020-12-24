package com.mhtech.platform.msrv.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mhtech.platform.msrv.auth.bean.pojo.UserDTO;


public class ApplicationContextHolder {

	private static ThreadLocal<UserDTO> currentUser = new ThreadLocal<UserDTO>();
	
	public static final List<String> BLACK_IPS_LIST = new ArrayList<String>();
	
	public static final Map<String,Long>  ACCESS_TIME =  new HashMap<String, Long>();

	public static void removeCurrentUser() {
		currentUser.remove();
	}
	
	public static UserDTO getCurrentUser() {
		return currentUser.get();
	}

	public static void setCurrentUser(UserDTO user) {
		currentUser.set(user);
	}
}
