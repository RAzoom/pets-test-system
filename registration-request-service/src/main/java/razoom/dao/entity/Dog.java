package razoom.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "dog")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOG_SEQ")
    @SequenceGenerator(name = "DOG_SEQ", sequenceName = "DOG_SEQ", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "BREED_ID")
    private HbKindBreed type;

    @OneToOne
    @JoinColumn(name = "REQUEST_REGISTRATION_ID")
    private RequestRegistration requestRegistration;
}
