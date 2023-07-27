package common;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowCallBackHandler {
	public void process(ResultSet rs) throws SQLException;
}
