package insurance.helper;

public enum InsuranceStatus {
	WAITING("Waiting"),
	PROCESSING("Processing"),
	REJECTED("Rejected"),
	ACTIVE("Active"),
	EXPIRED("Expired");


	private String value;

	InsuranceStatus(String value){
		this.value = value;
	}
	public String value() {
		return value;
	}
	public static boolean isValidStatus(String status) {
		for(InsuranceStatus validStatus : InsuranceStatus.values()) {
			if(status.equals(validStatus.value())) {
				return true;
			}
		}
		return false;
	}
}