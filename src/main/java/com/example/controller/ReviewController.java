package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Review;
import com.example.domain.Tourism;
import com.example.domain.User;
import com.example.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("")
	public String review() {
		User user=(User)session.getAttribute("userLogin");
		Tourism tourism=(Tourism) session.getAttribute("tourismDetail");
		if(reviewService.reviewLoad(user.getId(), tourism.getId())!=null) {
			session.setAttribute("review", reviewService.reviewLoad(user.getId(), tourism.getId()));
		}else {
			session.removeAttribute("review");
		}
		return "/review/review.html";
	}
	
	/**
	 * レビューを追加する
	 * @param review
	 * @return
	 */
	@PostMapping("/addReview")
	public String addReview(Review review) {
		User user=(User)session.getAttribute("userLogin");
		Tourism tourism=(Tourism) session.getAttribute("tourismDetail");
		review.setUserId(user.getId());
		review.setTourismId(tourism.getId());
		reviewService.reviewInsert(review);
		session.setAttribute("reviewList", reviewService.reviewList(tourism.getId()));
		session.setAttribute("reviewExists", reviewService.reviewExists(user.getId(), tourism.getId()));			
		return "/main/tourism-detail.html";
	}
}
