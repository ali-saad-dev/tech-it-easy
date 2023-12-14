package nl.novi.techiteasy.models;

import jakarta.persistence.*;

@Entity
@Table(name = "remoteController")
public class RemoteController {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="televisionName", length = 128)
    private String name;
    private String compatibleWith;
    private String batteryType;
    private String brand;
    @Column(name="price", length = 128)
    private Double price;
    private int originalStock;

    @OneToOne(mappedBy = "remoteController")
    private Television television;

    public void setTelevision(Television television) {
        this.television = television;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(int originalStock) {
        this.originalStock = originalStock;
    }
}
