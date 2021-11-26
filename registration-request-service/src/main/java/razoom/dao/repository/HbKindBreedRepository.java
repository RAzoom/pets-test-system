package razoom.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import razoom.dao.entity.HbKindBreed;

public interface HbKindBreedRepository extends JpaRepository<HbKindBreed, Long> {

    //todo погуглить можно ли сделать лучше, выглядит криво
    @Query(value = "SELECT t1 FROM HbKindBreed t1 WHERE concat(upper(breedEnd),'///',upper(breedRu)) like concat('%',upper(?1),'%')",
            countQuery = "SELECT count(1) FROM HbKindBreed t1 WHERE concat(upper(breedEnd),'///',upper(breedRu)) like concat('%',upper(?1),'%')")
    Page<HbKindBreed> findByFragment(String fragment, Pageable pageable);
}
