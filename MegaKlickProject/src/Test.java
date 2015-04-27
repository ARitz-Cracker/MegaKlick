import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;


public class Test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(119, 200, 200, 50);
		contentPane.add(label);
		
		JButton helloButton = new JButton("Hello.");
		
		

		helloButton.setBorder(BorderFactory.createEmptyBorder());
		helloButton.setContentAreaFilled(false);
		BufferedImage buttonIcon;
		try {
			buttonIcon = ImageIO.read(new File("C:/Users/Henry/git/MegaKlick/content/circ.png"));
			helloButton = new JButton(new ImageIcon(buttonIcon));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		helloButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label.setText("Hi.");
			}
		});
		helloButton.setBounds(10, 11, 64, 64);
		contentPane.add(helloButton);
		
		
		
		
		
	}
}
