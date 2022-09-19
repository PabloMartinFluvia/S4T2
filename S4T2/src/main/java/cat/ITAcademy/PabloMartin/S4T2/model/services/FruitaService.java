package cat.ITAcademy.PabloMartin.S4T2.model.services;

import cat.ITAcademy.PabloMartin.S4T2.model.domain.Fruita;
import cat.ITAcademy.PabloMartin.S4T2.model.repository.FruitaPersistence;
import cat.ITAcademy.PabloMartin.S4T2.model.services.exceptions.ConflictException;
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
        this.fruitaPersistence
                .findByNom(nom)
                .ifPresent(fruita -> {
                    throw new ConflictException("Nom ja registrar en BD (i no pot ser duplicat): " + nom);
                });
    }
}
