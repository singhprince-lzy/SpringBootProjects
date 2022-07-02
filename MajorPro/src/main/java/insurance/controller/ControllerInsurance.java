package insurance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import insurance.model.Insurance;
import insurance.model.request.InsuranceStatusRequest;
import insurance.model.request.InsuranceTypeRequest;
import insurance.service.InsuranceService;



/**
 * controller for Insurance
 * 
 * @author kunal.yadav
 *
 */


@RestController
@RequestMapping("/api/v1")
public class ControllerInsurance 
{
	@Autowired
	private InsuranceService insuranceservice;
	

	/*
	 * URL= localhost:9090/api/v1/create
	 */
	@PostMapping("insurance/{username}")
	@PreAuthorize("#username == authentication.name")
	public ResponseEntity<Insurance> createInsurance( 
			@PathVariable("username") String username,
			@Valid @RequestBody InsuranceTypeRequest insuranceType) throws BaseException, Exception
	{
	
	    return ResponseEntity.ok(insuranceservice.createInsurance(username, insuranceType));
	}

	/*
	 * URL= localhost:9090/api/v1/insurances
	 */
	@GetMapping("insurances")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Insurance>> getAllInsurance() throws NotFoundException
	{
		return ResponseEntity.ok(insuranceservice.getAllInsurance());
	}

	/*
	 * URL= localhost:9090/api/v1/status/{id}
	 */
	@PutMapping("status/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<Insurance> updateInsuranceStatus(
			@PathVariable long id,
			@Valid @RequestBody InsuranceStatusRequest insuranceStatusRequest) throws BaseException 
	{
		return ResponseEntity.ok( insuranceservice.updateInsuranceStatus(id, insuranceStatusRequest));
	}

	/*
	 * URL= localhost:9090/api/v1/insurancesbystatus/{status}
	 */
	@GetMapping("insurancesbystatus/{status}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity< List<Insurance>> getAllInsurancesbyStatus(@PathVariable("status") String status) throws BaseException
	{  
		return ResponseEntity.ok(insuranceservice.getAllInsurancesbyStatus(status));   
	}

	/*
	 * URL= localhost:9090/api/v1/insurances/{username}
	 */
	@GetMapping("insurances/{username}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN') or #username == principal")
	public ResponseEntity<List<Insurance>> getInsuranceByUsername(
			@PathVariable("username") String username) throws NotFoundException{
		return ResponseEntity.ok(insuranceservice.getInsuranceByUsername(username));
	}


	/*
	 * URL= localhost:9090/api/v1/insurances/dental or life
	 */
	
	@GetMapping("insurancesbytype/{insurancetype}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Insurance>> getAllDetailsByInsuranceType(@PathVariable("insurancetype") String insurancetype) 
	{
		return ResponseEntity.ok(insuranceservice.getDetailsByInsuranceType(insurancetype));
	}
	
	/*
	 * URL= localhost:8067/api/v1/insurance/{id}
	 */
	@GetMapping("insurance/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN') or #username == principal")
	public ResponseEntity<Insurance> findInsuranceById(@PathVariable long id) throws BaseException, NotFoundException
	{
		return ResponseEntity.ok(insuranceservice.getInsuranceById(id));
	}
	/*
	 * URL= localhost:8067/api/v1/customer/insurance/{id}
	 */
	@GetMapping("customer/insurance/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN') or #username == principal")
	public ResponseEntity<Customer> getCustomerByInsuranceId(@PathVariable long id) throws NotFoundException
	{
		return ResponseEntity.ok(insuranceservice.getCustomerByInsuranceId(id));
	}

} 