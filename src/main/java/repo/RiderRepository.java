package repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Rider;

@Repository
public interface RiderRepository extends CrudRepository<Rider, Integer>
{

}
