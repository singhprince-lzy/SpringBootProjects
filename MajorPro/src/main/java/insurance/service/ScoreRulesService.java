package insurance.service;

import java.util.List;

import insurance.exception.BaseException;
import insurance.exception.NotFoundException;
import insurance.model.ScoreRules;

public interface ScoreRulesService {
	/**
	 * @param type
	 * @return rule by its type
	 * @throws NotFoundException
	 */

	ScoreRules getRuleByType(String type) throws NotFoundException;

	/**
	 * @param scoreRules
	 * @return saved rules
	 * @throws BaseException
	 */
	
	ScoreRules saveScoreRules(ScoreRules scoreRules) throws BaseException;

	/**
	 * @param type
	 * @param newScoreRules
	 * @return updated rules
	 * @throws NotFoundException
	 */
	
	ScoreRules updateRules(String type, ScoreRules newScoreRules) throws NotFoundException;


	/**
	 * @return list of all rules
	 */
	
	List<ScoreRules> getRules();
}