package insurance;
	
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import insurance.exception.BaseException;
import insurance.model.AppUser;
import insurance.model.ScoreRules;
import insurance.service.AppUserService;
import insurance.service.ScoreRulesService;

@SpringBootApplication


/**
 * 
 *<h1>INSURANCE APPLICATION</h1>
 * This is an Insurance Application End user can apply for any of the two 
 * insurance types-LIFE and DENTAL
 * 
 * @author Kunal Yadav(kunal.yadav@impetus.com)
 * @since 2021 DECEMBER
 * 
 * 
 */

public class Insurance{
	
	private static final Logger log = LoggerFactory.getLogger(Insurance.class);

	public static void main(String[] args) {
		log.info("this is an info message");
	      log.warn("this is a warn message");
	      log.error("this is an error message");
	      log.trace("Log level: TRACE");
	        log.info("Log level: INFO");
	        log.debug("Log level: DEBUG");
	        log.error("Log level: ERROR");
	        log.warn("Log level: WARN");
		SpringApplication.run(Insurance.class, args);		
	}

	@Bean
	@Profile("!test")
	CommandLineRunner runnner(
			AppUserService appUserService,
			ScoreRulesService scoreRulesService) {
		return args ->{
			try {
				
				//Save admin to the Application
				
				appUserService.saveAppUserByAdmin(new AppUser("kunal", "1234", "ROLE_ADMIN"));
				
				//ScoreRules for Life
				
				scoreRulesService.saveScoreRules(
						new ScoreRules("Life",
								Double.valueOf(15000),
								Double.valueOf(1000),
								Long.valueOf(40),
								Long.valueOf(100),
								Long.valueOf(100),
								Long.valueOf(70),
								Long.valueOf(18),
								Long.valueOf(65),
								Long.valueOf(10),
								Long.valueOf(10),
								Long.valueOf(10),
								Long.valueOf(10),
								Long.valueOf(20),
								Long.valueOf(0)
								));
				
				// save score rules for Dental
				
				scoreRulesService.saveScoreRules(new ScoreRules("Dental",
						Double.valueOf(1500),
						Double.valueOf(100),
						Long.valueOf(1),
						Long.valueOf(50),
						Long.valueOf(100),
						Long.valueOf(70),
						Long.valueOf(18),
						Long.valueOf(65),
						Long.valueOf(15),
						Long.valueOf(15),
						Long.valueOf(15),
						Long.valueOf(0),
						Long.valueOf(0),
						Long.valueOf(15)
						));
			}catch (BaseException e) {
				System.out.println(e.getMessage());
			}catch(ConstraintViolationException e) {
				System.out.println(e.getMessage());
			}
		};
	};
}