import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	//private JButton instructionsButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				InstructionsScreen instance1 = new InstructionsScreen();
				if(instance1!= null){
					instance1.dispose();
				}
			}
		});
		setTitle("MegaKlick");
		try {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		setBounds(gd.getDisplayMode().getWidth()/2 - 864/2, gd.getDisplayMode().getHeight()/2 - 664/2, 864, 664);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setForeground(Color.WHITE);
		lblInstructions.setBounds(169, 344, 109, 14);
		contentPane.add(lblInstructions);
		
		JLabel instructionText = new JLabel("");
		instructionText.setHorizontalAlignment(SwingConstants.CENTER);
		instructionText.setForeground(Color.WHITE);
		instructionText.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		instructionText.setBounds(197, 478, 454, 50);
		contentPane.add(instructionText);
		
		/*JButton instructionsButton = new JButton();
		instructionsButton.setBorder(BorderFactory.createEmptyBorder());
		instructionsButton.setContentAreaFilled(false);*/
		BufferedImage buttonIcon;
		buttonIcon = ImageIO.read(new File("C:/megaklick/circ.png"));
		JButton instructionsButton = new JButton(new ImageIcon(buttonIcon));
		
		instructionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InstructionsScreen instance1 = new InstructionsScreen();
				instance1.setVisible(true);
			}
		});
		instructionsButton.setBounds(190, 280, 64, 64);
		contentPane.add(instructionsButton);
		
		instructionsButton.setBorderPainted(false);
		
		JLabel lblTitle = new JLabel("MegaKlick Logo?");
		lblTitle.setFont(new Font("Trajan Pro", Font.PLAIN, 36));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(247, 136, 354, 37);
		contentPane.add(lblTitle);
		
		JButton playButton = new JButton();
		playButton.setBorder(BorderFactory.createEmptyBorder());
		playButton.setContentAreaFilled(false);
		BufferedImage buttonIcon2;
		buttonIcon2 = ImageIO.read(new File("C:/megaklick/tri.png"));
		playButton = new JButton(new ImageIcon(buttonIcon2));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instructionText.setText("Nothing here yet.");
			}
		});
		playButton.setBounds(392, 280, 64, 64);
		contentPane.add(playButton);
		playButton.setBorderPainted(false);
		
		JLabel lblPlay = new JLabel("Play");
		lblPlay.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlay.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
		lblPlay.setForeground(Color.WHITE);
		lblPlay.setBounds(392, 345, 64, 13);
		contentPane.add(lblPlay);
		
		JButton highscoresButton = new JButton();
		highscoresButton.setBorder(BorderFactory.createEmptyBorder());
		highscoresButton.setContentAreaFilled(false);
		BufferedImage buttonIcon3;
		buttonIcon3 = ImageIO.read(new File("C:/megaklick/diamond.png"));
		highscoresButton = new JButton(new ImageIcon(buttonIcon3));
		highscoresButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				instructionText.setText("Highscores here.");
			}
		});
		highscoresButton.setBounds(600, 280, 64, 64);
		contentPane.add(highscoresButton);
		highscoresButton.setBorderPainted(false);
		
		JLabel lblHighscores = new JLabel("Highscores");
		lblHighscores.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighscores.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
		lblHighscores.setForeground(Color.WHITE);
		lblHighscores.setBounds(582, 344, 104, 14);
		contentPane.add(lblHighscores);
		
		//helloButton.HorizontalAlignment(SwingConstants.CENTER);
		//circleLabel.setRolloverEnabled(false);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Unable to load files: " + e.getMessage(),"IOException", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
			//e.printStackTrace();
		}
	}
}
