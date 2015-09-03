package hms.mchoice.core.repository;

import hms.mchoice.core.domain.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sashika on 8/23/15.
 */
public class MovieRepositoryImpl extends JdbcDaoSupport implements MovieRepository {


    @Override
    public void insert(Movie movie) throws Exception{
            String INSERT_SQL ="INSERT INTO movie (name, rating) VALUES(?,?);";
            getJdbcTemplate().update(INSERT_SQL, new Object[]{movie.getName(), movie.getRating()});
    }

    @Override
    public Movie findByName(String name) throws Exception{
        Movie movie=null;
        String FIND_SQL = "SELECT * FROM movie WHERE name = ? " ;
        movie = getJdbcTemplate().queryForObject(FIND_SQL, new Object[]{name}, new MovieMapper());
        return movie;
    }

    @Override
    public ArrayList<Movie> getAll(){
        List<Movie> list;
        String GET_ALL_SQL = "select * from movie";
        list = getJdbcTemplate().query(GET_ALL_SQL, new MovieMapper());
        return new ArrayList<Movie>(list);
    }

    @Override
    public void delete(String name){
        String SQL = "delete from movie where name = ?";
        getJdbcTemplate().update(SQL, name);
    }

    @Override
    public boolean isNameAvailable(String name) {
        String COUNT_SQL = "SELECT COUNT(*) FROM movie WHERE name = ?";
        int count = getJdbcTemplate().queryForObject(COUNT_SQL, new Object[]{name}, Integer.class);
        return count != 0;
    }

    @Override
    public int getNumberOfRecords(){
        String NUMBER_OF_RECORDS_SQL = "SELECT COUNT(*) FROM movie";
        return  getJdbcTemplate().queryForObject(NUMBER_OF_RECORDS_SQL, new Object[]{}, Integer.class);
    }
}
