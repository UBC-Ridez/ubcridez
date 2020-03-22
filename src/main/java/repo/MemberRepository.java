package repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer>
{

}
