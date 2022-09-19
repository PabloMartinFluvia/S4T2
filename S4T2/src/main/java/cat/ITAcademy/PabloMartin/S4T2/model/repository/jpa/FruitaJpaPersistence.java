package cat.ITAcademy.PabloMartin.S4T2.model.repository.jpa;

import cat.ITAcademy.PabloMartin.S4T2.model.domain.Fruita;
import cat.ITAcademy.PabloMartin.S4T2.model.repository.FruitaPersistence;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "jpa")
public class FruitaJpaPersistence implements FruitaPersistence{
    
    @Autowired
    private FruitaJpaRepository fruitaJpaRepository;
    

    @Override
    public Fruita add(Fruita fruita) {        
        return fruitaJpaRepository.save(new FruitaEntity(fruita)).toFruita();
    }

    @Override
    public Optional<Fruita> findByNom(String nom) {
        return fruitaJpaRepository.findByNom(nom).map(FruitaEntity::toFruita);
    }

    @Override
    public boolean existById(int id) {
        return fruitaJpaRepository.existsById(id);
    }

    @Override
    public Fruita update(Fruita fruita) {
        return add(fruita);
    }
}
