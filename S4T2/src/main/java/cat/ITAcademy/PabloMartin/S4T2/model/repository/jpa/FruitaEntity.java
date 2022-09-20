package cat.ITAcademy.PabloMartin.S4T2.model.repository.jpa;

import cat.ITAcademy.PabloMartin.S4T2.model.domain.Fruita;

//javax.persistence: API per a la gestió dels ORM
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column; //només cal indicar-ho als qtributs si NO és vol que s'apliquin els valors per defecte. 

//lombok: per a facilitar la vida, sobretot per als DTO
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.springframework.beans.BeanUtils;

@Builder
@Data  // @ToString, @EqualsAndHashCode, @Getter, and @Setter on all non-final fields, @RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //només considerar els camps explicitats
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Fruites") //o @Document (mongo) // opcionalment si vui un nom diferent a la classe (en taula o coleccio)
//@Table // per a especificacions no per defecte (nom de la taula, indexs, etc...)
public class FruitaEntity{ 
    
    @Id   
    @GeneratedValue //strategy i generator per defecte
    @EqualsAndHashCode.Include // only id
    private int id;   //@Setter(AccessLevel.NONE) for exceptions        
    
    @Column(nullable = false, unique = true, length = 45)
    private String nom;
    
    @Column(nullable = false)    
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
