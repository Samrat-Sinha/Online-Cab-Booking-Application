package com.cab.Service;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.UserException;
import com.cab.Model.CurrentUserSession;
import com.cab.Model.LoginDTO;

public interface LoginService {

	
	public CurrentUserSession login(LoginDTO dto)throws UserException;
	
	public String LogOut(String uuid)throws CurrentUserSessionException; 
	
	public CurrentUserSession userdata(String uuid)throws CurrentUserSessionException;
}
