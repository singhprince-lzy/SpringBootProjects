package insurance.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import insurance.exception.BaseException;
import insurance.exception.NotFoundException;
import insurance.model.Customer;

public interface CustomerService {
	
	/**
	 * save customer details
	 * 
	 * @param username
	 * @param customer
	 * @return create a customer
	 * @throws UsernameNotFoundException
	 * @throws BaseException
	 */

	Customer saveCustomer(String username, Customer customer) throws UsernameNotFoundException, BaseException;


	/**
	 * 
	 * Update customer details
	 * 
	 * @param username
	 * @param customer
	 * @return update customer and its dependents
	 * @throws UsernameNotFoundException
	 * @throws BaseException
	 * @throws NotFoundException
	 */
	
	public Customer updateCustomer(String username, Customer customer) throws UsernameNotFoundException, BaseException, NotFoundException;

	/**
	 * 
	 * @param username
	 * @return a customer by username
	 * @throws UsernameNotFoundException
	 * @throws NotFoundException
	 */
	
	Customer getCustomer(String username) throws UsernameNotFoundException, NotFoundException;

	/**
	 * 
	 * get list of all customers 
	 * 
	 * @return list of all the cutomers
	 */
	
	List<Customer> getAllCustomer();
}