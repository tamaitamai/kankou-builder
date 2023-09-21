package com.example.domain;

public class Tourism {
	private Integer id;
	private String name;
	private String image;
	private String explanation;
	private String prefecture;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getPrefecture() {
		return prefecture;
	}
	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}
	@Override
	public String toString() {
		return "Tourism [id=" + id + ", name=" + name + ", image=" + image + ", explanation=" + explanation
				+ ", prefecture=" + prefecture + "]";
	}
	
}
