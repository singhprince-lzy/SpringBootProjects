package insurance.service;

import java.util.List;

import insurance.exception.BaseException;
import insurance.exception.NotFoundException;
import insurance.model.Customer;
import insurance.model.Insurance;
import insurance.model.request.InsuranceStatusRequest;
import insurance.model.request.InsuranceTypeRequest;

public interface InsuranceService {

	/**
	 * @param username
	 * @param insuranceType
	 * @return new created insurance
	 * @throws NotFoundException
	 * @throws BaseException
	 * @throws Exception
	 */
	
	Insurance createInsurance(String username , InsuranceTypeRequest insuranceType) throws NotFoundException, BaseException, Exception;

	
	/**
	 * @param id
	 * @return existing insurance
	 * @throws NotFoundException
	 */
	
	Insurance getInsuranceById(long id) throws NotFoundException;

	
	/**
     * @param id
     * @param insurance
     * @return updated insurance
     * @throws BaseException
     */
	
    Insurance updateInsuranceStatus(long id,InsuranceStatusRequest insurance) throws BaseException;
  

	/**
	 * @return list of all insurances
	 */
    
	List<Insurance> getAllInsurance();

	/**
	 * @param status
	 * @return list of insurance by status
	 * @throws BaseException
	 */
	
	List<Insurance> getAllInsurancesbyStatus(String status) throws BaseException;


	/**
	 * @param username
	 * @return list of insurance associated with username
	 * @throws NotFoundException
	 */
	
	List<Insurance> getInsuranceByUsername(String username) throws NotFoundException;

	/**
	 * @param type
	 * @return list of insurance of a certain type
	 */
	
	List<Insurance> getDetailsByInsuranceType(String type);

	/**
	 * @param id
	 * @return customer who own the insurance
	 * @throws NotFoundException
	 */
	
	Customer getCustomerByInsuranceId(Long id) throws NotFoundException;
	

}