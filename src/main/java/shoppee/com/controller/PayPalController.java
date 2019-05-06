package shoppee.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import shoppee.com.entities.PayPalClient;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/paypal")
public class PayPalController {

	private final PayPalClient payPalClient;

	@Autowired
	PayPalController(PayPalClient payPalClient) {
		this.payPalClient = payPalClient;
	}

	@PostMapping(value = "/make/payment")
	public Map<String, Object> makePayment(@RequestParam("sum") String sum) {
		return payPalClient.createPayment(sum);
	}

	@PostMapping(value = "/complete/payment")
	public Map<String, Object> completePayment(HttpServletRequest request, @RequestParam("paymentId") String paymentId,
			@RequestParam("payerId") String payerId) {
		return payPalClient.completePayment(request);
	}

}