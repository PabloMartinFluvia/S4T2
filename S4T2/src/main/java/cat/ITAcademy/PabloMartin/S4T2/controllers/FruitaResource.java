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
import cat.ITAcademy.PabloMartin.S4T2.model.services.FruitaService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.CrossOrigin;
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
    public ResponseEntity<?> add(@RequestBody @Valid FruitaCreationDto fruitaDto){         
        Fruita fruita = Fruita.builder()
                .id(0)
                .nom(fruitaDto.getNom())
                .quantitatQuilos(fruitaDto.getQuantitatQuilos())
                .build();
        
        //si no error -> 201 created + retornar lo creat
        return ResponseEntity.status(HttpStatus.CREATED).body(fruitaService.add(fruita));        
    }
    
    @PutMapping(UPDATE)
    public ResponseEntity<?> update(@RequestBody @Valid FruitaUpdateDto fruitaDto){
        Fruita fruita = Fruita.builder()
                .id(fruitaDto.getId())
                .nom(fruitaDto.getNom())
                .quantitatQuilos(fruitaDto.getQuantitatQuilos())
                .build();
        //si no error -> 200 ok + retornar lo actualitzat
        return ResponseEntity.ok(fruitaService.update(fruita));
    }
    
}
