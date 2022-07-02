package insurance.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insurance.exception.BaseException;
import insurance.exception.NotFoundException;
import insurance.helper.InsuranceType;
import insurance.model.ScoreRules;
import insurance.repository.ScoreRulesRepository;
import insurance.service.ScoreRulesService;

@Service
@Transactional
public class ScoreRulesServiceImp implements ScoreRulesService {
	@Autowired
	private ScoreRulesRepository scoreRulesRepo;

	@Override
	public ScoreRules getRuleByType(String type) throws NotFoundException {
		return scoreRulesRepo.findByType(type)
		.orElseThrow(() -> new NotFoundException( type + " of insurance not found"));
	}

	@Override
	public ScoreRules saveScoreRules(ScoreRules scoreRules) throws BaseException{
		if(!InsuranceType.isValidType(scoreRules.getType())) 
			throw new BaseException(scoreRules.getType() + " is not a valid insurance type");
		
		if(scoreRulesRepo.findByType(scoreRules.getType()).isPresent())
			throw new BaseException(scoreRules.getType() + " Rule already exists");
		
		return scoreRulesRepo.save(scoreRules);
	}

	@Override
	public ScoreRules updateRules(String type, ScoreRules newScoreRules) throws NotFoundException {
		ScoreRules existingScoreRules = scoreRulesRepo.findByType(type)
		.orElseThrow(() -> new NotFoundException( type + " of insurance not found"));
		
		existingScoreRules.setBasePremium(newScoreRules.getBasePremium());
		existingScoreRules.setCostPerDependent(newScoreRules.getCostPerDependent());
		existingScoreRules.setCoverageTime(newScoreRules.getCoverageTime());
		existingScoreRules.setPremiumToCoverageMultiplyer(newScoreRules.getPremiumToCoverageMultiplyer());
		
		existingScoreRules.setBaseScore(newScoreRules.getBaseScore());
		existingScoreRules.setRequiredScore(newScoreRules.getRequiredScore());
		existingScoreRules.setMinAge(newScoreRules.getMinAge());
		existingScoreRules.setMaxAge(newScoreRules.getMaxAge());
		
		existingScoreRules.setSmokingPoints(newScoreRules.getSmokingPoints());
		existingScoreRules.setTobbacoPoints(newScoreRules.getTobbacoPoints());
		existingScoreRules.setAlcholPoints(newScoreRules.getAlcholPoints());
		existingScoreRules.setHereditaryDiseasesPoints(newScoreRules.getHereditaryDiseasesPoints());
		existingScoreRules.setMajorDiseasesPoints(newScoreRules.getMajorDiseasesPoints());
		existingScoreRules.setPreviousDentalConditionPoints(newScoreRules.getPreviousDentalConditionPoints());
		
		return existingScoreRules;
		
	}

	@Override
	public List<ScoreRules> getRules() {
		return scoreRulesRepo.findAll();
	}
}