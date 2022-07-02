package insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import insurance.model.Insurance;
@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

	public List<Insurance> findAllByType(String type);
	public List<Insurance> findAllByStatus(String status);
}