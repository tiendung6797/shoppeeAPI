package shoppee.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shoppee.com.dto.ReviewDto;
import shoppee.com.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

	@Query(value = "SELECT * FROM review JOIN product ON review.pro_id = product.pro_id WHERE product.store_id = :store_id", nativeQuery = true)
	List<Review> getListReviewByStore(@Param("store_id") Integer store_id);
	
	@Query(value = "SELECT * FROM review WHERE pro_id = :pro_id", nativeQuery = true)
	List<Review> getListReviewByProduct(@Param("pro_id") Integer pro_id);
}
