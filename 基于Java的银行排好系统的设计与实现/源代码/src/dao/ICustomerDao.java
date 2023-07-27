package dao;

import java.util.List;

import pojo.Customer;

import pojo.Work;

public interface ICustomerDao {
	// 自动产生ID
	int nextID(int type);

	// 保存ID到Customer表中
	void saveID(Customer customer);

	// 从customer表中取出一条记录，返回customer
	Customer selectID(int type);

	// 从customer表中取出一条记录，返回customer
	Customer findCustomer(int nextId, int type);

	// 查找出总记录数
	Integer findAllCustomer(int type);

	public List<Customer> loadAllUsers();

	// 更新记录
	public void update(Customer customer);

	// 查找未处理的记录
	List<Customer> findUprocess(int type);

	// 删除一条记录
	void removeRecord(int id);

	int base();

	Customer select(int type, int nextId);

	// 查出一条记录，反回一个customer对象
	Customer findUprocessCustomer(int type);

	void delete(int type);

	void deleteRecord(int type, int nextId);
}