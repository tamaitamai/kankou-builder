package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Tourism;
import com.example.repository.TourismRepository;

@Service
@Transactional
public class TourismService {
	@Autowired
	private TourismRepository tourismRepository;
	
	/**
	 * 県が一致する観光地一覧
	 * @param prefecture
	 * @return
	 */
	public List<Tourism> tourismByPrefecture(String prefecture){
		return tourismRepository.tourismByPrefecture(prefecture);
	}
	
	/**
	 * idの一致する観光地の抜き出し
	 * @param id
	 * @return
	 */
	public Tourism tourismById(Integer id) {
		return tourismRepository.tourismById(id);
	}

}
