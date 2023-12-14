package nl.novi.techiteasy.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class RemoteControllerInputDto {
    @NotBlank(message = "Name is mandatory")
    @Size(min=3, max=128)
    private String name;
    private String compatibleWith;
    private String batteryType;
    private String brand;
    @Positive
    private Double price;
    private int originalStock;
}
