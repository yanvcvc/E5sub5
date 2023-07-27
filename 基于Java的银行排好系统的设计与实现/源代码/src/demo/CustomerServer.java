package demo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import pojo.Customer;

import common.exception.transaction.BeanFactory;

import dao.ICustomerDao;

public class CustomerServer extends JFrame {
	private static int i = 0;
	private JFrame frame;

	private JButton button1;

	private JMenu fileMenu;

	private JMenuItem menuOpen;

	private JMenuItem menuClose;

	private JMenu editorMenu;

	private JMenuItem menuService;

	private JMenuItem menuFirst;

	private JMenu AboutMenu;

	private JMenuItem menuAbout;

	private JMenu accountMenu;

	private JMenuItem menuaccount;

	private JPanel panel1;

	private JPanel panel2;

	private JPanel panel3;

	private JLabel label1;

	private JLabel label2;

	private JLabel lvip;

	private JLabel lcommon;

	private JLabel lvipLeave;

	private JLabel lcommonLeave;

	private JButton button2;

	private static JLabel lblNow;

	private JTextField text2;

	private JTextField text3;

	private JPanel panel4;

	private JPanel panel5;

	private JPanel panel6;

	private JPanel panel7;

	private int id;

	private JMenu menuDrop;

	private String name;

	private JTextArea text4;

	private JMenu selectMenu;

	private JMenuItem select;

	private JCheckBoxMenuItem vipMenu;

	private JCheckBoxMenuItem normalMenu;

	public CustomerServer() throws IOException {
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream("src/peizhi.properties");
		pro.load(fis);
		name = pro.getProperty("name");
		System.out.println(name);
		
		frame = new JFrame(name);
		frame.setBounds(400, 400, 600, 400);
		setUpComponent();
		setUpListener();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void setUpListener() {
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TableDemo();
			}
		});
		// 菜单关闭文件
		menuClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFile();
			}

			private void closeFile() {

				int option = JOptionPane.showConfirmDialog(null, "是否退出", null,
						JOptionPane.WARNING_MESSAGE,
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION)
					// frame.dispose();
					System.exit(0);

			}
		});

		vipMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ICustomerDao dao = (ICustomerDao) BeanFactory
						.getPojo("customerDao");

				if (i != 0) {
					System.out.println(i + "*");
					if (vipMenu.getState()) {
						int num = dao.base() - 1;
						i--;
						JOptionPane.showMessageDialog(null, "是否删除？");
						dao.removeRecord(num);
						System.out.println("haah");
						vipMenu.setState(false);
					}
				} else {
					System.out.println(i + "*");
					JOptionPane.showMessageDialog(null, "你还没有申请号码");
					vipMenu.setState(false);
				}
			}
		});
		normalMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ICustomerDao dao = (ICustomerDao) BeanFactory
						.getPojo("customerDao");
				if (i != 0) {
					System.out.println(i + "*");
					if (normalMenu.getState()) {
						int num = dao.base() - 1;
						i--;
						JOptionPane.showMessageDialog(null, "是否删除？");
						dao.removeRecord(num);
						System.out.println("haah");
						normalMenu.setState(false);
					}
				} else {
					System.out.println(i + "*");
					JOptionPane.showMessageDialog(null, "你还没有申请号码");
					normalMenu.setState(false);
				}
			}

		});

		menuaccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ICustomerDao dao = (ICustomerDao) BeanFactory
						.getPojo("customerDao");
				List<Customer> list1 = dao.loadAllUsers();
				/*
				 * int num1 = dao.findAllCustomer(1); int num2 =
				 * dao.findAllCustomer(2);
				 */
				text2.setText("" + list1.size());
				List<Customer> list = dao.findUprocess(1);
				List<Customer> list2 = dao.findUprocess(2);
				text3.setText("" + (list.size() + list2.size()));
			}
		});

		// 菜单关于
		menuAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showOptionDialog(null, "产品名称：" + name + "\n"
						+ "程序设计者: 韩超 \n" + "版权归山西大学商务学院所有\n", "关于JNotePad",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, null, null);
			}
		});

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num;
				ICustomerDao dao = (ICustomerDao) BeanFactory
						.getPojo("customerDao");

				num = dao.nextID(1);
				i++;
				Customer customer = new Customer(num, 1);
				dao.saveID(customer);
				// dao.findCustomer(num, 1);
				List<Customer> list = dao.findUprocess(1);
				int num1 = list.size() - 1;
				lvipLeave.setText("前面还有 " + num1 + " 人排队，请耐心等候");
				text4.setText(name
						+ "\n"
						+ "客户号："
						+ num
						+ "\n"
						+ "vip业务"
						+ "\n"
						+ "取号时间："
						+ (new Date(System.currentTimeMillis()) + "\n"
								+ "您前面还有" + num1 + "人排队等候"));

			}
		});

		// 普通业务
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int num;

				ICustomerDao dao = (ICustomerDao) BeanFactory
						.getPojo("customerDao");

				num = dao.nextID(2);
				i++;
				lcommon.setText("您的号码为普通业务第" + num + "号");

				Customer customer = new Customer(num, 2);
				dao.saveID(customer);
				// dao.findCustomer(num, 2);
				List<Customer> list = dao.findUprocess(2);
				int num2 = list.size() - 1;

				lcommonLeave.setText("前面还有 " + num2 + " 人排队，请耐心等候");

				text4.setText(name
						+ "\n"
						+ "客户号："
						+ num
						+ "\n"
						+ "普通业务"
						+ "\n"
						+ "取号时间："
						+ (new Date(System.currentTimeMillis()) + "\n"
								+ "您前面还有" + num2 + "人排队等候"));

			}
		});

	}

	public void setUpComponent() {

		// 菜单栏
		JMenuBar menubar = new JMenuBar();

		// 设置文件菜单
		fileMenu = new JMenu("设置");
		menuClose = new JMenuItem("关闭");
		menuClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK));
		fileMenu.add(menuClose);

		// 设置编辑菜单
		editorMenu = new JMenu("弃号");
		menuDrop = new JMenu("弃号");
		vipMenu = new JCheckBoxMenuItem("vip", false);
		normalMenu = new JCheckBoxMenuItem("normal", false);

		menuDrop.add(vipMenu);
		menuDrop.add(normalMenu);
		editorMenu.add(menuDrop);

		// 设置统计菜单
		accountMenu = new JMenu("统计");
		menuaccount = new JMenuItem("统计");
		accountMenu.add(menuaccount);

		// 设置查询菜单
		selectMenu = new JMenu("查询");
		select = new JMenuItem("显示");
		selectMenu.add(select);

		// 设置关于菜单
		AboutMenu = new JMenu("关于");
		menuAbout = new JMenuItem("版本");
		AboutMenu.add(menuAbout);

		menubar.add(fileMenu);// 将fileMenu放入menubar中
		menubar.add(editorMenu);
		menubar.add(accountMenu);
		menubar.add(selectMenu);
		menubar.add(AboutMenu);

		frame.setJMenuBar(menubar);// 设置菜单栏

		button1 = new JButton("vip业务");
		button1.setFont(new Font("宋体", Font.PLAIN, 20));
		button1.setHorizontalAlignment(SwingConstants.CENTER);
		
		button2 = new JButton("普通业务");
		button2.setFont(new Font("宋体", Font.PLAIN, 20));
		button2.setHorizontalAlignment(SwingConstants.CENTER);
		label1 = new JLabel("总取票人数");
		label1.setFont(new Font("宋体", Font.PLAIN, 20));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label2 = new JLabel("总等待人数");
		label2.setFont(new Font("宋体", Font.PLAIN, 20));
		label2.setHorizontalAlignment(SwingConstants.CENTER);

		text2 = new JTextField(8);
		text3 = new JTextField(8);
		text4 = new JTextArea(10, 15);
		text4.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		text4.setFont(new Font("Serif", Font.BOLD, 15));
		text4.setLineWrap(true);
		JScrollPane pane = new JScrollPane(text4,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		lvip = new JLabel(" ");
		lcommon = new JLabel(" ");
		lvip.setFont(new Font("Serif", Font.BOLD, 20));
		lcommon.setFont(new Font("Serif", Font.BOLD, 20));
		lvipLeave = new JLabel(" ");
		lcommonLeave = new JLabel(" ");
		lvipLeave.setFont(new Font("Serif", Font.BOLD, 18));
		lcommonLeave.setFont(new Font("Serif", Font.BOLD, 18));
		lblNow = new JLabel("欢迎使用" + name);
		lblNow.setFont(new Font("Serif", Font.BOLD, 20));

		Container content = frame.getContentPane();
		content.setLayout(new BorderLayout());

		JPanel panel = new JPanel(new GridLayout(1, 2));

		panel1 = new JPanel(new FlowLayout());
		panel2 = new JPanel(new FlowLayout());
		panel3 = new JPanel(new FlowLayout());
		panel4 = new JPanel(new GridLayout(3, 1));

		panel.add(panel2);
		panel.add(panel1);
		panel.add(panel3);

		panel1.add(label1);
		panel1.add(text2);
		panel1.add(label2);
		panel1.add(text3);

		panel2.add(lblNow);
		panel3.add(pane);
		panel4.add(button1);
		panel4.add(button2);
		panel3.add(panel4);
		content.add(panel2, BorderLayout.NORTH);
		content.add(panel1, BorderLayout.CENTER);
		content.add(panel3, BorderLayout.SOUTH);

	}

	public static void main(String[] args) throws IOException {
		CustomerServer c = new CustomerServer();

		ServerSocket ss = null;
		Socket socket = null;
		try {
			ss = new ServerSocket(2000);
			while (true) {
				socket = ss.accept();

				SocketThread st = new SocketThread(socket, c.lblNow);// 启动一个线程
				st.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ss != null)
					ss.close();
				if (socket != null)
					socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
