package cat.ITAcademy.PabloMartin.S4T2.model.repository.mongo;

import cat.ITAcademy.PabloMartin.S4T2.model.domain.Fruita;
import cat.ITAcademy.PabloMartin.S4T2.model.repository.FruitaPersistence;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//@Repository(value = "mon")
public class FruitaMongoPersistence implements FruitaPersistence{

    @Autowired
    private FruitaMongoRepository fruitaMongoRepository;
    
    @Autowired
    private SequenceGeneratorService sequenceGeneratorS;
    
    @Override
    public Fruita add(Fruita fruita) {
        fruita.setId(sequenceGeneratorS.generateSequence(FruitaEntity.SEQUENCE_NAME));        
        return save(fruita);
    }
    
    private Fruita save (Fruita fruita){
        return fruitaMongoRepository.save(new FruitaEntity(fruita)).toFruita();
    }

    @Override
    public Optional<Fruita> findByNom(String nom) {
        return fruitaMongoRepository.findByNom(nom).map(FruitaEntity::toFruita);
    }

    @Override
    public boolean existById(int id) {
        return fruitaMongoRepository.existsById(id);
    }

    @Override
    public Fruita update(Fruita fruita) {
        return save(fruita);
    }

    @Override
    public void deleteOne(int id) {
        fruitaMongoRepository.deleteById(id);        
    }

    @Override
    public Optional<Fruita> getOne(int id) {
        return fruitaMongoRepository.findById(id).map(FruitaEntity::toFruita);
    }

    @Override
    public List<Fruita> getAll() {
        List<Fruita> fruites = new LinkedList<>();
        fruitaMongoRepository.findAll().forEach(fruitaEntity -> {
            fruites.add(fruitaEntity.toFruita());
        });
        return fruites;
    }
    
}
