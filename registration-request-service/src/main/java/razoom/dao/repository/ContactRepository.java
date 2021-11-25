package razoom.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import razoom.dao.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
