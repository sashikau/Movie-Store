package hms.mchoice.core.repository;

import com.mongodb.*;
import hms.mchoice.core.domain.Movie;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by shashika on 9/2/15.
 */
public class MovieRepositoryMongoImpl implements MovieRepository {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MovieRepositoryMongoImpl.class);
    MongoClient mongoClient;
    DB db;
    DBCollection collection;

    public MovieRepositoryMongoImpl(){
        try {
            mongoClient = new MongoClient();
            db = mongoClient.getDB("movies");
            collection = db.getCollection("movie");
        }catch (Exception e){
            logger.error("Error occurred while creating mongoClient",e);
        }
    }

    @Override
    public void insert(Movie movie) {
        BasicDBObject document = new BasicDBObject();
        String movieName=movie.getName();
        Double movieRating=movie.getRating();

        document.put("name", movieName);
        document.put("rating", movieRating);
        collection.insert(document);
    }

    @Override
    public Movie findByName(String name) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("name", name);
        DBCursor cursor = collection.find(whereQuery);
        Movie movie=null;
        String movieName=null;
        Double movieRating=0.0;

        while(cursor.hasNext()) {
            movie = new Movie();
            movieRating=Double.parseDouble(cursor.next().get("rating").toString());
            movie.setName(name);
            movie.setRating(movieRating);
        }

        return movie;
    }

    @Override
    public ArrayList<Movie> getAll() {
        DBCursor cursor = collection.find();
        ArrayList<Movie> movieList= new ArrayList<Movie>();
        Movie movie=null;

        while (cursor.hasNext()) {
            movie = new Movie();
            DBObject obj =cursor.next();

            movie.setName(obj.get("name").toString());
            movie.setRating(Double.parseDouble(obj.get("rating").toString()));

            movieList.add(movie);
        }

        return movieList;
    }

    @Override
    public void delete(String name) {
        if(findByName(name)!=null) {
            BasicDBObject document = new BasicDBObject();
            document.put("name", name);
            collection.remove(document);
        }
    }

    @Override
    public boolean isNameAvailable(String name) {
        return findByName(name)!=null;
    }

    @Override
    public int getNumberOfRecords() {
        return (int)collection.getCount();
    }
}
