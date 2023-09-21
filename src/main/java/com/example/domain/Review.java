package com.example.domain;

public class Review {
	private Integer id;
	private Integer tourismId;
	private Integer userId;
	private Integer rank;
	private String name;
	private String comment;
		
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(Integer tourismId, Integer userId, Integer rank, String name, String comment) {
		super();
		this.tourismId = tourismId;
		this.userId = userId;
		this.rank = rank;
		this.name = name;
		this.comment = comment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTourismId() {
		return tourismId;
	}

	public void setTourismId(Integer tourismId) {
		this.tourismId = tourismId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", tourismId=" + tourismId + ", userId=" + userId + ", rank=" + rank + ", name="
				+ name + ", comment=" + comment + "]";
	}
}
