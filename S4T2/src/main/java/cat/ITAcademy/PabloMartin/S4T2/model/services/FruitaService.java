package cat.ITAcademy.PabloMartin.S4T2.model.services;

import cat.ITAcademy.PabloMartin.S4T2.model.domain.Fruita;
import cat.ITAcademy.PabloMartin.S4T2.model.repository.FruitaPersistence;
import cat.ITAcademy.PabloMartin.S4T2.model.services.exceptions.ConflictException;
import cat.ITAcademy.PabloMartin.S4T2.model.services.exceptions.NotFoundException;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class FruitaService {
    
    @Resource(name = "${bd.option}")
    private FruitaPersistence fruitaPersistence;
    
    public Fruita add(Fruita fruita){
        /*
        Si està proporcionat un nom que ja existeix en la BD
            -> error 409 CONFLICT
            ConflictException
        */
        assertNomNotExist(fruita.getNom());
        return fruitaPersistence.add(fruita);
    }
    
    public void assertNomNotExist(String nom) {
        fruitaPersistence
                .findByNom(nom)
                .ifPresent(fruita -> {
                    throw new ConflictException("Nom ja registrar en BD (i no pot ser duplicat): " + nom);
                });
    }
    
    public Fruita update(Fruita fruita){
        int id = fruita.getId();
        if(fruitaPersistence.existById(id)){
            assertNomNotExist(fruita.getNom());
            return fruitaPersistence.update(fruita);
        }else{
            throw new NotFoundException("No hi ha cap fruita amb el id proporcionat: "+id);
        }
    }
    
    public void deleteOne(int id){
        if(fruitaPersistence.existById(id)){ // to avoid EmptyResultDataAccessException
            fruitaPersistence.deleteOne(id);
        }        
    }
}
