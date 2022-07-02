package insurance.model;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "customer")
public class Customer{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;

	@OneToOne
	@JsonBackReference
	private AppUser appUser;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;

	@NotNull
	@Pattern(regexp = "^(0|91)?[6-9][0-9]{9}$",
	message = "formate is not valid")
	private String mobileNo;
	@NotNull
	@Email
	private String email;
	@NotBlank
	private String gender;
	@NotBlank
	private String nomineeName;
	@NotBlank
	private String nomineeRelation;
	@NotNull

	@JsonFormat(pattern="yyyy-MM-dd", timezone = "IST")
	private Date dob;
	@NotBlank
	private String permanentAddress;
	@NotBlank
	private String occupation;
	@NotNull
	@Min(value = 0)
	private Long annualIncome;
	@NotBlank
	private String maritalStatus;
	@NotBlank
	private String fatherName;
	@NotBlank
	private String motherName;
	@NotNull
	private Boolean smoking;
	@NotNull
	private Boolean tobbacoConsumpthion;
	@NotNull
	private Boolean alcholConsumption;
	@NotNull
	private Boolean anyHereditaryDiseases;
	@NotNull
	private Boolean anyMajorDiseases;
	@NotNull
	private Boolean anyPreviousDentalCondition;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Insurance> insurances = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Dependent> dependents = new ArrayList<>();

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long customerId, AppUser appUser, @NotBlank String firstName, @NotBlank String lastName,
			@NotNull @Pattern(regexp = "^(0|91)?[6-9][0-9]{9}$", message = "formate is not valid") String mobileNo,
			@NotNull @Email String email, @NotBlank String gender, @NotBlank String nomineeName,
			@NotBlank String nomineeRelation, @NotNull Date dob, @NotBlank String permanentAddress,
			@NotBlank String occupation, @NotNull @Min(0) Long annualIncome, @NotBlank String maritalStatus,
			@NotBlank String fatherName, @NotBlank String motherName, @NotNull Boolean smoking,
			@NotNull Boolean tobbacoConsumpthion, @NotNull Boolean alcholConsumption,
			@NotNull Boolean anyHereditaryDiseases, @NotNull Boolean anyMajorDiseases,
			@NotNull Boolean anyPreviousDentalCondition, List<Insurance> insurances, List<Dependent> dependents) {
		super();
		this.customerId = customerId;
		this.appUser = appUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.gender = gender;
		this.nomineeName = nomineeName;
		this.nomineeRelation = nomineeRelation;
		this.dob = dob;
		this.permanentAddress = permanentAddress;
		this.occupation = occupation;
		this.annualIncome = annualIncome;
		this.maritalStatus = maritalStatus;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.smoking = smoking;
		this.tobbacoConsumpthion = tobbacoConsumpthion;
		this.alcholConsumption = alcholConsumption;
		this.anyHereditaryDiseases = anyHereditaryDiseases;
		this.anyMajorDiseases = anyMajorDiseases;
		this.anyPreviousDentalCondition = anyPreviousDentalCondition;
		this.insurances = insurances;
		this.dependents = dependents;
	}


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getNomineeRelation() {
		return nomineeRelation;
	}

	public void setNomineeRelation(String nomineeRelation) {
		this.nomineeRelation = nomineeRelation;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Long getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(Long annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public Boolean getSmoking() {
		return smoking;
	}

	public void setSmoking(Boolean smoking) {
		this.smoking = smoking;
	}

	public Boolean getTobbacoConsumpthion() {
		return tobbacoConsumpthion;
	}

	public void setTobbacoConsumpthion(Boolean tobbacoConsumpthion) {
		this.tobbacoConsumpthion = tobbacoConsumpthion;
	}

	public Boolean getAlcholConsumption() {
		return alcholConsumption;
	}

	public void setAlcholConsumption(Boolean alcholConsumption) {
		this.alcholConsumption = alcholConsumption;
	}

	public Boolean getAnyHereditaryDiseases() {
		return anyHereditaryDiseases;
	}

	public void setAnyHereditaryDiseases(Boolean anyHereditaryDiseases) {
		this.anyHereditaryDiseases = anyHereditaryDiseases;
	}

	public Boolean getAnyMajorDiseases() {
		return anyMajorDiseases;
	}

	public void setAnyMajorDiseases(Boolean anyMajorDiseases) {
		this.anyMajorDiseases = anyMajorDiseases;
	}

	public Boolean getAnyPreviousDentalCondition() {
		return anyPreviousDentalCondition;
	}

	public void setAnyPreviousDentalCondition(Boolean anyPreviousDentalCondition) {
		this.anyPreviousDentalCondition = anyPreviousDentalCondition;
	}

	public List<Insurance> getInsurances() {
		return insurances;
	}

	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}

	public List<Dependent> getDependents() {
		return dependents;
	}

	public void setDependents(List<Dependent> dependents) {
		this.dependents = dependents;
	}

	@Override
	public int hashCode() {
		if(appUser==null) {
			return Objects.hash(alcholConsumption, annualIncome, anyHereditaryDiseases, anyMajorDiseases,
					anyPreviousDentalCondition, customerId, dob, email, fatherName, firstName, gender,
					lastName, maritalStatus, mobileNo, motherName, nomineeName, nomineeRelation, occupation,
					permanentAddress, smoking, tobbacoConsumpthion);
		}
		return Objects.hash(alcholConsumption, annualIncome, anyHereditaryDiseases, anyMajorDiseases,
				anyPreviousDentalCondition, appUser.getUsername(), customerId,  dob, email, fatherName, firstName, gender,
				lastName, maritalStatus, mobileNo, motherName, nomineeName, nomineeRelation, occupation,
				permanentAddress, smoking, tobbacoConsumpthion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if(this.appUser==null&&other.appUser==null) {
			return Objects.equals(alcholConsumption, other.alcholConsumption)
					&& Objects.equals(annualIncome, other.annualIncome)
					&& Objects.equals(anyHereditaryDiseases, other.anyHereditaryDiseases)
					&& Objects.equals(anyMajorDiseases, other.anyMajorDiseases)
					&& Objects.equals(anyPreviousDentalCondition, other.anyPreviousDentalCondition)
					&& Objects.equals(customerId, other.customerId)
					&& Objects.equals(dob, other.dob)
					&& Objects.equals(email, other.email) && Objects.equals(fatherName, other.fatherName)
					&& Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender)
					&& Objects.equals(lastName, other.lastName)
					&& Objects.equals(maritalStatus, other.maritalStatus) && Objects.equals(mobileNo, other.mobileNo)
					&& Objects.equals(motherName, other.motherName) && Objects.equals(nomineeName, other.nomineeName)
					&& Objects.equals(nomineeRelation, other.nomineeRelation)
					&& Objects.equals(occupation, other.occupation)
					&& Objects.equals(permanentAddress, other.permanentAddress) && Objects.equals(smoking, other.smoking)
					&& Objects.equals(tobbacoConsumpthion, other.tobbacoConsumpthion);

		}
		else if(this.appUser!=null&&other.appUser!=null) {
			return Objects.equals(alcholConsumption, other.alcholConsumption)
					&& Objects.equals(annualIncome, other.annualIncome)
					&& Objects.equals(anyHereditaryDiseases, other.anyHereditaryDiseases)
					&& Objects.equals(anyMajorDiseases, other.anyMajorDiseases)
					&& Objects.equals(anyPreviousDentalCondition, other.anyPreviousDentalCondition)
					&& Objects.equals(appUser.getUsername(), other.appUser.getUsername()) 
					&& Objects.equals(customerId, other.customerId)
					&& Objects.equals(dob, other.dob)
					&& Objects.equals(email, other.email) 
					&& Objects.equals(fatherName, other.fatherName)
					&& Objects.equals(firstName, other.firstName) 
					&& Objects.equals(gender, other.gender)
					&& Objects.equals(lastName, other.lastName)
					&& Objects.equals(maritalStatus, other.maritalStatus) 
					&& Objects.equals(mobileNo, other.mobileNo)
					&& Objects.equals(motherName, other.motherName)
					&& Objects.equals(nomineeName, other.nomineeName)
					&& Objects.equals(nomineeRelation, other.nomineeRelation)
					&& Objects.equals(occupation, other.occupation)
					&& Objects.equals(permanentAddress, other.permanentAddress) 
					&& Objects.equals(smoking, other.smoking)
					&& Objects.equals(tobbacoConsumpthion, other.tobbacoConsumpthion);
		}
		else {
			return false;
		}
	}

	@Override
	public String toString() {
		if(appUser==null) {
			return "Customer [customerId=" + customerId + ", appUser=" + "null" + ", firstName=" + firstName
					+ ", lastName=" + lastName + ", mobileNo=" + mobileNo + ", email=" + email + ", gender=" + gender
					+ ", nomineeName=" + nomineeName + ", nomineeRelation=" + nomineeRelation + ", dob=" + dob
					+ ", permanentAddress=" + permanentAddress + ", occupation=" + occupation + ", annualIncome="
					+ annualIncome + ", maritalStatus=" + maritalStatus + ", fatherName=" + fatherName + ", motherName="
					+ motherName + ", smoking=" + smoking + ", tobbacoConsumpthion=" + tobbacoConsumpthion
					+ ", alcholConsumption=" + alcholConsumption + ", anyHereditaryDiseases=" + anyHereditaryDiseases
					+ ", anyMajorDiseases=" + anyMajorDiseases + ", anyPreviousDentalCondition="
					+ anyPreviousDentalCondition + ", insurances=" + insurances + ", dependents=" + dependents + "]";
		}
		return "Customer [customerId=" + customerId + ", appUser=" + appUser.getUsername() + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", mobileNo=" + mobileNo + ", email=" + email + ", gender=" + gender
				+ ", nomineeName=" + nomineeName + ", nomineeRelation=" + nomineeRelation + ", dob=" + dob
				+ ", permanentAddress=" + permanentAddress + ", occupation=" + occupation + ", annualIncome="
				+ annualIncome + ", maritalStatus=" + maritalStatus + ", fatherName=" + fatherName + ", motherName="
				+ motherName + ", smoking=" + smoking + ", tobbacoConsumpthion=" + tobbacoConsumpthion
				+ ", alcholConsumption=" + alcholConsumption + ", anyHereditaryDiseases=" + anyHereditaryDiseases
				+ ", anyMajorDiseases=" + anyMajorDiseases + ", anyPreviousDentalCondition="
				+ anyPreviousDentalCondition + ", insurances=" + insurances + ", dependents=" + dependents + "]";
	}
}