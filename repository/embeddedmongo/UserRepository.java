package zany.repository.embeddedmongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import zany.repository.entity.User;

@Repository(value = "embeddedUser")
public interface UserRepository extends MongoRepository<User, String> {

    // public List<SystemEntity> find(String hostName);

}
