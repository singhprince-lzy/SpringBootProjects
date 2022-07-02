package insurance.model;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.annotation.JsonManagedReference;



	/**
	 * Model class for AppUser
	 * 
	 * @author Kunal Yadav
	 * 
	 */



@Entity 
public class AppUser {


	/**
	 * 
	 * username shoulld starts with alphabet and only contains alpha numeric values
	 * 
	 */
	
	@Id
	@NotBlank
	@Pattern(regexp = "^([a-zA-Z]+[a-zA-Z0-9]*)$",
	message = "should starts with alphabet and only contains alpha numeric values.")
	private String username;


	/**
	 * 
	 * password should not contain any blank space and minimum size should be 4
	 * 
	 */
	
	@NotBlank
	@Size(min = 4)
	@Pattern(regexp = "^\\S*$", message = "should not contain any space")
	private String password;

	
	
	/**
	 * appuser has one to one mapping with role 
	 * 
	 */
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "appUser", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Customer customer;

	private String role;

	public AppUser() {
		super();
	}


	/**
	 * parameterized constructor
	 * @param username , password and role
	 */

	public AppUser(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	@Override
	public String toString() {
		return "AppUser [username=" + username + 
				", password=" + password + 
				", customer=" + customer != null ? customer.getCustomerId().toString() : "null"+ 
				", role=" + role + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(
				customer != null ? customer.getCustomerId() : 1,
				password,
				role,
				username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUser other = (AppUser) obj;
		return ((this.customer != null && other.getCustomer()!= null)
				? Objects.equals(customer, other.customer)
					: ((this.customer == null && other.getCustomer() == null)
						? true : false)) 
				&& Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) 
				&& Objects.equals(username, other.username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role){

		this.role = role;
	}
}