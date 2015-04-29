import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;


public class InstructionsScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstructionsScreen frame = new InstructionsScreen();
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
	public InstructionsScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setFont(new Font("Trajan Pro", Font.PLAIN, 20));
		lblInstructions.setForeground(Color.WHITE);
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setBounds(151, 11, 183, 57);
		contentPane.add(lblInstructions);
		
		JLabel lblClickStuff = new JLabel("As the shapes appear, click the correct shapes as fast ");
		lblClickStuff.setFont(new Font("Trajan Pro", Font.PLAIN, 14));
		lblClickStuff.setForeground(Color.WHITE);
		lblClickStuff.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickStuff.setBounds(10, 79, 464, 30);
		contentPane.add(lblClickStuff);
		
		JLabel lblAsPossibleThe = new JLabel("as possible. The faster you click the shapes, the more");
		lblAsPossibleThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsPossibleThe.setForeground(Color.WHITE);
		lblAsPossibleThe.setFont(new Font("Trajan Pro", Font.PLAIN, 14));
		lblAsPossibleThe.setBounds(10, 104, 464, 30);
		contentPane.add(lblAsPossibleThe);
		
		JLabel lblPointsYouGet = new JLabel(" points you get. If the shapes are not clicked within");
		lblPointsYouGet.setForeground(Color.WHITE);
		lblPointsYouGet.setFont(new Font("Trajan Pro", Font.PLAIN, 14));
		lblPointsYouGet.setBounds(20, 137, 454, 14);
		contentPane.add(lblPointsYouGet);
		
		JLabel lblACertainTime = new JLabel(" a certain time, they will disappear. ");
		lblACertainTime.setFont(new Font("Trajan Pro", Font.PLAIN, 14));
		lblACertainTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblACertainTime.setForeground(Color.WHITE);
		lblACertainTime.setBounds(30, 162, 444, 14);
		contentPane.add(lblACertainTime);
	}
}
