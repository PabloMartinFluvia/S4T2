package cat.ITAcademy.PabloMartin.S4T2.model.repository.mongo;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

    @Autowired
    MongoOperations mongoOps;
    
    public int generateSequence(String seqName) {
        Query q = new Query().addCriteria(Criteria.where("_id").is(seqName));
        Update u = new Update().inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);
        
        DatabaseSequence contador = mongoOps.findAndModify(q, u, options, DatabaseSequence.class);
        
        return !Objects.isNull(contador) ? contador.getSeq() : 1;
    }
}
