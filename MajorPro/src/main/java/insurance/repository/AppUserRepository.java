package insurance.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import insurance.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String>{
	Optional<AppUser> findByUsername(String username);
	List<AppUser> findByRole(String role);
}
