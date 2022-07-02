package insurance.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import insurance.exception.BaseException;
import insurance.exception.NotFoundException;
import insurance.model.Customer;
import insurance.service.CustomerService;

/**
 * controller for Customer
 * 
 * @author kunal.yadav
 *
 */




@RestController
@RequestMapping("/api/v1")
public class ControllerCustomer {
	

	 private static final Logger logger = LoggerFactory.getLogger(ControllerCustomer.class);
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("customer/{username}")
	@PreAuthorize("#username == authentication.name")
	public ResponseEntity<Customer> createCustomer(
			
			@PathVariable("username") String username,
			@Valid @RequestBody Customer customer) throws UsernameNotFoundException, BaseException {
		
		return ResponseEntity.ok().body(customerService.saveCustomer(username, customer));
	}
	
	@GetMapping("customer/{username}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN') or #username == authentication.name")
	public ResponseEntity<Customer> getCustomer(
			@PathVariable("username") String username
			) throws UsernameNotFoundException, BaseException, NotFoundException{
		
		return ResponseEntity.ok().body(customerService.getCustomer(username));
	}
	
	@PutMapping("customer/{username}")
	@PreAuthorize("#username == authentication.name")
	public ResponseEntity<Customer> updateCustomer(
			@PathVariable("username") String username,
			@Valid @RequestBody Customer customer
			) throws UsernameNotFoundException, BaseException, NotFoundException{
		
		return ResponseEntity.ok().body(customerService.updateCustomer(username, customer));
	}
	
	@GetMapping("customers")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		logger.info("cust");
		return ResponseEntity.ok().body(customerService.getAllCustomer());
	}

}