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
@Table(name="kind")
public class Kind
{
	//
	@Id
	@Column(name="kind_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	//
	@Column(name="kind_name")
	private String kindName;
	// 
	@Column(name="kind_desc")
	private String kindDesc;
	//
	@OneToMany(targetEntity=Item.class ,
		mappedBy="kind")
	private Set<Item> items = new HashSet<Item>();

	// 
	public Kind()
	{
	}
	// 
	public Kind(String kindName , String kindDesc)
	{
		this.kindName = kindName;
		this.kindDesc = kindDesc;
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
	public void setKindName(String kindName)
	{
		this.kindName = kindName;
	}
	public String getKindName()
	{
		return this.kindName;
	}

	//
	public void setKindDesc(String kindDesc)
	{
		this.kindDesc = kindDesc;
	}
	public String getKindDesc()
	{
		return this.kindDesc;
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