package RrsCaseStudy.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RrsCaseStudy.model.Payment;
import RrsCaseStudy.model.Reservation;
import RrsCaseStudy.repository.PaymentRepository;
@Service
public class PaymentService {
@Autowired
private PaymentRepository repository;
	public Payment doPay(Payment payment, Reservation order) {
		
			payment.setStatus(paymentStatus());
			payment.setAmount(order.getTotalTicketFair());
			payment.setTxnId(UUID.randomUUID().toString());
			return repository.save(payment);
	}
	private String paymentStatus() {
		return "success";
	}
	
	public Optional<Payment> getPaymentById(String txnId)
	{
		return repository.findById(txnId);
	}
	public List<Payment> getAll() {
		return repository.findAll();
	}
	public void deletePayment(String txnId) {
		repository.deleteById(txnId);
		
	}

}
