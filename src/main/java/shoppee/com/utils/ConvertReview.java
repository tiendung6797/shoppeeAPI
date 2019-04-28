package shoppee.com.utils;

import java.util.ArrayList;
import java.util.List;

import shoppee.com.dto.ReviewDto;
import shoppee.com.entities.Review;

public class ConvertReview {
	
	public static List<ReviewDto> ListReviewToListReviewDto(List<Review> listReview){
		List<ReviewDto> listReviewDto = new ArrayList<ReviewDto>();
		for(Review objReview : listReview) {
			listReviewDto.add(new ReviewDto(objReview.getReview_id(), objReview.getUser(), objReview.getStar_number(), objReview.getDetail(), objReview.getActive()));
		}
		return listReviewDto;
	}

}
