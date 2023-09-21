package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Tourism;

@Repository
public class TourismRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static RowMapper<Tourism> TOURISM_ROW_MAPPER=(rs,i)->{
		Tourism tourism=new Tourism();
		tourism.setId(rs.getInt("id"));
		tourism.setName(rs.getString("name"));
		tourism.setImage(rs.getString("image"));
		tourism.setExplanation(rs.getString("explanation"));
		tourism.setPrefecture(rs.getString("prefecture"));
		return tourism;
	};
	
	/**
	 * 県が一致する観光地一覧
	 * @param prefecture
	 * @return
	 */
	public List<Tourism> tourismByPrefecture(String prefecture){
		String sql="select id,name,image,explanation,prefecture from tourisms where prefecture like :prefecture;";
		SqlParameterSource param=new MapSqlParameterSource("prefecture","%"+prefecture+"%");
		List<Tourism> tourismList=template.query(sql, param, TOURISM_ROW_MAPPER);
		return tourismList;
	}
	
	/**
	 * idの一致する観光地の抜き出し
	 * @param id
	 * @return
	 */
	public Tourism tourismById(Integer id) {
		String sql="select id,name,image,explanation,prefecture from tourisms where id=:id;";
		SqlParameterSource param=new MapSqlParameterSource("id",id);
		Tourism tourism=template.queryForObject(sql, param,TOURISM_ROW_MAPPER);
		return tourism;
	}
}
