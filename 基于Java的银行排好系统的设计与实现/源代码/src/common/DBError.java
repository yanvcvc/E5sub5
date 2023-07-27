package common;

import java.sql.SQLException;
import java.sql.SQLWarning;

public class DBError {
	public static void out(SQLException e) {
		if (e == null)
			return;
		e.printStackTrace();
		int errorCode = e.getErrorCode();
		String message = e.getMessage();
		System.out.println("errorCode:" + errorCode);
		System.out.println("message:" + message);
		SQLException nextException = e.getNextException();
		out(nextException);
	}

	public static void out(SQLWarning w) {
		if (w == null)
			return;
		w.printStackTrace();
		int warningCode = w.getErrorCode();
		String message = w.getMessage();
		System.out.println("errorCode:" + warningCode);
		System.out.println("message:" + message);
		SQLException nextException = w.getNextWarning();
		out(nextException);

	}

}
