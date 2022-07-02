package insurance.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import insurance.config.Jwt;
public class CustomAuthenticaionFilter extends UsernamePasswordAuthenticationFilter{
	private final AuthenticationManager authenticationManager;
	private final Jwt jwt;
	public CustomAuthenticaionFilter(AuthenticationManager authenticationManager,Jwt jwt) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwt = jwt;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(username, password);
		return authenticationManager.authenticate(authenticationToken);
	}
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User)authResult.getPrincipal();
		List<String> roles = user
				.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		//createing jwt token
		String access_token = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + jwt.getTokenExp() * 60 * 60 * 1000 ))
				.withIssuedAt(new Date(System.currentTimeMillis()))
				.withClaim("role", user
						.getAuthorities()
						.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.sign(jwt.getAlgorithmForSigning());
		//setting token and role to response body
		Map<String, String> response_body = new HashMap<>();
		response_body.put("access_token", access_token);
		response_body.put("role", roles.get(0));
		response_body.put("username", user.getUsername());

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), response_body);
	}
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println(failed.getMessage());
		super.unsuccessfulAuthentication(request, response, failed);
	}
}