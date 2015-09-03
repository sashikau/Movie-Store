package hms.mchoice.web.service;


import hms.mchoice.core.domain.Movie;
import hms.mchoice.core.repository.MovieRepository;
import hms.mchoice.web.bean.MovieForm;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Created by sashika on 8/23/15.
 */
public class Mservice {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Mservice.class);

    @Autowired
    private MovieRepository movieRepository;

    public void saveMovie(MovieForm movieForm) {
        Movie movie = new Movie();
        movie.setName(movieForm.getName());
        movie.setRating(movieForm.getMovieRating());

        try {
            movieRepository.insert(movie);
            logger.info("Movie insertion successful");

        }catch (Exception e){
            logger.error("Error Occurred while inserting the movie {}, check the database it seems movie is already inserted",movieForm.getName(), e);
        }
    }

    public ArrayList<Movie> getMovieList(){
        return movieRepository.getAll();
    }

    public boolean isMovieAvailable(String name){
        return movieRepository.isNameAvailable(name);
    }
}
