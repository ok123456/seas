package com.seas.articles.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.seas.articles.bean.Articles;

public interface ArticlesDao {

	public List<Articles> selectArticles(@Param("title") String title);

	public Articles queryArticles(@Param("id") Integer id);

	public void deleteArticles(@Param("id") Integer id);

	public void insertArticles(Articles articles);

	public void updateArticles(Articles articles);
}
