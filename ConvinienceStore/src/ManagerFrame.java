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
	//새로운 물품을 추가하기 위해 입력받는 물품 정보 텍스트필드
	private JTextField goodsNameInput;
	private JTextField goodsCategoryInput;
	private JTextField goodsPriceInput;
	private JTextField goodsStockInput;
	//테이블을 만들기 위한 데이터들
	FileInputStream in = null;
	Management manager = MainFrame.returnManager();
	String columnNames[] = {"상품 번호","카테고리", "이름", "가격", "재고"};
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
		setTitle("*편의점 프로그램*");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//기존의 데이터 읽어와서 테이블에 출력하기
		/*
		try {
			in = new FileInputStream("information.txt");
		} catch (Exception e1) {
			System.out.print("파일 읽기에 실패했습니다.");
		}
		
		try {
			manager = 
		} catch (Exception e1) {
			System.out.println("파일읽기에 실패했습니다.");
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
		

		//사용자가 원하는 작업을 할 수 있도록 현재 모드와 취해야할 액션에 대한 안내를 해주는 textPane
		JTextPane textPane = new JTextPane();
		textPane.setText("관리자 모드");
		textPane.setFont(new Font("나눔바른펜", Font.PLAIN, 12));
		textPane.setBackground(SystemColor.menu);
		textPane.setBounds(185, 10, 65, 21);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("원하시는 작업을 선택해 주세요.");
		textPane_1.setFont(new Font("나눔바른펜", Font.PLAIN, 20));
		textPane_1.setBackground(SystemColor.menu);
		textPane_1.setBounds(104, 30, 236, 29);
		contentPane.add(textPane_1);
		
		//상품을 추가하는 이벤트를 진행하도록 하는 버튼
		JButton btnNewButton = new JButton("상품 추가");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//버튼이 눌리면 새로운 패널을 만들어 추가하고 새로운 상품을 추가하기 위한 텍스트필드와 버튼 텍스트팬이 생긴다
				JPanel addPanel = new JPanel();
				addPanel.setBounds(0, 266, 435, 164);
				contentPane.add(addPanel);
				addPanel.setLayout(null);
				
				JLabel goodsNameLabel = new JLabel("상품 이름: ");
				goodsNameLabel.setBounds(34, 49, 79, 15);
				addPanel.add(goodsNameLabel);
				
				JLabel goodsCategoryLabel = new JLabel("카테고리: ");
				goodsCategoryLabel.setBounds(34, 74, 79, 15);
				addPanel.add(goodsCategoryLabel);
				
				JLabel goodsPriceLabel = new JLabel("상품 가격: ");
				goodsPriceLabel.setBounds(34, 99, 79, 15);
				addPanel.add(goodsPriceLabel);
				
				JLabel goodsStockLabel = new JLabel("상품 재고: ");
				goodsStockLabel.setBounds(34, 124, 79, 15);
				addPanel.add(goodsStockLabel);
				
				JTextPane textPane_2 = new JTextPane();
				textPane_2.setBounds(121, 10, 236, 29);
				addPanel.add(textPane_2);
				textPane_2.setText("상품 정보를 입력해 주세요.");
				textPane_2.setFont(new Font("나눔바른펜", Font.PLAIN, 20));
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
				
				//추가 버튼을 누르면 입력한 값을 읽어와 객체를 만들어 goodsList에 추가하고 일차원 배열을 만들어 테이블에도 출력한다.
				JButton addButton = new JButton("추가");
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
						
						//추가가 완료되면 사용한 패널을 다시 숨긴다.
						addPanel.setVisible(false);
					}
				});

				//상품 추가를 취소한 경우
				JButton cancelButton = new JButton("취소");
				cancelButton.setBounds(310, 97, 97, 23);
				addPanel.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//입력한 모든 값을 없애고
						goodsNameInput.setText("");
						goodsCategoryInput.setText("");
						goodsPriceInput.setText("");
						goodsStockInput.setText("");
						
						//패널을 숨긴다.
						addPanel.setVisible(false);
					}
				});

				
			}
		});
		btnNewButton.setBounds(326, 80, 97, 23);
		contentPane.add(btnNewButton);
		
		//상품 내용에 변동이 있을 경우 클릭하면 조회하는 이벤트를 진행하는 button
		JButton button = new JButton("상품 조회");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//클릭되면 dtm을 비우고
				dtm = new DefaultTableModel(columnNames,0);
				//manager에서 goodsList를 다시 가져와 테이블을 다시 그려준다.
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
		
		//삭제하고자 하는 상품이 있을 경우 클릭하면 이벤트를 진행하는 button_1
		JButton button_1 = new JButton("상품 삭제");
		button_1.setBounds(326, 146, 97, 23);
		contentPane.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//버튼이 눌리면 해당 이벤트를 진행하기 위한 패널과 텍스트필드, 텍스트 팬, 버튼들이 생긴다.
				JPanel panel = new JPanel();
				panel.setBounds(12, 271, 411, 147);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel deleteLabel = new JLabel("삭제하고자 하는 물품의 번호를 입력하세요.");
				deleteLabel.setFont(new Font("나눔바른펜", Font.PLAIN, 20));
				deleteLabel.setBounds(49, 10, 350, 27);
				panel.add(deleteLabel);
				
				JTextPane textPane_2 = new JTextPane();
				textPane_2.setText("상품 번호: ");
				textPane_2.setFont(new Font("굴림", Font.PLAIN, 14));
				textPane_2.setBackground(SystemColor.menu);
				textPane_2.setBounds(12, 68, 85, 21);
				panel.add(textPane_2);
				
				JTextField textField = new JTextField();
				textField.setColumns(10);
				textField.setBounds(90, 68, 160, 21);
				panel.add(textField);
				
				JButton deleteButton = new JButton("삭제");
				deleteButton.setBounds(320, 56, 79, 23);
				panel.add(deleteButton);
				deleteButton.addActionListener(new ActionListener() {//삭제 버튼이 눌리면
					public void actionPerformed(ActionEvent e) {
						//상품 번호로 입력받은 값을 아이템 넘버로 갖는 상품을 찾아 테이블과 goodsList에서 삭제한다.
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
						//삭제 작업이 완료된 다음 패널을 숨긴다.
						panel.setVisible(false);
					}
				});
				
				JButton deleteCancelButton = new JButton("취소");
				deleteCancelButton.setBounds(320, 103, 79, 23);
				panel.add(deleteCancelButton);
				deleteCancelButton.addActionListener(new ActionListener() {//관리가자 삭제 작업을 취소하면
					public void actionPerformed(ActionEvent e) {
						//패널을 숨긴다
						panel.setVisible(false);
					}
				});
				

			}
		});
		
		JButton button_2 = new JButton("저장");//작업한 데이터를 저장하는 작업을 진행하는 button_2
		button_2.setBounds(326, 200, 97, 23);
		contentPane.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//버튼이 눌리면 작업한 내용을 저장한다.
				FileOutputStream out = null;
				try {
					//fOut에 information.txt파일 저장
					out = new FileOutputStream("information.txt");
					//out에 객체의 프로그램 실행 내용을 저장
					manager.saveFile(out);
				}
				catch(IOException e1) {
					System.out.print("출력중 에러가 발생했습니다.");
				}
				catch(Exception e1) {	//출력을 하는 과정에서 에러가 발생한 경우
					System.out.println("저장에 실패했습니다.");
				}

			}
		});
		
		JButton button_3 = new JButton("뒤로가기");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_3.setBounds(326, 233, 97, 23);
		contentPane.add(button_3);
		
	}
}
