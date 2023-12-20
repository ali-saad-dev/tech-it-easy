package nl.novi.techiteasy.models;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "television")
public class Television {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="television-name", length = 128)
    private String name;
    @Column(name="price", length = 128)
    private double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "remoteController_id", referencedColumnName = "id")
    private RemoteController remoteController;
    public void setRemoteController(RemoteController remoteController) {
        this.remoteController = remoteController;
    }


    @ManyToOne
    @JoinColumn(name="ciModule_id")
    private CI_Module ciModule;
    public Set<WallBracket> getWallBrackets() {
        return wallBrackets;
    }
    @ManyToMany
    @JoinTable(name = "television_wallbracket",
            joinColumns = @JoinColumn(name = "television_id"),
            inverseJoinColumns = @JoinColumn(name = "wallbracket_id"))
    private Set<WallBracket> wallBrackets = new HashSet<>();
    public Television(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Television() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
