package shoppee.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shoppee.com.entities.ProductBill;

public interface ProductBillRepository extends JpaRepository<ProductBill, Integer>{
	
	@Query(value = "SELECT * FROM productofbill WHERE user_id = :user_id AND status = \"Đã thanh toán-Đã giao hàng\"", nativeQuery = true)
	List<ProductBill> getProductByUserAndYesMoney(@Param("user_id") Integer user_id);
	
	@Query(value = "SELECT * FROM productofbill WHERE user_id = :user_id AND status = \"Đã thanh toán-Chưa giao hàng\" OR status = \"Chưa thanh toán-Chưa giao hàng\"", nativeQuery = true)
	List<ProductBill> getProductByUserAndNoMoney(@Param("user_id") Integer user_id);
	
	@Query(value = "SELECT * FROM productofbill WHERE user_id = :user_id AND status = \"Đã hủy\"", nativeQuery = true)
	List<ProductBill> getProductByUserAndCancel(@Param("user_id") Integer user_id);
	
	@Query(value = "SELECT * FROM productofbill WHERE store_id = :store_id", nativeQuery = true)
	List<ProductBill> getProductByStore(@Param("store_id") Integer store_id);
	
	@Query(value = "SELECT *, SUM(cost) FROM productofbill GROUP BY bill_number", nativeQuery = true)
	List<ProductBill> getBill();
}