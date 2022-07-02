/**
 * 
 * JWT(JSON Web Token) is used to carry information related to the 
 * identity and characteristics (claims) of a client.
 * This information is signed by the server in order for it to 
 * detect whether it was tampered with after sending it to the client.
 *  
 */


package insurance.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class Jwt {
	private String secretKey;
	private String tokenPrefix;
	private Integer tokenExp;

	public Algorithm getAlgorithmForSigning() {
		return Algorithm.HMAC256(secretKey.getBytes());
	}

	public Jwt() {
	}

	
	public String getSecretKey() {
		return secretKey;
	}
	public String getTokenPrefix() {
		return tokenPrefix;
	}
	public Integer getTokenExp() {
		return tokenExp;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
	public void setTokenExp(Integer tokenExp) {
		this.tokenExp = tokenExp;
	}
}