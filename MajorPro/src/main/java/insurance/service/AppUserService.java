package insurance.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import insurance.exception.BaseException;
import insurance.exception.NotFoundException;
import insurance.model.AppUser;
import insurance.model.request.UserUpdateRequest;
import insurance.model.response.AppUserResponse;


	/**
	 * @author Kunal Yadav
	 * 
	 */ 

public interface AppUserService {
	

	/**
	 * This will take username as input and provide user details
	 * 
	 * @param username
	 * @return userDetails
	 * @throws UsernameNotFoundException
	 */

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	

	/**
	 * save app user in the database
	 * 
	 * @param appUser
	 * @return saved appUser
	 * @throws BaseException
	 * @throws NotFoundException 
	 */

	AppUserResponse saveAppUser(AppUser appUser) throws BaseException, NotFoundException;


	/**
	 * App User would be saved by admin
	 * 
	 * @param appUser
	 * @return saved appUser
	 * @throws BaseException
	 */
	
	AppUserResponse saveAppUserByAdmin(AppUser appUser) throws BaseException;

	

	/**
	 * Get user
	 * 
	 * @param username
	 * @return appUser
	 * @throws NotFoundException
	 */
	
	AppUserResponse getUser(String username) throws NotFoundException;


	/**
	 * @return List of AppUsers
	 */
	
	List<AppUserResponse> getUsers();


	/**
	 * 
	 * 
	 * @param username
	 * @param userUpdateRequest
	 * @return message
	 * @throws NotFoundException
	 * @throws BaseException 
	 */
	
	String updateUser(String username, UserUpdateRequest userUpdateRequest) throws NotFoundException, BaseException;


	/**
	 * @param username
	 * @return message 
	 * @throws NotFoundException
	 * @throws BaseException 
	 */
	
	String deleteUser(String username) throws NotFoundException, BaseException;


	/**
	 * 
	 * This function will return list of users by role
	 * 
	 * @param role
	 * @return list of user by their role
	 * @throws NotFoundException 
	 */
	
	List<AppUserResponse> getUsersByRole(String role) throws NotFoundException;


}