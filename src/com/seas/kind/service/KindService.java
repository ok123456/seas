package com.seas.kind.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.seas.kind.bean.Kind;
import com.seas.kind.dao.KindDao;
import com.seas.util.UserReadWriteTransaction;

@Service
public class KindService extends UserReadWriteTransaction {
	@Resource
	private KindDao kindDao;

	public List<Kind> selectKind(String title) {
		return kindDao.selectKind(title);
	}

	public Kind queryKind(Integer id) {
		return kindDao.queryKind(id);
	}

	public Map<String,Boolean>  deleteKind(Integer id) {
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		boolean bol=true;
		try {
			kindDao.deleteKind(id);
		} catch (Exception e) {
			e.printStackTrace();
			bol = false;
		}
	
		map.put("status", bol);
		return map;
	
	}

	public Map<String,Boolean> insertKind(Kind kind) {
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		boolean bol=true;
		try {
			kindDao.insertKind(kind);
		} catch (Exception e) {
			e.printStackTrace();
			bol = false;
		}
	
		map.put("status", bol);
		return map;
		
	}

	public Map<String,Boolean> updateKind(Kind kind) {
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		boolean bol=true;
		try {
			kindDao.updateKind(kind);
		} catch (Exception e) {
			e.printStackTrace();
			bol = false;
		}
	
		map.put("status", bol);
		return map;
		
	}

}
