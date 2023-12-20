package nl.novi.techiteasy.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class WallBracketInputDto {

    public Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min=3, max=128)
    public String name;
    public String size;
    public Boolean adjustable;
    @Positive
    public Double price;
}
