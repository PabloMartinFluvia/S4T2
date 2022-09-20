package cat.ITAcademy.PabloMartin.S4T2;

import cat.ITAcademy.PabloMartin.S4T2.model.repository.FruitaPersistence;
import cat.ITAcademy.PabloMartin.S4T2.model.repository.jpa.FruitaJpaPersistence;
import cat.ITAcademy.PabloMartin.S4T2.model.repository.mongo.FruitaMongoPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BDConfig {
    
    @Bean(name = "jpa")    
    public FruitaPersistence fruitaJpaPersistence(){
        return new FruitaJpaPersistence();
    }
    
    @Bean(name = "mon")    
    public FruitaPersistence fruitaMongoPersistence(){
        return new FruitaMongoPersistence();
    }
}
