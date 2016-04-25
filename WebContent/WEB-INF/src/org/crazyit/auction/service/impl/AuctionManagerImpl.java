package org.crazyit.auction.service.impl;

import org.apache.log4j.Logger;

import java.util.*;

import org.crazyit.auction.business.*;
import org.crazyit.auction.dao.*;
import org.crazyit.auction.domain.*;
import org.crazyit.auction.exception.AuctionException;
import org.crazyit.auction.service.AuctionManager;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂java联盟</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class AuctionManagerImpl implements AuctionManager
{
	static Logger log = Logger.getLogger(
		AuctionManagerImpl.class.getName());
	
	private AuctionUserDao userDao;
	private BidDao bidDao;
	private ItemDao itemDao;
	private KindDao kindDao;
	private StateDao stateDao;
	
	private MailSender mailSender;
	private SimpleMailMessage message;
	
	public void setUserDao(AuctionUserDao userDao)
	{
		this.userDao = userDao;
	}
	public void setBidDao(BidDao bidDao)
	{
		this.bidDao = bidDao;
	}
	public void setItemDao(ItemDao itemDao)
	{
		this.itemDao = itemDao;
	}
	public void setKindDao(KindDao kindDao)
	{
		this.kindDao = kindDao;
	}
	public void setStateDao(StateDao stateDao)
	{
		this.stateDao = stateDao;
	}
	
	public void setMailSender(MailSender mailSender)
	{
		this.mailSender = mailSender;
	}
	public void setMessage(SimpleMailMessage message)
	{
		this.message = message;
	}

	/**
	 * 根据用户查找目前仍在拍卖中的全部物品
	 * @param userId 所属者的ID
	 * @return 属于当前用户的、处于拍卖中的全部物品。
	 */
	public List<ItemBean> getItemByWiner(Integer winerId) throws AuctionException
	{
		try
		{
			List<Item> items = itemDao.findItemByWiner(winerId);
			List<ItemBean> result = new ArrayList<>();
			for (Iterator<Item> it = items.iterator() ; it.hasNext() ; )
			{
				ItemBean ib = new ItemBean();
				initItem(ib , it.next());
				result.add(ib);
			}
			return result;
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("查询用户所有的物品出现异常请重新查询");
		}
	}

	/**
	 * 查询流拍的全部物品
	 * @return 全部流拍物品
	 */
	public List<ItemBean> getFailItems() throws AuctionException
	{
		try
		{
			List<Item> items = itemDao.findItemByState(3);
			List<ItemBean> result = new ArrayList<>();
			for (Iterator<Item> it = items.iterator() ; it.hasNext() ; )
			{
				ItemBean ib = new ItemBean();
				initItem(ib , it.next());
				result.add(ib);
			}
			return result;
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("��ѯ������Ʒ�����쳣,������");
		}
	}

	/**
	 * 根据用户名，密码验证登录是否成功
	 * @param username 登录的用户名
 	 * @param pass 登录的密码
	 * @return 登录成功返回用户ID，否则返回-1
	 */
	public int validLogin(String username , String pass) throws AuctionException
	{
		try
		{
			AuctionUser u = userDao.findUserByNameAndPass(username , pass);
			if (u != null)
			{
				return u.getId();
			}
			return -1;
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("�����û���¼�����쳣,������");
		}
	}
	/**
	 * 用户注册
	 * 根据用户名，密码验证用户是否已经存在
	 * @param username 用户名
 	 * @param pass 密码
 	 * @email 电子邮箱
	 * @return 注册成功返回1,
	 */
	public int validRegister(String username , String password,String email) throws AuctionException
	{
		try
		{
			
			AuctionUser u = userDao.findUserByName(username);
			if (u != null)
			{
				return -1;
			}
			else{ 			
				try{
					AuctionUser user = new AuctionUser(6,username,password,email);
					userDao.save(user);
					return user.getId();
				}
				catch(Exception e){
					log.debug(e.getMessage());
					throw new AuctionException("注册出现异常，请重试");
				}
			}
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("处理用户注册出现异常，请重试");
		}
	}

	/**
	 * 查询用户的全部出价
	 * @param userId 竞价用户的ID
	 * @return 用户的全部出价
	 */
	public List<BidBean> getBidByUser(Integer userId) throws AuctionException
	{
		try
		{
			List<Bid> l = bidDao.findByUser(userId);
			List<BidBean> result = new ArrayList<>();
			for ( int i = 0 ; i < l.size() ; i++ )
			{
				Bid bid = l.get(i);
				BidBean bb = new BidBean();
				initBid(bb, bid);
				result.add(bb);
			}
			return result;
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("daf");
		}
	}

	/**
	 * 根据用户查找目前仍在拍卖中的全部物品
	 * @param userId 所属者的ID
	 * @return 属于当前用户的、处于拍卖中的全部物品。
	 */
	public List<ItemBean> getItemsByOwner(Integer userId) throws AuctionException
	{
		try
		{
			List<ItemBean> result = new ArrayList<>();
			List<Item> items = itemDao.findItemByOwner(userId);
			for (Iterator<Item> it = items.iterator() ; it.hasNext() ; )
			{
				ItemBean ib = new ItemBean();
				initItem( ib ,it.next());
				result.add(ib);
			}
			return result;
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("��ѯ�û����е���Ʒ�����쳣,������");
		}
	}

	/**
	 * 查询全部种类
	 * @return 系统中全部全部种类
	 */
	public List<KindBean> getAllKind() throws AuctionException
	{
		List<KindBean> result = new ArrayList<>();
		try
		{
			List<Kind> kl = kindDao.findAll(Kind.class);
			for (Kind k : kl )
			{
				result.add(new KindBean(k.getId(),
					k.getKindName(), k.getKindDesc()));
			}
			return result;
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("查询全部种类出现异常，请重试");
		}
	}

	/**
	 * 添加物品
	 * @param item 新增的物品
	 * @param avail 有效天数
	 * @param kindId 物品种类ID
	 * @param userId 添加者的ID
	 * @return 新增物品的主键
	 */
	public int addItem(Item item , int avail , int kind , Integer userId)
		throws AuctionException
	{
		try
		{
			Kind k = kindDao.get(Kind.class , kind);
			AuctionUser owner = userDao.get(AuctionUser.class , userId);
			//
			item.setAddtime(new Date());
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE , avail);
			item.setEndtime(c.getTime());
			item.setMaxPrice(item.getInitPrice());
			item.setItemState(stateDao.get(State.class , 1));
			item.setKind(k);
			item.setOwner(owner);
			// �־û�Item����
			itemDao.save(item);
			return item.getId();
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("添加物品出现异常，请重试");
		}
	}

	/**
	 * 添加种类
	 * @param kind 新增的种类
	 * @return 新增种类的主键
	 */
	public int addKind(Kind kind)
		throws AuctionException
	{
		try
		{
			kindDao.save(kind);
			return kind.getId();
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("�����������쳣,������");
		}
	}

	/**
	 * 根据产品分类，获取处于拍卖中的全部物品
	 * @param kindId 种类id;
	 * @return 该类的全部产品
	 */
	public List<ItemBean> getItemsByKind(int kindId) throws AuctionException
	{
		List<ItemBean> result = new ArrayList<>();
		try
		{
			List<Item> items = itemDao.findItemByKind(kindId);
			for (Iterator<Item> it = items.iterator() ; it.hasNext() ; )
			{
				ItemBean ib = new ItemBean();
				initItem(ib , it.next());
				result.add(ib);
			}
			return result;
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("根据种类获取物品出现异常");
		}
	}

	/**
	 * 根据种类id获取种类名
	 * @param kindId 种类id;
	 * @return 该种类的名称
	 */
	public String getKind(int kindId) throws AuctionException
	{
		try
		{
			Kind  k = kindDao.get(Kind.class , kindId);
			if (k != null)
			{
				return k.getKindName();
			}
			return null;
		}
		catch (Exception ex)
		{
			log.debug(ex.getMessage());
			throw new AuctionException("��������id��ȡ�������Ƴ����쳣,������");
		}
	}
	/**
	 * 根据物品id，获取物品
	 * @param itemId 物品id;
	 * @return 指定id对应的物品
	 */
	public ItemBean getItem(int itemId)
		throws AuctionException
	{
		try
		{
			Item item = itemDao.get(Item.class , itemId);
			ItemBean ib = new ItemBean();
			initItem(ib , item);
			return ib;
		}
		catch (Exception ex)
		{
			log.debug(ex.getMessage());
			throw new AuctionException("������Ʒid��ȡ��Ʒ��ϸ��Ϣ�����쳣,������");
		}
	}

	/**
	 * 增加新的竞价，并对竞价用户发邮件通知
	 * @param itemId 物品id;
	 * @param bid 竞价
	 * @param userId 竞价用户的ID
	 * @return 返回新增竞价记录的ID
	 */
	public int addBid(int itemId , Bid bid , Integer userId)
		throws AuctionException
	{
		try
		{
			AuctionUser au = userDao.get(AuctionUser.class , userId);
			Item item = itemDao.get(Item.class , itemId);
			if (bid.getBidPrice() > item.getMaxPrice())
			{
				item.setMaxPrice(bid.getBidPrice());
				itemDao.save(item);
			}
			// 
			bid.setBidItem(item);
			bid.setBidUser(au);
			bid.setBidDate(new Date());
			// 
			bidDao.save(bid);
			// 
			/*SimpleMailMessage msg = new SimpleMailMessage(this.message);
			msg.setTo(au.getEmail());
			msg.setText("Dear "
				+ au.getUsername()
				+ "谢谢你参与竞价，你的竞价物品是: "
				+ item.getItemName());
			mailSender.send(msg);
		*/	
			return bid.getId();
		}
		catch(Exception ex)
		{
			log.debug(ex.getMessage());
			ex.printStackTrace();
			throw new AuctionException("处理竞价出现异常，请重试");
		}
	}

	/**
	 * 根据时间来修改物品的状态、赢取者
	 */
	public void updateWiner()throws AuctionException
	{
		try
		{
			List itemList = itemDao.findItemByState(1);
			for (int i = 0 ; i < itemList.size() ; i++ )
			{
				Item item = (Item)itemList.get(i);
				if (!item.getEndtime().after(new Date()))
				{
					
					AuctionUser au = bidDao.findUserByItemAndPrice(
						item.getId() , item.getMaxPrice());
				
					if (au != null)
					{
					
						item.setWiner(au);
					
						item.setItemState(stateDao.get(State.class , 2));
						itemDao.save(item);
					}
					else
					{
					
						item.setItemState(stateDao.get(State.class , 3));
						itemDao.save(item);
					}
				}
			}
		}
		catch (Exception ex)
		{
			log.debug(ex.getMessage());
			throw new AuctionException("����ʱ�����޸���Ʒ��״̬��Ӯȡ�߳����쳣,������");
		}
	}

	/**
	 * 将一个Bid对象转换成BidBean对象
	 * @param bb BidBean对象
	 * @param bid Bid对象
	 */
	private void initBid(BidBean bb , Bid bid)
	{
		bb.setId(bid.getId().intValue());
		if (bid.getBidUser() != null )
			bb.setUser(bid.getBidUser().getUsername());
		if (bid.getBidItem() != null )
			bb.setItem(bid.getBidItem().getItemName());
		bb.setPrice(bid.getBidPrice());
		bb.setBidDate(bid.getBidDate());
	}

	/**
	 * 将一个Item PO转换成ItemBean的VO
	 * @param ib ItemBean的VO
	 * @param item Item的PO
	 */
	private void initItem(ItemBean ib , Item item)
	{
		ib.setId(item.getId());
		ib.setName(item.getItemName());
		ib.setDesc(item.getItemDesc());
		ib.setRemark(item.getItemRemark());
		if (item.getKind() != null)
			ib.setKind(item.getKind().getKindName());
		if (item.getOwner() != null)
			ib.setOwner(item.getOwner().getUsername());
		if (item.getWiner() != null)
			ib.setWiner(item.getWiner().getUsername());
		ib.setAddTime(item.getAddtime());
		ib.setEndTime(item.getEndtime());
		if (item.getItemState() != null)
			ib.setState(item.getItemState().getStateName());
		ib.setInitPrice(item.getInitPrice());
		ib.setMaxPrice(item.getMaxPrice());
	}
}