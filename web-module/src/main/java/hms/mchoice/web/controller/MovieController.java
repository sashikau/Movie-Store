package hms.mchoice.web.controller;

import hms.mchoice.core.domain.Movie;
import hms.mchoice.web.bean.MovieForm;
import hms.mchoice.web.service.Mservice;
import hms.mchoice.web.validator.MovieValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import org.apache.log4j.Logger;
/**
 * Created by sashika on 8/23/15.
 */
@Controller
public class MovieController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Mservice.class);
    private static final String INFO = "movieInfo";
    private static final String ADD="addInfo";

    @Autowired
    private Mservice mservice;

    @Autowired
    private MovieValidator movieValidator;

    @RequestMapping (value = "/movieInfo", method = RequestMethod.GET)
    public ModelAndView movieInfo() {
        ArrayList<Movie> movieList = mservice.getMovieList();
        ModelAndView modelAndView = new ModelAndView(INFO);
        modelAndView.addObject("list", movieList);
        logger.info("Display movieInfo page");
        return modelAndView;
    }

    @RequestMapping (value = "/addInfo", method = RequestMethod.GET)
    public ModelAndView addInfo() {
        return new ModelAndView(ADD, "movieForm", new MovieForm());
    }

    @RequestMapping (value = "/addInfo", method = RequestMethod.POST)
    public ModelAndView addInfo(@Validated @ModelAttribute ("movieForm") MovieForm movieForm, BindingResult result) {
        movieValidator.validate(movieForm,result);

        if (result.hasErrors()){
            logger.error("Cannot load the page, {} validation errors", result.getErrorCount());
            return addInfo();
        }
        else {
            mservice.saveMovie(movieForm);
            logger.info("no validation errors");
            return movieInfo();
        }

    }

  
}
