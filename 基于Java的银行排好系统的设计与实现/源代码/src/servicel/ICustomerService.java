package servicel;

import java.util.List;

import pojo.Customer;

public interface ICustomerService {
	// int nextType(int type)throws CustomerServiceException;
	/*
	 * void saveCustomer(Customer customer)throws CustomerServiceException; int
	 * findnum(int type)throws CustomerServiceException;
	 */
	// 对业务处理
	void process(int nextId, int type);

	int unProcess(int type);

	// 保存customer
	void saveCustomer(int nextId, int type);
}
