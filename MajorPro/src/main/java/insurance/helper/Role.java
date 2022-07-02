

package insurance.helper;
public enum Role {
	ADMIN("ROLE_ADMIN"),
	CUSTOMER("ROLE_CUSTOMER"),
	UNDERWRITTER("ROLE_UNDERWRITER");
	private String value;
	Role(String value){
		this.value = value;
	}
	public String value() {
		return value;
	}
	public static boolean isValidRole(String role) {
		for(Role validRole : Role.values()) {
			if(role.equals(validRole.value())) {
				return true;
			}
		}
		return false;
	}
}