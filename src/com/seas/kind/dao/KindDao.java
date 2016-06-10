package com.seas.kind.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.seas.kind.bean.Kind;

public interface KindDao {

	public List<Kind> selectKind(@Param("title") String title);

	public Kind queryKind(@Param("id") Integer id);

	public void deleteKind(@Param("id") Integer id);

	public void insertKind(Kind kind);

	public void updateKind(Kind kind);
}
