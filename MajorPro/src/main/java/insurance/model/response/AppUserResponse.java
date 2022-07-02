package insurance.model.response;

import java.util.Objects;

public class AppUserResponse{

	private String username;
	private String role;
	
	
	public AppUserResponse(String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}
	
	public AppUserResponse() {
		super();
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUserResponse other = (AppUserResponse) obj;
		return Objects.equals(role, other.role) && Objects.equals(username, other.username);
	}

	
}