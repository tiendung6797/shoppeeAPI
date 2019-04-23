package shoppee.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shoppee.com.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query(value ="SELECT * FROM product p WHERE p.pro_id = :id", nativeQuery=true)
	Product getProductById(@Param("id") Integer id);
}
