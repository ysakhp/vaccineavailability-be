package com.vaccine.availability.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.vaccine.availability.model.DBSequence;

@Service
public class SequenceService {

	@Autowired
	private MongoOperations mongoOperations;

	public int getSequenceNumber(String sequenceName) {
		Query query = new Query(Criteria.where("id").is(sequenceName));
		Update update = new Update().inc("seqNo",1);
		
		
		DBSequence counter = mongoOperations.findAndModify(query, 
				update,new FindAndModifyOptions().returnNew(true).upsert(true),DBSequence.class);
		
		return !Objects.isNull(counter) ? counter.getSeqNo() : 1;
	}

}
