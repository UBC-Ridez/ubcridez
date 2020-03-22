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

import model.Preference;
import repo.PreferenceRepository;

@RestController
public class PreferenceRestController
{

  @Autowired
  private PreferenceRepository preferenceRepository;

  @RequestMapping(value = "/preference", method = RequestMethod.GET)
  @ResponseBody
  public List<Preference> findAll()
  {
    return (List<Preference>) preferenceRepository.findAll();
  }

  @RequestMapping(value = "/preference/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Preference findAll(@PathVariable(value = "id") Integer id)
  {
    return (Preference) preferenceRepository.findById(id).get();
  }

  @RequestMapping(value = "/preference", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<String> saveAddess(@RequestBody Preference preference)
  {
    preferenceRepository.save(preference);
    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

  @RequestMapping(value = "/preference/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteAddress(
      @PathVariable(value = "id") Integer id)
  {
    try
    {
      preferenceRepository.deleteById(id);

    } catch (Exception e)
    {
      return new ResponseEntity<String>("FAILURE",
          HttpStatus.EXPECTATION_FAILED);
    }
    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

}
