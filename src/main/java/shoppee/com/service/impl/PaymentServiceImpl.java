package shoppee.com.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppee.com.entities.Payment;
import shoppee.com.repository.PaymentRepository;
import shoppee.com.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Payment getPaymentById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Payment> objPayment = paymentRepository.findById(id);
		return objPayment.get();
	}
}
