package razoom.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "HB_KIND_BREED")
public class HbKindBreed {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "BREED_END", updatable = false)
    private String breedEnd;

    @Column(name = "BREED_RU", updatable = false)
    private String breedRu;

}
