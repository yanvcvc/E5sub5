package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.TUserDAO;
import com.model.TUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport
{
    private int userId;
	private String userName;
	private String userPw;
	private String userRealname;
	private int userType;
	private String userAddress;
	private String userSex;
	private String userTel;
	private String userEmail;
	private String userQq;
	private String userOne1;
   
	
	
	
	private String message;
	private String path;
	
	private TUserDAO userDAO;
	
	
	
	public String adminLogin()
	{
		String sql="from TUser where userName=? and userPw=? and userType=0";
		Object[] con={userName,userPw};
		List userList=userDAO.getHibernateTemplate().find(sql,con);
		if(userList.size()==0)
		{
			Map request=(Map)ServletActionContext.getContext().get("request");
			request.put("error", "用户名或密码错误");
    		return ActionSupport.ERROR;
		}
		else
		{
			 Map session= ServletActionContext.getContext().getSession();
			 TUser user=(TUser)userList.get(0);
			 session.put("admin", user);
			 return ActionSupport.SUCCESS;
		}
	}
	
	public String userLogin()
	{
		String sql="from TUser where userName=? and userPw=? and userType=1";
		Object[] con={userName,userPw};
		List userList=userDAO.getHibernateTemplate().find(sql,con);
		if(userList.size()==0)
		{
			Map request=(Map)ServletActionContext.getContext().get("request");
			request.put("error", "用户名或密码错误");
    		return ActionSupport.ERROR;
		}
		else
		{
			 Map session= ServletActionContext.getContext().getSession();
			 TUser user=(TUser)userList.get(0);
			 session.put("user", user);
			 return ActionSupport.SUCCESS;
		}
	}
	
	
	public String userLogout()
	{
		Map session= ServletActionContext.getContext().getSession();
		session.remove("user");
		return ActionSupport.SUCCESS;
	}
	
	
	
	
	
	
	
	public String userPwEdit()
	{
		Map session=ActionContext.getContext().getSession();
		TUser user=(TUser)session.get("admin");
		user.setUserPw(userPw);
		userDAO.getHibernateTemplate().update(user);
		
		this.setMessage("修改成功");
		this.setPath("/admin/anquan/userPwEdit.jsp");
		return "succeed";
	}
	
	
	public String userReg()
	{
		TUser user=new TUser();
		user.setUserName(userName);
		user.setUserPw(userPw);
		user.setUserType(1);
		user.setUserAddress(userAddress);
		user.setUserTel(userTel);
		user.setUserRealname(userRealname);
		user.setUserEmail(userEmail);
		user.setUserSex(userSex);
		user.setUserQq(userQq);
		
		userDAO.save(user);
		this.setPath("/index.jsp");
		return "succeed";
	}
	
	
	
	//工作人员录入
	public String userAdd()
	{
		TUser user=new TUser();
		user.setUserName(userName);
		user.setUserType(userType);
		user.setUserAddress(userAddress);
		user.setUserTel(userTel);
		user.setUserEmail(userEmail);
		user.setUserSex(userSex);
		user.setUserQq(userQq);
		
		userDAO.save(user);
		this.setPath("userManage.action");
		return "succeed";
	}
	
	
    //会员录入
	public String huiyuanAdd()
	{
		TUser user=new TUser();
		user.setUserName(userName);
		user.setUserType(8);
		user.setUserAddress(userAddress);
		user.setUserTel(userTel);
		user.setUserEmail(userEmail);
		user.setUserSex(userSex);
		user.setUserQq(userQq);
		user.setUserOne1(userOne1);
		userDAO.save(user);
		this.setPath("huiyuanManage.action");
		return "succeed";
	}
	
	

	//工作人员管理
	public String userManage()
	{
		List userList=userDAO.findAllGongzuoRenyuan();
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("userList", userList);
		return ActionSupport.SUCCESS;
	}
	
//	会员管理
	public String huiyuanManage()
	{
		List userList=userDAO.findAllHuiyuan();
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("userList", userList);
		return ActionSupport.SUCCESS;
	}
	
	
	public String huiyuanSearch()
	{
        String sql="from TUser t where t.userName like '%"+userName+"%'";
		
		List userList=userDAO.getHibernateTemplate().find(sql);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("userList", userList);
		return ActionSupport.SUCCESS;

	}
	
	
	
	
	public String userXiugai()
	{
		Map session= ServletActionContext.getContext().getSession();
		TUser user=(TUser)session.get("user");
		user.setUserName(userName);
		user.setUserPw(userPw);
		user.setUserName(userName);
		user.setUserPw(userPw);
		user.setUserAddress(userAddress);
		user.setUserTel(userTel);
		user.setUserRealname(userRealname);
		user.setUserEmail(userEmail);
		user.setUserSex(userSex);
		user.setUserQq(userQq);
		userDAO.getHibernateTemplate().update(user);
		
		session.put("user", user);
		this.setMessage("修改成功");
		this.setPath("/userinfo.jsp");
		
		return "succeed";
	}
	
	
	public String delUser()
	{
		userDAO.delete(userDAO.findById(userId));
		this.setMessage("删除成功");
		this.setPath("userManage.action");
		return "succeed";
	}
	
	
	public String huiyuanDel()
	{
		userDAO.delete(userDAO.findById(userId));
		this.setMessage("删除成功");
		this.setPath("huiyuanManage.action");
		return "succeed";
	}
	
	
	public String getUserAddress()
	{
		return userAddress;
	}

	public void setUserAddress(String userAddress)
	{
		this.userAddress = userAddress;
	}

	public String getUserEmail()
	{
		return userEmail;
	}

	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	public String getUserQq()
	{
		return userQq;
	}

	public void setUserQq(String userQq)
	{
		this.userQq = userQq;
	}

	public int getUserType()
	{
		return userType;
	}

	public void setUserType(int userType)
	{
		this.userType = userType;
	}

	public String getUserRealname()
	{
		return userRealname;
	}

	public void setUserRealname(String userRealname)
	{
		this.userRealname = userRealname;
	}

	public String getUserSex()
	{
		return userSex;
	}

	public void setUserSex(String userSex)
	{
		this.userSex = userSex;
	}

	public String getUserTel()
	{
		return userTel;
	}

	public void setUserTel(String userTel)
	{
		this.userTel = userTel;
	}

	public int getUserId()
	{
		return userId;
	}


	public void setUserId(int userId)
	{
		this.userId = userId;
	}


	public String getUserOne1()
	{
		return userOne1;
	}

	public void setUserOne1(String userOne1)
	{
		this.userOne1 = userOne1;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public TUserDAO getUserDAO()
	{
		return userDAO;
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


	public void setUserDAO(TUserDAO userDAO)
	{
		this.userDAO = userDAO;
	}


	public String getUserPw()
	{
		return userPw;
	}

	public void setUserPw(String userPw)
	{
		this.userPw = userPw;
	}

	
}
