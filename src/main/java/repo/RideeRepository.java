package repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Ridee;

@Repository
public interface RideeRepository extends CrudRepository<Ridee, Integer>
{

}
