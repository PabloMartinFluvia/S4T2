package cat.ITAcademy.PabloMartin.S4T2.model.repository.jpa;

import cat.ITAcademy.PabloMartin.S4T2.model.domain.Fruita;
import cat.ITAcademy.PabloMartin.S4T2.model.repository.FruitaPersistence;
import java.util.LinkedList;
import java.util.List;
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

    @Override
    public void deleteOne(int id) {
        fruitaJpaRepository.deleteById(id);        
    }

    @Override
    public Optional<Fruita> getOne(int id) {
        return fruitaJpaRepository.findById(id).map(FruitaEntity::toFruita);
    }

    @Override
    public List<Fruita> getAll() {
        List<Fruita> fruites = new LinkedList<>();
        fruitaJpaRepository.findAll().forEach(fruitaEntity -> {
            fruites.add(fruitaEntity.toFruita());
        });
        return fruites;
    }
}
