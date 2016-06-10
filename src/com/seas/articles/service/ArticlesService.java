package com.seas.articles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.seas.articles.bean.Articles;
import com.seas.articles.dao.ArticlesDao;
import com.seas.util.UserReadWriteTransaction;

@Service
public class ArticlesService extends UserReadWriteTransaction {
	@Resource
	private ArticlesDao articlesDao;

	public List<Articles> selectArticles(String title) {
		return articlesDao.selectArticles(title);
	}

	public Articles queryArticles(Integer id) {
		return articlesDao.queryArticles(id);
	}

	public Map<String,Boolean>  deleteArticles(Integer id) {
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		boolean bol=true;
		try {
			articlesDao.deleteArticles(id);
		} catch (Exception e) {
			e.printStackTrace();
			bol = false;
		}
	
		map.put("status", bol);
		return map;
	
	}

	public Map<String,Boolean> insertArticles(Articles articles) {
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		boolean bol=true;
		try {
			articlesDao.insertArticles(articles);
		} catch (Exception e) {
			e.printStackTrace();
			bol = false;
		}
	
		map.put("status", bol);
		return map;
		
	}

	public Map<String,Boolean> updateArticles(Articles articles) {
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		boolean bol=true;
		try {
			articlesDao.updateArticles(articles);
		} catch (Exception e) {
			e.printStackTrace();
			bol = false;
		}
	
		map.put("status", bol);
		return map;
		
	}

}
