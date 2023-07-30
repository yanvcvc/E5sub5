package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.TCardDAO;
import com.dao.TUserDAO;
import com.model.TCard;
import com.opensymphony.xwork2.ActionSupport;

public class CardAction extends ActionSupport
{
	private int cardId;
	private String cardLeixiong;
	private String cardGuize;
	private String cardYouhuizhengce;
	
	private String message;
	private String path;
	
	private TCardDAO cardDAO;
	
	
	
	
	
	
	public String cardAdd()
	{
		TCard card=new TCard();
		card.setCardGuize(cardGuize);
		card.setCardLeixiong(cardLeixiong);
		card.setCardYouhuizhengce(cardYouhuizhengce);
		
		cardDAO.save(card);
		this.setMessage("录入成功");
		this.setPath("cardManage.action");
		return "succeed";
	}
	
	public String cardManage()
	{
		List cardList=cardDAO.findAll();
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("cardList", cardList);
		return ActionSupport.SUCCESS;
	}
	
	public String cardDel()
	{
		cardDAO.delete(cardDAO.findById(cardId));
		this.setMessage("删除成功");
		this.setPath("cardManage.action");
		return "succeed";
	}
	

	public TCardDAO getCardDAO()
	{
		return cardDAO;
	}

	public void setCardDAO(TCardDAO cardDAO)
	{
		this.cardDAO = cardDAO;
	}

	public String getCardGuize()
	{
		return cardGuize;
	}

	public void setCardGuize(String cardGuize)
	{
		this.cardGuize = cardGuize;
	}

	public int getCardId()
	{
		return cardId;
	}

	public void setCardId(int cardId)
	{
		this.cardId = cardId;
	}

	public String getCardLeixiong()
	{
		return cardLeixiong;
	}

	public void setCardLeixiong(String cardLeixiong)
	{
		this.cardLeixiong = cardLeixiong;
	}

	public String getCardYouhuizhengce()
	{
		return cardYouhuizhengce;
	}

	public void setCardYouhuizhengce(String cardYouhuizhengce)
	{
		this.cardYouhuizhengce = cardYouhuizhengce;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}
	
}
