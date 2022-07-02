package com.insurance.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import insurance.exception.BaseException;
import insurance.exception.NotFoundException;
import insurance.model.AppUser;
import insurance.model.ScoreRules;
import insurance.repository.ScoreRulesRepository;
import insurance.service.imp.ScoreRulesServiceImp;

@ExtendWith(MockitoExtension.class)
class ScoreRulesUnitTest {
	@InjectMocks
	private ScoreRulesServiceImp scoreRulesServiceImp;
	@Mock
	private ScoreRulesRepository scoreRulesRepo;

	private String username;
	private String password;
	private String role;
	private AppUser appUser;
	private ScoreRules scoreRule;
	private ScoreRules newScoreRules;

	private String type;
	private Double basePremium;
	private Double costPerDependent;
	private Long coverageTime;
	private Long premiumToCoverageMultiplyer;
	private	Long baseScore;
	private	Long requiredScore;
	private Long minAge;
	private Long maxAge;
	private Long smokingPoints;
	private Long tobbacoPoints;
	private Long alcholPoints;
	private Long hereditaryDiseasesPoints;
	private Long majorDiseasesPoints;
	private Long previousDentalConditionPoints;
	private List<ScoreRules> scoreRulesList;

	@BeforeEach
	void setup() {
		username = "admin";
		password = "admin123";
		role = "ROLE_ADMIN";
		type="Life";

		appUser = new AppUser();
		appUser.setUsername(username);
		appUser.setPassword(password);
		appUser.setRole(role);

		basePremium=15000.0;
		costPerDependent=4000.0;
		coverageTime=Long.valueOf(40);
		premiumToCoverageMultiplyer=Long.valueOf(100);
		baseScore=Long.valueOf(100);
		requiredScore=Long.valueOf(70);
		minAge=Long.valueOf(18);
		maxAge=Long.valueOf(65);
		smokingPoints=Long.valueOf(10);
		tobbacoPoints=Long.valueOf(10);
		alcholPoints=Long.valueOf(10);
		hereditaryDiseasesPoints=Long.valueOf(10);
		majorDiseasesPoints=Long.valueOf(20);
		previousDentalConditionPoints=Long.valueOf(0);

        scoreRule=new ScoreRules();
		scoreRule.setType(type);
		scoreRule.setBasePremium(basePremium);
		scoreRule.setCostPerDependent(costPerDependent);
		scoreRule.setCoverageTime(coverageTime);
		scoreRule.setPremiumToCoverageMultiplyer(premiumToCoverageMultiplyer);
		scoreRule.setBaseScore(baseScore);
		scoreRule.setRequiredScore(requiredScore);
		scoreRule.setMinAge(minAge);
		scoreRule.setMaxAge(maxAge);
		scoreRule.setSmokingPoints(smokingPoints);
		scoreRule.setTobbacoPoints(tobbacoPoints);
		scoreRule.setAlcholPoints(alcholPoints);
		scoreRule.setHereditaryDiseasesPoints(hereditaryDiseasesPoints);
		scoreRule.setMajorDiseasesPoints(majorDiseasesPoints);
		scoreRule.setPreviousDentalConditionPoints(previousDentalConditionPoints);
		
		
		requiredScore=Long.valueOf(77);
		maxAge=Long.valueOf(70);

		newScoreRules=new ScoreRules();
		newScoreRules.setType(type);
		newScoreRules.setBasePremium(basePremium);
		newScoreRules.setCostPerDependent(costPerDependent);
		newScoreRules.setCoverageTime(coverageTime);
		newScoreRules.setPremiumToCoverageMultiplyer(premiumToCoverageMultiplyer);
		newScoreRules.setBaseScore(baseScore);
		newScoreRules.setRequiredScore(requiredScore);
		newScoreRules.setMinAge(minAge);
		newScoreRules.setMaxAge(maxAge);
		newScoreRules.setSmokingPoints(smokingPoints);
		newScoreRules.setTobbacoPoints(tobbacoPoints);
		newScoreRules.setAlcholPoints(alcholPoints);
		newScoreRules.setHereditaryDiseasesPoints(hereditaryDiseasesPoints);
		newScoreRules.setMajorDiseasesPoints(majorDiseasesPoints);
		newScoreRules.setPreviousDentalConditionPoints(previousDentalConditionPoints);
		
		scoreRulesList = new ArrayList<>();
	}


	@Test
	void testGetRulesByType() throws NotFoundException
	{
		when(scoreRulesRepo.findByType(scoreRule.getType())).thenReturn(Optional.of(scoreRule));
		assertThat(scoreRulesServiceImp.getRuleByType(type)).isEqualTo(scoreRule);		
		verify(scoreRulesRepo, times(1)).findByType(type);
	}

	@Test
	void testGetRules()
	{
		scoreRulesList.add(scoreRule);

		when(scoreRulesRepo.findAll()).thenReturn(scoreRulesList);
		assertThat(scoreRulesServiceImp.getRules()).isEqualTo(scoreRulesList);
		verify(scoreRulesRepo).findAll();
	}

	@Test
	void testSaveScoreRules() throws BaseException
	{
//		when(scoreRulesRepo.findByType(scoreRule.getType())).thenReturn(Optional.of(scoreRule));
		when(scoreRulesRepo.save(scoreRule)).thenReturn(scoreRule);
		assertThat(scoreRulesServiceImp.saveScoreRules(scoreRule)).isEqualTo(scoreRule);
		verify(scoreRulesRepo,times(1)).findByType(type);
		verify(scoreRulesRepo,times(1)).save(scoreRule);
	}

	@Test
	void testUpdateScoreRules() throws NotFoundException
	{
		when(scoreRulesRepo.findByType(scoreRule.getType())).thenReturn(Optional.of(scoreRule));
		assertThat(scoreRulesServiceImp.updateRules(type, newScoreRules)).isEqualTo(newScoreRules);
		verify(scoreRulesRepo,times(1)).findByType(type);
	}

}