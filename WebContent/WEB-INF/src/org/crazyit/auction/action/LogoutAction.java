package org.crazyit.auction.action;

import com.opensymphony.xwork2.ActionContext;

import java.util.*;
import org.crazyit.auction.service.AuctionManager;
import org.crazyit.auction.exception.AuctionException;
import org.crazyit.auction.action.base.BaseAction;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class LogoutAction extends BaseAction
{
	private Integer userId;
	@Override
	public String execute() throws Exception
	{
		Map session = ActionContext.getContext().getSession();
	//	HttpSession session = request.getSession(true);
		session.put("userId" , null); 
		//目前来说这种退出登录的方法感觉不是很好
	//但是由于不是很了解，因此暂时是用这个办法。
		//session.invalidate();
		return SUCCESS;
	}
	// username的setter和getter方法
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
	public Integer getUserId()
	{
		return this.userId;
	}

}