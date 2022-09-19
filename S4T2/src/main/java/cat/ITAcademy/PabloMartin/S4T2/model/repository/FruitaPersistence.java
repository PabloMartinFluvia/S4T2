package cat.ITAcademy.PabloMartin.S4T2.model.repository;

import cat.ITAcademy.PabloMartin.S4T2.model.domain.Fruita;
import java.util.Optional;


public interface FruitaPersistence {
 
    Fruita add(Fruita fruita);
    
    Optional<Fruita> findByNom(String nom);
}
