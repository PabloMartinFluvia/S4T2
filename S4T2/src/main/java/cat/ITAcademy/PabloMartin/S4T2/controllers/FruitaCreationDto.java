package cat.ITAcademy.PabloMartin.S4T2.controllers;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FruitaCreationDto {
        
    @Null(message = "En un POST el client NO pot proporcionar un ID.")
    private Integer id;
    
    @NotBlank(message = "S'ha de proporcionar un nom.") // Not null, not empty and not only blank
    @Size(min = 1, max = 45,message = "El nom ha d'estar entre [1,45] caràcters.")
    private String nom;
    
    @PositiveOrZero(message = "La quiantitat en Kg ha de ser >= 0.")
    private int quantitatQuilos;     
}
