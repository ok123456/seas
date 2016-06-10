package com.seas.articles.bean;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


public class Articles implements Serializable {
	private Integer id;
	@NotNull(message = "{NotNull.articles.title}")
	@Length(min=1, max=200, message="{Length.articles.title}")
	private String title;// 文章标题
	@NotNull(message = "{NotNull.articles.content}")
	@Length(min=1, message="{Length.articles.content}")
	private String content;// 文章
	private String author;
	private Date create_time;// 文章编写时间
	private Integer accessory;// 附件
	private Integer classify;
	private String details;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getAccessory() {
		return accessory;
	}
	public void setAccessory(Integer accessory) {
		this.accessory = accessory;
	}
	public Integer getClassify() {
		return classify;
	}
	public void setClassify(Integer classify) {
		this.classify = classify;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "Articles [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author
				+ ", create_time=" + create_time + ", accessory=" + accessory + ", classify=" + classify + ", details="
				+ details + "]";
	}


	
}
