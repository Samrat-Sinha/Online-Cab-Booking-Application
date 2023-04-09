package com.cab.Service;



import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.UserException;
import com.cab.Model.CurrentUserSession;
import com.cab.Model.LoginDTO;
import com.cab.Model.User;
import com.cab.Repositary.CurrentUserSessionRepo;
import com.cab.Repositary.UserRepo;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private CurrentUserSessionRepo currRepo;
	
	@Override
	public CurrentUserSession login(LoginDTO dto) throws UserException {
		// TODO Auto-generated method stub
		Optional<User> usr = uRepo.findByEmail(dto.getEmail());
		if(usr.isPresent()) {
			User existingUser = usr.get();
			Optional<CurrentUserSession> validUserSession = currRepo.findById(existingUser.getUserId());
			if(validUserSession.isPresent()) {
				throw new UserException("User is already Logged In with this emailId");
			}
			else {
				if(existingUser.getPassword().equals(dto.getPassword())) {
					String key = RandomStringUtils.randomAlphanumeric(6);
					
					CurrentUserSession curr = new CurrentUserSession();
					curr.setCurrUserId(existingUser.getUserId());
					curr.setUuid(key);
					curr.setCurrRole(existingUser.getRole());
					curr.setCurrStatus("Login Succussfull");
					return currRepo.save(curr);	
				}
				else {
					throw new UserException("Please Enter a Valid Password!!");
				}
			}
		}
		else {
			throw new UserException("Please Enter a correct User id!!!");
		}
	}

	@Override
	public String LogOut(String uuid) throws CurrentUserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validUserToken = currRepo.findByUuid(uuid);
		if(validUserToken.isPresent()) {
			CurrentUserSession curr = validUserToken.get();
			currRepo.delete(curr);
			return "Logged Out Successfull";
		}
		else {
			throw new CurrentUserSessionException("User Not Logged In with this email");
		}
	}

	@Override
	public CurrentUserSession userdata(String uuid) throws CurrentUserSessionException {
		// TODO Auto-generated method stub
		return null;
	}

}