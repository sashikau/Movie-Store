package hms.mchoice.web.validator;

import hms.mchoice.web.bean.MovieForm;
import hms.mchoice.web.service.Mservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Created by shashika on 8/25/15.
 */
public class MovieValidator implements Validator {

    @Autowired
    private Mservice mservice;

    @Override
    public boolean supports(Class<?> aClass) {
        return MovieForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        MovieForm moiveForm = (MovieForm) obj;

        if (moiveForm.getName() == null || moiveForm.getName().trim().equals("")) {
            errors.rejectValue("name", "name.required");
        }else if( moiveForm.getName().length() <2 || moiveForm.getName().length()>20 ) {
            errors.rejectValue("name", "check.the.length");
        }else if(mservice.isMovieAvailable(moiveForm.getName())){
           errors.rejectValue("name","all.ready.there");
       }

        if (moiveForm.getRating() == null || moiveForm.getRating().trim().equals("")) {
                errors.rejectValue("rating", "rating.required");
        }else {
               try{
                   double value=Double.parseDouble(moiveForm.getRating());
                   if(value<0){
                       errors.rejectValue("rating", "positive.required");
                   }else if(value >10){
                       errors.rejectValue("rating", "at.most.ten");
                   }else {
                       moiveForm.setMovieRating(value);
                   }
               }catch (Exception e){
                   errors.rejectValue("rating", "double.required");
               }
        }

    }
}
