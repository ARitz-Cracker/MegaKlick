package megak;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextArea;

public class InstructionsScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 757992310391046994L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	void setVisibleThis() {
		this.setVisible(false);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstructionsScreen frame = new InstructionsScreen();
					frame.setVisible(true);
					frame.setResizable(false);
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
		setTitle("MegaKlick - Instructions");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		setBounds(gd.getDisplayMode().getWidth() / 2 - 864 / 2, gd
				.getDisplayMode().getHeight() / 2 - 664 / 2, 864, 664);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setFont(new Font("Trajan Pro", Font.PLAIN, 36));
		lblInstructions.setForeground(Color.WHITE);
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setBounds(286, 187, 276, 37);
		contentPane.add(lblInstructions);

		JTextArea instruction = new JTextArea();
		instruction.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		instruction.setEditable(false);
		instruction.setForeground(Color.WHITE);
		instruction.setBackground(Color.BLACK);
		instruction.setAlignmentY(CENTER_ALIGNMENT);
		instruction
				.setText("The goal of MegaKlick is to survive as long as possible by \nclicking the correct shapes. The correct shape will be \ndesignated by the 'goal' indicator in the top right-hand \ncorner. The game ends when you run out of life points.\n\nClicking a correct shape: +4 life points\nClicking an incorrect shape: -5 life points\nMissing a correct shape: -7 life points");
		instruction.setBounds(180, 260, 558, 187);
		contentPane.add(instruction);

		JButton btnBack = new JButton("");
		btnBack.setBorder(BorderFactory.createEmptyBorder());
		btnBack.setContentAreaFilled(false);
		BufferedImage buttonIcon2;
		try {
			buttonIcon2 = ImageIO.read(new File(
					"resources/whiteTriangle.png"));
			btnBack = new JButton(new ImageIcon(buttonIcon2));
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisibleThis();
				}
			});
			btnBack.setForeground(Color.WHITE);
			btnBack.setFont(new Font("Trajan Pro", Font.PLAIN, 14));
			btnBack.setBounds(392, 430, 64, 64);
			contentPane.add(btnBack);
			btnBack.setBorderPainted(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel lblBack = new JLabel("Back");
		lblBack.setFont(new Font("Trajan Pro", Font.PLAIN, 14));
		lblBack.setForeground(Color.WHITE);
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setBounds(401, 500, 46, 14);
		contentPane.add(lblBack);
	}
}
