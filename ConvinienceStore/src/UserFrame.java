import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.io.*;

class UserPanel extends JPanel{
	private UserFrame frame;		
	JTextPane textPane = new JTextPane();
	JTextPane textPane_1 = new JTextPane();
	JButton button = new JButton("��ǰ ����");
	JButton button_1 = new JButton("�ڷ� ����");

	public UserPanel(UserFrame frame) {
		this.frame = frame;
		setLayout(null);
		
		textPane.setText("����� ���");
		textPane.setFont(new Font("�����ٸ���", Font.PLAIN, 12));
		textPane.setBackground(SystemColor.menu);
		textPane.setBounds(189, 38, 93, 21);
		add(textPane);
		
		textPane_1.setText("���Ͻô� �۾��� ������ �ּ���.");
		textPane_1.setFont(new Font("�����ٸ���", Font.PLAIN, 20));
		textPane_1.setBackground(SystemColor.menu);
		textPane_1.setBounds(108, 65, 225, 29);
		add(textPane_1);
		
		button.setBounds(139, 119, 166, 23);
		add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==button) {
					frame.change("buyPanel");
				}
			}
		});
		
		button_1.setBounds(154, 185, 140, 23);
		add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==button_1) {
					frame.dispose();
				}
			}
		});
	}
}

class BuyPanel extends JPanel{
	private UserFrame frame;			
	FileInputStream in = null;
	Management manager = null;
	String columnNames[] = {"��ǰ ��ȣ","ī�װ�", "�̸�", "����", "���"};
	DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
	JTable table = new JTable(dtm);
	JTextPane userModeText = new JTextPane();
	JTextPane buyGoodsText = new JTextPane();
	JTextPane noticeText = new JTextPane();
	JButton buttonYes = new JButton("��");
	JButton buttonNo = new JButton("�ƴϿ�");
	JButton buttonBack = new JButton("�ڷΰ���");
	JButton buttonCheck = new JButton("Ȯ��");
	JScrollPane scrollPane = new JScrollPane();
	JTextField text = new JTextField();		
	JTextField text_1 = new JTextField();
	JLabel lblNewLabel = new JLabel("��ǰ ��ȣ: ");
	JLabel cntlabel = new JLabel("����: ");
	int totalPrice = 0;
	

	public BuyPanel(UserFrame frame) {
		this.frame = frame;
		setLayout(null);
		
		try {
			in = new FileInputStream("information.txt");
		} catch (Exception e) {
			System.out.print("���� �б⿡ �����߽��ϴ�.");
		}
		
		try {
			manager = new Management(in);
		} catch (Exception e1) {
			System.out.println("�����б⿡ �����߽��ϴ�.");
			manager = new Management();
		}

		for(int i = 0; i<manager.getNum(); i++) {
			String category = manager.getGoodsList()[i].getcategory();
			String name = manager.getGoodsList()[i].getname();
			int price = manager.getGoodsList()[i].getprice();
			int stock = manager.getGoodsList()[i].getstock();
			
			Object [] newrow = {i, category, name, (int)price, (int)stock};
			dtm.addRow(newrow);
		}

		
		userModeText.setText("����� ���");
		userModeText.setFont(new Font("�����ٸ���", Font.PLAIN, 12));
		userModeText.setBackground(SystemColor.menu);
		userModeText.setBounds(233, 10, 65, 21);
		add(userModeText);
		
		buyGoodsText.setText("��ǰ ����");
		buyGoodsText.setFont(new Font("�����ٸ���", Font.PLAIN, 20));
		buyGoodsText.setBackground(SystemColor.menu);
		buyGoodsText.setBounds(224, 29, 74, 29);
		add(buyGoodsText);
		
		buttonYes.setBounds(144, 326, 97, 23);
		buttonYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.parseInt(text.getText());
				int numWanted = Integer.parseInt(text_1.getText());
				int newStock = (int)dtm.getValueAt(i, 4)-numWanted;
				manager.getGoodsList()[i].setstock(newStock);
				dtm.setValueAt(manager.getGoodsList()[i].getstock(), i, 4);
				text.setText("");
				text_1.setText("");
				totalPrice = 0;    
				noticeText.setText("��	"+totalPrice+"���Դϴ�. �����Ͻðڽ��ϱ�?");
			}
		});
		add(buttonYes);
		
		buttonNo.setBounds(297, 326, 97, 23);
		buttonNo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				text.setText("");
				text_1.setText("");
				totalPrice = 0;
				noticeText.setText("��	"+totalPrice+"���Դϴ�. �����Ͻðڽ��ϱ�?");
			}
		});
		add(buttonNo);
		
		buttonBack.setBounds(12, 10, 97, 23);		
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==buttonBack) {
					frame.change("userPanel");
				}
			}
		});
		add(buttonBack);
		
		
		scrollPane.setBounds(12, 68, 511, 154);
		add(scrollPane);
		scrollPane.setViewportView(table);
		

		buttonCheck.setBounds(408, 247, 97, 23);
		add(buttonCheck);
		buttonCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int i = Integer.parseInt(text.getText());
					int numWanted = Integer.parseInt(text_1.getText());
					totalPrice = (int)dtm.getValueAt(i, 3)*numWanted;					
					
					noticeText.setText("��	"+totalPrice+"���Դϴ�. �����Ͻðڽ��ϱ�?");
			}
		});
		
		
		noticeText.setText("��	"+totalPrice+"���Դϴ�. �����Ͻðڽ��ϱ�?");
		noticeText.setFont(new Font("�����ٸ���", Font.PLAIN, 20));
		noticeText.setBackground(SystemColor.menu);
		noticeText.setBounds(115, 287, 328, 29);
		add(noticeText);
		
		text.setText("");
		text.setBounds(100, 248, 116, 21);
		add(text);
		text.setColumns(10);
		
		text_1.setText("");
		text_1.setColumns(10);
		text_1.setBounds(270, 247, 110, 21);
		add(text_1);
		
		lblNewLabel.setBounds(30, 248, 110, 15);
		add(lblNewLabel);
		
		cntlabel.setBounds(233, 249, 57, 15);
		add(cntlabel);
	}
	
}

public class UserFrame extends JFrame {
	
	Management manager = null;
	
	public UserFrame() {
	}

	public UserPanel userPanel = null;;
	public BuyPanel buyPanel = null;
	
	public void change(String panelName) {
		if(panelName.equals("buyPanel")) {
			getContentPane().removeAll();
			setBounds(100, 100, 551, 410);
			getContentPane().add(buyPanel);
			revalidate();
			repaint();
		}else {
			getContentPane().removeAll();
			setBounds(100, 100, 450, 300);
			getContentPane().add(userPanel);
			revalidate();
			repaint();
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UserFrame userFrame = new UserFrame();
		
		userFrame.setTitle("*������ ���α׷�*");
		userFrame.userPanel = new UserPanel(userFrame);
		userFrame.buyPanel = new BuyPanel(userFrame);
		
		userFrame.add(userFrame.userPanel);		
		userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		userFrame.setSize(450, 300);
		userFrame.setVisible(true);
	}

}
