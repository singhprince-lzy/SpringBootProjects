package insurance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import insurance.filter.CustomAuthenticaionFilter;
import insurance.filter.CustomAuthrizationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security  extends WebSecurityConfigurerAdapter{
	private final UserDetailsService userDeatilsService;
	private final PasswordEncoder passwordEncoder;
	private final Jwt jwt;

	@Autowired
	public Security(UserDetailsService userDeatilsService, PasswordEncoder passwordEncoder, Jwt jwt) {
		super();

		this.userDeatilsService = userDeatilsService;
		this.passwordEncoder = passwordEncoder;
		this.jwt = jwt;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDeatilsService).passwordEncoder(passwordEncoder);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomAuthenticaionFilter customAuthenticationFilter = new CustomAuthenticaionFilter(authenticationManagerBean(), jwt);
		customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");

		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeHttpRequests().antMatchers("/**").permitAll();
		http.authorizeHttpRequests().antMatchers("/api/v1/register/**").permitAll();
		http.authorizeHttpRequests().anyRequest().authenticated();
		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore(new CustomAuthrizationFilter(jwt), UsernamePasswordAuthenticationFilter.class);	
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}