package common;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DynamicRs {
	public static void out(ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				// 取出列名
				String columnName = rsmd.getColumnName(i);
				Object value = rs.getObject(columnName);
				System.out.print(columnName + ":" + value + "\t");
			}
			System.out.println();
		} catch (SQLException e) {
			DBError.out(e);
		}
	}

}