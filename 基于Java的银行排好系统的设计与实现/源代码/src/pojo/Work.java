package pojo;

import java.io.Serializable;
import java.util.Date;

public class Work implements Serializable {
	private int id;
	private Date processDate;
	private int workId;
	private int type;
	private int nextId;
	private int status;

	public Work() {
	}

	public Work(int workId, int type, int nextId) {
		this.workId = workId;
		this.type = type;
		this.nextId = nextId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getWorkId() {
		return workId;
	}

	public void setWorkId(int workId) {
		this.workId = workId;
	}

	public int getNextId() {
		return nextId;
	}

	public void setNextId(int nextId) {
		this.nextId = nextId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
