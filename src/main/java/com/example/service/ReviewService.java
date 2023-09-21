package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Review;
import com.example.repository.ReviewRepository;

@Service
@Transactional
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;
	
	/**
	 * レビューの追加
	 * @param review
	 */
	public void reviewInsert(Review review) {
		reviewRepository.reviewInsert(review);
	}
	
	/**
	 * レビューの更新
	 * @param review
	 */
	public void reviewUpdate(Review review) {
		reviewRepository.reviewUpdate(review);
	}
	
	/**
	 * 各観光地のレビューを取り出し
	 * @param tourismId
	 * @return
	 */
	public List<Review> reviewList(Integer tourismId){
		return reviewRepository.reviewList(tourismId);
	}
	
	/**
	 * レビュー情報の確保
	 * @param userId
	 * @param tourismId
	 * @return
	 */
	public Review reviewLoad(Integer userId,Integer tourismId) {
		return reviewRepository.reviewLoad(userId, tourismId);
	}

	/**
	 * レビューが投稿済みかを判別
	 * @param userId
	 * @param tourismId
	 * @return
	 */
	public boolean reviewExists(Integer userId,Integer tourismId) {
		return reviewRepository.reviewExists(userId, tourismId);
	}
}
