package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {
	private Connection conn;

	public JdbcTemplate(Connection conn) {
		this.conn = conn;
	}

	// 需要执行一个更新操作,
	// 而且更新的值在sql语句中已经指定
	public int update(String sql) {
		Statement stmt = null;

		int count = 0;
		try {
			stmt = conn.createStatement();
			count = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			DBError.out(e);
			throw new DataAccessException("can not execute :" + sql, e);
		} finally {
			DBUtil.close(null, stmt, null);
		}
		return count;
	}

	// 需要执行一个更新操作
	// 并且更新的值不固定,由调用者决定
	// insert into teacher values(?,?,?,?)
	public int update(String sql, PreparedStatementSetter setter) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			// 给占位符赋值
			if (setter != null)
				setter.set(pstmt);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			DBError.out(e);
			throw new DataAccessException("can not execute: " + sql, e);
		} finally {
			DBUtil.close(pstmt, null);
		}
		return count;
	}

	// 执行一个查询操作
	// 并且如何处理结果集,由调用者决定
	public void query(String sql, RowCallBackHandler handler) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next())
				handler.process(rs);
		} catch (SQLException e) {
			DBError.out(e);
			throw new DataAccessException("can not execute: " + sql, e);
		} finally {
			DBUtil.close(rs, stmt, null);
		}
	}

	// 执行一个包含有占位符的查询语句,
	// 并且如何处理结果集由调用者决定
	// select * from teacher where salary>?
	public void query(String sql, PreparedStatementSetter setter,
			RowCallBackHandler handler) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if (setter != null)
				setter.set(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next())
				handler.process(rs);
		} catch (SQLException e) {
			DBError.out(e);
			throw new DataAccessException("can not execute: " + sql, e);
		} finally {
			DBUtil.close(rs, pstmt, null);
		}
	}
}
