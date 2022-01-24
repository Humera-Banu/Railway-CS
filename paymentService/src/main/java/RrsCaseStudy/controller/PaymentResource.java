package RrsCaseStudy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import RrsCaseStudy.Service.PaymentService;
import RrsCaseStudy.model.Payment;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/pay")
public class PaymentResource {
	@Autowired
	private RestTemplate restTemplate ;
	@Autowired
	public PaymentService service;
	
	@PostMapping("/payment")
	public Payment doPayment(@RequestBody Payment payment) {
		int rId = payment.getrId();
		System.out.println(rId);
		RrsCaseStudy.model.Reservation order =  restTemplate.exchange("http://localhost:8083/reservation/get/"+ rId,HttpMethod.GET,null,RrsCaseStudy.model.Reservation.class).getBody(); 
		return service.doPay(payment,order);
		
	}
	@GetMapping("/payment")
	public List<Payment> getAll() {
		return service.getAll();
	}
	@GetMapping("/payment/{txnId}")
	public Optional<Payment> getPayments(@PathVariable String txnId)
	{
		return service.getPaymentById(txnId);
	}
	@DeleteMapping("/delete/{txnId}")
		public void deletePay(@PathVariable ("txnId") String txnId)
		{
			service.deletePayment(txnId);
		}
	}

