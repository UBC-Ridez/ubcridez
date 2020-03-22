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

import model.Insurance;
import repo.InsuranceRepository;

@CrossOrigin
@RestController
public class InsuranceRestController
{

  @Autowired
  private InsuranceRepository insuranceRepository;

  @RequestMapping(value = "/insurance", method = RequestMethod.GET)
  @ResponseBody
  public List<Insurance> findAll()
  {
    return (List<Insurance>) insuranceRepository.findAll();
  }

  @RequestMapping(value = "/insurance/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Insurance findAll(@PathVariable(value = "id") Integer id)
  {
    return (Insurance) insuranceRepository.findById(id).get();
  }

  @RequestMapping(value = "/insurance", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<String> saveAddess(@RequestBody Insurance insurance)
  {
    insuranceRepository.save(insurance);
    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

  @RequestMapping(value = "/insurance/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteAddress(
      @PathVariable(value = "id") Integer id)
  {
    try
    {
      insuranceRepository.deleteById(id);

    } catch (Exception e)
    {
      return new ResponseEntity<String>("FAILURE",
          HttpStatus.EXPECTATION_FAILED);
    }
    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

}
