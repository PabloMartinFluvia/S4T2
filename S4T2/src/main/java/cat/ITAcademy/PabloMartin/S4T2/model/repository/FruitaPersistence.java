package cat.ITAcademy.PabloMartin.S4T2.model.repository;

import cat.ITAcademy.PabloMartin.S4T2.model.domain.Fruita;
import java.util.Optional;


public interface FruitaPersistence {
 
    Fruita add(Fruita fruita);
    
    Optional<Fruita> findByNom(String nom);
    
    boolean existById(int id);
    
    Fruita update(Fruita fruita);
    
    void deleteOne(int id);
    
    Optional<Fruita> getOne(int id);
}
