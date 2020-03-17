package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import model.Address;
import repo.AddressRepository;

@SpringBootApplication
public class StartApplication implements CommandLineRunner
{

  private static final Logger log = LoggerFactory
      .getLogger(StartApplication.class);

  @Autowired
  private AddressRepository repository;

  public static void main(String[] args)
  {
    SpringApplication.run(StartApplication.class, args);
  }

  @Override
  public void run(String... args)
  {

    log.info("StartApplication...");

    Address address = new Address();
    address.setCity("Vancouver");
    repository.save(address);

    System.out.println("\nfindAll()");
    repository.findAll().forEach(x -> System.out.println(x));

    System.out.println("\nfindById(1L)");
    repository.findById(1).ifPresent(x -> System.out.println(x));

    System.out.println("\nfindByName('Node')");
    repository.findByCity("Vancouver").forEach(x -> System.out.println(x));

  }

}