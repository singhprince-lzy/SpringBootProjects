package insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import insurance.model.Customer;
import insurance.model.Dependent;

public interface DependentRepository extends JpaRepository<Dependent, Long> {
	Dependent findByCustomer(Customer customer);
}