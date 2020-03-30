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

import model.Vehicle;
import repo.VehicleRepository;

@CrossOrigin
@RestController
public class VehicleRestController
{

  @Autowired
  private VehicleRepository vehicleRepository;

  @RequestMapping(value = "/vehicle", method = RequestMethod.GET)
  @ResponseBody
  public List<Vehicle> findAll()
  {
    return (List<Vehicle>) vehicleRepository.findAll();
  }

  @RequestMapping(value = "/vehicle/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Vehicle findAll(@PathVariable(value = "id") Integer id)
  {
    return (Vehicle) vehicleRepository.findById(id).get();
  }

  @RequestMapping(value = "/vehicle", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<String> saveAddess(@RequestBody Vehicle vehicle)
  {
    String result = "SUCCESS";
    HttpStatus status = HttpStatus.OK;
    try
    {
      vehicleRepository.save(vehicle);
    } catch (Exception e)
    {
      result = "FAILURE";
      status = HttpStatus.EXPECTATION_FAILED;
    }
    return new ResponseEntity<String>(result, status);
  }

  @RequestMapping(value = "/vehicle/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteAddress(
      @PathVariable(value = "id") Integer id)
  {
    String result = "SUCCESS";
    HttpStatus status = HttpStatus.OK;
    try
    {
      vehicleRepository.deleteById(id);

    } catch (Exception e)
    {
      result = "FAILURE";
      status = HttpStatus.EXPECTATION_FAILED;
    }
    return new ResponseEntity<String>(result, status);
  }

}
