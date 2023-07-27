package demo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import pojo.Work;

import common.exception.transaction.BeanFactory;

import dao.IWorkDao;

public class TableWorkDemo {
	private JFrame frame;

	private Container contentPane;

	private JButton btn;

	// private Object data[][]=new Object[4][4];
	/*
	 * private Customer co[]=new Customer[10]; private int size;
	 */
	public TableWorkDemo() {

		frame = new JFrame("员工处理情况");
		frame.setBounds(300, 100, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = frame.getContentPane();
		frame.setVisible(true);
		initGUI();
	}

	public void initGUI() {
		contentPane.setLayout(new BorderLayout());
		btn = new JButton("确定");
		JPanel p = new JPanel(new FlowLayout());
		p.add(btn);
		String colHeads[] = { "服务台编号", "服务类型", "处理时间", "顾客票号" };
		IWorkDao dao = (IWorkDao) BeanFactory.getPojo("workDao");
		List<Work> list = dao.loadAllWork();
		Object data[][] = new Object[list.size()][4];
		for (int i = 0; i < list.size(); i++) {

			data[i][0] = list.get(i).getWorkId();
			data[i][1] = list.get(i).getType();
			if (list.get(i).getType() == 1) {
				data[i][1] = "vip用户";
			} else {
				data[i][1] = "普通用户";
			}
			data[i][2] = list.get(i).getProcessDate();
			data[i][3] = list.get(i).getNextId();
		}
		JTable table = new JTable(data, colHeads);
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane jsp = new JScrollPane(table, v, h);
		contentPane.add(BorderLayout.CENTER, jsp);
		contentPane.add(BorderLayout.SOUTH, p);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
	}

}
