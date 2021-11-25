package razoom.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import razoom.dao.entity.RequestRegistration;

public interface RequestRegistrationRepository extends JpaRepository<RequestRegistration, Long> {
}
