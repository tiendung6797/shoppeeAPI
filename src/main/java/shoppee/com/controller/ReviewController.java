package shoppee.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import shoppee.com.dto.ReviewDto;
import shoppee.com.entities.Review;
import shoppee.com.service.impl.ReviewServiceImpl;
import shoppee.com.utils.ConvertReview;
import shoppee.com.utils.TokenResult;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("review")
public class ReviewController {

	@Autowired
	private ReviewServiceImpl reviewService;

	@GetMapping("allByStore/{store_id}")
	public ResponseEntity<List<Review>> getAllByStore(@PathVariable(value = "store_id") Integer store_id) {
		List<Review> listReview = reviewService.getALLReviewByStore(store_id);
		if (listReview.size() > 0) {
			return new ResponseEntity<List<Review>>(listReview, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("{id}")
	public ResponseEntity<Review> getReviewById(@PathVariable(value = "id") Integer id) {
		if (reviewService.getReviewById(id) == null) {
			TokenResult error = new TokenResult("False", "Không tìm thấy review!!");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		} else {
			Review objReview = reviewService.getReviewById(id);
			return new ResponseEntity<Review>(objReview, HttpStatus.OK);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Review> deleteReview(@PathVariable(value = "id") Integer id) {
		Review objReview = reviewService.getReviewById(id);
		if (objReview == null) {
			TokenResult error = new TokenResult("False", "Không tìm thấy review!!");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		} else {
			reviewService.deleteReview(id);
			TokenResult result = new TokenResult("True", "Xóa review thành công!!");
			return new ResponseEntity(result, HttpStatus.OK);
		}
	}

	@GetMapping("allByProduct/{pro_id}")
	public ResponseEntity<List<ReviewDto>> getAllByProduct(@PathVariable(value = "pro_id") Integer pro_id, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
		      @RequestParam(name = "size", required = false, defaultValue = "8") Integer size,
		      @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort) {
		Sort sortable = null;
		if(sort.equals("ASC")) {
			sortable = Sort.by("review_id").ascending();
		}
		if(sort.equals("DESC")) {
			sortable = Sort.by("review_id").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);
		List<ReviewDto> listReviewDto = ConvertReview
				.ListReviewToListReviewDto(reviewService.getListReviewByProduct(pageable, pro_id));
		if (listReviewDto.size() > 0) {
			return new ResponseEntity<List<ReviewDto>>(listReviewDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
