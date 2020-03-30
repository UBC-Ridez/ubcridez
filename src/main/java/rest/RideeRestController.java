package rest;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

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


  @PersistenceUnit
  private EntityManagerFactory entityManagerFactory;
  
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
    String result = "SUCCESS";
    HttpStatus status = HttpStatus.OK;
    try
    {
      rideeRepository.save(ridee);
    } catch (Exception e)
    {
      result = "FAILURE";
      status = HttpStatus.EXPECTATION_FAILED;
    }
    return new ResponseEntity<String>(result, status);
  }

  @RequestMapping(value = "/ridee/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteAddress(
      @PathVariable(value = "id") Integer id)
  {
    String result = "SUCCESS";
    HttpStatus status = HttpStatus.OK;
    try
    {
      rideeRepository.deleteById(id);
    } catch (Exception e)
    {
      result = "FAILURE";
      status = HttpStatus.EXPECTATION_FAILED;
    }
    return new ResponseEntity<String>(result, status);
  }
}
