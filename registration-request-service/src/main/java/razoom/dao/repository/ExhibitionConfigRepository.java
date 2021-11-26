package razoom.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import razoom.dao.entity.ExhibitionConfig;

public interface ExhibitionConfigRepository extends JpaRepository<ExhibitionConfig, Long> {

    @Query(value = "SELECT t1 FROM ExhibitionConfig t1 " +
            " WHERE id = ?1 " +
            " and current_timestamp between t1.startRegistration and t1.finishRegistration " +
            " and t1.state = 'ACTIVE'")
    ExhibitionConfig findActiveById(Long id);
}