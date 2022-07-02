package insurance.service.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import insurance.exception.BaseException;
import insurance.exception.NotFoundException;
import insurance.helper.Relation;
import insurance.helper.Role;
import insurance.model.AppUser;
import insurance.model.Customer;
import insurance.model.Dependent;
import insurance.model.Insurance;
import insurance.repository.AppUserRepository;
import insurance.repository.CustomerRepository;
import insurance.repository.DependentRepository;
import insurance.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImp implements CustomerService {
	private static final Logger log = LoggerFactory.getLogger(AppUserServiceImp.class);

	private final AppUserRepository appUserRepo;
	private final CustomerRepository customerRepo;
	private final DependentRepository dependentRepo;

	@Autowired
	public CustomerServiceImp(AppUserRepository appUserRepo, CustomerRepository customerRepo,
			DependentRepository dependentRepo) {
		super();
		this.appUserRepo = appUserRepo;
		this.customerRepo = customerRepo;
		this.dependentRepo = dependentRepo;
	}

	@Override
	public Customer saveCustomer(String username, Customer customer)
			throws UsernameNotFoundException, BaseException {
		
		log.info("Saving customer info for the username : "+ username);
		
		AppUser existingUser = appUserRepo.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException(username + " not found "));
		if(!existingUser.getRole().equals(Role.CUSTOMER.value())) 
			throw new BaseException(username + " is not a customer");
		if(existingUser.getCustomer() != null) 
			throw new BaseException(username + " already have customer info");
		//setting insurance to empty list
		customer.setInsurances(new ArrayList<Insurance>());
		//validating dependents, invalid throws exception
		validateDepentents(customer.getDependents());
		customer.setAppUser(existingUser);
		existingUser.setCustomer(customer);
		return existingUser.getCustomer();
	}

	@Override
	public Customer getCustomer(String username) throws UsernameNotFoundException, NotFoundException{
		log.info("Fetching customer info for the username : "+ username);
		
		AppUser existingUser = appUserRepo.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException(username + " not found "));
		if(existingUser.getCustomer() == null) 
			throw new NotFoundException(username + " customer info not found");
		return existingUser.getCustomer();
	}

	
	@Override
	public Customer updateCustomer(String username, Customer customer) throws UsernameNotFoundException, NotFoundException, BaseException{
		log.info("updating customer info for the username : "+ username);
		AppUser existingUser = appUserRepo.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException(username + " not found "));
		if(existingUser.getCustomer() == null) 
			throw new NotFoundException(username + " customer info not found");
		validateDepentents(customer.getDependents());
		Customer existingCustomer = existingUser.getCustomer();
		
		//deleting existing dependents
		deleteDependents(existingCustomer.getDependents());
		
		//addging dependents
		existingCustomer.setDependents(new ArrayList<Dependent>());
		customer.getDependents().forEach((dep)->{
			dep.setCustomer(existingCustomer);
			existingCustomer.getDependents().add(dep);
		});
		//setting other details
		existingCustomer.setFirstName(customer.getFirstName());
		existingCustomer.setLastName(customer.getLastName());
		existingCustomer.setDob(customer.getDob());
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setMobileNo(customer.getMobileNo());
		existingCustomer.setFatherName(customer.getFatherName());
		existingCustomer.setMotherName(customer.getMotherName());
		existingCustomer.setGender(customer.getGender());
		existingCustomer.setNomineeName(customer.getNomineeName());
		existingCustomer.setNomineeRelation(customer.getNomineeRelation());
		existingCustomer.setMaritalStatus(customer.getMaritalStatus());
		existingCustomer.setAnnualIncome(customer.getAnnualIncome());
		existingCustomer.setPermanentAddress(customer.getPermanentAddress());
		existingCustomer.setOccupation(customer.getOccupation());
		existingCustomer.setAlcholConsumption(customer.getAlcholConsumption());
		existingCustomer.setSmoking(customer.getSmoking());
		existingCustomer.setTobbacoConsumpthion(customer.getTobbacoConsumpthion());
		existingCustomer.setAnyHereditaryDiseases(customer.getAnyHereditaryDiseases());
		existingCustomer.setAnyMajorDiseases(customer.getAnyMajorDiseases());
		existingCustomer.setAnyPreviousDentalCondition(customer.getAnyPreviousDentalCondition());
		return existingCustomer;
	}
	
	
	@Override
	public List<Customer> getAllCustomer(){
		return customerRepo.findAll();
	}
	
	
	private void validateDepentents(List<Dependent> dependents) throws BaseException{
		Set<String> relationSet = new HashSet<String>();
		
		for(Dependent dependent : dependents) {
			if(!Relation.isValidRelation(dependent.getRelation())) 
				throw new BaseException(dependent.getDependentName() + " has an invalid relation");
			
			if(relationSet.contains(dependent.getRelation())) {
				throw new BaseException("only one dependent can have a realtion : " + dependent.getRelation());
			}
			
			relationSet.add(dependent.getRelation());
		}
	}
	
	private void deleteDependents(List<Dependent> dependents) {
		for(Dependent dependent : dependents) {
			dependentRepo.delete(dependent);
		}
	}
}