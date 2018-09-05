package com.tcs.KingfisherDay.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FLAG")
public class Flag {
	@Id
	@Column(name = "KEY", nullable = false)
	private String key;
	@Column(name = "VALUE", nullable = false)
	private boolean value;

	public Flag() {

	}

	public Flag(String key, boolean value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Flag [key=" + key + ", value=" + value + "]";
	}

}
