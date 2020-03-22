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

import model.Member;
import repo.MemberRepository;

@RestController
public class MemberRestController
{

  @Autowired
  private MemberRepository memberRepository;

  @RequestMapping(value = "/member", method = RequestMethod.GET)
  @ResponseBody
  public List<Member> findAll()
  {
    return (List<Member>) memberRepository.findAll();
  }

  @RequestMapping(value = "/member/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Member findAll(@PathVariable(value = "id") Integer id)
  {
    return (Member) memberRepository.findById(id).get();
  }

  @RequestMapping(value = "/member", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<String> saveAddess(@RequestBody Member member)
  {
    memberRepository.save(member);
    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

  @RequestMapping(value = "/member/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteAddress(
      @PathVariable(value = "id") Integer id)
  {
    try
    {
      memberRepository.deleteById(id);

    } catch (Exception e)
    {
      return new ResponseEntity<String>("FAILURE",
          HttpStatus.EXPECTATION_FAILED);
    }
    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
  }

}
