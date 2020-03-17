package repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>
{

  List<Address> findByCity(String city);
}
