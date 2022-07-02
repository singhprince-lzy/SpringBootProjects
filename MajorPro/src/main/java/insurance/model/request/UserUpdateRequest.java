package insurance.model.request;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserUpdateRequest {
	@NotBlank
	@Size(min = 4)
	@Pattern(regexp = "^\\S*$", message = "should not contain any space")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserUpdateRequest other = (UserUpdateRequest) obj;
		return Objects.equals(password, other.password);
	}
	
	
}