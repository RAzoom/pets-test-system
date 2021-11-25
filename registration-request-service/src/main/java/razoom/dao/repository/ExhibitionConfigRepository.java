package razoom.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import razoom.dao.entity.ExhibitionConfig;

public interface ExhibitionConfigRepository extends JpaRepository<ExhibitionConfig, Long> {
}