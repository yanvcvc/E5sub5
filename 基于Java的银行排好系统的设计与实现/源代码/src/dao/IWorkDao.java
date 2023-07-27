package dao;

import java.util.List;

import pojo.Work;
import pojo.WorkMen;

public interface IWorkDao {
	// 保存Work
	void saveWork(Work work);

	// 产生ID号
	int base();

	// 查找work
	Work findWork(int nextId, int type);

	// 查询员工的密码，名字
	WorkMen find(String name);

	List<Work> loadAllWork();

}
