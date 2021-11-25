package razoom.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import razoom.dao.entity.Physic;

public interface PhysicRepository extends JpaRepository<Physic, Long> {
}
