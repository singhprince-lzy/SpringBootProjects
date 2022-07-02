/**
 * shows the type of insurance available for customers
 * 
 * @author kunal.yadav
 *
 */


package insurance.helper;
public enum InsuranceType {
	Life("Life"),
	Dental("Dental");
	private String value;
	InsuranceType(String value){
		this.value = value;
	}
	public String value() {
		return value;
	}
	public static boolean isValidType(String type) {
		for(InsuranceType validType : InsuranceType.values()) {
			if(type.equals(validType.value())) {
				return true;
			}
		}
		return false;
	}
}