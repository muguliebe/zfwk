package zany.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import zany.model.framework.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
