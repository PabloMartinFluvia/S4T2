/*
S'encarrega de:
    definir els paths i el mètodes del recurs
    regorganitzar la recepció de dades de la petició
    gestionar la validació dels dto's
    delegar en el servei la execució de la lògica necessaria per a satisfer la petició
        *podria atacar a una interfaç
    proporcionar al client una resposta
*/

/*
OBS: per a poder passar una classe dto a json (internament usa Jackson) cal que la classe
tingui un construgtor sense arguments
*/        
    
/*
ResponseEnitity:
    Extensio de HTTPEntity (header + body)
    Afegeix HTTPStatus (enum del resultat de la petició)
    STATUS de la resposta: https://www.rfc-editor.org/rfc/rfc7231#section-6   https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
        Headers de la resposta: https://www.rfc-editor.org/rfc/rfc7231#section-7
    Té 2 builders (un per al body i un altre per al header)
*/

package cat.ITAcademy.PabloMartin.S4T2.controllers;

import cat.ITAcademy.PabloMartin.S4T2.model.domain.Fruita;
import cat.ITAcademy.PabloMartin.S4T2.model.repository.FruitaEnitiy;
import cat.ITAcademy.PabloMartin.S4T2.model.services.FruitaService;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = FruitaResource.ORIGIN)
@RestController
@RequestMapping(FruitaResource.RESOURCE_URI)
public class FruitaResource {
    
    public static final String ORIGIN = "http://localhost:8080";
    
    //===>>>> enunciat del excercici demana substantiu en SINGULAR ==>>> és mala practica, no?  
    public static final String RESOURCE_URI = "/fruites";
    
    public static final String ADD = "/add";
    
    public static final String UPDATE = "/update";
    
    public static final String DELETE_ONE = "/delete/{id}";
    
    public static final String GET_ONE = "/getOne/{id}";
    
    public static final String GET_ALL = "/getAll";
    
    @Autowired
    private FruitaService fruitaService;
    
    @PostMapping(ADD)
    public ResponseEntity<?> add(@Valid @RequestBody Fruita fruita){ 
        
        /*
        1)Problemes previs abans d'executar-se el mètode: passar del body al objecte
        Body null / no proporcionat
            excepcio: org.springframework.http.converter.HttpMessageNotReadableException /BadRequest / 400 
        Valors proporcionats de camps en conflicte anotacions restrictives dels atributs de la classe:
             Si la classe és una entitat: org.hibernate.PropertyValueException 500 InternalError                 
        */
        
        /*
        2) Validacions extra de la data proporcionada s'ha proporcionat correctament:
        ***De moment no uso @Valid ni anotacions de javax.validation en Fruita
        NO s'ha introduït un id
            TO DO ==>> no sé com comprovar-ho, de moment m'asseguro que el id del objecte és igual al que tindria si no s'hagués proporcionat
        Nom not null 
        Quantitat > 0
        */
        
        if(fruita.getId() != 0){
            //trow exception
        }else if (fruita.getNom() == null){
            //throw exception
        }else if(fruita.getQuantitatQuilos() < 0){
            //throw exception
        }
                
        /*
        3) Dir-li al servei que executi la lògica necessaria
            (camps del dto proporcionat lògics segons lo que demana el client, servei s'encarrega )        
        */
        Fruita fruitaCreated = fruitaService.add(fruita);       
        
        /*
        4) Resposta. Si tot ha anat OK (no hi ha hagut cap error):
            HTTPSTATUS 201 (CREATED)
            Recomanable en el body referencia o representació del recurs
        */
        return ResponseEntity.status(HttpStatus.CREATED).body(fruitaCreated);               
    }
    
    @PutMapping(UPDATE)
    public ResponseEntity<?> update (@RequestBody FruitaEnitiy fruita){
        /*
        1) Problemes previs (Ídem al POST)
        @RequestBody by default required: true
        Usant HttpInputMessage i HttpMessageConverter intenta convertir el body a un objecte de la classe X.
        Body:
            null / no proporcionat -> 
                HttpMessageNotReadableException /BadRequest / 400 
            camps primitius no proporcionats ->
            camps NO primitius no proporcionats ->
                poden ser NULL ->
                NOT NULL ->
            camps extra proporcionats (no definits en la classe) ->
            camps propornionats NO compleixen alguna restricció definida en la classe ->                 
        TO DO ==>> 
            Excepcions / errors: al no arrivar-se a executar el mètode -> no es poden capturar dins d'ell.
        */
        
        /*
        2) Validacions extra de la data proporcionada
        TO DO ==>>
            1) llançar excepció bad request si id no té un format vàlid (valid: id>0)???
        */
        
        /*
        3) Solicitar al servei que executi la lògica necessaria per a actualitzar la entitat/document
        TO DO ==>>
            Com ha de gestionar el servei el fet de que no hi hagi registre amb aquest id???
        */        
        FruitaEnitiy fruitaUpdated = fruitaService.update(fruita);  
        
        /*
        4) Resposta. Si tot ha anat OK (no hi ha hagut cap error):
            HTTPSTATUS 200 (OK)
            Recomanable en el body referencia o representació del recurs
        */
        return ResponseEntity.ok(fruitaUpdated);                
    } 
    
    @DeleteMapping(DELETE_ONE)
    public ResponseEntity<?> deleteOne(@PathVariable int id){
        /*
        1) Problemes previs 
        @PathVariable per defecte required = true
        Si no es proporciona el path extra es llança una excepció 404            
        Si es proporciona un pathVariable que NO es pot converir a int -> error 400 BadRequest
        TO DO ==> gestionar les excepcions 404 (not found) i 400 (bad request)        
        */
        
        /*
        2) Validacions extra de la data proporcionada
        TO DO ==>>
            1) llançar excepció bad request si id no té un format vàlid (valid: id>0)???
            2) O codificar la restricció amb una anotació en la entitat i que automaticament 
            llanci excepció al intentar crear el bean???
        */
        
        /*
        3) Solicitar al servei que executi la lògica necessaria per a eliminar la entitat/document
        IMPORTANT: NO retornar error si el recurs NO existeix
        */ 
        fruitaService.deleteById(id);
        
        /*
        4) Resposta. Si tot ha anat OK (no hi ha hagut cap error):
            HTTPSTATUS 204 (NO CONTENT)
        IMPORTANT: NO retornar error si el recurs NO existeix
        */
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  //204
    }
    
    @GetMapping(GET_ONE)
    public ResponseEntity<?> findById(@PathVariable int id){
         /*
        1) Problemes previs (Idem al DELETE ONE)
        @PathVariable per defecte required = true
        Si no es proporciona el path extra es llança una excepció 404            
        Si es proporciona un pathVariable que NO es pot converir a int -> error 400 BadRequest
        TO DO ==> gestionar les excepcions 404 (not found) i 400 (bad request)        
        */
        
        /*
        2) Validacions extra de la data proporcionada
        TO DO ==>>
            1) llançar excepció bad request si id no té un format vàlid (valid: id>0)???
            2) O codificar la restricció amb una anotació en la entitat i que automaticament 
            llanci excepció al intentar crear el bean???
        */
        
        /*
        3) Solicitar al servei que executi la lògica necessaria per a llegir la entitat/document
        TO DO ==>>
            Com ha de gestionar el servei el fet de que no hi hagi registre amb aquest id???
        */ 
        
        FruitaEnitiy fruitaRequired = fruitaService.findById(id);
        
        /*
        4) Resposta. Si tot ha anat OK (no hi ha hagut cap error):
            HTTPSTATUS 200 (OK)
            Representació del recurs
        */
        return ResponseEntity.ok(fruitaRequired); //200 OK            
    }
    
    @GetMapping(GET_ALL)
    public ResponseEntity<?> findAll(){     
        /*
        1) Solicitar al servei que executi la lògica necessaria per a llegir totes les entitats / documents.
        TO DO ==>>
            Com ha de gestionar el servei el fet de que no hi hagi cap recurs guardat???
        */ 
        /*
        2) Resposta. Si tot ha anat OK (no hi ha hagut cap error):
            HTTPSTATUS 200 (OK)
            Representació dels recursos
        */
        return ResponseEntity.ok(fruitaService.findAll()); //200 OK
    }     
    
    /*
    TO DO ==> Gestionar errors:
        405 "Method Not Allowed" (ex: POST quan s'espera un GET)
        404 quan client apunta a un end point inexistent en el servidor
    */
    
    /*
    FindOne or UpdateOne: si no es trova en la BD -> 404 exception
    */
}
