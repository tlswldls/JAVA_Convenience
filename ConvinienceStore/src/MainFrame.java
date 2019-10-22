import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setTitle("*������ ���α׷�*");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("�����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame.main(null);
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 15));
		btnNewButton.setBounds(145, 107, 130, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("������");
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 15));
		btnNewButton_1.setBounds(145, 140, 130, 23);
		panel.add(btnNewButton_1);
		
		JButton button = new JButton("����");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 13));
		button.setBounds(163, 199, 97, 23);
		panel.add(button);
		
		JLabel lblNewLabel = new JLabel("���Ͻô� �۾��� ������ �ּ���.");
		lblNewLabel.setFont(new Font("�����ٸ���", Font.PLAIN, 20));
		lblNewLabel.setBounds(99, 48, 229, 38);
		panel.add(lblNewLabel);
	}
}
