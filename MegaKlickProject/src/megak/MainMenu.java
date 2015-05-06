package megak;

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

import megak.GameScreen;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7038961076547386467L;
	private JPanel contentPane;
	// private JButton instructionsButton;

	/**
	 * Launch the application.
	 */

	InstructionsScreen instructionsScreen;
	HighscoresScreen highscoresScreen;
	GameScreen gameScreen;

	private void createInstructionsScreen() {
		instructionsScreen = new InstructionsScreen();
		instructionsScreen.setVisible(false);
		instructionsScreen.setResizable(false);
	}

	private void HighscoresFunction() {
		highscoresScreen = new HighscoresScreen();
		highscoresScreen.setVisible(false);
		highscoresScreen.setResizable(false);
	}

	private void GameFunction() {
		gameScreen = new GameScreen();
		gameScreen.setVisible(false);
		gameScreen.setResizable(false);
		if (highscoresScreen != null) {
			gameScreen.setHighscoreScreen(highscoresScreen);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MainMenu frame = new MainMenu();
					frame.setVisible(true);
					frame.setResizable(false);
					// frame.setUndecorated(true);
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
		setTitle("MegaKlick");

		createInstructionsScreen();
		HighscoresFunction();
		GameFunction();
		highscoresScreen.setGameScreen(gameScreen);
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 500, 300);
			contentPane = new JPanel();
			contentPane.setBackground(Color.BLACK);
			GraphicsDevice gd = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			setBounds(gd.getDisplayMode().getWidth() / 2 - 864 / 2, gd
					.getDisplayMode().getHeight() / 2 - 664 / 2, 864, 664);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JLabel lblInstructions = new JLabel("Instructions");
			lblInstructions.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
			lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
			lblInstructions.setForeground(Color.WHITE);
			lblInstructions.setBounds(169, 500, 109, 14);
			contentPane.add(lblInstructions);

			/*
			 * JButton instructionsButton = new JButton();
			 * instructionsButton.setBorder(BorderFactory.createEmptyBorder());
			 * instructionsButton.setContentAreaFilled(false);
			 */
			BufferedImage buttonIcon;
			buttonIcon = ImageIO.read(new File("resources/whiteCircle.png"));
			JButton instructionsButton = new JButton(new ImageIcon(buttonIcon));

			instructionsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					instructionsScreen.setVisible(true);
					/*
					 * InstructionsScreen instance1 = new InstructionsScreen();
					 * instance1.setVisible(true);
					 */
				}
			});
			instructionsButton.setBounds(190, 430, 64, 64);
			contentPane.add(instructionsButton);

			instructionsButton.setBorderPainted(false);

			JButton playButton = new JButton();
			playButton.setBorder(BorderFactory.createEmptyBorder());
			playButton.setContentAreaFilled(false);
			BufferedImage buttonIcon2;
			buttonIcon2 = ImageIO.read(new File(
					"resources/whiteTriangle.png"));
			playButton = new JButton(new ImageIcon(buttonIcon2));
			playButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameScreen.setVisible(true);
					gameScreen.StartGame();
				}
			});
			playButton.setBounds(392, 430, 64, 64);
			contentPane.add(playButton);
			playButton.setBorderPainted(false);

			JLabel lblPlay = new JLabel("Play");
			lblPlay.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlay.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
			lblPlay.setForeground(Color.WHITE);
			lblPlay.setBounds(392, 500, 64, 13);
			contentPane.add(lblPlay);

			JLabel noStats = new JLabel("");
			noStats.setHorizontalAlignment(SwingConstants.CENTER);
			noStats.setForeground(Color.WHITE);
			noStats.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
			noStats.setBounds(161, 517, 526, 43);
			contentPane.add(noStats);

			JButton highscoresButton = new JButton();
			highscoresButton.setBorder(BorderFactory.createEmptyBorder());
			highscoresButton.setContentAreaFilled(false);
			BufferedImage buttonIcon3;
			buttonIcon3 = ImageIO
					.read(new File("resources/whiteDiamond.png"));
			highscoresButton = new JButton(new ImageIcon(buttonIcon3));
			highscoresButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					highscoresScreen.setVisible(true);
				}
			});
			highscoresButton.setBounds(600, 430, 64, 64);
			contentPane.add(highscoresButton);
			highscoresButton.setBorderPainted(false);

			JLabel lblHighscores = new JLabel("Highscores");
			lblHighscores.setHorizontalAlignment(SwingConstants.CENTER);
			lblHighscores.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
			lblHighscores.setForeground(Color.WHITE);
			lblHighscores.setBounds(582, 500, 104, 14);
			contentPane.add(lblHighscores);

			JButton logoMain = new JButton("");
			logoMain.setBorder(BorderFactory.createEmptyBorder());
			logoMain.setContentAreaFilled(false);
			BufferedImage buttonIconLogo;
			buttonIconLogo = ImageIO.read(new File("resources/logo.png"));
			logoMain = new JButton(new ImageIcon(buttonIconLogo));
			logoMain.setBounds(345, 201, 158, 151);
			contentPane.add(logoMain);
			logoMain.setBorderPainted(false);

			JLabel lblMegaklick = new JLabel("MegaKlick");
			lblMegaklick.setHorizontalAlignment(SwingConstants.CENTER);
			lblMegaklick.setForeground(Color.WHITE);
			lblMegaklick.setFont(new Font("Trajan Pro", Font.PLAIN, 50));
			lblMegaklick.setBounds(220, 78, 408, 51);
			contentPane.add(lblMegaklick);

			// helloButton.HorizontalAlignment(SwingConstants.CENTER);
			// circleLabel.setRolloverEnabled(false);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Unable to load files: " + e.getMessage(), "IOException",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
			// e.printStackTrace();
		}
	}
}
