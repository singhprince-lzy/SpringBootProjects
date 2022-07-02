package insurance.model.request;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

public class InsuranceTypeRequest {
	@NotBlank
	private String type;
	
	public InsuranceTypeRequest() {
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InsuranceTypeRequest other = (InsuranceTypeRequest) obj;
		return Objects.equals(type, other.type);
	}

	
	
}