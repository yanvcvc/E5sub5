package common.exception.transaction;

import servicel.ICustomerService;

import servicel.impl.CustomerServiceImp;

import dao.ICustomerDao;
import dao.IWorkDao;

import dao.imp.CustomerDaoImp;
import dao.imp.WorkDaoImp;

/*
 * 构造业务逻辑层，数据访问层接口实现类的对象，而且还是单例
 */

public class BeanFactory {
	private static ICustomerDao customerDao;
	private static ICustomerService customerService;
	private static IWorkDao workDao;

	public static final String CUSTOMERDAO = "customerDao";
	public static final String CUSTOMERSERVICE = "customerService";
	public static final String WORKDAO = "workDao";
	public static final String WORKSERVICE = "workService";

	public static Object getPojo(String beanName) {
		if (beanName.equals(CUSTOMERDAO)) {
			return getCustomerDao();
		} else if (beanName.equals(CUSTOMERSERVICE)) {
			return getCustomerService();
		} else if (beanName.equals(WORKDAO)) {
			return getWorkDao();

		} else {
			return null;
		}
	}

	private synchronized static IWorkDao getWorkDao() {
		if (workDao == null)
			workDao = new WorkDaoImp();
		return workDao;
	}

	private synchronized static ICustomerDao getCustomerDao() {
		if (customerDao == null)
			customerDao = new CustomerDaoImp();
		return customerDao;
	}

	private synchronized static ICustomerService getCustomerService() {
		if (customerService == null)
			customerService = new CustomerServiceImp();
		return customerService;
	}

}
