package razoom.dao.entity;

import lombok.Getter;
import lombok.Setter;
import razoom.dao.pojo.ExhibitionConfigState;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "EXHIBITION_CONFIG")
public class ExhibitionConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXHIBITION_CONFIG_SEQ")
    @SequenceGenerator(name = "EXHIBITION_CONFIG_SEQ", sequenceName = "EXHIBITION_CONFIG_SEQ", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Column(name = "START_EXHIBITION", nullable = false)
    private ZonedDateTime startExhibition;

    @Column(name = "FINISH_EXHIBITION", nullable = false)
    private ZonedDateTime finishExhibition;

    @Column(name = "START_REGISTRATION", nullable = false)
    private ZonedDateTime startRegistration;

    @Column(name = "FINISH_REGISTRATION", nullable = false)
    private ZonedDateTime finishRegistration;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SYSNAME", nullable = false)
    private String sysname;

    @Column(name = "STATE", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExhibitionConfigState state;
}
