package si.fri.rso.skupina15.entities;

import javax.persistence.*;

@Entity(name = "registration")
@Table(name = "registration")
@NamedQueries(value =
        {
                @NamedQuery(name = "si.fri.rso.skupina15.entities.Registration.getAll", query = "SELECT r FROM registration r")
        })
public class Registration {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_registration;

    @Column
    private String registration_date;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persone")
    private Persone persone;

//    @JsonbTransient
//    @ManyToOne//(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_event")
//    private Event event;

    // Getters and setters

    public Integer getId_registration() {
        return id_registration;
    }

    public void setId_registration(Integer id_registration) {
        this.id_registration = id_registration;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public Persone getPersone() {
        return persone;
    }

    public void setPersone(Persone person) {
        this.persone = person;
    }

//    public Event getE() {
//        return event;
//    }
//
//    public void setE(Event e) {
//        this.event = e;
//    }
}
