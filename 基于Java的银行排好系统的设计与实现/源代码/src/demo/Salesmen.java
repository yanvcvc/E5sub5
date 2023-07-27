package demo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import pojo.WorkMen;

import common.exception.transaction.BeanFactory;

import dao.IWorkDao;

/**
 * 业务员登陆实现
 * @author KAdmin
 */
public class Salesmen {
	private JFrame frame;
	private Container contentPane;

	private JButton button1;
	private JButton button2;
	private JLabel label1;
	private JLabel label2;
	private JTextField jText1;
	private JPasswordField jpassword;
	private int id;

	public Salesmen() {
		frame = new JFrame("营业员登录");
		setUpComponent();
		setUpListener();
		frame.setBounds(300, 300, 400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	private void setUpListener() {

		button1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				IWorkDao dao = (IWorkDao) BeanFactory.getPojo("workDao");
				try {
					WorkMen work = dao.find(jText1.getText());
					// System.out.println(work);
					System.out.println(jText1.getText());
					System.out.println(jpassword.getPassword());
					System.out.println(work.getPassword());
					if (work == null) {
						JOptionPane.showMessageDialog(null, "用户名不存在", "消息框",
								JOptionPane.OK_OPTION);
					} else if (!jpassword.getText().trim().equals(
							work.getPassword())) {
						JOptionPane.showMessageDialog(null, "密码不正确", "消息框",
								JOptionPane.OK_OPTION);

					} else {

						try {
							String panel = JOptionPane.showInputDialog(null,
									"输入服务台编号", "请输入",
									JOptionPane.DEFAULT_OPTION);
							System.out.println(panel + "*");
							if (panel == null || panel.trim().length() == 0) {
								// if(JOptionPane.OPTION_TYPE_PROPERTY==JOptionPane.YES_NO_OPTION)
								JOptionPane.showMessageDialog(null, "不能为空",
										"消息框", JOptionPane.OK_OPTION);
							} else {

								int num = Integer.valueOf(panel);

								// JOptionPane.showMessageDialog(null,"不能为空","消息框",JOptionPane.OK_OPTION);
								new ProcessClient(num);
								frame.dispose();
							}
						} catch (Exception e1) {
							e1.getMessage();
						}

					}

				} catch (Exception e1) {
					e1.getMessage();
				}

			}

		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}

		});

	}

	private void close() {
		System.exit(0);

	}

	private void setUpComponent() {
		// 按钮
		button1 = new JButton("登录");
		button2 = new JButton("退出");
		// 标签
		label1 = new JLabel("用户名:");
		label2 = new JLabel("密   码:");
		// 文本框
		jText1 = new JTextField(10);
		jpassword = new JPasswordField(10);

		frame.add(button1);
		frame.add(button2);
		frame.add(label1);
		frame.add(label2);
		frame.add(jText1);
		frame.add(jpassword);
		Container contentPane = frame.getContentPane();

		contentPane.setLayout(new BorderLayout());
		JPanel p = new JPanel(new GridLayout(3, 1));
		JPanel p1 = new JPanel(new FlowLayout());
		JPanel p2 = new JPanel(new FlowLayout());
		JPanel p3 = new JPanel(new FlowLayout());

		p1.add(button1);
		p1.add(button2);
		p2.add(label1);
		p2.add(jText1);
		p3.add(label2);
		p3.add(jpassword);

		contentPane.add(p1, BorderLayout.SOUTH);
		contentPane.add(p2, BorderLayout.NORTH);
		contentPane.add(p3, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		new Salesmen();
	}
}
