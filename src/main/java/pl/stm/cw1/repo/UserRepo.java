package pl.stm.cw1.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.stm.cw1.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
}
