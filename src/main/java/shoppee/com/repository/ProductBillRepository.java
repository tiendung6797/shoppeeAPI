package shoppee.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shoppee.com.entities.ProductBill;

@Transactional
@Repository
public interface ProductBillRepository extends JpaRepository<ProductBill, Integer>, CrudRepository<ProductBill, Integer>{
	
	@Query(value = "SELECT * FROM productofbill WHERE user_id = :user_id AND status = \"Đã thanh toán-Đã giao hàng\"", nativeQuery = true)
	List<ProductBill> getProductByUserAndYesMoney(@Param("user_id") Integer user_id);
	
	@Query(value = "SELECT * FROM productofbill WHERE user_id = :user_id AND status = \"Đã thanh toán-Chưa giao hàng\" OR user_id = :user_id AND status = \"Chưa thanh toán-Chưa giao hàng\"", nativeQuery = true)
	List<ProductBill> getProductByUserAndNoMoney(@Param("user_id") Integer user_id);
	
	@Query(value = "SELECT * FROM productofbill WHERE user_id = :user_id AND status = \"Đã hủy\"", nativeQuery = true)
	List<ProductBill> getProductByUserAndCancel(@Param("user_id") Integer user_id);
	
	@Query(value = "SELECT * FROM productofbill WHERE store_id = :store_id", nativeQuery = true)
	List<ProductBill> getProductByStore(@Param("store_id") Integer store_id);
	
	@Query(value = "SELECT * FROM productofbill GROUP BY bill_number", nativeQuery = true)
	List<ProductBill> getBill();
	
	@Modifying
	@Query(value = "UPDATE ProductBill SET status = 'Đã hủy' WHERE bill_number = :bill_number")
	void  updateBillCancel(@Param("bill_number") String bill_number);
	
	@Modifying
	@Query(value = "UPDATE ProductBill SET status = 'Đã thanh toán-Đã giao hàng' WHERE bill_number = :bill_number")
	void  updateBillSuccess(@Param("bill_number") String bill_number);
	
}