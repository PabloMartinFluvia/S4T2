package cat.ITAcademy.PabloMartin.S4T2.model.repository.jpa;

import cat.ITAcademy.PabloMartin.S4T2.model.repository.FruitaPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "jpa")
public class FruitaJpaPersistence implements FruitaPersistence{
    
    @Autowired
    private FruitaJpaRepository fruitaRepository;
}
