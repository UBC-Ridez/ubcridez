package repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Preference;

@Repository
public interface PreferenceRepository extends CrudRepository<Preference, Integer>
{

}
