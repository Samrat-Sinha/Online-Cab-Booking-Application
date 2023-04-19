package com.cab.Service;

import com.cab.Exception.AdminException;
import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.CustomerException;
import com.cab.Model.CurrentUserSession;
import com.cab.Model.UserLoginDTO;

public interface UserLoginService {

public CurrentUserSession login(UserLoginDTO dto)throws CustomerException, AdminException;
	
	public String LogOut(String uuid)throws CurrentUserSessionException; 
	
	
}
