package zany.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import zany.repository.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

    // public List<SystemEntity> find(String hostName);

}
