package demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import javax.swing.JLabel;

import pojo.Customer;
import pojo.Work;
import servicel.ICustomerService;

import common.exception.transaction.BeanFactory;

import dao.ICustomerDao;

public class SocketThread extends Thread {
	private Socket socket;

	// private CustomerType customers;

	private JLabel lblMain;

	public SocketThread(Socket socket, JLabel lblMain) {
		this.socket = socket;
		// this.customers = customers;
		this.lblMain = lblMain;
	}

	public void run() {
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			br = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(), true);
			String temp = null;
			int index = 0;
			while (!(temp = br.readLine()).equals("end")) {
				ICustomerService service = (ICustomerService) BeanFactory
						.getPojo("customerService");
				ICustomerDao dao = (ICustomerDao) BeanFactory
						.getPojo("customerDao");

				if ((index = temp.indexOf("vip")) != -1) {

					int num = service.unProcess(1);
					if (num > 0) {
						pw.println(num + "");

						lblMain.setText("请Vip业务" + num + "号顾客到"
								+ temp.substring(index + 3) + "号Vip专柜办理业务!");

						service.process(num, 1);
					} else {
						pw.println(num + "");
						lblMain.setText("");
					}

				} else if ((index = temp.indexOf("normals")) != -1) {

					List<Customer> list = dao.findUprocess(1);
					if (list.size() >= 2) {

						int num = service.unProcess(1);
						if (num > 0) {
							pw.println("vip" + num);

							lblMain.setText("请Vip业务" + num + "号顾客到"
									+ temp.substring(index + 7) + "号普通专柜办理业务!");
							service.process(num, 1);
						} else {
							pw.println("vip" + num);
							lblMain.setText("");
						}
					} else {
						int num = service.unProcess(2);
						if (num > 0) {
							pw.println("normals" + num);

							lblMain.setText("请普通业务" + num + "号顾客到"
									+ temp.substring(index + 7) + "号普通专柜办理业务!");
							service.process(num, 2);
						} else {
							pw.println("normals" + num);
							lblMain.setText("");
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				pw.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
