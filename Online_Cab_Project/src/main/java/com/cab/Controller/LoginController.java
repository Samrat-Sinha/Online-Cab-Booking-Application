package com.cab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cab.Exception.CurrentUserSessionException;
import com.cab.Exception.UserException;
import com.cab.Model.CurrentUserSession;
import com.cab.Model.LoginDTO;
import com.cab.Service.LoginService;

@RestController
@RequestMapping("/OnlineCabBookingApplication/Userlogin")
public class LoginController {

	@Autowired
	private LoginService logService;
	
	@PostMapping("/Login")
	public ResponseEntity<CurrentUserSession> loginHandler(@RequestBody LoginDTO dto) throws UserException{
		return new ResponseEntity<CurrentUserSession>(logService.login(dto),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logoutHandler(@RequestParam String uuid) throws CurrentUserSessionException{
		return new ResponseEntity<String>(logService.LogOut(uuid),HttpStatus.OK);
	}
	
}
