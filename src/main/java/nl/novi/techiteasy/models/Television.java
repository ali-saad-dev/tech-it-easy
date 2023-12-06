package nl.novi.techiteasy.models;


import jakarta.persistence.*;

@Entity
@Table(name = "television")
public class Television {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="full_name", length = 128)
    private String name;
    @Column(name="price", length = 128)
  private double price;

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

    public void setId(Long id) {
        this.id = id;
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
