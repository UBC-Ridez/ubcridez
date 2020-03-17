package com.mkyong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import model.Address;
import repo.AddressRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest
{

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private AddressRepository repository;

  @Test
  public void testFindByName()
  {
    Address address = new Address();
    address.setCity("HEHE");
    entityManager.persist(address);

    List<Address> addresses = repository.findByCity("HEHE");
    assertEquals(1, addresses.size());

    assertThat(addresses).extracting(Address::getCity).containsOnly("C++");

  }

}
