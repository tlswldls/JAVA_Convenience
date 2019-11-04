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

class UserPanel extends JPanel{	//메인 페이지에서 사용자 모드를 선택하면 접근 가능한 유저 패널
	private UserFrame frame;		
	JTextPane textPane = new JTextPane();	//현재 모드를 알려주는 텍스트팬
	JTextPane textPane_1 = new JTextPane();	//안내 문구를 출력하는 텍스트팬2
	JButton toBuyPanelButton = new JButton("상품 구매");	//상품구매를 진행할 수 있는 패널로 이동하는 버튼
	JButton button_1 = new JButton("뒤로 가기");	//메인페이지로 되돌아갈 수 있는 뒤로 가기 버튼

	public UserPanel(UserFrame frame) {
		this.frame = frame;
		setLayout(null);
		
		textPane.setText("사용자 모드");
		textPane.setFont(new Font("나눔바른펜", Font.PLAIN, 12));
		textPane.setBackground(SystemColor.menu);
		textPane.setBounds(189, 38, 93, 21);
		add(textPane);
		
		textPane_1.setText("원하시는 작업을 선택해 주세요.");
		textPane_1.setFont(new Font("나눔바른펜", Font.PLAIN, 20));
		textPane_1.setBackground(SystemColor.menu);
		textPane_1.setBounds(108, 65, 225, 29);
		add(textPane_1);
		
		toBuyPanelButton.setBounds(139, 119, 166, 23);
		add(toBuyPanelButton);
		toBuyPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==toBuyPanelButton) {
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
	//테이블을 출력하기 위한 데이터들
	String columnNames[] = {"상품 번호","카테고리", "이름", "가격", "재고"};
	DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
	JTable table = new JTable(dtm);
	JScrollPane scrollPane = new JScrollPane();
	//안내 문구를 출력하기 위한 textPane
	JTextPane userModeText = new JTextPane();
	JTextPane buyGoodsText = new JTextPane();
	JTextPane noticeText = new JTextPane();
	//구매 물품 총 가격 확인, 상품 구매, 취소 등의 액션을 진행할 수 있는 버튼들
	JButton buttonYes = new JButton("네");
	JButton buttonNo = new JButton("아니오");
	JButton buttonBack = new JButton("뒤로가기");
	JButton buttonCheck = new JButton("확인");
	//사용자가 입력한 값을 읽어오기 위한 텍스트 필드
	JTextField text = new JTextField();		
	JTextField text_1 = new JTextField();
	//사용자가 입력해야 하는 값에 대한 안내를 출력하는 라벨
	JLabel lblNewLabel = new JLabel("상품 번호: ");
	JLabel cntlabel = new JLabel("개수: ");
	//손님이 지불해야 하는 총 금액을 나타내는 totalPrice
	int totalPrice = 0;
	

	public BuyPanel(UserFrame frame) {
		this.frame = frame;
		setLayout(null);
		
		Management manager = MainFrame.returnManager();
		for(int i = 0; i<manager.getNum(); i++) {
			String category = manager.getGoodsList()[i].getcategory();
			String name = manager.getGoodsList()[i].getname();
			int price = manager.getGoodsList()[i].getprice();
			int stock = manager.getGoodsList()[i].getstock();
			
			Object [] newrow = {i, category, name, (int)price, (int)stock};
			dtm.addRow(newrow);
		}

		
		userModeText.setText("사용자 모드");
		userModeText.setFont(new Font("나눔바른펜", Font.PLAIN, 12));
		userModeText.setBackground(SystemColor.menu);
		userModeText.setBounds(233, 10, 65, 21);
		add(userModeText);
		
		buyGoodsText.setText("상품 구매");
		buyGoodsText.setFont(new Font("나눔바른펜", Font.PLAIN, 20));
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
				noticeText.setText("총	"+totalPrice+"원입니다. 구매하시겠습니까?");
			}
		});
		add(buttonYes);
		
		buttonNo.setBounds(297, 326, 97, 23);
		buttonNo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				text.setText("");
				text_1.setText("");
				totalPrice = 0;
				noticeText.setText("총	"+totalPrice+"원입니다. 구매하시겠습니까?");
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
					
					noticeText.setText("총	"+totalPrice+"원입니다. 구매하시겠습니까?");
			}
		});
		
		
		noticeText.setText("총	"+totalPrice+"원입니다. 구매하시겠습니까?");
		noticeText.setFont(new Font("나눔바른펜", Font.PLAIN, 20));
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
		
		userFrame.setTitle("*편의점 프로그램*");
		userFrame.userPanel = new UserPanel(userFrame);
		userFrame.buyPanel = new BuyPanel(userFrame);
		
		userFrame.add(userFrame.userPanel);		
		userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		userFrame.setSize(450, 300);
		userFrame.setVisible(true);
	}

}
