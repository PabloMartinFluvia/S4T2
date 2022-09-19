package cat.ITAcademy.PabloMartin.S4T2.model.repository.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitaJpaRepository extends JpaRepository<FruitaEnitiy, Integer>{
    
    Optional<FruitaEnitiy> findByNom(String nom);
}
