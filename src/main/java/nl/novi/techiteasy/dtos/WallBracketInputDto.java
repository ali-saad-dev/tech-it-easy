package nl.novi.techiteasy.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class WallBracketInputDto {
    @NotBlank(message = "Name is mandatory")
    @Size(min=3, max=128)
    private String name;
    private String size;
    private Boolean adjustable;
    @Positive
    private Double price;
}
