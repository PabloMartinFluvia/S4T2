package cat.ITAcademy.PabloMartin.S4T2.model.repository.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitaJpaRepository extends JpaRepository<FruitaEntity, Integer>{
    
    Optional<FruitaEntity> findByNom(String nom);
}