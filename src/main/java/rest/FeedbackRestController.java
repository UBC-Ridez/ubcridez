package rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.Feedback;
import repo.FeedbackRepository;

@RestController
public class FeedbackRestController
{

  @Autowired
  private FeedbackRepository feedbackRepository;

  @RequestMapping(value = "/feedback", method = RequestMethod.GET)
  @ResponseBody
  public List<Feedback> findAll()
  {
    return (List<Feedback>) feedbackRepository.findAll();
  }

  @RequestMapping(value = "/feedback/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Feedback findAll(@PathVariable(value = "id") Integer id)
  {
    return (Feedback) feedbackRepository.findById(id).get();
  }

  @RequestMapping(value = "/feedback", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<String> saveAddess(@RequestBody Feedback feedback)
  {
    feedbackRepository.save(feedback);
    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

  @RequestMapping(value = "/feedback/{id}", method = RequestMethod.POST)
  public ResponseEntity<String> deleteAddress(
      @PathVariable(value = "id") Integer id)
  {
    try
    {
      feedbackRepository.deleteById(id);

    } catch (Exception e)
    {
      return new ResponseEntity<String>("FAILURE",
          HttpStatus.EXPECTATION_FAILED);
    }
    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

}