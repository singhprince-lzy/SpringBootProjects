/**
 * Dependents in the insurance
 * 
 * @author kunal.yadav
 *
 */



package insurance.helper;
public enum Relation {
	FATHER("Father"),
	MOTHER("Mother"),
	SPOUSE("Spouse"),
	CHILD1("Child1"),
	CHILD2("Child2");
	private String value;
	Relation(String value){
		this.value = value;
	}
	public String value() {
		return value;
	}
	public static boolean isValidRelation(String relation) {
		for(Relation validRelation : Relation.values()) {
			if(relation.equals(validRelation.value())) {
				return true;
			}
		}
		return false;
	}
}