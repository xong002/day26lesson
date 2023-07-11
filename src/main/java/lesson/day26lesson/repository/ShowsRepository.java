package lesson.day26lesson.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShowsRepository {
    
    public static final String FIELD_NAME = "name";
    public static final String COLLECTION_TVSHOWS = "tvshows";
    public static final String FIELD_GENRES = "genres";
    public static final String A_TYPE = "type";

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

    // db.tvshow.find({
    //     genres: {$in: ['Horror']}
    // })
    public List<String> findShowsByGenre(Object... genreList){
        // set filter
        Criteria criteria = Criteria.where(FIELD_GENRES).in(genreList);

        //create query
        Query query = Query.query(criteria);
        query.fields()
            .exclude("_id")
            .include(FIELD_NAME);

        // perform query (String.class because only returning one value = NAME)
        List<String> result = mongoTemplate.find(query, String.class, COLLECTION_TVSHOWS);

        return result;

    }

    public List<String> getAllTypes(){
       List<String> result = mongoTemplate.findDistinct(new Query(), "type", "tvshows", String.class);
       return result; 
    }

    // db.tvshows.find(
    // { type: {$regex: 'reality', $options: 'i'}}
    // )
    // .limit(5)
    // .sort({name: 1})
    // .projection({ id: 1, name: 1, summary: 1, 'image.original': 1, _id: 0})
    public List<Document> getShowsByType(String type, int limit, int skip){
        Criteria criteria = Criteria.where("type").regex(type, "i");

        Query query = Query.query(criteria).limit(limit).skip(skip).with(Sort.by(Direction.ASC, "name"));
        query.fields().exclude("_id").include("id", "name", "summary", "image.original");

        return mongoTemplate.find(query, Document.class, COLLECTION_TVSHOWS);

    }
}
