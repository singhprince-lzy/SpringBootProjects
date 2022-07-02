package insurance.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="insurance")
public class Insurance{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long insuranceId;

	@ManyToOne
	@JsonBackReference
	private Customer customer;

	private String type;

	@JsonFormat(pattern="yyyy-MM-dd",  timezone = "IST")
	private Date issueDate;

	@JsonFormat(pattern="yyyy-MM-dd",  timezone = "IST")
	private Date expireDate;
	private Double Premium;
	private Double Coverage;
	private Long score;
	private String status;

	public Insurance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Insurance(Long insuranceId, Customer customer, String type, Date issueDate, Date expireDate, Double premium,
			Double coverage, Long score, String status) {
		super();
		this.insuranceId = insuranceId;
		this.customer = customer;
		this.type = type;
		this.issueDate = issueDate;
		this.expireDate = expireDate;
		Premium = premium;
		Coverage = coverage;
		this.score = score;
		this.status = status;
	}


	public Long getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(Long insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Double getPremium() {
		return Premium;
	}

	public void setPremium(Double premium) {
		Premium = premium;
	}

	public Double getCoverage() {
		return Coverage;
	}

	public void setCoverage(Double coverage) {
		Coverage = coverage;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		if(this.customer == null) {
			return Objects.hash(Coverage, Premium, expireDate, insuranceId, issueDate, score, status, type);
		}
		return Objects.hash(Coverage, Premium, customer.getCustomerId(), expireDate, insuranceId, issueDate, score, status, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Insurance other = (Insurance) obj;
		if(this.customer == null && other.customer == null) {
			return Objects.equals(Coverage, other.Coverage) && Objects.equals(Premium, other.Premium)
					&& Objects.equals(expireDate, other.expireDate)
					&& Objects.equals(insuranceId, other.insuranceId) && Objects.equals(issueDate, other.issueDate)
					&& Objects.equals(score, other.score) && Objects.equals(status, other.status)
					&& Objects.equals(type, other.type);
		}else if(this.customer != null && other.customer != null) {
			return Objects.equals(Coverage, other.Coverage) && Objects.equals(Premium, other.Premium)
					&& Objects.equals(customer.getCustomerId(), other.customer.getCustomerId()) && Objects.equals(expireDate, other.expireDate)
					&& Objects.equals(insuranceId, other.insuranceId) && Objects.equals(issueDate, other.issueDate)
					&& Objects.equals(score, other.score) && Objects.equals(status, other.status)
					&& Objects.equals(type, other.type);
		}else {
			return false;
		}

	}
}
