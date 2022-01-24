package RrsCaseStudy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import RrsCaseStudy.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {

}
