package com.liuchongyang.hgshop.pojo;

import java.io.Serializable;

/**
 * 
 * @ClassName: SpecOption 
 * @Description: 规格选项实体类
 * @author: 86157
 * @date: 2020年3月4日 上午10:05:11
 */
public class SpecOption implements Serializable {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -5423563182084629473L;
	
	private Integer id;
	private String optionName;
	private Integer specId;
	private int orders = 0;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SpecOption [id=" + id + ", optionName=" + optionName + ", specId=" + specId + ", orders=" + orders
				+ "]";
	}
	public SpecOption() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SpecOption(Integer id, String optionName, Integer specId, int orders) {
		super();
		this.id = id;
		this.optionName = optionName;
		this.specId = specId;
		this.orders = orders;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((optionName == null) ? 0 : optionName.hashCode());
		result = prime * result + orders;
		result = prime * result + ((specId == null) ? 0 : specId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpecOption other = (SpecOption) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (optionName == null) {
			if (other.optionName != null)
				return false;
		} else if (!optionName.equals(other.optionName))
			return false;
		if (orders != other.orders)
			return false;
		if (specId == null) {
			if (other.specId != null)
				return false;
		} else if (!specId.equals(other.specId))
			return false;
		return true;
	}
}
