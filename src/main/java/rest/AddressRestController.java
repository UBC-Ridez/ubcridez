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

import model.Address;
import repo.AddressRepository;

@CrossOrigin
@RestController
public class AddressRestController
{

  @Autowired
  private AddressRepository addressRepository;

  @RequestMapping(value = "/address", method = RequestMethod.GET)
  @ResponseBody
  public List<Address> findAll()
  {
    return (List<Address>) addressRepository.findAll();
  }

  @RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Address findAll(@PathVariable(value = "id") Integer id)
  {
    return (Address) addressRepository.findById(id).get();
  }

  @RequestMapping(value = "/address/{city}", method = RequestMethod.GET)
  @ResponseBody
  public List<Address> findByCity(@PathVariable(value = "city") String city)
  {
    return addressRepository.findByCity(city);
  }
  
  @RequestMapping(value = "/address", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<String> saveAddess(@RequestBody Address address)
  {
    String result = "SUCCESS";
    HttpStatus status = HttpStatus.OK;
    try
    {
      addressRepository.save(address);
    } catch (Exception e)
    {
      result = "FAILURE";
      status = HttpStatus.EXPECTATION_FAILED;
    }
    return new ResponseEntity<String>(result, status);
  }

  @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteAddress(
      @PathVariable(value = "id") Integer id)
  {
    String result = "SUCCESS";
    HttpStatus status = HttpStatus.OK;
    try
    {
      addressRepository.deleteById(id);
    } catch (Exception e)
    {
      result = "FAILURE";
      status = HttpStatus.EXPECTATION_FAILED;
    }
    return new ResponseEntity<String>(result, status);
  }

}
