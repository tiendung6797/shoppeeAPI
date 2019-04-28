package shoppee.com.service;

import java.util.List;

import shoppee.com.entities.Review;

public interface ReviewService {
	
	List<Review> getALLReviewByStore(Integer store_id);

	Review getReviewById(Integer id);
	
	void deleteReview(Integer id);
	
	List<Review> getListReviewByProduct(Integer pro_id);
}
