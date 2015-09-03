package hms.mchoice.core.repository;

import hms.mchoice.core.domain.Movie;

import java.util.ArrayList;

/**
 * Created by sashika on 8/23/15.
 */
public interface MovieRepository {

    void insert(Movie movie) throws  Exception;

    Movie findByName(String name) throws Exception;

    ArrayList<Movie> getAll();

    void delete(String name);

    boolean isNameAvailable(String name);

    int getNumberOfRecords();
}
