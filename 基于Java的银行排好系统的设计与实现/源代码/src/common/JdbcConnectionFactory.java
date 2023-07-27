package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JdbcConnectionFactory {
	private static String dbUrl;

	private static String dbDriver;

	private static String user;

	private static String password;
	static {
		try {
			/*
			 * Properties pro = new Properties(); FileInputStream fis = new
			 * FileInputStream( "common/jdbcinfo.properties"); pro.load(fis);
			 * dbUrl = pro.getProperty("url"); dbDriver =
			 * pro.getProperty("driver"); user = pro.getProperty("user");
			 * password = pro.getProperty("password");
			 */
			Properties pro = new Properties();
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("src/config.xml");

			// 得到根节点
			Node root = doc.getDocumentElement();
			System.out.println(root.getNodeName());
			NodeList list = root.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node childNode = list.item(i);
				if (childNode instanceof Element) {
					Element element = (Element) childNode;
					String elementName = element.getNodeName();
					String elementValue = element.getFirstChild()
							.getNodeValue();
					pro.setProperty(elementName, elementValue);
				}
			}
			dbUrl = pro.getProperty("url");
			dbDriver = pro.getProperty("driver");
			user = pro.getProperty("user");
			password = pro.getProperty("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection(dbUrl, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
