package com.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.TZhijiao;

/**
 * Data access object (DAO) for domain model class TZhijiao.
 * 
 * @see com.model.TZhijiao
 * @author MyEclipse Persistence Tools
 */

public class TZhijiaoDAO extends HibernateDaoSupport
{
	private static final Log log = LogFactory.getLog(TZhijiaoDAO.class);

	protected void initDao()
	{
		// do nothing
	}

	public void save(TZhijiao transientInstance)
	{
		log.debug("saving TZhijiao instance");
		try
		{
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re)
		{
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TZhijiao persistentInstance)
	{
		log.debug("deleting TZhijiao instance");
		try
		{
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re)
		{
			log.error("delete failed", re);
			throw re;
		}
	}

	public TZhijiao findById(java.lang.Integer id)
	{
		log.debug("getting TZhijiao instance with id: " + id);
		try
		{
			TZhijiao instance = (TZhijiao) getHibernateTemplate().get(
					"com.model.TZhijiao", id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TZhijiao instance)
	{
		log.debug("finding TZhijiao instance by example");
		try
		{
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re)
		{
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value)
	{
		log.debug("finding TZhijiao instance with property: " + propertyName
				+ ", value: " + value);
		try
		{
			String queryString = "from TZhijiao as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re)
		{
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll()
	{
		log.debug("finding all TZhijiao instances");
		try
		{
			String queryString = "from TZhijiao";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	public TZhijiao merge(TZhijiao detachedInstance)
	{
		log.debug("merging TZhijiao instance");
		try
		{
			TZhijiao result = (TZhijiao) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TZhijiao instance)
	{
		log.debug("attaching dirty TZhijiao instance");
		try
		{
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re)
		{
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TZhijiao instance)
	{
		log.debug("attaching clean TZhijiao instance");
		try
		{
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re)
		{
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TZhijiaoDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (TZhijiaoDAO) ctx.getBean("TZhijiaoDAO");
	}
}