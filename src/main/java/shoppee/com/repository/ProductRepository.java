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
	
	@Query(value ="SELECT * FROM product p WHERE p.active = 1", nativeQuery=true)
	List<Product> getAllProductPublic();
	
	@Query(value ="SELECT * FROM product p WHERE p.pro_id = :id", nativeQuery=true)
	Product getProductById(@Param("id") Integer id);
	
	@Query("SELECT e FROM Product e")
	List<Product> getProductPaginationAdmin(Pageable pageable);
	
	@Query("SELECT e FROM Product e WHERE e.active = 1")
	List<Product> getProductPagination(Pageable pageable);
	
	@Query(value ="SELECT * FROM product p WHERE p.sale_product = 1 ORDER BY count_view DESC", nativeQuery=true)
	List<Product> getAllSaleProduct();
	
	@Query("SELECT e FROM Product e WHERE e.sale_product = 1 AND e.active = 1")
	List<Product> getSaleProductPagination(Pageable pageable);
	
	@Query(value ="SELECT * FROM product p WHERE p.hot_product = 1 ORDER BY count_selled DESC", nativeQuery=true)
	List<Product> getAllHotProduct();
	
	@Query("SELECT e FROM Product e WHERE e.hot_product = 1 AND e.active = 1")
	List<Product> getHotProductPagination(Pageable pageable);
	
	@Query(value ="SELECT e FROM Product e WHERE e.store_id = :storeId")
	List<Product> getStoreProductPaginationAdmin(Pageable pageable, @Param("storeId") Integer storeId);
	
	@Query(value ="SELECT e FROM Product e WHERE e.store_id = :storeId AND e.active = 1")
	List<Product> getStoreProductPagination(Pageable pageable, @Param("storeId") Integer storeId);
	
	@Query(value ="SELECT e FROM Product e WHERE e.store_id = :storeId AND e.pro_id <> :proId AND e.active = 1")
	List<Product> getStoreProductPagination(Pageable pageable, @Param("storeId") Integer storeId, @Param("proId") Integer proId);

	@Query(value ="SELECT e FROM Product e WHERE e.cat_id = :catId AND e.active = 1")
	List<Product> getStoreProductByCatId(Pageable pageable, @Param("catId") Integer catId);
	
	
}
