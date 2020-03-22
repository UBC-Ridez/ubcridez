package repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>
{
  List<Address> findByCity(String city);
}
