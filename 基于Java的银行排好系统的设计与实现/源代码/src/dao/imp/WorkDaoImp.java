package dao.imp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Customer;
import pojo.Work;
import pojo.WorkMen;

import common.JdbcConnectionFactory;
import common.JdbcTemplate;
import common.PreparedStatementSetter;
import common.RowCallBackHandler;

import dao.IWorkDao;

public class WorkDaoImp implements IWorkDao {

	public void saveWork(final Work work) {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		String sql = "insert into work_tbl values(w_seq.nextval,?,?,?,?)";
		template.update(sql, new PreparedStatementSetter() {
			public void set(PreparedStatement pstmt) throws SQLException {

				pstmt.setDate(1, new Date(System.currentTimeMillis()));
				pstmt.setInt(2, work.getWorkId());
				pstmt.setInt(3, work.getType());
				pstmt.setInt(4, work.getNextId());
				// pstmt.setInt(5, 0);
			}
		});

	}

	public int base() {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		String selectSql = "select id from workid_tbl";
		String updateSql = "update workid_tbl set id=id+1";

		final List list = new ArrayList();
		template.query(selectSql, new RowCallBackHandler() {
			public void process(ResultSet rs) throws SQLException {
				list.add(rs.getInt(1));

			}
		});
		template.update(updateSql);
		return (Integer) list.get(0);
	}

	public Work findWork(int nextId, int type) {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		final List<Work> list = new ArrayList<Work>();
		String sql = "select * from work_tbl where nextId=" + nextId
				+ " and type=" + type;
		template.query(sql, new RowCallBackHandler() {
			public void process(ResultSet rs) throws SQLException {
				Work work = new Work();
				work.setId(rs.getInt(1));
				work.setProcessDate(rs.getDate(2));
				work.setWorkId(rs.getInt(3));
				work.setType(rs.getInt(4));
				work.setNextId(rs.getInt(5));
				work.setStatus(rs.getInt(6));
				list.add(work);
			}
		});
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	/**
	 *  查询业务员用户名密码
	 */
	public WorkMen find(final String name) {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		final List<WorkMen> list = new ArrayList<WorkMen>();
		// String sql="select * from workmen_tbl where name= "+name;
		template.query("select * from workmen_tbl where name=?",
				new PreparedStatementSetter() {
					public void set(PreparedStatement pstmt)
							throws SQLException {
						pstmt.setString(1, name);
					}
				}, new RowCallBackHandler() {
					public void process(ResultSet rs) throws SQLException {
						WorkMen work = new WorkMen();

						work.setName(name);
						work.setPassword(rs.getString("password"));
						list.add(work);
					}
				});
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	public List<Work> loadAllWork() {
		Connection conn = JdbcConnectionFactory.getConnection();
		JdbcTemplate template = new JdbcTemplate(conn);
		final List<Work> list = new ArrayList<Work>();
		String sql = "select * from work_tbl order by id asc";
		template.query(sql, new RowCallBackHandler() {
			public void process(ResultSet rs) throws SQLException {
				Work work = new Work();
				work.setId(rs.getInt(1));
				work.setProcessDate(rs.getDate(2));
				work.setWorkId(rs.getInt(3));
				work.setType(rs.getInt(4));
				work.setNextId(rs.getInt(5));
				work.setStatus(rs.getInt(6));
				list.add(work);
			}
		});
		return list;
	}

}
