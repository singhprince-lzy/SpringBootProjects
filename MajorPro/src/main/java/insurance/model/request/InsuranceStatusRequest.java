package insurance.model.request;
import javax.validation.constraints.NotBlank;
public class InsuranceStatusRequest {
	@NotBlank
	private String status;
	public InsuranceStatusRequest() {	
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}