package shoppee.com.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shoppee.com.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	/*@Query(value ="SELECT * FROM product p", nativeQuery=true)
	Product getAllProduct();*/
	
	@Query(value ="SELECT * FROM product p WHERE p.pro_id = :id", nativeQuery=true)
	Product getProductById(@Param("id") Integer id);
	
	@Query("SELECT e FROM Product e")
	List<Product> getProductPagination(Pageable pageable);
	
	@Query(value ="SELECT * FROM product p WHERE p.sale_product = 1 ORDER BY count_view DESC", nativeQuery=true)
	List<Product> getAllSaleProduct();
	
	@Query("SELECT e FROM Product e WHERE e.sale_product = 1")
	List<Product> getSaleProductPagination(Pageable pageable);
	
	@Query(value ="SELECT * FROM product p WHERE p.hot_product = 1 ORDER BY count_selled DESC", nativeQuery=true)
	List<Product> getAllHotProduct();
	
	@Query("SELECT e FROM Product e WHERE e.hot_product = 1")
	List<Product> getHotProductPagination(Pageable pageable);
	
	@Query(value ="SELECT e FROM Product e WHERE e.store_id = :storeId")
	List<Product> getStoreProductPagination(Pageable pageable, @Param("storeId") Integer storeId);
	
	@Query(value ="SELECT e FROM Product e WHERE e.store_id = :storeId AND e.pro_id <> :proId")
	List<Product> getStoreProductPagination(Pageable pageable, @Param("storeId") Integer storeId, @Param("proId") Integer proId);
	
	
}
