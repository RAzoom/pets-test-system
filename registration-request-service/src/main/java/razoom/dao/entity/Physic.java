package razoom.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PHYSIC")
public class Physic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHYSIC_SEQ")
    @SequenceGenerator(name = "PHYSIC_SEQ", sequenceName = "PHYSIC_SEQ", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "PATRONYMIC")
    private String patronymic;

    @Column(name = "BIRTHDATE", nullable = false)
    private LocalDate birthdate;

    @OneToOne
    @JoinColumn(name = "REQUEST_REGISTRATION_ID")
    private RequestRegistration requestRegistration;

    @OneToMany(mappedBy = "physic", cascade = CascadeType.PERSIST)
    private List<Contact> contactList = new ArrayList<>();

    public Physic addContact(Contact contact) {
        this.contactList.add(contact);
        return this;
    }

}
