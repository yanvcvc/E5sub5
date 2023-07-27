package demo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pojo.Customer;
import pojo.Work;

import common.exception.transaction.BeanFactory;

import dao.ICustomerDao;
import dao.IWorkDao;

/**
 * *号普通业务网柜台UI
 * @author KAdmin
 *
 */
public class ProcessClient {
	private JFrame f;

	private Container c;

	private JButton startBtn;

	private JButton nextBtn;

	private JButton stopBtn;

	private JLabel lbl;
	
	// 柜台ID号
	private int id;

	private PrintWriter pw;

	private BufferedReader br;

	private Socket socket;

	private JMenuItem ct;

	private JMenuItem c2;

	private JMenuItem c3;

	private JMenuItem drop;

	private JMenu dropCu;

	private JMenuItem About;

	private JMenuItem dropVip;

	private JMenuItem dropNormal;

	private String name;

	private JMenuItem firstProcess;

	private JMenuItem workProcess;

	public ProcessClient(int id) {
		this.id = id;

		if (id == 1)
			f = new JFrame("1号vip专柜");
		else
			f = new JFrame(id + "号普通业务柜台");

		f.setBounds(id * 300 - 100, 80, 300, 200);
		f.addWindowListener(new WorkClose());
		c = f.getContentPane();

		Properties pro = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream("src/peizhi.properties");
			pro.load(fis);
			name = pro.getProperty("name");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JMenuBar menuBar = new JMenuBar();
		JMenu count = new JMenu("统计");
		JMenu delete = new JMenu("移除");
		JMenu first = new JMenu("显示");
		JMenu AboutMenu = new JMenu("关于");

		menuBar.add(count);
		menuBar.add(delete);
		menuBar.add(first);
		menuBar.add(AboutMenu);

		ct = new JMenuItem("总取票数");
		JMenu c1 = new JMenu("未处理票数");
		c2 = new JMenuItem("vip用户");
		c3 = new JMenuItem("普通用户");

		count.add(ct);
		count.add(c1);
		c1.add(c2);
		c1.add(c3);

		drop = new JMenuItem("移除所有");
		dropCu = new JMenu("选择移除");
		dropVip = new JMenuItem("vip");
		dropNormal = new JMenuItem("Normal");

		delete.add(drop);
		delete.add(dropCu);
		dropCu.add(dropVip);
		dropCu.add(dropNormal);

		firstProcess = new JMenuItem("顾客显示");
		workProcess = new JMenuItem("员工处理显示");
		first.add(firstProcess);
		first.add(workProcess);

		About = new JMenuItem("版本");
		AboutMenu.add(About);

		f.setJMenuBar(menuBar);

		lbl = new JLabel();
		startBtn = new JButton("开始受理");
		nextBtn = new JButton("下一位顾客");
		stopBtn = new JButton("停止受理");
		nextBtn.setEnabled(false);
		stopBtn.setEnabled(false);

		JPanel p = new JPanel();
		p.add(startBtn);
		p.add(nextBtn);
		p.add(stopBtn);
		c.add(lbl, BorderLayout.CENTER);
		c.add(p, BorderLayout.SOUTH);

		MyActionListener listener = new MyActionListener();
		startBtn.addActionListener(listener);
		nextBtn.addActionListener(listener);
		stopBtn.addActionListener(listener);
		ct.addActionListener(listener);
		c2.addActionListener(listener);
		c3.addActionListener(listener);
		drop.addActionListener(listener);
		dropVip.addActionListener(listener);
		dropNormal.addActionListener(listener);
		firstProcess.addActionListener(listener);
		workProcess.addActionListener(listener);
		About.addActionListener(listener);

		f.setVisible(true);

		init();
	}

	public void init() {
		try {
			socket = new Socket("localhost", 2000);

			br = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == startBtn || e.getSource() == nextBtn
					|| e.getSource() == stopBtn) {
				startBtn.setEnabled(false);
				nextBtn.setEnabled(true);
				stopBtn.setEnabled(true);

				try {

					// vip 业务
					if (id == 1){
						pw.println("vip" + id);
						ICustomerDao dao = (ICustomerDao) BeanFactory
								.getPojo("customerDao");
						IWorkDao workdao = (IWorkDao) BeanFactory
								.getPojo("workDao");

						int num = Integer.parseInt(br.readLine());
						if (num > 0) {
							Work work = new Work(1, 1, num);
							workdao.saveWork(work);
							
							// 更新处理kallen
							Customer customer = new Customer(num, 1);
							dao.update(customer);
							
							List<Customer> list = dao.findUprocess(1);
							int num1 = list.size();
							lbl.setText("正在为Vip业务" + num + "号顾客办理业务！" + "还有"
									+ num1 + "人等待");

						} else {
							lbl.setText("没有客户需被处理");

							stopBtn.setEnabled(false);
							nextBtn.setEnabled(false);
							startBtn.setEnabled(true);

						}
					}

					
					// 普通业务
					if (id != 1) {

						pw.println("normals" + id);
						String temp = br.readLine();
						int index = 0;
						int num = 0;

						if ((index = temp.indexOf("vip")) != -1) {

							ICustomerDao dao = (ICustomerDao) BeanFactory
									.getPojo("customerDao");
							IWorkDao workdao = (IWorkDao) BeanFactory
									.getPojo("workDao");

							num = Integer.parseInt(temp.substring(index + 3));

							if (num > 0) {

								Work work = new Work(id, 1, num);
								workdao.saveWork(work);

								List<Customer> list = dao.findUprocess(1);
								int num1 = list.size();

								lbl.setText("正在为Vip业务" + num + "号顾客办理业务！"
										+ "还有" + num1 + "人等待");

							} else {
								lbl.setText("没有客户需被处理");
								stopBtn.setEnabled(false);
								nextBtn.setEnabled(false);
								startBtn.setEnabled(true);
							}

						} else if ((index = temp.indexOf("normals")) != -1) {

							ICustomerDao dao = (ICustomerDao) BeanFactory
									.getPojo("customerDao");
							IWorkDao workdao = (IWorkDao) BeanFactory
									.getPojo("workDao");

							num = Integer.parseInt(temp.substring(index + 7));

							if (num > 0) {
								// 处理业务
								Work work = new Work(id, 2, num);
								workdao.saveWork(work);
								
								// 更新处理kallen
								Customer customer = new Customer(num, 2);
								dao.update(customer);
								
								// 查找未处理的记录
								List<Customer> list = dao.findUprocess(2);
								int num1 = list.size();
								// num1--;
								// System.out.println(num1);

								lbl.setText("正在为普通业务" + num + "号顾客办理业务！" + "还有"
										+ num1 + "人等待");

							} else {
								lbl.setText("没有客户需被处理");
								stopBtn.setEnabled(false);
								nextBtn.setEnabled(false);
								startBtn.setEnabled(true);
							}

						}
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				if (e.getSource() == stopBtn) {
					startBtn.setEnabled(true);
					nextBtn.setEnabled(false);
					stopBtn.setEnabled(false);
				}
			}

			if (e.getSource() == ct) {
				ICustomerDao dao = (ICustomerDao) BeanFactory
						.getPojo("customerDao");
				List<Customer> list = dao.loadAllUsers();
				int num = list.size();
				lbl.setText("总取票人数为：" + num);
			}
			if (e.getSource() == c2) {
				ICustomerDao dao = (ICustomerDao) BeanFactory
						.getPojo("customerDao");
				List<Customer> list = dao.findUprocess(1);
				int num = list.size();
				lbl.setText("未处理的vip用户为：" + num);
				if (num == 0)
					lbl.setText("没有人等待");
			}
			if (e.getSource() == c3) {
				ICustomerDao dao = (ICustomerDao) BeanFactory
						.getPojo("customerDao");
				List<Customer> list = dao.findUprocess(2);
				int num = list.size();

				lbl.setText("未处理的普通用户为：" + num);
				if (num == 0)
					lbl.setText("没有人等待");
			}
			if (e.getSource() == drop) {
				ICustomerDao dao = (ICustomerDao) BeanFactory
						.getPojo("customerDao");
				dao.delete(1);
				dao.delete(2);
				int num = dao.findAllCustomer(1);
				int num1 = dao.findAllCustomer(2);
				if ((num + num1) == 0)
					lbl.setText("已删除");
				else
					lbl.setText("删除失败");
			}
			if (e.getSource() == dropVip) {
				ICustomerDao dao = (ICustomerDao) BeanFactory
						.getPojo("customerDao");
				String panel = JOptionPane.showInputDialog("输入号票编号");

				try {
					if (panel == null) {
						throw new Exception("b");
					}
					int num = Integer.valueOf(panel);
					dao.deleteRecord(1, num);
					Customer customer = dao.findCustomer(num, 1);
					if (customer == null)
						lbl.setText("已删除");
					else
						lbl.setText("删除失败");

				} catch (Exception e1) {
					e1.getMessage();
				}

			}
			if (e.getSource() == dropNormal) {
				ICustomerDao dao = (ICustomerDao) BeanFactory
						.getPojo("customerDao");
				String panel = JOptionPane.showInputDialog("输入号票编号");
				try {

					if (panel == null) {
						throw new Exception("不能为空");
					}
					int num = Integer.valueOf(panel);
					dao.deleteRecord(2, num);
					Customer customer = dao.findCustomer(num, 1);
					if (customer == null)
						lbl.setText("已删除");
					else
						lbl.setText("删除失败");
				} catch (Exception e1) {
					e1.getMessage();
				}
			}
			if (e.getSource() == firstProcess) {
				// ICustomerDao
				// dao=(ICustomerDao)BeanFactory.getPojo("customerDao");
				new TableDemo();
			}
			// 员工处理显示
			if (e.getSource() == workProcess) {
				new TableWorkDemo();
			}
			if (e.getSource() == About) {
				JOptionPane.showOptionDialog(null, "产品名称：" + name + "\n"
						+ "程序设计者: 韩超 \n" + "山西大学商务学院所有\n", "关于JNotePad",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, null, null);
			}

		};

	}

	class WorkClose extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			pw.println("end");
			System.exit(0);
		}
	};

	/*
	 * public static void main(String args[]) { new
	 * ProcessClient(Integer.parseInt(args[0])); }
	 */
}