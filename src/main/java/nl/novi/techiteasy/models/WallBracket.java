package nl.novi.techiteasy.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "wallBracket")
public class WallBracket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String size;
    private Boolean adjustable;
    private Double price;

    public Set<Television> getTelevisions() {return televisions;}

    @ManyToMany
    @JoinTable(name = "television_wallbracket",
            joinColumns = @JoinColumn(name = "wallbracket_id"),
            inverseJoinColumns = @JoinColumn(name = "television_id"))
    private Set<Television> televisions = new HashSet<>();
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getAdjustable() {
        return adjustable;
    }

    public void setAdjustable(Boolean adjustable) {
        this.adjustable = adjustable;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
