package hms.mchoice.core.repository;

import hms.mchoice.core.domain.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.Assert.*;

@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/test-context.xml")
public class MovieRepositoryMongoImplTest {

    @Autowired
    private MovieRepository movieRepositoryMongo;

    private Movie movie;
    private  int countBeforeInsert;
    private int countAfterInsert;

    @Before
    public void testInit() throws Exception {
        movie = new Movie();
        movie.setName("testMovie1");
        movie.setRating(5);
        countBeforeInsert=movieRepositoryMongo.getNumberOfRecords();
        movieRepositoryMongo.insert(movie);
    }

    @Test
    public void testInsert() throws Exception {
        assertNotNull(movieRepositoryMongo.findByName("testMovie1"));
        assertEquals(5,movieRepositoryMongo.findByName("testMovie1").getRating(),0);
    }

    @Test
    public void testFindByName() throws Exception {
        assertNotNull(movieRepositoryMongo.findByName("testMovie1"));
        assertNull(movieRepositoryMongo.findByName("test"));
    }

    @Test
    public void testGetAll() throws Exception {
        assertTrue(movieRepositoryMongo.getAll().size() > countBeforeInsert);

    }

    @Test
    public void testIsNameAvailable() throws Exception {
        assertTrue(movieRepositoryMongo.isNameAvailable("testMovie1"));
    }

    @Test
    public void testGetNumberOfRecords() throws Exception {
        countAfterInsert=movieRepositoryMongo.getNumberOfRecords();
        assertEquals(countBeforeInsert+1,countAfterInsert);
    }

    @After
    public void testDelete() throws Exception {
        movieRepositoryMongo.delete("testMovie1");
        assertNull(movieRepositoryMongo.findByName("testMovie1"));
    }
}