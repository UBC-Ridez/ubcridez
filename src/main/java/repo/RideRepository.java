package repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Ride;

@Repository
public interface RideRepository extends CrudRepository<Ride, Integer>
{

}
