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

import model.Ridee;
import repo.RideeRepository;

@CrossOrigin
@RestController
public class RideeRestController
{

  @Autowired
  private RideeRepository rideeRepository;

  @RequestMapping(value = "/ridee", method = RequestMethod.GET)
  @ResponseBody
  public List<Ridee> findAll()
  {
    return (List<Ridee>) rideeRepository.findAll();
  }

  @RequestMapping(value = "/ridee/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Ridee findAll(@PathVariable(value = "id") Integer id)
  {
    return (Ridee) rideeRepository.findById(id).get();
  }

  @RequestMapping(value = "/ridee", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<String> saveAddess(@RequestBody Ridee ridee)
  {
    rideeRepository.save(ridee);
    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

  @RequestMapping(value = "/ridee/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteAddress(
      @PathVariable(value = "id") Integer id)
  {
    try
    {
      rideeRepository.deleteById(id);

    } catch (Exception e)
    {
      return new ResponseEntity<String>("FAILURE",
          HttpStatus.EXPECTATION_FAILED);
    }
    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

}
