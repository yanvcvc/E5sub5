package pojo;

import java.io.Serializable;

public class WorkMen implements Serializable {
	private String name;
	private String password;

	public WorkMen() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name" + name + "\tpassword" + password;
	}

}
