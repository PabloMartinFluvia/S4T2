package cat.ITAcademy.PabloMartin.S4T2.model.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/*
Interface del patró DAO per a aquesta entitat. El framework s'encarrega de la 
implementació (en la capa de dades).

Alternativa:
Per desacoplar el servei de com es gestiona el patró DAO es pot fer que el servei
ataqui a una interface FruitaPersistence (dins de la capa del domain/model)
i que tingui la implementació en la capa de dades FruitaPersistenceMotorBD amb 
la anotacio @Repository (i que sigui aquesta qui ataqui a aquesta interface, 
que llavors estaria en la capa de dades.).

Obs:
JpaRepository<T, ID> és el genèric de .data.jpa (acava heredant de CRUD repository,
principal genèric de la API org.springframework.data)
*/

public interface FruitaRepository extends JpaRepository<FruitaEnitiy, Integer>{
    
    Optional<FruitaEnitiy> findByNom(String nom);
}
