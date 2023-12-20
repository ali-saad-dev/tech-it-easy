package nl.novi.techiteasy.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class RemoteControllerInputDto {

    public Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min=3, max=128)
    public String name;
    public String compatibleWith;
    public String batteryType;
    public String brand;
    @Positive
    public Double price;
    public int originalStock;

}
