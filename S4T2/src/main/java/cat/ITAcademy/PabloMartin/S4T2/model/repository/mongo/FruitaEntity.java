package cat.ITAcademy.PabloMartin.S4T2.model.repository.mongo;

import cat.ITAcademy.PabloMartin.S4T2.model.domain.Fruita;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data  // @ToString, @EqualsAndHashCode, @Getter, and @Setter on all non-final fields, @RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //només considerar els camps explicitats
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "fruites")
public class FruitaEntity {
    
    @Transient
    public static final String SEQUENCE_NAME = "fruites_sequence";
    
    @Id    
    private int id;
    
    @Indexed(unique = true)
    @EqualsAndHashCode.Include // only id
    private String nom;
    
    private int quantitatQuilos; 
    
    public FruitaEntity(Fruita fruita) {
        BeanUtils.copyProperties(fruita, this);
    }
    
    public void fromFruita(Fruita fruita) {
        BeanUtils.copyProperties(fruita, this);
    }

    public Fruita toFruita() {
        Fruita fruita = new Fruita();
        BeanUtils.copyProperties(this, fruita);
        return fruita;
    }
}
