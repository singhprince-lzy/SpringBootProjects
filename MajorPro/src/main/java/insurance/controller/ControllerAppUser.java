package insurance.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import insurance.Insurance;
import insurance.exception.BaseException;
import insurance.exception.NotFoundException;
import insurance.model.AppUser;
import insurance.model.request.UserUpdateRequest;
import insurance.model.response.AppUserResponse;
import insurance.service.AppUserService;
@RestController
@RequestMapping("/api/v1")



/**
 * controller for app user
 * 
 * @author kunal.yadav
 *
 */

public class ControllerAppUser {
	private final AppUserService appUserService;

	private static final Logger logger = LoggerFactory.getLogger(Insurance.class);
	@Autowired
	public ControllerAppUser(AppUserService appUserService) {
		this.appUserService = appUserService;
	}

	//register new user by anyone
	@PostMapping("register")
	public ResponseEntity<AppUserResponse> saveAppUser(
			@Valid @RequestBody AppUser appUser) throws BaseException, NotFoundException{
		
		return ResponseEntity.ok().body(appUserService.saveAppUser(appUser));
	}

	/**
	 * Admin has authority 
	 * @throws BaseException
	 */
	//register new user by admin
	@PostMapping("registerbyadmin")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<AppUserResponse> saveAppUserByAdmin(
			@Valid @RequestBody AppUser appUser
			) throws BaseException{

		return ResponseEntity.ok().body(appUserService.saveAppUserByAdmin(appUser));
	}

	//get user details by username
	@GetMapping("user/{username}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN') or #username == authentication.name")
	public ResponseEntity<AppUserResponse> getUser(
			@PathVariable("username") String username) throws NotFoundException{
		return ResponseEntity.ok().body(appUserService.getUser(username));
	}

	//update user by username
	@PutMapping("user/{username}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or #username == authentication.name")
	public ResponseEntity<String> updateUser(
			@PathVariable("username") String username,
			@Valid @RequestBody UserUpdateRequest userUpdateRequest) throws NotFoundException, BaseException{

		return ResponseEntity.ok().body(appUserService.updateUser(username, userUpdateRequest));
	}

	//get list of users
	@GetMapping("users")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<AppUserResponse>> getAllUsers(){
		return ResponseEntity.ok().body(appUserService.getUsers());
	}

	// get list of users by role
	@GetMapping("usersbyrole/{role}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<AppUserResponse>> getUsersByRole(
			@PathVariable("role") String role) throws NotFoundException{

		return ResponseEntity.ok().body(appUserService.getUsersByRole(role));
	}

	//delete a user by username
	@DeleteMapping("user/{username}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or #username == authentication.name")
	public ResponseEntity<String> deleteUser(
			@PathVariable("username") String username) throws NotFoundException, BaseException{

		return ResponseEntity.ok().body(appUserService.deleteUser(username));
	}
}