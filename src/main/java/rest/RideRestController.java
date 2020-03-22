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

import model.Ride;
import repo.RideRepository;

@RestController
public class RideRestController
{

  @Autowired
  private RideRepository rideRepository;

  @RequestMapping(value = "/ride", method = RequestMethod.GET)
  @ResponseBody
  public List<Ride> findAll()
  {
    return (List<Ride>) rideRepository.findAll();
  }

  @RequestMapping(value = "/ride/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Ride findAll(@PathVariable(value = "id") Integer id)
  {
    return (Ride) rideRepository.findById(id).get();
  }

  @RequestMapping(value = "/ride", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<String> saveAddess(@RequestBody Ride ride)
  {
    rideRepository.save(ride);
    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

  @RequestMapping(value = "/ride/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteAddress(
      @PathVariable(value = "id") Integer id)
  {
    try
    {
      rideRepository.deleteById(id);

    } catch (Exception e)
    {
      return new ResponseEntity<String>("FAILURE",
          HttpStatus.EXPECTATION_FAILED);
    }
    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

}
