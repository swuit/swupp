package org.crazyit.auction.domain;

import java.util.*;

import javax.persistence.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
@Entity
@Table(name="state")
public class State
{
	//
	@Id
	@Column(name="state_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	//
	@Column(name="state_name")
	private String stateName;
	//
	@OneToMany(targetEntity=Item.class ,
		mappedBy="itemState")
	private Set<Item> items = new HashSet<Item>();

	// 
	public State()
	{
	}
	//
	public State(Integer id , String stateName)
	{
		this.id = id;
		this.stateName = stateName;
	}

	//
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
	}

	// 
	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}
	public String getStateName()
	{
		return this.stateName;
	}

	//
	public void setItems(Set<Item> items)
	{
		this.items = items;
	}
	public Set<Item> getItems()
	{
		return this.items;
	}
}