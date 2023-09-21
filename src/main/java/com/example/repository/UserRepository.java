package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

@Repository
public class UserRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static RowMapper<User> USER_ROW_MAPPER=(rs,i)->{
		User user=new User();
		user.setId(rs.getInt("id"));
		user.setMail(rs.getString("mail"));
		user.setPassword(rs.getString("password"));
		return user;
	};
	
	/**
	 * ユーザー情報の追加
	 * @param user
	 */
	public void userInsert(User user) {
		String sql="insert into users(mail,password)\r\n"
				+ "values(:mail,:password);";
		SqlParameterSource param=new BeanPropertySqlParameterSource(user);
		template.update(sql, param);		
	}
	
	/**
	 * ユーザー情報の確保
	 * @param mail
	 * @param password
	 * @return
	 */
	public User userLogin(String mail,String password) {
		String sql="select id,mail,password from users where mail=:mail and password=:password;";
		SqlParameterSource param=new MapSqlParameterSource("mail",mail).addValue("password", password);
		User user=template.queryForObject(sql, param, USER_ROW_MAPPER);
		return user;		
	}
}
