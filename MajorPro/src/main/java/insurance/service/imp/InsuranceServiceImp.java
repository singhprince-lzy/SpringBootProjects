package insurance.service.imp;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insurance.exception.BaseException;
import insurance.exception.NotFoundException;
import insurance.helper.InsuranceStatus;
import insurance.helper.InsuranceType;
import insurance.model.AppUser;
import insurance.model.Customer;
import insurance.model.Insurance;
import insurance.model.ScoreRules;
import insurance.model.request.InsuranceStatusRequest;
import insurance.model.request.InsuranceTypeRequest;
import insurance.repository.AppUserRepository;
import insurance.repository.InsuranceRepository;
import insurance.repository.ScoreRulesRepository;
import insurance.service.InsuranceService;

@Service
@Transactional
public class InsuranceServiceImp implements InsuranceService {

	private static final Logger log = LoggerFactory.getLogger(InsuranceServiceImp.class);

	@Autowired
	private InsuranceRepository insurancerepository;
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private ScoreRulesRepository rulesRepository;  

	//CREATE INSURANCE
	@Override
	public Insurance createInsurance (String username, InsuranceTypeRequest insuranceType) throws Exception{
		log.info("Creating Insurance for Customer " + username);
		AppUser existingUser = appUserRepository.findByUsername(username) 
				.orElseThrow(()-> new NotFoundException(username + " Not found"));

		if(existingUser.getCustomer() == null)
			throw new NotFoundException("No customer found with the username :" + username);

		if(!InsuranceType.isValidType(insuranceType.getType())) 
			throw new BaseException(insuranceType.getType() + " is not a valid type of insurance");

		Customer existingCustomer = existingUser.getCustomer();

		if(insuranceAlreadyExist(existingCustomer.getInsurances(), insuranceType.getType()))
			throw new BaseException(insuranceType.getType() + " insurance already exists");

		Insurance newInsurance = new Insurance();
		newInsurance.setType(insuranceType.getType());

		int age= Period.between(existingCustomer.getDob().toLocalDate(), LocalDate.now()).getYears();

		ScoreRules scoreRules = rulesRepository.findByType(newInsurance.getType())
				.orElseThrow(()-> new BaseException("Insurance Category Not Found"+ newInsurance.getType()));

		if(!(age>=scoreRules.getMinAge() && age<=scoreRules.getMaxAge())){
			log.info("Not a Valid Age to apply for Insurance");
			throw new BaseException("TOO OLD OR TOO YOUNG TO APPLY FOR INSURANCE");
		}

		newInsurance.setScore(getScore(existingCustomer, scoreRules));
		newInsurance.setPremium(getPremium(existingCustomer, scoreRules, newInsurance.getScore()));
		newInsurance.setStatus(InsuranceStatus.WAITING.value());
		newInsurance.setCoverage(newInsurance.getPremium() * scoreRules.getPremiumToCoverageMultiplyer());
		newInsurance.setIssueDate(Date.valueOf(LocalDate.now()));
		newInsurance.setExpireDate(Date.valueOf(LocalDate.now().plusYears(scoreRules.getCoverageTime())));
		newInsurance.setCustomer(existingCustomer);
		existingCustomer.getInsurances().add(newInsurance);
		//insurancerepository.save(newInsurance);
		return newInsurance;
	}

	private boolean insuranceAlreadyExist(List<Insurance> existingInsurances, String type) {
		for(Insurance insurance : existingInsurances){
			Boolean isRejected = insurance.getStatus().equals(InsuranceStatus.REJECTED.value());
			Boolean isExpired = insurance.getStatus().equals(InsuranceStatus.EXPIRED.value());
			Boolean isSameType = insurance.getType().equals(type);

			if(!isRejected && !isExpired && isSameType) {
				return true;
			}
		}
		return false;
	}

	private long getScore(Customer existingCustomer, ScoreRules rules) throws Exception {
		log.info("Getting Final Score");
		Long score=rules.getBaseScore();

		if(existingCustomer.getTobbacoConsumpthion())
			score-=rules.getTobbacoPoints();

		if(existingCustomer.getAlcholConsumption())
			score-=rules.getAlcholPoints();

		if(existingCustomer.getAnyHereditaryDiseases())
			score-=rules.getHereditaryDiseasesPoints();

		if(existingCustomer.getAnyMajorDiseases())
			score-=rules.getMajorDiseasesPoints();

		if(existingCustomer.getSmoking())
			score-=rules.getSmokingPoints();

		if(existingCustomer.getAnyPreviousDentalCondition())
			score-=rules.getPreviousDentalConditionPoints();

		return score;
	}

	private double getPremium(Customer existingCustomer, ScoreRules rules, Long score)
	{
		log.info("Getting Base Premium Amount");
		double basePremium=rules.getBasePremium();
		basePremium += existingCustomer.getDependents().size() * rules.getCostPerDependent();
		basePremium = basePremium + (basePremium * 10)/score;
		return Math.round(basePremium);
	}

	//GET ALL INSURANCE
	@Override
	public List<Insurance> getAllInsurance(){
		log.info("Getting All Insurance");
		return insurancerepository.findAll();
	}

	//GET INSURANCE BY ID
	@Override
	public Insurance getInsuranceById(long id) throws NotFoundException{  
		log.info("Getting Insurance by ID "+id);
		return insurancerepository.findById(id).orElseThrow(()-> new NotFoundException("Insurance Not Found"));
	}

	@Override
	public Customer getCustomerByInsuranceId(Long id) throws NotFoundException {
		log.info("Getting Customer by Insurance ID "+id);
		Insurance existingInsurance = insurancerepository.findById(id)
				.orElseThrow(()->new NotFoundException("Insurance Not Found"));
		return existingInsurance.getCustomer();
	}


	@Override
	public Insurance updateInsuranceStatus(long id, InsuranceStatusRequest insuranceStatusRequest) throws BaseException  {
		Insurance existingInsurance = insurancerepository.findById(id)
				.orElseThrow(()->new BaseException("No Insurance Found with id:"+id));

		if(!existingInsurance.getStatus().equals(InsuranceStatus.PROCESSING.value())) {
			log.info("status is : " + existingInsurance.getStatus());
			throw new BaseException("Status can't be updated");
		}

		if(insuranceStatusRequest.getStatus().equals(InsuranceStatus.ACTIVE.value())) {
			existingInsurance.setStatus(InsuranceStatus.ACTIVE.value());
		}else if(insuranceStatusRequest.getStatus().equals(InsuranceStatus.REJECTED.value())) {
			existingInsurance.setStatus(InsuranceStatus.REJECTED.value());
		}else {
			throw new BaseException("Invalid Status");
		}
		return existingInsurance;
	}

	@Override
	public List<Insurance> getDetailsByInsuranceType(String type)
	{
		log.info("Getting Insurance Details by type of Insurance "+type);

		return insurancerepository.findAllByType(type);
	}

	@Override
	public List<Insurance> getAllInsurancesbyStatus(String status) throws BaseException
	{
		log.info("Getting Insurance By Status");
		if(!(InsuranceStatus.isValidStatus(status)))
		{
			log.info("Invalid Status Given");
			throw new BaseException("Not A Valid Status");
		}
		List<Insurance> insuranceList = insurancerepository.findAllByStatus(status);
		return insuranceList;
	}


	@Override
	public List<Insurance> getInsuranceByUsername(String username) throws NotFoundException{
		log.info("Getting the Insurance for Customer " + username);

		AppUser existingUser = appUserRepository.findByUsername(username).orElseThrow(()-> new NotFoundException(username + " Not found"));
		if(existingUser.getCustomer() == null){
			log.info(username+" Does not Match With any existing Customer");
			throw new NotFoundException(username + " Does not Match With any existing Customer");
		}
		return existingUser.getCustomer().getInsurances();
	}


}