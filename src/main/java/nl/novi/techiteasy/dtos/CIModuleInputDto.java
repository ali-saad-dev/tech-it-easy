package nl.novi.techiteasy.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CIModuleInputDto {
    @NotBlank(message = "Name is mandatory")
    @Size(min=3, max=128)
    private String name;
    private String type;
    @Positive
    private Double price;
}
