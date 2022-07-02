package insurance.service.imp;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import insurance.helper.InsuranceStatus;
import insurance.model.Insurance;
import insurance.model.ScoreRules;
import insurance.repository.InsuranceRepository;
import insurance.repository.ScoreRulesRepository;

@Service
@EnableScheduling
public class InsuranceProcessingService {
	private Logger log = LoggerFactory.getLogger(InsuranceProcessingService.class);
	@Autowired
	private InsuranceRepository insuranceRepo;
	@Autowired
	private ScoreRulesRepository scoreRulesRepo;
	
	@Scheduled(fixedDelay = 100000)
	@Transactional
	public void processInsurances() {
		log.info("processing insurances");
		//getting waiting insurances
		List<Insurance> unprocessedInsurances = insuranceRepo.findAllByStatus(InsuranceStatus.WAITING.value());
		log.info(unprocessedInsurances.size() + " no of insurances to be processed");
		//getting score rules
		Map<String , ScoreRules> rules = new HashMap<String, ScoreRules>();
		scoreRulesRepo.findAll().forEach((rule) -> rules.put(rule.getType(), rule));
		unprocessedInsurances.forEach((insurance)->{
			if(insurance.getScore() >= rules.get(insurance.getType()).getRequiredScore()) {
				insurance.setStatus(InsuranceStatus.ACTIVE.value());
				log.info("Active : " + insurance);
			}else {
				insurance.setStatus(InsuranceStatus.PROCESSING.value());
				log.info("Processing : " + insurance);
			}
		});
	}
	@Scheduled(cron = "0 0 0 * * ?")
	@Transactional
	public void processExpiredInsurances() {
		log.info("processing expired insurances");
		
		Date date=new Date(System.currentTimeMillis()); 
		List<Insurance> activeInsurances = insuranceRepo.findAllByStatus(InsuranceStatus.ACTIVE.value());
		log.info(activeInsurances.size() + " no of active insurances");
		activeInsurances.forEach((insurance)->{
			if(date.after(insurance.getExpireDate())) {
				insurance.setStatus(InsuranceStatus.EXPIRED.value());
				log.info("Expired : " + insurance);
			}
		});
	}
}
