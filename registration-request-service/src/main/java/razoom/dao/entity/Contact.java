package razoom.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "CONTACT")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACT_SEQ")
    @SequenceGenerator(name = "CONTACT_SEQ", sequenceName = "CONTACT_SEQ", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID")
    private HbKindContact type;
}
