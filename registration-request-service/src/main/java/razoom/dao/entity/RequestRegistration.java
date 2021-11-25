package razoom.dao.entity;

import lombok.Getter;
import lombok.Setter;
import razoom.dao.pojo.RequestRegistrationState;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "REQUEST_REGISTRATION")
public class RequestRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REQUEST_REGISTRATION_SEQ")
    @SequenceGenerator(name = "REQUEST_REGISTRATION_SEQ", sequenceName = "REQUEST_REGISTRATION_SEQ", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUMBER_REG", unique = true, nullable = false)
    private Long numberReg;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private RequestRegistrationState state;

    @Column(name = "TRY_COUNT")
    private Long tryCount;

    @Column(name = "CREATE_DATE", nullable = false)
    private ZonedDateTime createDate;

    @Column(name = "LAST_TRY_DATE")
    private ZonedDateTime lastTryDate;

    @ManyToOne
    @JoinColumn(name = "EXHIBITION_ID")
    private ExhibitionConfig exhibition;
}
