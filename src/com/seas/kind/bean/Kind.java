package com.seas.kind.bean;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


public class Kind implements Serializable {
	private Integer id;
	
	@NotNull(message = "{NotNull.Kind.name}")
	@Length(min=1, max=20, message="{Length.Kind.name}")
	private String name;
	
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
	
	

	
}
