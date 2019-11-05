import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;

class Dialog extends JDialog{
	JButton ok = new JButton("OK");
	JLabel caution = new JLabel();
	
	public Dialog(JFrame frame, String title, String text) {
		super(frame,title);
		setLayout(new FlowLayout());
		caution.setText(text);
		add(caution);
		add(ok);
		setSize(300,100);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}

public class MainFrame extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("*편의점 프로그램*");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("사용자");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame.main(null);
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton.setBounds(145, 107, 130, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("관리자");
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton_1.setBounds(145, 140, 130, 23);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManagerFrame.main(null);
			}
		});

		
		JButton button = new JButton("종료");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		button.setFont(new Font("굴림", Font.PLAIN, 13));
		button.setBounds(163, 199, 97, 23);
		panel.add(button);
		
		JLabel lblNewLabel = new JLabel("원하시는 작업을 선택해 주세요.");
		lblNewLabel.setFont(new Font("나눔바른펜", Font.PLAIN, 20));
		lblNewLabel.setBounds(99, 48, 229, 38);
		panel.add(lblNewLabel);
		
		//기존의 데이터를 가져오기 위한 FileInputStream in과 매니저 객체
		

	}
	
	public static Management returnManager() {
		Management manager = null;
		FileInputStream in = null;
		JFrame dialogframe = new JFrame();
		Dialog dialog;

		dialog = new Dialog(dialogframe, "주의", "데이터 불러오기에 실패했습니다. 새로운 데이터를 생성합니다.");
		
		try {
			in = new FileInputStream("information.txt");
		} catch (Exception e) {
			System.out.print("파일 읽기에 실패했습니다.");
			manager = new Management();
			dialog.setVisible(true);
		}
		
		try {
			manager = new Management(in);
		} catch (Exception e1) {
			System.out.println("파일읽기에 실패했습니다.");
			manager = new Management();
			dialog.setVisible(true);
		}
		
		
		return manager;
	}
}
