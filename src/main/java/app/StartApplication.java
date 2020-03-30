package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import repo.AddressRepository;

@SpringBootApplication
@EntityScan(
{ "model" })
@ComponentScan(
{ "service", "service.impl", "rest" })
@EnableJpaRepositories("repo")
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


//    System.out.println("\nfindAll()");
//    repository.findAll().forEach(x -> System.out.println(x));
//
//    System.out.println("\nfindById(1L)");
//    repository.findById(1).ifPresent(x -> System.out.println(x));
//
//    System.out.println("\nfindByName('Node')");
//    repository.findByCity("Vancouver").forEach(x -> System.out.println(x));

  }

}