package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Review;
import com.example.domain.Tourism;
import com.example.domain.User;
import com.example.service.ReviewService;
import com.example.service.TourismService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/tourism")
public class TourismController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private TourismService tourismService;
	
	@Autowired
	private ReviewService reviewService;
	
	/**
	 * 観光地一覧
	 * @return
	 */
	@GetMapping("")
	public String tourism() {
		List<Tourism> tourismList=tourismService.tourismByPrefecture("");
		
		//県一覧確保
		Map<String,Integer> prefectureMap=new HashMap<>();		
		for(int i=0;i<tourismList.size();i++) {
			prefectureMap.put(tourismList.get(i).getPrefecture(),i);
		}
		
		List<String> prefectureList=new ArrayList<>();
		for(String prefecture:prefectureMap.keySet()) {
			prefectureList.add(prefecture);
		}
		
		session.setAttribute("prefectureList", prefectureList);
		session.setAttribute("tourismList", tourismList);
		return "main/tourism.html";
	}
	
	/**
	 * ホーム画面に移動
	 * @return
	 */
	@GetMapping("/home")
	public String home() {
		return "main/home.html";
	}
	
	/**
	 * 観光地詳細を表示する
	 * @param id
	 * @return
	 */
	@PostMapping("/tourismDetail")
	public String tourismDetail(Integer id) {
		List<Review> reviewList= reviewService.reviewList(id);
		Double totalRank=0.0;
		for(int i=0;i<reviewList.size();i++) {
			totalRank+=reviewList.get(i).getRank();
		}
		Double averageRank=totalRank/reviewList.size();
		session.setAttribute("averageRank", averageRank);
		
		User user=(User) session.getAttribute("userLogin");
		session.setAttribute("reviewList", reviewList);
		session.setAttribute("reviewExists", reviewService.reviewExists(user.getId(), id));
		session.setAttribute("tourismDetail", tourismService.tourismById(id));
		return "/main/tourism-detail.html";
	}
		
	/**
	 * 県で観光地を絞り込む
	 * @param prefecture
	 * @return
	 */
	@PostMapping("/prefectureSelect")
	@ResponseBody
	public List<Tourism> prefectureSelect(@RequestParam("prefecture") String prefecture){
		return tourismService.tourismByPrefecture(prefecture);
	}

}
