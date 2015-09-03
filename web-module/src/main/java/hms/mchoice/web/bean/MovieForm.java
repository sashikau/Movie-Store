package hms.mchoice.web.bean;


/**
 * Created by sashika on 8/23/15.
 */
public class MovieForm {

    private String name;
    private String rating;
    private double movieRating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public double getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(double movieRating) {
        this.movieRating = movieRating;
    }
}
