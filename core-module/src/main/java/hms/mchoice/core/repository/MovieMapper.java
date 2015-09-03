package hms.mchoice.core.repository;

import hms.mchoice.core.domain.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shashika on 8/24/15.
 */
public class MovieMapper implements RowMapper<Movie> {

    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie();
        movie.setName(rs.getString("name"));
        movie.setRating(rs.getDouble("rating"));
        return movie;
    }
}
