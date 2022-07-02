package com.insurance.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import insurance.config.Jwt;
import insurance.model.AppUser;
import insurance.model.ScoreRules;
import insurance.service.ScoreRulesService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ScoreRules.class)
@ActiveProfiles("test")
public class ScoreRulesControllerUnitTest {
	@Autowired
	private MockMvc mocMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	ScoreRulesService scoreRulesService;

	@MockBean
	UserDetailsService userDetailsService;
	@MockBean
	PasswordEncoder passwordEncoder;
	@MockBean
	Jwt jwtConfig;

	final String activeUsername = "admin";
	final String activeRoleAdmin = "ROLE_ADMIN";

	private String username;
	private String password;
	private String role;
	private ScoreRules scoreRule;
	private AppUser appUser;

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
	private java.util.List<ScoreRules> scoreRulesList;

	@BeforeEach
	void setUp() {
		username = activeUsername;
		password = "admin123";
		role = activeRoleAdmin;

		appUser = new AppUser();
		appUser.setUsername(username);
		appUser.setPassword(password);
		appUser.setRole(role);
		
		type="Life";
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

		scoreRulesList=new ArrayList<>();
		scoreRulesList.add(scoreRule);

	}

	@Test
	@WithMockUser(username = activeUsername, authorities = {activeRoleAdmin} )
	void testSavecustomer() throws Exception {
		when(scoreRulesService.saveScoreRules(scoreRule)).thenReturn(scoreRule);

		String url = "/api/v1/rule/";

		MvcResult mvcResult = mocMvc.perform(
				post(url)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(scoreRule))
					.characterEncoding("UTF-8"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();


		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		String expectedJsonResponse = objectMapper.writeValueAsString(scoreRule);
		assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

		verify(scoreRulesService, times(1)).saveScoreRules(scoreRule);
	}

	@Test
	@WithMockUser(username = activeUsername, authorities = {activeRoleAdmin} )
	void testUpdateScoreRules() throws Exception {
		when(scoreRulesService.updateRules(type,scoreRule)).thenReturn(scoreRule);

		String url = "/api/v1/rule/" + type;

		MvcResult mvcResult = mocMvc.perform(
				put(url)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(scoreRule))
				.characterEncoding("UTF-8"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		String expectedJsonResponse = objectMapper.writeValueAsString(scoreRule);
		assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

		verify(scoreRulesService, times(1)).updateRules(type, scoreRule);
	}

	@Test
	@WithMockUser(username = activeUsername, authorities = {activeRoleAdmin})
	void testGetAllScoreRules() throws Exception {
		when(scoreRulesService.getRules()).thenReturn(scoreRulesList);

		String url = "/api/v1/rules/";

		MvcResult mvcResult = mocMvc.perform(
				get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		String expectedJsonResponse = objectMapper.writeValueAsString(scoreRulesList);

		assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

		verify(scoreRulesService).getRules();
	}


	@Test
	@WithMockUser(username = activeUsername, authorities = {activeRoleAdmin})
	void testGetScoreRulesByType() throws Exception {
		when(scoreRulesService.getRuleByType(type)).thenReturn(scoreRule);

		String url = "/api/v1/rule/" + type;

		MvcResult mvcResult = mocMvc.perform(
				get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		String expectedJsonResponse = objectMapper.writeValueAsString(scoreRule);
		assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

		verify(scoreRulesService).getRuleByType(type);
	}
	
}