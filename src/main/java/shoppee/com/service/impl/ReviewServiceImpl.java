package shoppee.com.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppee.com.entities.Review;
import shoppee.com.repository.ReviewRepository;
import shoppee.com.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public List<Review> getALLReviewByStore(Integer store_id) {
		// TODO Auto-generated method stub
		List<Review> listReviewByStore = reviewRepository.getListReviewByStore(store_id);
		return listReviewByStore;
	}

	@Override
	public Review getReviewById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Review> objReview = reviewRepository.findById(id);
		return objReview.get();
	}

	@Override
	public void deleteReview(Integer id) {
		// TODO Auto-generated method stub
		reviewRepository.deleteById(id);
	}
	
}
