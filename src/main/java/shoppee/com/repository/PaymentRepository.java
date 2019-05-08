package shoppee.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shoppee.com.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
