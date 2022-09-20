package cat.ITAcademy.PabloMartin.S4T2.model.repository.mongo;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FruitaMongoRepository extends MongoRepository<FruitaEntity, Integer>{
    
    Optional<FruitaEntity> findByNom(String nom);
}
