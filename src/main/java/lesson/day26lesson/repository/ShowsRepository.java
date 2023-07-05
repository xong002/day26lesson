package lesson.day26lesson.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShowsRepository {
    
    public static final String FIELD_NAME = "name";
    public static final String COLLECTION_TVSHOWS = "tvshow";

    @Autowired
    private MongoTemplate mongoTemplate;

    // db.tvshow.find({name: 'the name'})
    public List<Document> findShowsByName(String programName){
        
        // set the filter
        // Criteria criteria = Criteria.where(FIELD_NAME).is(programName);
        Criteria criteria = Criteria.where(FIELD_NAME).regex(programName, "i"); //for non-case sensitive

        // create a Mongo query with filter
        Query query = Query.query(criteria);

        // perform the query (use org.bson Document)
        List<Document> result = mongoTemplate.find(query,Document.class, COLLECTION_TVSHOWS);
        
        return result;

    }
}
