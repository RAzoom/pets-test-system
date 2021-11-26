package razoom.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import razoom.dao.entity.HbKindContact;

public interface HbKindContactRepository extends JpaRepository<HbKindContact, Long> {

    @Query(value = "SELECT t1 FROM HbKindContact t1 " +
            " WHERE sysname = ?1 ")
    HbKindContact findBySysname(String id);
}
