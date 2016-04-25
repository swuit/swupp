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
@Table(name="item")
public class Item
{
	//
	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	//
	@Column(name="item_remark")
	private String itemRemark;
	//
	@Column(name="item_name")
	private String itemName;
	// 
	@Column(name="item_desc")
	private String itemDesc;
	// 
	private Date addtime;
	//
	private Date endtime;
	//
	@Column(name="init_price")
	private double initPrice;
	//
	@Column(name="max_price")
	private double maxPrice;
	//
	@ManyToOne(targetEntity=AuctionUser.class)
	@JoinColumn(name="owner_id", nullable=false)
	private AuctionUser owner;
	//
	@ManyToOne(targetEntity=Kind.class)
	@JoinColumn(name="kind_id", nullable=false)
	private Kind kind;
	//
	@ManyToOne(targetEntity=AuctionUser.class)
	@JoinColumn(name="winer_id", nullable=true)
	private AuctionUser winer;
	//
	@ManyToOne(targetEntity=State.class)
	@JoinColumn(name="state_id", nullable=false)
	private State itemState;
	//
	@OneToMany(targetEntity=Bid.class ,
		mappedBy="bidItem")
	private Set<Bid> bids = new HashSet<Bid>();

	//
	public Item()
	{
	}
	//
	public Item( String itemName , String itemDesc
		, String itemRemark , double initPrice)
	{
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.itemRemark = itemRemark;
		this.initPrice = initPrice;
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
	public void setItemRemark(String itemRemark)
	{
		this.itemRemark = itemRemark;
	}
	public String getItemRemark()
	{
		return this.itemRemark;
	}

	// 
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	public String getItemName()
	{
		return this.itemName;
	}

	// 
	public void setItemDesc(String itemDesc)
	{
		this.itemDesc = itemDesc;
	}
	public String getItemDesc()
	{
		return this.itemDesc;
	}

	// 
	public void setAddtime(Date addtime)
	{
		this.addtime = addtime;
	}
	public Date getAddtime()
	{
		return this.addtime;
	}

	// 
	public void setEndtime(Date endtime)
	{
		this.endtime = endtime;
	}
	public Date getEndtime()
	{
		return this.endtime;
	}

	// 
	public void setInitPrice(double initPrice)
	{
		this.initPrice = initPrice;
	}
	public double getInitPrice()
	{
		return this.initPrice;
	}

	//
	public void setMaxPrice(double maxPrice)
	{
		this.maxPrice = maxPrice;
	}
	public double getMaxPrice()
	{
		return this.maxPrice;
	}

	// 
	public void setOwner(AuctionUser owner)
	{
		this.owner = owner;
	}
	public AuctionUser getOwner()
	{
		return this.owner;
	}

	// 
	public void setKind(Kind kind)
	{
		this.kind = kind;
	}
	public Kind getKind()
	{
		return this.kind;
	}

	//
	public void setWiner(AuctionUser winer)
	{
		this.winer = winer;
	}
	public AuctionUser getWiner()
	{
		return this.winer;
	}

	// 
	public void setItemState(State itemState)
	{
		this.itemState = itemState;
	}
	public State getItemState()
	{
		return this.itemState;
	}

	//
	public void setBids(Set<Bid> bids)
	{
		this.bids = bids;
	}
	public Set<Bid> getBids()
	{
		return this.bids;
	}
}