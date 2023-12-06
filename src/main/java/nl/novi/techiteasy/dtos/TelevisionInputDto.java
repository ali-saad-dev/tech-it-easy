package nl.novi.techiteasy.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TelevisionInputDto {
    public Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min=3, max=128)
    public String name;

    @Positive
    public double price;
}
