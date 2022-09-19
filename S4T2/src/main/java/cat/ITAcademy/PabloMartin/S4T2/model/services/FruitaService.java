package cat.ITAcademy.PabloMartin.S4T2.model.services;

import cat.ITAcademy.PabloMartin.S4T2.model.domain.Fruita;
import cat.ITAcademy.PabloMartin.S4T2.model.repository.FruitaEnitiy;
import cat.ITAcademy.PabloMartin.S4T2.model.repository.FruitaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitaService {

    @Autowired
    private FruitaRepository fruitaRepository; 
    
    /*
    Obs: metode add(T) del CRUD repository GUARDA O SOBREESCRIU 
    */
    
    public Fruita add(Fruita fruita){    //id 0 ; nom not NULL; quantitat >=0
        /*
        Com que no s'ha creat cap classe per a desacoplar el domain de la interface del repository ->
        La validació de que la data sigui coherent amb les restriccions del disseny de la BD es fan aquí.
        També la conversió del model a la entitat.
        */
        assertNameValid(fruita.getNom());
        
        //entitat pot estar acoplat al model, xo no viceversa.
        return fruitaRepository.save(new FruitaEnitiy(fruita)).toFruita();
    }
    
    public void assertNameValid(String nom){
        int length = nom.length();
        if(length < 1 || length > 45){ //restricció tamany del string
            //TO DO ==>> throw new ConflictException("Longitud del nom fora del rang [1 , 45]: " + nom);
        }          
        if(fruitaRepository.findByNom(nom).isPresent()){ // restricció nom UNIQUE            
            //TO DO ==>> throw new ConflictException("Nom ja existeix: " + nom);
        }
    }
    
    public FruitaEnitiy update(FruitaEnitiy fruita){   
        if(fruitaRepository.existsById(fruita.getId())){ // IllegalArgumentException si ID null    
            return fruitaRepository.save(fruita); // IllegalArgumentException si T null    
        }else{ //not found
            //TO DO ==>> que retornar???
            //throw exception / error (ja que no hi ha cap registre a actualitzar)???
            //Obs: és un 404, ja que la url proporcionada pel client no apunta a cap registre en la BD???
            return null; 
        }
    }
    
    public void deleteById(int id){
        //no retornar error si el recurs no existeix
        fruitaRepository.deleteById(id); //IllegalArgumentException si ID null
    }
    
    public FruitaEnitiy findById(int id){
        
        Optional<FruitaEnitiy> fruitaOptional = fruitaRepository.findById(id); // IllegalArgumentException si ID null    
        
        if(fruitaOptional.isPresent()){
            return fruitaOptional.get();
        }else{ //not found
            //TO DO ==>> que retornar???
            //throw exception / error (ja que no hi ha cap registre amb aquest id)???
            //Obs: és un 404, ja que la url proporcionada pel client no apunta a cap registre en la BD???
            return null;             
        }  
    }
    
    public List<FruitaEnitiy> findAll() {
        //TO DO ==>> Cal fer algu si no hi ha cap registre / document guardat?
        return fruitaRepository.findAll();
    }
}
