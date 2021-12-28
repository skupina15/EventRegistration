package si.fri.rso.skupina15.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "item")
@Table(name = "item")
@NamedQueries(value =
        {
                @NamedQuery(name = "si.fri.rso.skupina15.entities.Item.getAll", query = "SELECT i FROM item i"),
                @NamedQuery(name = "si.fri.rso.skupina15.entities.Item.getByOwner", query = "SELECT i FROM item i where i.owner = :owner_id")})

public class Item {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_item;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Float daily_price;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persone")
    private Persone owner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item",  cascade = CascadeType.REMOVE)
    private List<Rent> rentals;

    @Column
    private String tag;

    // Getters and setters
    public Integer getId_item() {
        return id_item;
    }

    public void setId_item(Integer id_item) {
        this.id_item = id_item;
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

    public Float getDaily_price() {
        return daily_price;
    }

    public void setDaily_price(Float daily_price) {
        this.daily_price = daily_price;
    }

    public Persone getOwner() {
        return owner;
    }

    public void setOwner(Persone owner) {
        this.owner = owner;
    }

    public List<Rent> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rent> rentals) {
        this.rentals = rentals;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
