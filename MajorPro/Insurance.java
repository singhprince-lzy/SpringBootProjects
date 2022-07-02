package insurance;
	
import javax.validation.ConstraintViolationException;

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

public class Insurance{

	public static void main(String[] args) {
		SpringApplication.run(Insurance.class, args);		
	}

	@Bean
	@Profile("!test")
	CommandLineRunner runnner(
			AppUserService appUserService,
			ScoreRulesService scoreRulesService) {
		return args ->{
			try {
				//some users
				appUserService.saveAppUserByAdmin(new AppUser("kunal", "1234", "ROLE_ADMIN"));
				//ScoreRules
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