package pojo;

import java.io.Serializable;
import java.sql.Date;

public class Customer implements Serializable {
	private int id;
	private int nextId;
	private Date takeDate;
	private int type;
	private int flag;
	private int worktype;
	private Work work;

	public Customer() {
	}

	public Customer(int nextId, int type) {

		// this.takeDate = takeDate;
		this.type = type;
		this.nextId = nextId;
		// this.flag = flag;
		// this.worktype = worktype;
		// this.work = work;
		// this.nextval = nextval;
	}

	public Customer(int type) {
		this.type = type;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getId() {
		return id;

	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNextId() {
		return nextId;
	}

	public void setNextId(int nextId) {
		this.nextId = nextId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getTakeDate() {
		return takeDate;
	}

	public void setTakeDate(Date takeDate) {
		this.takeDate = takeDate;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public int getWorktype() {
		return worktype;
	}

	public void setWorktype(int worktype) {
		this.worktype = worktype;
	}

}
