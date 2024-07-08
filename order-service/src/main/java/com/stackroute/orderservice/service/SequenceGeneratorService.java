package com.stackroute.orderservice.service;

import java.util.Objects;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;


import com.stackroute.orderservice.entity.OrderAutoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service

public class SequenceGeneratorService {
    private final MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequence(String seqName) {



        Query query = new Query(Criteria.where("_id").is(seqName));
        Update update = new Update().inc("seq" ,1);
       OrderAutoId counter = mongoOperations.findAndModify(query,update,options().returnNew(true).upsert(true),
               OrderAutoId.class);


        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
