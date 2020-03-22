package rest;

import java.util.List;

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

import model.Rider;
import repo.RiderRepository;

@CrossOrigin
@RestController
public class RiderRestController
{

  @Autowired
  private RiderRepository riderRepository;

  @RequestMapping(value = "/rider", method = RequestMethod.GET)
  @ResponseBody
  public List<Rider> findAll()
  {
    return (List<Rider>) riderRepository.findAll();
  }

  @RequestMapping(value = "/rider/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Rider findAll(@PathVariable(value = "id") Integer id)
  {
    return (Rider) riderRepository.findById(id).get();
  }

  @RequestMapping(value = "/rider", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<String> saveAddess(@RequestBody Rider rider)
  {
    riderRepository.save(rider);
    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

  @RequestMapping(value = "/rider/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteAddress(
      @PathVariable(value = "id") Integer id)
  {
    try
    {
      riderRepository.deleteById(id);

    } catch (Exception e)
    {
      return new ResponseEntity<String>("FAILURE",
          HttpStatus.EXPECTATION_FAILED);
    }
    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

}
