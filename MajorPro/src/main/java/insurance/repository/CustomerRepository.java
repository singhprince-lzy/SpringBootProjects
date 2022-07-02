package insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import insurance.model.Customer;
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}