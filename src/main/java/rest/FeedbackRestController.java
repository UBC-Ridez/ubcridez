package rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.Feedback;
import repo.FeedbackRepository;
import repo.MemberRepository;
import repo.RideRepository;

@CrossOrigin
@RestController
public class FeedbackRestController
{

  @Autowired
  private FeedbackRepository feedbackRepository;
  
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private RideRepository rideRepository;

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

  //NON hibernate way
  @RequestMapping(value = "/feedback", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<String> saveAddess(@RequestBody Map<String, String> feedbackMap)
  {
    String result = "SUCCESS";
    HttpStatus status = HttpStatus.OK;
    try
    {
      Feedback feedback = feedbackRepository.findById(Integer.parseInt(feedbackMap.get("id"))).get();
      feedback.setFeedback(feedbackMap.get("feedback"));
      feedback.setFeedbackType(feedbackMap.get("feedbackType"));
      feedback.setMember(memberRepository.findById(Integer.parseInt(feedbackMap.get("memberId"))).get());
      feedback.setRide(rideRepository.findById(Integer.parseInt(feedbackMap.get("rideId"))).get());
      feedbackRepository.save(feedback);
    } catch (Exception e)
    {
      result = "FAILURE";
      status = HttpStatus.EXPECTATION_FAILED;
    }
    return new ResponseEntity<String>(result, status);
  }

  @RequestMapping(value = "/feedback/{id}", method = RequestMethod.POST)
  public ResponseEntity<String> deleteAddress(
      @PathVariable(value = "id") Integer id)
  {
    String result = "SUCCESS";
    HttpStatus status = HttpStatus.OK;
    try
    {
      feedbackRepository.deleteById(id);

    } catch (Exception e)
    {
      result = "FAILURE";
      status = HttpStatus.EXPECTATION_FAILED;
    }
    return new ResponseEntity<String>(result, status);
  }

}
