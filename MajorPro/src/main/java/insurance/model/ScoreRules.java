package insurance.model;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Entity
public class ScoreRules {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ruleId;
	@Column(unique = true)
	private String type;
	
	@Min(value = 0)
	@NotNull
	private Double basePremium;
	@Min(value = 0)
	@NotNull
	private Double costPerDependent;
	@Min(value = 0)
	@NotNull
	private Long coverageTime;
	@Min(value = 0)
	@NotNull
	private Long premiumToCoverageMultiplyer;
	
	@Min(value = 0)
	@NotNull
	private	Long baseScore;
	@Min(value = 0)
	@NotNull
	private	Long requiredScore;
	@NotNull
	@Min(value = 0)
	private Long minAge;
	@Max(value = 150)
	@NotNull
	private Long maxAge;

	@Min(value = 0)
	@NotNull
	private Long smokingPoints;
	@Min(value = 0)
	@NotNull
	private Long tobbacoPoints;
	@Min(value = 0)
	@NotNull
	private Long alcholPoints;
	@Min(value = 0)
	@NotNull
	private Long hereditaryDiseasesPoints;
	@Min(value = 0)
	@NotNull
	private Long majorDiseasesPoints;
	@Min(value = 0)
	@NotNull
	private Long previousDentalConditionPoints;


	public ScoreRules() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ScoreRules(String type,
			Double basePremium, Double costPerDependent,Long coverageTime, Long premiumToCoverageMultiplyer,
			Long baseScore, Long requiredScore, Long minAge, Long maxAge, 
			Long smokingPoints, Long tobbacoPoints, Long alcholPoints,
			Long hereditaryDiseasesPoints,Long majorDiseasesPoints, Long previousDentalConditionPoints) {
		super();
		this.type = type;
		this.basePremium = basePremium;
		this.costPerDependent = costPerDependent;
		this.premiumToCoverageMultiplyer = premiumToCoverageMultiplyer;
		this.coverageTime=coverageTime;
		
		this.baseScore = baseScore;
		this.requiredScore = requiredScore;
		this.minAge = minAge;
		this.maxAge = maxAge;
		
		this.smokingPoints = smokingPoints;
		this.tobbacoPoints = tobbacoPoints;
		this.alcholPoints = alcholPoints;
		this.hereditaryDiseasesPoints = hereditaryDiseasesPoints;
		this.majorDiseasesPoints = majorDiseasesPoints;
		this.previousDentalConditionPoints = previousDentalConditionPoints;
		
	}


	public Long getRequiredScore() {
		return requiredScore;
	}


	public void setRequiredScore(Long requiredScore) {
		this.requiredScore = requiredScore;
	}
	public Long getRuleId() {
		return ruleId;
	}


	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Long getBaseScore() {
		return baseScore;
	}


	public void setBaseScore(Long baseScore) {
		this.baseScore = baseScore;
	}


	public Long getMinAge() {
		return minAge;
	}


	public void setMinAge(Long minAge) {
		this.minAge = minAge;
	}


	public Long getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Long maxAge) {
		this.maxAge = maxAge;
	}

	public Long getCoverageTime() {
		return coverageTime;
	}

	public void setCoverageTime(Long coverageTime) {
		this.coverageTime = coverageTime;
	}

	public Double getBasePremium() {
		return basePremium;
	}

	public void setBasePremium(Double basePremium) {
		this.basePremium = basePremium;
	}

	public Long getSmokingPoints() {
		return smokingPoints;
	}

	public void setSmokingPoints(Long smokingPoints) {
		this.smokingPoints = smokingPoints;
	}
	public Long getTobbacoPoints() {
		return tobbacoPoints;
	}


	public void setTobbacoPoints(Long tobbacoPoints) {
		this.tobbacoPoints = tobbacoPoints;
	}


	public Long getAlcholPoints() {
		return alcholPoints;
	}


	public void setAlcholPoints(Long alcholPoints) {
		this.alcholPoints = alcholPoints;
	}


	public Long getHereditaryDiseasesPoints() {
		return hereditaryDiseasesPoints;
	}


	public void setHereditaryDiseasesPoints(Long hereditaryDiseasesPoints) {
		this.hereditaryDiseasesPoints = hereditaryDiseasesPoints;
	}


	public Long getMajorDiseasesPoints() {
		return majorDiseasesPoints;
	}


	public void setMajorDiseasesPoints(Long majorDiseasesPoints) {
		this.majorDiseasesPoints = majorDiseasesPoints;
	}


	public Long getPreviousDentalConditionPoints() {
		return previousDentalConditionPoints;
	}


	public void setPreviousDentalConditionPoints(Long previousDentalConditionPoints) {
		this.previousDentalConditionPoints = previousDentalConditionPoints;
	}


	public Double getCostPerDependent() {
		return costPerDependent;
	}


	public void setCostPerDependent(Double costPerDependent) {
		this.costPerDependent = costPerDependent;
	}
	public Long getPremiumToCoverageMultiplyer() {
		return premiumToCoverageMultiplyer;
	}
	public void setPremiumToCoverageMultiplyer(Long premiumToCoverageMultiplyer) {
		this.premiumToCoverageMultiplyer = premiumToCoverageMultiplyer;
	}
	@Override
	public int hashCode() {
		return Objects.hash(alcholPoints, basePremium, baseScore, costPerDependent, coverageTime,
				hereditaryDiseasesPoints, majorDiseasesPoints, maxAge, minAge, premiumToCoverageMultiplyer,
				previousDentalConditionPoints, requiredScore, ruleId, smokingPoints, tobbacoPoints, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreRules other = (ScoreRules) obj;
		return Objects.equals(alcholPoints, other.alcholPoints) && Objects.equals(basePremium, other.basePremium)
				&& Objects.equals(baseScore, other.baseScore)
				&& Objects.equals(costPerDependent, other.costPerDependent)
				&& Objects.equals(coverageTime, other.coverageTime)
				&& Objects.equals(hereditaryDiseasesPoints, other.hereditaryDiseasesPoints)
				&& Objects.equals(majorDiseasesPoints, other.majorDiseasesPoints)
				&& Objects.equals(maxAge, other.maxAge) && Objects.equals(minAge, other.minAge)
				&& Objects.equals(premiumToCoverageMultiplyer, other.premiumToCoverageMultiplyer)
				&& Objects.equals(previousDentalConditionPoints, other.previousDentalConditionPoints)
				&& Objects.equals(requiredScore, other.requiredScore) && Objects.equals(ruleId, other.ruleId)
				&& Objects.equals(smokingPoints, other.smokingPoints)
				&& Objects.equals(tobbacoPoints, other.tobbacoPoints) && Objects.equals(type, other.type);
	}
	@Override
	public String toString() {
		return "ScoreRules [ruleId=" + ruleId + ", type=" + type + ", basePremium=" + basePremium
				+ ", costPerDependent=" + costPerDependent + ", coverageTime=" + coverageTime
				+ ", premiumToCoverageMultiplyer=" + premiumToCoverageMultiplyer + ", baseScore=" + baseScore
				+ ", requiredScore=" + requiredScore + ", minAge=" + minAge + ", maxAge=" + maxAge + ", smokingPoints="
				+ smokingPoints + ", tobbacoPoints=" + tobbacoPoints + ", alcholPoints=" + alcholPoints
				+ ", hereditaryDiseasesPoints=" + hereditaryDiseasesPoints + ", majorDiseasesPoints="
				+ majorDiseasesPoints + ", previousDentalConditionPoints=" + previousDentalConditionPoints + "]";
	}
}