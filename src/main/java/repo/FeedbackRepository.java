package repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Feedback;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Integer>
{

}
