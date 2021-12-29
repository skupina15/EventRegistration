package si.fri.rso.skupina15.entities;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity(name = "event")
@Table(name = "event")
@NamedQueries(value =
        {
                @NamedQuery(name = "si.fri.rso.skupina15.entities.Event.getAll", query = "SELECT e FROM event e")
        })

public class Event {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_event;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String start_date;

    @Column
    private String end_date;

    @JsonbTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event",  cascade = CascadeType.REMOVE)
    private List<Registration> registrations;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persone")
    private Persone host;

    @Column
    private String tag;

    // Getters and setters
    public Integer getId_event() {
        return id_event;
    }

    public void setId_event(Integer id_event) {
        this.id_event = id_event;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public Persone getHost() {
        return host;
    }

    public void setHost(Persone host) {
        this.host = host;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
