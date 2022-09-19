package cat.ITAcademy.PabloMartin.S4T2.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data  // @ToString, @EqualsAndHashCode, @Getter, and @Setter on all non-final fields, @RequiredArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Fruita {
    
    private int id;
    
    private String nom;
    
    private int quantitatQuilos;  
}
