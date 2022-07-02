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
import insurance.model.ScoreRules;
import insurance.service.ScoreRulesService;

/**
 * controller for ScoreRules
 * 
 * @author kunal.yadav
 *
 */


@RestController
@RequestMapping("/api/v1")
public class ControllerScoreRules {
	@Autowired
	private ScoreRulesService scoreRulesService;

	@GetMapping("rule/{type}")
	public ResponseEntity<ScoreRules> getRulesByType(
			@PathVariable("type") String type) throws NotFoundException{
		return ResponseEntity.ok().body(scoreRulesService.getRuleByType(type));
	}

	//update rule by type
	@PutMapping("rule/{type}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<ScoreRules> updateRules(
			@PathVariable("type") String type,
			@Valid @RequestBody ScoreRules scoreRules) throws NotFoundException{
		return ResponseEntity.ok().body(scoreRulesService.updateRules(type, scoreRules));
	}


	//save rule by type
	@PostMapping("rule")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<ScoreRules> saveRules(
			@Valid @RequestBody ScoreRules scoreRules) throws NotFoundException, BaseException{
		return ResponseEntity.ok().body(scoreRulesService.saveScoreRules(scoreRules));
	}


	//get list of users
	@GetMapping("rules")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<ScoreRules>> getAllRules(){
		return ResponseEntity.ok().body(scoreRulesService.getRules());
	}
}