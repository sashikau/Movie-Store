package hms.mchoice.core.repository;

import hms.mchoice.core.domain.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import java.util.ArrayList;

@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/test-context.xml")
public class MovieRepositoryImplTest {

    @Autowired
    private MovieRepository movieRepository;

    Movie movie1;
    Movie movie2;
    Movie movie3;
    Movie movie4;

    public void createMovieObjects() throws Exception {

        movie1 = new Movie();
        movie1.setName("testMovie1");
        movie1.setRating(5);

        movie2 = new Movie();
        movie2.setName("testMovie2");
        movie2.setRating(6);

        movie3 = new Movie();
        movie3.setName("testMovie3");
        movie3.setRating(7);

        movie4 = new Movie();
        movie4.setName("testMovie4");
        movie4.setRating(8);
    }

    @Before
    public void testInsert() throws Exception {
        Movie testMovie1;
        Movie testMovie2;
        Movie testMovie3;
        Movie testMovie4;

        createMovieObjects();

        movieRepository.insert(movie1);
        movieRepository.insert(movie2);
        movieRepository.insert(movie3);
        movieRepository.insert(movie4);

        testMovie1=movieRepository.findByName("testMovie1");
        testMovie2=movieRepository.findByName("testMovie2");
        testMovie3=movieRepository.findByName("testMovie3");
        testMovie4=movieRepository.findByName("testMovie4");

        assertNotNull(testMovie1);
        assertNotNull(testMovie2);
        assertNotNull(testMovie3);
        assertNotNull(testMovie4);

        assertEquals(5, movieRepository.findByName("testMovie1").getRating(), 0);
        assertEquals(6, movieRepository.findByName("testMovie2").getRating(), 0);
        assertEquals(7, movieRepository.findByName("testMovie3").getRating(), 0);
        assertEquals(8, movieRepository.findByName("testMovie4").getRating(), 0);
    }

    @Test
    public void testGetRatingByName() throws Exception {
        assertEquals(5,movieRepository.findByName("testMovie1").getRating(), 0.0);
        try {
            assertNull(movieRepository.findByName("testMovie"));
        }catch (Exception e){
            System.out.println("Try to find a movie which is not available in the database");
        }

    }

    @Test
    public void testGetAll() throws Exception {
        ArrayList<Movie> MovieList = movieRepository.getAll();
        assertEquals(4,MovieList.size());
        assertEquals("testMovie1", MovieList.get(0).getName());
        assertEquals("testMovie2", MovieList.get(1).getName());
        assertEquals("testMovie3", MovieList.get(2).getName());
        assertEquals("testMovie4", MovieList.get(3).getName());
    }

    @After
    public void testDelete() throws Exception {
        movieRepository.delete("testMovie1");
        movieRepository.delete("testMovie2");
        movieRepository.delete("testMovie3");
        movieRepository.delete("testMovie4");

        try {
            assertNull(movieRepository.findByName("testMovie1"));
            assertNull(movieRepository.findByName("testMovie2"));
            assertNull(movieRepository.findByName("testMovie3"));
            assertNull(movieRepository.findByName("testMovie4"));
        }catch (Exception e){
            System.out.println("Movie is already deleted");
        }
    }


}