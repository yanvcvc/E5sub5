package servicel.impl;

import java.sql.Connection;

import pojo.Customer;
import pojo.Work;
import servicel.ICustomerService;

import common.DBUtil;
import common.JdbcConnectionFactory;
import common.exception.transaction.BeanFactory;

import dao.ICustomerDao;
import dao.IWorkDao;

public class CustomerServiceImp implements ICustomerService {

	public void process(int nextId, int type) {
		Customer customer = null;
		Connection conn = null;
		try {

			conn = JdbcConnectionFactory.getConnection();
			// conn.setAutoCommit(false);
			ICustomerDao dao = (ICustomerDao) BeanFactory
					.getPojo("customerDao");
			customer = dao.findCustomer(nextId, type);

			if (customer == null) {
				throw new Exception("用户不存在");
			}
			if (customer.getFlag() == 1) {
				throw new Exception("此用户处理");
			}
			customer.setFlag(1);

			dao.update(customer);
			// conn.commit();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtil.close(null, conn);
		}

	}

	public synchronized int unProcess(int type) {

		Customer customer = null;
		Connection conn = null;
		try {
			conn = JdbcConnectionFactory.getConnection();
			// conn.setAutoCommit(false);

			ICustomerDao dao = (ICustomerDao) BeanFactory
					.getPojo("customerDao");
			customer = dao.findUprocessCustomer(type);
			if (customer == null) {
				throw new Exception("已处理完毕");

			}
			return customer.getNextId();
		} catch (Exception e) {
			// conn.rollback();

			e.getMessage();

		} finally {
			DBUtil.close(null, conn);
		}
		return 0;

	}

	public void saveCustomer(int nextId, int type) {

		Connection conn = null;
		try {
			conn = JdbcConnectionFactory.getConnection();
			ICustomerDao dao = (ICustomerDao) BeanFactory
					.getPojo("customerDao");
			IWorkDao workDao = (IWorkDao) BeanFactory.getPojo("workDao");
			Work work = workDao.findWork(nextId, type);
			Customer customer = dao.findCustomer(nextId, type);
			customer.setWorktype(work.getWorkId());

		} catch (Exception e) {
			// conn.rollback();

			e.getMessage();

		} finally {
			DBUtil.close(null, conn);
		}

	}

	/*
	 * public void saveCustomer(Customer customer) throws
	 * CustomerServiceException { ICustomerDao
	 * dao=(ICustomerDao)BeanFactory.getPojo("customerDao");
	 * dao.saveID(customer); }
	 * 
	 * public int findnum(int type) throws CustomerServiceException {
	 * ICustomerDao dao=(ICustomerDao)BeanFactory.getPojo("customerDao");
	 * Customer customer=dao.findCustomer(nextType(type)); dao.saveID(customer);
	 * int id=dao.selectID(customer.getType()); return id; }
	 */

}