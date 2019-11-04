import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerFrame extends JFrame {

	private JPanel contentPane;
	//���ο� ��ǰ�� �߰��ϱ� ���� �Է¹޴� ��ǰ ���� �ؽ�Ʈ�ʵ�
	private JTextField goodsNameInput;
	private JTextField goodsCategoryInput;
	private JTextField goodsPriceInput;
	private JTextField goodsStockInput;
	//���̺��� ����� ���� �����͵�
	FileInputStream in = null;
	Management manager = MainFrame.returnManager();
	String columnNames[] = {"��ǰ ��ȣ","ī�װ�", "�̸�", "����", "���"};
	DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
	JTable table = new JTable(dtm);


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerFrame frame = new ManagerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManagerFrame() {
		setTitle("*������ ���α׷�*");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//������ ������ �о�ͼ� ���̺� ����ϱ�
		/*
		try {
			in = new FileInputStream("information.txt");
		} catch (Exception e1) {
			System.out.print("���� �б⿡ �����߽��ϴ�.");
		}
		
		try {
			manager = 
		} catch (Exception e1) {
			System.out.println("�����б⿡ �����߽��ϴ�.");
			manager = new Management();
		}
		*/

		for(int i = 0; i<manager.getGoodsList().length; i++) {
			String category = manager.getGoodsList()[i].getcategory();
			String name = manager.getGoodsList()[i].getname();
			int price = manager.getGoodsList()[i].getprice();
			int stock = manager.getGoodsList()[i].getstock();
			
			Object [] newrow = {manager.getGoodsList()[i].getitemNumber(), category, name, (int)price, (int)stock};
			dtm.addRow(newrow);
		}


		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 80, 302, 179);
		contentPane.add(scrollPane);
		

		//����ڰ� ���ϴ� �۾��� �� �� �ֵ��� ���� ���� ���ؾ��� �׼ǿ� ���� �ȳ��� ���ִ� textPane
		JTextPane textPane = new JTextPane();
		textPane.setText("������ ���");
		textPane.setFont(new Font("�����ٸ���", Font.PLAIN, 12));
		textPane.setBackground(SystemColor.menu);
		textPane.setBounds(185, 10, 65, 21);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("���Ͻô� �۾��� ������ �ּ���.");
		textPane_1.setFont(new Font("�����ٸ���", Font.PLAIN, 20));
		textPane_1.setBackground(SystemColor.menu);
		textPane_1.setBounds(104, 30, 236, 29);
		contentPane.add(textPane_1);
		
		//��ǰ�� �߰��ϴ� �̺�Ʈ�� �����ϵ��� �ϴ� ��ư
		JButton btnNewButton = new JButton("��ǰ �߰�");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ư�� ������ ���ο� �г��� ����� �߰��ϰ� ���ο� ��ǰ�� �߰��ϱ� ���� �ؽ�Ʈ�ʵ�� ��ư �ؽ�Ʈ���� �����
				JPanel addPanel = new JPanel();
				addPanel.setBounds(0, 266, 435, 164);
				contentPane.add(addPanel);
				addPanel.setLayout(null);
				
				JLabel goodsNameLabel = new JLabel("��ǰ �̸�: ");
				goodsNameLabel.setBounds(34, 49, 79, 15);
				addPanel.add(goodsNameLabel);
				
				JLabel goodsCategoryLabel = new JLabel("ī�װ�: ");
				goodsCategoryLabel.setBounds(34, 74, 79, 15);
				addPanel.add(goodsCategoryLabel);
				
				JLabel goodsPriceLabel = new JLabel("��ǰ ����: ");
				goodsPriceLabel.setBounds(34, 99, 79, 15);
				addPanel.add(goodsPriceLabel);
				
				JLabel goodsStockLabel = new JLabel("��ǰ ���: ");
				goodsStockLabel.setBounds(34, 124, 79, 15);
				addPanel.add(goodsStockLabel);
				
				JTextPane textPane_2 = new JTextPane();
				textPane_2.setBounds(121, 10, 236, 29);
				addPanel.add(textPane_2);
				textPane_2.setText("��ǰ ������ �Է��� �ּ���.");
				textPane_2.setFont(new Font("�����ٸ���", Font.PLAIN, 20));
				textPane_2.setBackground(SystemColor.menu);
				
				goodsNameInput = new JTextField();
				goodsNameInput.setBounds(107, 49, 191, 21);
				addPanel.add(goodsNameInput);
				goodsNameInput.setColumns(10);
				
				goodsCategoryInput = new JTextField();
				goodsCategoryInput.setBounds(107, 74, 191, 21);
				addPanel.add(goodsCategoryInput);
				goodsCategoryInput.setColumns(10);
				
				goodsPriceInput = new JTextField();
				goodsPriceInput.setBounds(107, 99, 191, 21);
				addPanel.add(goodsPriceInput);
				goodsPriceInput.setColumns(10);
				
				goodsStockInput = new JTextField();
				goodsStockInput.setBounds(107, 124, 191, 21);
				addPanel.add(goodsStockInput);
				goodsStockInput.setColumns(10);
				
				//�߰� ��ư�� ������ �Է��� ���� �о�� ��ü�� ����� goodsList�� �߰��ϰ� ������ �迭�� ����� ���̺��� ����Ѵ�.
				JButton addButton = new JButton("�߰�");
				addButton.setBounds(310, 61, 97, 23);
				addPanel.add(addButton);
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = goodsNameInput.getText();
						String category = goodsCategoryInput.getText();
						int price = Integer.parseInt(goodsPriceInput.getText());
						int stock = Integer.parseInt(goodsStockInput.getText());
						
						manager.insertGoods(new Goods(category, name, price, manager.getcount(), stock));
						Object [] newrow = {manager.getcount(), category, name, (int)price, (int)stock};
						dtm.addRow(newrow);
						goodsNameInput.setText("");
						goodsCategoryInput.setText("");
						goodsPriceInput.setText("");
						goodsStockInput.setText("");
						
						//�߰��� �Ϸ�Ǹ� ����� �г��� �ٽ� �����.
						addPanel.setVisible(false);
					}
				});

				//��ǰ �߰��� ����� ���
				JButton cancelButton = new JButton("���");
				cancelButton.setBounds(310, 97, 97, 23);
				addPanel.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//�Է��� ��� ���� ���ְ�
						goodsNameInput.setText("");
						goodsCategoryInput.setText("");
						goodsPriceInput.setText("");
						goodsStockInput.setText("");
						
						//�г��� �����.
						addPanel.setVisible(false);
					}
				});

				
			}
		});
		btnNewButton.setBounds(326, 80, 97, 23);
		contentPane.add(btnNewButton);
		
		//��ǰ ���뿡 ������ ���� ��� Ŭ���ϸ� ��ȸ�ϴ� �̺�Ʈ�� �����ϴ� button
		JButton button = new JButton("��ǰ ��ȸ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ŭ���Ǹ� dtm�� ����
				dtm = new DefaultTableModel(columnNames,0);
				//manager���� goodsList�� �ٽ� ������ ���̺��� �ٽ� �׷��ش�.
				for(int i = 0; i<manager.getGoodsList().length; i++) {
					String category = manager.getGoodsList()[i].getcategory();
					String name = manager.getGoodsList()[i].getname();
					int price = manager.getGoodsList()[i].getprice();
					int stock = manager.getGoodsList()[i].getstock();
					
					Object [] newrow = {manager.getGoodsList()[i].getitemNumber(), category, name, (int)price, (int)stock};
					dtm.addRow(newrow);
				}

			}
		});
		button.setBounds(326, 113, 97, 23);
		contentPane.add(button);
		
		//�����ϰ��� �ϴ� ��ǰ�� ���� ��� Ŭ���ϸ� �̺�Ʈ�� �����ϴ� button_1
		JButton button_1 = new JButton("��ǰ ����");
		button_1.setBounds(326, 146, 97, 23);
		contentPane.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ư�� ������ �ش� �̺�Ʈ�� �����ϱ� ���� �гΰ� �ؽ�Ʈ�ʵ�, �ؽ�Ʈ ��, ��ư���� �����.
				JPanel panel = new JPanel();
				panel.setBounds(12, 271, 411, 147);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel deleteLabel = new JLabel("�����ϰ��� �ϴ� ��ǰ�� ��ȣ�� �Է��ϼ���.");
				deleteLabel.setFont(new Font("�����ٸ���", Font.PLAIN, 20));
				deleteLabel.setBounds(49, 10, 350, 27);
				panel.add(deleteLabel);
				
				JTextPane textPane_2 = new JTextPane();
				textPane_2.setText("��ǰ ��ȣ: ");
				textPane_2.setFont(new Font("����", Font.PLAIN, 14));
				textPane_2.setBackground(SystemColor.menu);
				textPane_2.setBounds(12, 68, 85, 21);
				panel.add(textPane_2);
				
				JTextField textField = new JTextField();
				textField.setColumns(10);
				textField.setBounds(90, 68, 160, 21);
				panel.add(textField);
				
				JButton deleteButton = new JButton("����");
				deleteButton.setBounds(320, 56, 79, 23);
				panel.add(deleteButton);
				deleteButton.addActionListener(new ActionListener() {//���� ��ư�� ������
					public void actionPerformed(ActionEvent e) {
						//��ǰ ��ȣ�� �Է¹��� ���� ������ �ѹ��� ���� ��ǰ�� ã�� ���̺�� goodsList���� �����Ѵ�.
						int i = Integer.parseInt(textField.getText());
						for(int j=0;j<manager.getGoodsList().length;j++) {
							if(dtm.getValueAt(j, 0).equals(i)) {
								try {
									manager.deleteGoods(j);
									dtm.removeRow(j);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						}
						//���� �۾��� �Ϸ�� ���� �г��� �����.
						panel.setVisible(false);
					}
				});
				
				JButton deleteCancelButton = new JButton("���");
				deleteCancelButton.setBounds(320, 103, 79, 23);
				panel.add(deleteCancelButton);
				deleteCancelButton.addActionListener(new ActionListener() {//�������� ���� �۾��� ����ϸ�
					public void actionPerformed(ActionEvent e) {
						//�г��� �����
						panel.setVisible(false);
					}
				});
				

			}
		});
		
		JButton button_2 = new JButton("����");//�۾��� �����͸� �����ϴ� �۾��� �����ϴ� button_2
		button_2.setBounds(326, 200, 97, 23);
		contentPane.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ư�� ������ �۾��� ������ �����Ѵ�.
				FileOutputStream out = null;
				try {
					//fOut�� information.txt���� ����
					out = new FileOutputStream("information.txt");
					//out�� ��ü�� ���α׷� ���� ������ ����
					manager.saveFile(out);
				}
				catch(IOException e1) {
					System.out.print("����� ������ �߻��߽��ϴ�.");
				}
				catch(Exception e1) {	//����� �ϴ� �������� ������ �߻��� ���
					System.out.println("���忡 �����߽��ϴ�.");
				}

			}
		});
		
		JButton button_3 = new JButton("�ڷΰ���");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_3.setBounds(326, 233, 97, 23);
		contentPane.add(button_3);
		
	}
}
