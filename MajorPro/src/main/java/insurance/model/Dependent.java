package insurance.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Dependent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dependentId;
	@NotBlank
	private String dependentName;
	@NotBlank
	private String relation;
	@ManyToOne
	@JsonBackReference
	private Customer customer;
	
	public Dependent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dependent(Long dependentId, String dependentName, String relation, Customer customer) {
		super();
		this.dependentId = dependentId;
		this.dependentName = dependentName;
		this.relation = relation;
		this.customer = customer;
	}
	public Long getDependentId() {
		return dependentId;
	}
	public void setDependentId(Long dependentId) {
		this.dependentId = dependentId;
	}
	public String getDependentName() {
		return dependentName;
	}
	public void setDependentName(String dependentName) {
		this.dependentName = dependentName;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public String toString() {
		if(customer==null) {
			return "Dependent [dependentId=" + dependentId + ", dependentName=" + dependentName + ", relation=" + relation
					+ ", customer=" + "null" + "]";
		}
		return "Dependent [dependentId=" + dependentId + ", dependentName=" + dependentName + ", relation=" + relation
				+ ", customer=" + customer.getCustomerId() + "]";
	}
	@Override
	public int hashCode() {
		if(customer==null) {
			return Objects.hash(dependentId, dependentName, relation);
		}
		return Objects.hash(customer.getCustomerId(), dependentId, dependentName, relation);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dependent other = (Dependent) obj;
		if(this.customer==null&&this.customer==null) {
			return Objects.equals(dependentId, other.dependentId)
					&& Objects.equals(dependentName, other.dependentName) && Objects.equals(relation, other.relation);
		}
		else if(this.customer!=null&&this.customer!=null) {
			return Objects.equals(customer.getCustomerId(), other.customer.getCustomerId()) && Objects.equals(dependentId, other.dependentId)
					&& Objects.equals(dependentName, other.dependentName) && Objects.equals(relation, other.relation);
		}
		else {
			return false;
		}
	}
	
	
	
}
