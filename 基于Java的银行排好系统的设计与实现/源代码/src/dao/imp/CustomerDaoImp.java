package dao.imp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Customer;

import common.JdbcConnectionFactory;
import common.JdbcTemplate;
import common.PreparedStatementSetter;
import common.RowCallBackHandler;

import dao.ICustomerDao;

public class CustomerDaoImp implements ICustomerDao {

	public int nextID(int type) {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
//		String sql = "select nextval from srt_tbl where type=" + type;
		String sql = "select nextId from srt_tbl where type=" + type;
		String sql1 = "update srt_tbl set nextId=nextId+1 where type=" + type;

		final List list = new ArrayList();

		template.query(sql, new RowCallBackHandler() {
			public void process(ResultSet rs) throws SQLException {

				int oid = rs.getInt(1);
				list.add(oid);
				// System.out.println(oid);
			}
		});
		
		template.update(sql1);
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (Integer) list.get(0);
	}

	public void saveID(final Customer customer) {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		String sql = "insert into customer_tbl values(?,?,?,?,?) ";
		template.update(sql, new PreparedStatementSetter() {
			public void set(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, base());
				pstmt.setInt(2, customer.getNextId());
				pstmt.setDate(3, new Date(System.currentTimeMillis()));
				pstmt.setInt(4, customer.getType());
				pstmt.setInt(5, customer.getFlag());

			}
		});

	}

	public Customer findCustomer(int nextId, int type) {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		String sql = "select * from customer_tbl where nextId=" + nextId
				+ " and type=" + type;
		final List<Customer> list = new ArrayList<Customer>();
		template.query(sql, new RowCallBackHandler() {
			public void process(ResultSet rs) throws SQLException {
				Customer customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setNextId(rs.getInt(2));
				customer.setTakeDate(rs.getDate(3));
				customer.setType(rs.getInt(4));
				customer.setFlag(rs.getInt(5));

				list.add(customer);
			}
		});
		if (conn != null) {
			try {
				conn.close();
				Thread.sleep(400);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	public Customer selectID(final int type) {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		final List<Customer> list = new ArrayList<Customer>();
		template.query("select * from customer_tbl where type=" + type,
				new RowCallBackHandler() {
					public void process(ResultSet rs) throws SQLException {
						Customer customer = new Customer();
						customer.setId(rs.getInt(1));
						customer.setNextId(rs.getInt(2));
						customer.setTakeDate(rs.getDate(3));
						customer.setType(rs.getInt(4));
						customer.setFlag(rs.getInt(5));

						list.add(customer);
					}
				});
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	public Integer findAllCustomer(final int type) {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		String sql = "select * from customer_tbl where type=" + type;
		final List<Customer> list = new ArrayList<Customer>();
		template.query(sql,

		new RowCallBackHandler() {
			public void process(ResultSet rs) throws SQLException {
				Customer customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setNextId(rs.getInt(2));
				customer.setTakeDate(rs.getDate(3));
				customer.setType(rs.getInt(4));
				customer.setFlag(rs.getInt(5));

				list.add(customer);
			}
		});
		return list.size();
	}

	public List<Customer> loadAllUsers() {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		final List<Customer> list = new ArrayList<Customer>();
		String sql = "select * from customer_tbl order by nextId asc";
		template.query(sql, new RowCallBackHandler() {
			public void process(ResultSet rs) throws SQLException {
				Customer customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setNextId(rs.getInt(2));
				customer.setTakeDate(rs.getDate(3));
				customer.setType(rs.getInt(4));
				customer.setFlag(rs.getInt(5));

				list.add(customer);
			}
		});
		return list;
	}

	public void update(Customer customer) {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		String sql = "update customer_tbl set flag=1 where nextId="
				+ customer.getNextId();
		template.update(sql);

	}

	public List<Customer> findUprocess(int type) {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		final List<Customer> list = new ArrayList<Customer>();
		String sql = "select * from customer_tbl where flag=0 and type=" + type
				+ " order by id asc";
		template.query(sql, new RowCallBackHandler() {
			public void process(ResultSet rs) throws SQLException {
				Customer customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setNextId(rs.getInt(2));
				customer.setTakeDate(rs.getDate(3));
				customer.setType(rs.getInt(4));
				customer.setFlag(rs.getInt(5));

				list.add(customer);
			}
		});
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public void removeRecord(int id) {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		String sql = "delete from customer_tbl where id=" + id;
		template.update(sql);

	}

	public int base() {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		String selectSql = "select id from hl_tbl";
		String updateSql = "update hl_tbl set id=id+1";

		final List list = new ArrayList();
		template.query(selectSql, new RowCallBackHandler() {
			public void process(ResultSet rs) throws SQLException {
				list.add(rs.getInt(1));

			}
		});
		template.update(updateSql);
		return (Integer) list.get(0);
	}

	public Customer findUprocessCustomer(int type) {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		final List<Customer> list = new ArrayList<Customer>();
		String sql = "select * from customer_tbl where flag=0 and type=" + type
				+ " order by id asc";

		template.query(sql, new RowCallBackHandler() {
			public void process(ResultSet rs) throws SQLException {
				Customer customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setNextId(rs.getInt(2));
				customer.setTakeDate(rs.getDate(3));
				customer.setType(rs.getInt(4));
				customer.setFlag(rs.getInt(5));

				list.add(customer);
			}
		});
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	public void delete(int type) {

		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		String sql = "delete from customer_tbl where type=" + type;
		template.update(sql);
	}

	public void deleteRecord(int type, int nextId) {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		String sql = "delete from customer_tbl where type=" + type
				+ " and nextId=" + nextId;
		template.update(sql);

	}

	public Customer select(int type, int nextId) {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		final List<Customer> list = new ArrayList<Customer>();
		String sql = "select * from customer_tbl where flag=0 and type=" + type
				+ " and nextId=" + nextId;

		template.query(sql, new RowCallBackHandler() {
			public void process(ResultSet rs) throws SQLException {
				Customer customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setNextId(rs.getInt(2));
				customer.setTakeDate(rs.getDate(3));
				customer.setType(rs.getInt(4));
				customer.setFlag(rs.getInt(5));

				list.add(customer);
			}
		});
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}
}
