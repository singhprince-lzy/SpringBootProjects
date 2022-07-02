package insurance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import insurance.model.ScoreRules;
@Repository
public interface ScoreRulesRepository extends JpaRepository<ScoreRules, Long>{
	public Optional<ScoreRules> findByType(String type);
}