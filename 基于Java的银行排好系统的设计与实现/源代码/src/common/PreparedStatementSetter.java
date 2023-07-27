package common;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
	public void set(PreparedStatement pstmt) throws SQLException;

}
