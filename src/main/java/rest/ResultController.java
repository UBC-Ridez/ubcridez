package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.ResultService;

@RestController
@RequestMapping(value =
{ "/api/result" })
@CrossOrigin
public class ResultController
{

  @Autowired
  ResultService resultService;

}
