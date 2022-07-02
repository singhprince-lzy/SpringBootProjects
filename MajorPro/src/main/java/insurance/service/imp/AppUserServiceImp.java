package insurance.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import insurance.exception.BaseException;
import insurance.exception.NotFoundException;
import insurance.helper.InsuranceStatus;
import insurance.helper.Role;
import insurance.model.AppUser;
import insurance.model.Insurance;
import insurance.model.request.UserUpdateRequest;
import insurance.model.response.AppUserResponse;
import insurance.repository.AppUserRepository;
import insurance.service.AppUserService;

@Service

public class AppUserServiceImp implements UserDetailsService, AppUserService{
	private static final Logger log = LoggerFactory.getLogger(AppUserServiceImp.class);
	
	private final AppUserRepository appUserRepo;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public AppUserServiceImp(
			AppUserRepository appUserRepo,
			PasswordEncoder passwordEncoder) {
		super();
		this.appUserRepo = appUserRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(
			String username) throws UsernameNotFoundException {
		
		AppUser appUser =  appUserRepo.findByUsername(username).
				orElseThrow(()-> new UsernameNotFoundException(username + " not found "));
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(appUser.getRole()));
		return new User(appUser.getUsername(), appUser.getPassword(), authorities);
	}
	
	@Transactional
	@Override
	public AppUserResponse saveAppUser(AppUser appUser) throws BaseException, NotFoundException{
		log.info("creating new user :" + appUser.getUsername());
		
		if(appUserRepo.findByUsername(appUser.getUsername()).isPresent()) {
			log.warn(appUser.getUsername()+ " already Exist");
			
			throw new BaseException(appUser.getUsername() + " already Exist");
		}
		log.info("reached 1");
		
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		appUser.setRole(Role.CUSTOMER.value());
		appUser.setCustomer(null);
		appUserRepo.save(appUser);
		AppUserResponse appUserResponse = new AppUserResponse(
				appUser.getUsername(),
				appUser.getRole());
		return appUserResponse;
	}
	
	@Transactional
	@Override
	public AppUserResponse saveAppUserByAdmin(AppUser appUser)  throws BaseException{
		log.info("creating new user by admin :" + appUser.getUsername());
		
		if(appUserRepo.findByUsername(appUser.getUsername()).isPresent()){
			log.info(appUser.getUsername()+ " already Exist");
			
			throw new BaseException(appUser.getUsername() + " already Exist");
		}
		
		if(!Role.isValidRole(appUser.getRole())) {
			throw new BaseException(appUser.getRole() + "is not a valid role");
		}
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		appUser.setCustomer(null);
		appUserRepo.save(appUser);
		AppUserResponse appUserResponse = new AppUserResponse(
				appUser.getUsername(),
				appUser.getRole());
		return appUserResponse;
	}
	
	@Transactional
	@Override
	public AppUserResponse getUser(String username) throws NotFoundException{
		log.info("Getting details of user : " + username);
		AppUser appUser = appUserRepo.findByUsername(username)
				.orElseThrow(()-> new NotFoundException(username + " not found "));
		AppUserResponse appUserResponse = new AppUserResponse(
				appUser.getUsername(),
				
				appUser.getRole());
		return appUserResponse;
	}
	@Transactional
	@Override
	public List<AppUserResponse> getUsers(){
		log.info("Getting list of users");
		List<AppUser> appUsers = appUserRepo.findAll();
		List<AppUserResponse> appUsersResponse = new ArrayList<AppUserResponse>();
		for(AppUser appUser : appUsers) {
			appUsersResponse.add(new AppUserResponse(
					appUser.getUsername(),				
					appUser.getRole()));
		}
		return appUsersResponse;
	}
	@Transactional
	@Override
	public List<AppUserResponse> getUsersByRole(String role) throws NotFoundException {
		log.info("Getting list of users with role : " + role +  " " + Role.ADMIN.value());
		
		if(!Role.isValidRole(role)) {
			log.info(role + " does not exists");;
			throw new NotFoundException(role + " does not exists");
		}
		List<AppUser> appUsers =  appUserRepo.findByRole(role);
		List<AppUserResponse> appUsersResponse = new ArrayList<AppUserResponse>();
		for(AppUser appUser : appUsers) {
			appUsersResponse.add(new AppUserResponse(
					appUser.getUsername(),
					
					appUser.getRole()));
		}
		return appUsersResponse;
	}
	@Transactional
	@Override
	public String updateUser(String username, UserUpdateRequest userUpdateRequest) throws NotFoundException, BaseException {
		log.info("updating user details of " + username);
		AppUser oldUser = appUserRepo.findByUsername(username)
				.orElseThrow(()-> new NotFoundException(username + " not found "));		
		oldUser.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
		return username + " password Updated..";
	}
	@Transactional
	@Override
	public String deleteUser(String username) throws NotFoundException, BaseException  {
		log.info("deleting user" + username);		
		AppUser existingAppUser = appUserRepo.findByUsername(username)
				.orElseThrow(()-> new NotFoundException(username + " not found in the database"));
		if(!isUserDeleteable(existingAppUser)) {
			throw new BaseException(username + " can not be deleted because of active insurance");
		}
		appUserRepo.delete(existingAppUser);
		return username + " is deleted";
	}	
	@Transactional
	//will be decided based on insurance status
	private boolean isUserDeleteable(AppUser appUser) {
		if(appUser.getCustomer() == null) return true;
		List<Insurance> insurances = appUser.getCustomer().getInsurances();
		if(insurances.size() == 0) return true;
		for(Insurance insurance : insurances) {
			Boolean isRejected = insurance.getStatus().equals(InsuranceStatus.REJECTED.value());
			Boolean isExpired = insurance.getStatus().equals(InsuranceStatus.EXPIRED.value());
			if(!isRejected && !isExpired) {
				return false;
			}
		}
		return true;
	}
}