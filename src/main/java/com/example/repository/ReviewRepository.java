package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Review;

@Repository
public class ReviewRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static RowMapper<Review> REVIEW_ROW_MAPPER=(rs,i)->{
		Review review=new Review();
		review.setId(rs.getInt("id"));
		review.setTourismId(rs.getInt("tourism_id"));
		review.setUserId(rs.getInt("user_id"));
		review.setRank(rs.getInt("rank"));
		review.setName(rs.getString("name"));
		review.setComment(rs.getString("comment"));
		return review;
	};
	
	/**
	 * レビューの追加
	 * @param review
	 */
	public void reviewInsert(Review review) {
		String sql="";
		if(reviewExists(review.getUserId(), review.getTourismId())) {
			sql="update reviews set rank=:rank,name=:name,comment=:comment "
					+ "where user_id=:userId and tourism_id=:tourismId;";			
		}else {
			sql="INSERT INTO reviews(tourism_id,user_id,rank,name,comment)\r\n"
					+ "VALUES(:tourismId,:userId,:rank,:name,:comment);";			
		}
		SqlParameterSource param=new BeanPropertySqlParameterSource(review);
		template.update(sql, param);
	}
	
	/**
	 * レビューの更新
	 * @param review
	 */
	public void reviewUpdate(Review review) {
		String sql="update reviews set rank=2,name='あ',comment='い' "
				+ "where user_id=:userId and tourism_id=:tourismId;";
		SqlParameterSource param=new BeanPropertySqlParameterSource(review);
		template.update(sql, param);
	}
	
	/**
	 * 各観光地のレビューの一覧を取り出し
	 * @param tourismId
	 * @return
	 */
	public List<Review> reviewList(Integer tourismId){
		String sql="select id,tourism_id,user_id,rank,name,comment from reviews where tourism_id=:tourismId order by id;";		
		SqlParameterSource param=new MapSqlParameterSource("tourismId",tourismId);
		List<Review> reviewList=template.query(sql, param,REVIEW_ROW_MAPPER);
		return reviewList;
	}
	
	/**
	 * レビュー情報の確保
	 * @param userId
	 * @param tourismId
	 * @return
	 */
	public Review reviewLoad(Integer userId,Integer tourismId) {
		String sql="select id,tourism_id,user_id,rank,name,comment from reviews "
				+ "where user_id=:userId and tourism_id=:tourismId order by id;";
		SqlParameterSource param=new MapSqlParameterSource("userId",userId).addValue("tourismId", tourismId);
		List<Review> reviewList=template.query(sql, param,REVIEW_ROW_MAPPER);
		if(reviewList.size()==0) {
			return null;
		}
		return reviewList.get(0);
	}
	
	/**
	 * レビューが投稿済みかを判別
	 * @param userId
	 * @param tourismId
	 * @return
	 */
	public boolean reviewExists(Integer userId,Integer tourismId) {
		String sql="select exists(select from reviews where user_id=:userId and tourism_id=:tourismId);";
		SqlParameterSource param=new MapSqlParameterSource("userId",userId).addValue("tourismId", tourismId);
		boolean exists=template.queryForObject(sql, param, boolean.class);
		return exists;
	}
}
