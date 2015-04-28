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
import java.awt.Color;

public class Test extends JFrame {

	private JPanel contentPane;
	//private JButton instructionsButton;

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
		try {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setForeground(Color.WHITE);
		lblInstructions.setBounds(20, 147, 109, 14);
		contentPane.add(lblInstructions);
		
		JLabel instructionText = new JLabel("");
		instructionText.setHorizontalAlignment(SwingConstants.CENTER);
		instructionText.setForeground(Color.WHITE);
		instructionText.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		instructionText.setBounds(20, 200, 454, 50);
		contentPane.add(instructionText);
		
		JButton instructionsButton = new JButton();
		instructionsButton.setBorder(BorderFactory.createEmptyBorder());
		instructionsButton.setContentAreaFilled(false);
		BufferedImage buttonIcon;
		buttonIcon = ImageIO.read(new File("C:/Users/Henry/git/MegaKlick/content/circ.png"));
		instructionsButton = new JButton(new ImageIcon(buttonIcon));
		
		instructionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				instructionText.setText("Insert instructions here.");
			}
		});
		instructionsButton.setBounds(40, 80, 64, 64);
		contentPane.add(instructionsButton);
		
		instructionsButton.setBorderPainted(false);
		
		JLabel lblTitle = new JLabel("MegaKlick Logo?");
		lblTitle.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(145, 10, 200, 50);
		contentPane.add(lblTitle);
		
		JButton playButton = new JButton();
		playButton.setBorder(BorderFactory.createEmptyBorder());
		playButton.setContentAreaFilled(false);
		BufferedImage buttonIcon2;
		buttonIcon2 = ImageIO.read(new File("C:/Users/Henry/git/MegaKlick/content/tri.png"));
		playButton = new JButton(new ImageIcon(buttonIcon2));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instructionText.setText("Nothing here yet.");
			}
		});
		playButton.setBounds(209, 80, 64, 64);
		contentPane.add(playButton);
		playButton.setBorderPainted(false);
		
		JLabel lblPlay = new JLabel("Play");
		lblPlay.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlay.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
		lblPlay.setForeground(Color.WHITE);
		lblPlay.setBounds(209, 143, 64, 22);
		contentPane.add(lblPlay);
		
		JButton highscoresButton = new JButton();
		highscoresButton.setBorder(BorderFactory.createEmptyBorder());
		highscoresButton.setContentAreaFilled(false);
		BufferedImage buttonIcon3;
		buttonIcon3 = ImageIO.read(new File("C:/Users/Henry/git/MegaKlick/content/diamond.png"));
		highscoresButton = new JButton(new ImageIcon(buttonIcon3));
		highscoresButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				instructionText.setText("Highscores here.");
			}
		});
		highscoresButton.setBounds(375, 80, 64, 64);
		contentPane.add(highscoresButton);
		highscoresButton.setBorderPainted(false);
		
		JLabel lblHighscores = new JLabel("Highscores");
		lblHighscores.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighscores.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
		lblHighscores.setForeground(Color.WHITE);
		lblHighscores.setBounds(357, 147, 104, 14);
		contentPane.add(lblHighscores);
		
		//helloButton.HorizontalAlignment(SwingConstants.CENTER);
		//circleLabel.setRolloverEnabled(false);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
