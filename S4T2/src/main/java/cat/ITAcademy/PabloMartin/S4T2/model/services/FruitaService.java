package cat.ITAcademy.PabloMartin.S4T2.model.services;

import cat.ITAcademy.PabloMartin.S4T2.model.repository.FruitaPersistence;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class FruitaService {
    
    @Resource(name = "${bd.option}")
    private FruitaPersistence fruitaPersistence;
}
