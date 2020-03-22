package repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Integer>
{

}
