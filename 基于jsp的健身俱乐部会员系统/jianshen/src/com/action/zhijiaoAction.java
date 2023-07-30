package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.TQicaiDAO;
import com.dao.TZhijiaoDAO;
import com.model.TZhijiao;
import com.opensymphony.xwork2.ActionSupport;

public class zhijiaoAction extends ActionSupport
{
	private int zhijiaoId;
	private String zhijiaoJiaolianName;
	private String zhijiaoKecheng;
	private String zhijiaoHuiyuan;
	
	private String message;
	private String path;
	
	private TZhijiaoDAO zhijiaoDAO;

	
	
	public String zhijiaoAdd()
	{
		TZhijiao zhijiao=new TZhijiao();
		zhijiao.setZhijiaoJiaolianName(zhijiaoJiaolianName);
		zhijiao.setZhijiaoKecheng(zhijiaoKecheng);
		zhijiao.setZhijiaoHuiyuan(zhijiaoHuiyuan);
		zhijiaoDAO.save(zhijiao);
		this.setMessage("录入成功");
		this.setPath("zhijiaoManage.action");
		return "succeed";
	}
	
	
	public String zhijiaoManage()
	{
		List zhijiaoList=zhijiaoDAO.findAll();
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("zhijiaoList", zhijiaoList);
		return ActionSupport.SUCCESS;
	}
	
	public String zhijiaoDel()
	{
		zhijiaoDAO.delete(zhijiaoDAO.findById(zhijiaoId));
		this.setMessage("删除成功");
		this.setPath("zhijiaoManage.action");
		return "succeed";
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

	public TZhijiaoDAO getZhijiaoDAO()
	{
		return zhijiaoDAO;
	}

	public void setZhijiaoDAO(TZhijiaoDAO zhijiaoDAO)
	{
		this.zhijiaoDAO = zhijiaoDAO;
	}

	public String getZhijiaoHuiyuan()
	{
		return zhijiaoHuiyuan;
	}

	public void setZhijiaoHuiyuan(String zhijiaoHuiyuan)
	{
		this.zhijiaoHuiyuan = zhijiaoHuiyuan;
	}

	public int getZhijiaoId()
	{
		return zhijiaoId;
	}

	public void setZhijiaoId(int zhijiaoId)
	{
		this.zhijiaoId = zhijiaoId;
	}

	public String getZhijiaoJiaolianName()
	{
		return zhijiaoJiaolianName;
	}

	public void setZhijiaoJiaolianName(String zhijiaoJiaolianName)
	{
		this.zhijiaoJiaolianName = zhijiaoJiaolianName;
	}

	public String getZhijiaoKecheng()
	{
		return zhijiaoKecheng;
	}

	public void setZhijiaoKecheng(String zhijiaoKecheng)
	{
		this.zhijiaoKecheng = zhijiaoKecheng;
	}
	
	
	
}
