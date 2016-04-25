package org.crazyit.auction.action;

import com.opensymphony.xwork2.ActionContext;

import java.util.*;
import org.crazyit.auction.service.AuctionManager;
import org.crazyit.auction.exception.AuctionException;
import org.crazyit.auction.action.base.BaseAction;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class RegisterAction extends BaseAction
{
	private String username;
	private String password;
	private String vercode;
	private String email;

	@Override
	public String execute() throws Exception
	{
		Map session = ActionContext.getContext().getSession();
		String ver2 = (String )session.get("rand");
		// 晴空随即验证码字符串
		session.put("rand" , null);
		if (vercode.equals(ver2))
		{
			Integer userId = mgr.validRegister(username,password,email);
			if (userId != null && userId > 0)
			{
				session.put("userId" , userId);
				return SUCCESS;
			}
			else if(userId != null && userId == -1) 
			{
				addActionError("用户名已经存在，注册失败");
				return "failure";
			}
			else
			{
				addActionError("注册失败");
				return "failure";
			}
		}
		else
		{
			addActionError("验证码输入错误。请重新注册");
			return "failure";
		}
	}
	// username的setter和getter方法
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUsername()
	{
		return this.username;
	}

	// password的setter和getter方法
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return this.password;
	}
	
	//email的setter方法和getter方法
	public void setEmail(String email)
	{
			this.email = email;
	}
	public String getEmail()
	{
		return this.email;
	}
	
	//  vercode的setter和getter方法
	public void setVercode(String vercode)
	{
		this.vercode = vercode;
	}
	public String getVercode()
	{
		return this.vercode;
	}
}