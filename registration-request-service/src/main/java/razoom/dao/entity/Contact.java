package razoom.dao.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "CONTACT")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACT_SEQ")
    @SequenceGenerator(name = "CONTACT_SEQ", sequenceName = "CONTACT_SEQ", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @NonNull
    @Column(name = "value", nullable = false)
    private String value;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "TYPE_ID")
    private HbKindContact type;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "PHYSIC_ID")
    private Physic physic;
}
