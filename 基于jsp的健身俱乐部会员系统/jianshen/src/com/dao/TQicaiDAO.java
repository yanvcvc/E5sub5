package com.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.TQicai;

/**
 * Data access object (DAO) for domain model class TQicai.
 * 
 * @see com.model.TQicai
 * @author MyEclipse Persistence Tools
 */

public class TQicaiDAO extends HibernateDaoSupport
{
	private static final Log log = LogFactory.getLog(TQicaiDAO.class);

	protected void initDao()
	{
		// do nothing
	}

	public void save(TQicai transientInstance)
	{
		log.debug("saving TQicai instance");
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

	public void delete(TQicai persistentInstance)
	{
		log.debug("deleting TQicai instance");
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

	public TQicai findById(java.lang.Integer id)
	{
		log.debug("getting TQicai instance with id: " + id);
		try
		{
			TQicai instance = (TQicai) getHibernateTemplate().get(
					"com.model.TQicai", id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TQicai instance)
	{
		log.debug("finding TQicai instance by example");
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
		log.debug("finding TQicai instance with property: " + propertyName
				+ ", value: " + value);
		try
		{
			String queryString = "from TQicai as model where model."
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
		log.debug("finding all TQicai instances");
		try
		{
			String queryString = "from TQicai";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	public TQicai merge(TQicai detachedInstance)
	{
		log.debug("merging TQicai instance");
		try
		{
			TQicai result = (TQicai) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TQicai instance)
	{
		log.debug("attaching dirty TQicai instance");
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

	public void attachClean(TQicai instance)
	{
		log.debug("attaching clean TQicai instance");
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

	public static TQicaiDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (TQicaiDAO) ctx.getBean("TQicaiDAO");
	}
}