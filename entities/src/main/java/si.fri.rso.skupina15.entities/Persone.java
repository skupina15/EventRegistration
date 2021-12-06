package si.fri.rso.skupina15.entities;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity(name = "persone")
@Table(name = "persone")
@NamedQueries(value =
        {
                @NamedQuery(name = "si.fri.rso.skupina15.entities.Persone.getAll", query = "SELECT u FROM persone u"),
        })

public class Persone {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_persone;

    @Column
    private String user_name;

    @Column
    private String email;

    @JsonbTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "host",  cascade = CascadeType.REMOVE)
    private List<Event> events;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "persone",  cascade = CascadeType.REMOVE)
    private List<Registration> registrations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "persone",  cascade = CascadeType.REMOVE)
    private List<Rent> rentals;

    //Getters and setters

    public Integer getId_persone() {
        return id_persone;
    }

    public void setId_persone(Integer id_persone) {
        this.id_persone = id_persone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public List<Rent> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rent> rentals) {
        this.rentals = rentals;
    }
}
