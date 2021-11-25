package razoom.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import razoom.dao.entity.Dog;

public interface DogRepository extends JpaRepository<Dog, Long> {
}
