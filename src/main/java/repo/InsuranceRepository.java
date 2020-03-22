package repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Insurance;

@Repository
public interface InsuranceRepository extends CrudRepository<Insurance, Integer>
{

}
