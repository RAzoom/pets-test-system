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
@Table(name = "HB_KIND_CONTACT")
public class HbKindContact {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "public_name", updatable = false)
    private String publicName;

    @Column(name = "sysname", updatable = false)
    private String sysname;

}
