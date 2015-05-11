//GameScreen.java - Where all the fun happens.

package megak;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
//import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import megak.VariableTimerTask;
import megak.HighscoresScreen;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JProgressBar;

public class GameScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1233138023960243746L;

	//self!
	private JPanel contentPane;

	//Dynamic elements
	private HighscoresScreen hsScreen;
	private JProgressBar healthBar; 
	private JLabel scoreText;
	private JButton playButton;
	private JLabel countdownText;
	private JButton btnStartGeam;
	
	
	//Arrays of stuff
	private GameShape clickButton[] = fnButtonArray(20);
	private Timer clickButtonTimer[] = fnButtonTimer(20);
	String shapeFiles[] = { "whiteOctogon.png", "whiteCircle.png",
			"whiteDiamond.png", "whiteHexagon.png", "whitePentagon.png",
			"whiteSquare.png", "whiteTriangle.png" };
	
	// Other Variables
	private Timer timer = null;// new Timer();
	private String shapeGoal = "whiteCircle.png";
	private int clickedButtons = 0;
	private byte life = 50;
	private boolean playingGame = false;
	private long spawnTime = 5000;
	private double startTime = System.currentTimeMillis();
	
	// Public functions 
	
	// Tell me where to shove my scores to
	public void setHighscoreScreen(HighscoresScreen scr) {
		hsScreen = scr;
	}
	
	// Displays a countdown on the screen, then starts the game.
	public void StartGame() {
		/*
		 * while (true){ //
		 * //System.out.println((double)System.currentTimeMillis()/1000);
		 * System.
		 * out.println(Math.sin((double)System.currentTimeMillis()/30000));
		 * 
		 * if (false){break;} }
		 */
		try {
			if (btnStartGeam != null) {
				btnStartGeam.setVisible(false); //If the test button exists, REMOVE FROM PREMISIS
				btnStartGeam = null;
			}
			this.setVisible(true);
			playingGame = true;
			shapeGoal = shapeFiles[(int) (Math.random() * (shapeFiles.length - 1))];
			BufferedImage buttonIcon2;
			buttonIcon2 = ImageIO.read(new File("resources/" + shapeGoal));
			playButton.setIcon(new ImageIcon(buttonIcon2));
			scoreText.setText("Score: 0");
			spawnTime = 2000;
			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					countdownText.setText("3");
					countdownText.setVisible(true);
				}
			}, (long) (1000));
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					countdownText.setText("2");
				}
			}, (long) (2000));
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					countdownText.setText("1");
				}
			}, (long) (3000));
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					countdownText.setText("GO!");
					startTime = System.currentTimeMillis() - 100000;
					GameTick();
				}
			}, (long) (4000));
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					countdownText.setVisible(false);
				}
			}, (long) (4500));
			// GameTick();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Unable to load files: " + e.getMessage(), "IOException",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
	
	//Quits the game, kills all shapes, and if the user gets a high score, ask for their name.
	public void EndGame() {
		playingGame = false;
		timer.cancel();
		for (int i = 0; true; i += 1) { // INFINITE i++!!!
			try {
				if (clickButton[i] == null) {
					continue;
				} else {
					clickButton[i].setVisible(false);
					clickButton[i] = null;
					clickButtonTimer[i].cancel();
				}
			} catch (ArrayIndexOutOfBoundsException e) { // Testing out different coding methods. This probably isn't a good idea.

				if (hsScreen == null) {
					JOptionPane
							.showMessageDialog(
									null,
									"Unable to set High Score because the High Score screen doesn't exist. (Your score was " + clickedButtons + ", btw)",
									"Warning", JOptionPane.ERROR_MESSAGE);
				} else {
					boolean needinput = hsScreen.GetMinimumScore() < clickedButtons;
					if (!needinput){
						this.setVisible(false);
						JOptionPane.showMessageDialog(null,
								"Game Over. You didn't get a High Score this time...", "Sorry...",
								JOptionPane.INFORMATION_MESSAGE);
					}
					String input;
					while (needinput) {
						input = JOptionPane.showInputDialog("You got a high score! Enter your name so everyone can see good you are at clicking things!");
						if (input == null) {
							needinput = false;
							this.setVisible(false);
						} else if (input.length() > 0) {
							hsScreen.TakeScore(input, clickedButtons);
							needinput = false;
							this.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null,
									"A blank name? Really?", "Warning",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				clickedButtons = 0;
				life = 50;
				healthBar.setValue(50);
				break;
			}
		}
	}
	
	// Main!
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameScreen frame = new GameScreen();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Creates the game window
	public GameScreen() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (playingGame) {
					EndGame();
				}
			}
		});
		try {
			setTitle("MegaKlick Game");
			setForeground(Color.WHITE);
			setBackground(Color.BLACK);
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			GraphicsDevice gd = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			setBounds(gd.getDisplayMode().getWidth() / 2 - 864 / 2, gd
					.getDisplayMode().getHeight() / 2 - 664 / 2, 864, 664);
			// setBounds(100, 100, 864, 664);
			contentPane = new JPanel();
			contentPane.setForeground(Color.WHITE);
			contentPane.setBackground(Color.BLACK);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			btnStartGeam = new JButton("Ready?");
			btnStartGeam.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					StartGame();
				}
			});
			btnStartGeam.setBounds(312, 398, 240, 60);
			contentPane.add(btnStartGeam);

			healthBar = new JProgressBar();
			healthBar.setBounds(15, 16, 321, 24);
			contentPane.add(healthBar);

			healthBar.setValue(50);

			playButton = new JButton();
			playButton.setBorder(BorderFactory.createEmptyBorder());
			playButton.setContentAreaFilled(false);
			BufferedImage buttonIcon2;
			buttonIcon2 = ImageIO.read(new File(
					"resources/whiteTriangle.png"));
			playButton = new JButton(new ImageIcon(buttonIcon2));
			playButton.setBounds(774, 11, 64, 64);
			contentPane.add(playButton);
			playButton.setBorderPainted(false);

			scoreText = new JLabel("Score: 00");
			scoreText.setFont(new Font("Trajan Pro", Font.PLAIN, 22));
			scoreText.setHorizontalAlignment(SwingConstants.LEFT);
			scoreText.setForeground(Color.WHITE);
			scoreText.setBounds(15, 51, 354, 24);
			contentPane.add(scoreText);

			JLabel GoalText = new JLabel("Goal:");
			GoalText.setHorizontalAlignment(SwingConstants.RIGHT);
			GoalText.setForeground(Color.WHITE);
			GoalText.setFont(new Font("Trajan Pro", Font.PLAIN, 22));
			GoalText.setBounds(670, 16, 94, 24);
			contentPane.add(GoalText);

			countdownText = new JLabel("Loading...");
			countdownText.setHorizontalAlignment(SwingConstants.CENTER);
			countdownText.setForeground(Color.WHITE);
			countdownText.setFont(new Font("Trajan Pro", Font.PLAIN, 50));
			countdownText.setBounds(15, 171, 812, 76);
			contentPane.add(countdownText);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Unable to load files: " + e.getMessage(), "IOException",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
			// e.printStackTrace();
		}

	}


	//Private functions
	
	//Does stuff each game interval
	private void GameTick() {
		AddButton();
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (playingGame) {
					GameTick();
				}
			}
		}, (long) (Math.random() * spawnTime));
	}
	
	
	
	//Adds the specified value to the "life" variable and updates the health bar.
	private void AddLife(byte val) {
		life += val;
		if (healthBar != null) {
			healthBar.setValue((int) life);
		}
		if (life > 100) {
			life = 100;
		}
		if (life <= 0) {
			EndGame();
		}
	}

	
	// Adds a random shape to the game screen, also checks for any overlap.
	private void AddButton() {
		try {
			int i = 0;
			boolean noFreeSpace = true;
			while (noFreeSpace) {
				try {
					if (clickButton[i] == null) {
						noFreeSpace = false;
					} else {
						i += 1;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					return; // Too many shapes, don't create one.
				}
			}
			// System.out.println(i);
			BufferedImage buttonIcon;

			String shapeFileName = shapeFiles[(int) (Math.random() * (shapeFiles.length - 1))];
			buttonIcon = ImageIO
					.read(new File("resources/" + shapeFileName));
			clickButton[i] = new GameShape();
			clickButton[i].setIcon(new ImageIcon(buttonIcon));
			clickButton[i].SetID(i);
			clickButton[i].SetCorrect(shapeFileName.equals(shapeGoal));
			clickButton[i].setActionCommand(Integer.toString(i)); //Pass the id to the button action so we know what button they actually pressed!
			clickButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int ii = Integer.parseInt(arg0.getActionCommand());
					if (clickButton[ii] != null) { // Just in case...
						clickButtonTimer[ii].cancel(); // Kill all the timers so you don't loose health for "missing" a shape.
						clickButton[ii].setVisible(false);
						if (clickButton[ii].IsCorrect()) {
							clickedButtons += 1;
							if (life == 100) {
								clickedButtons += 1; // Bonus points for full life
							}
							scoreText.setText("Score: " + clickedButtons);
							AddLife((byte) 4);
						} else {
							clickedButtons -= 1;
							scoreText.setText("Score: " + clickedButtons);
							AddLife((byte) -5);
						}

						clickButton[ii] = null;

						if (Math.random() > spawnTime
								/ ((Math.sin((double) System
										.currentTimeMillis()
										/ (30000 * ((System.currentTimeMillis() - startTime) / 100000))) * 620) + 635)) {
							spawnTime = (long) (spawnTime * 1.2);
							System.out.println("Slower");
						} else {
							spawnTime = (long) (spawnTime * 0.92);
							System.out.println("Faster");
						}
						// System.out.println("Score: "+clickedButtons);
					}
				}
			});
			boolean touching = true;
			int xpos = 0;
			int ypos = 0;
			int iix;
			int iiy;
			while (touching) { // Roll for a new location if a shape overlaps with an eisting one!
				xpos = (int) (Math.random() * 700) + 50;
				ypos = (int) (Math.random() * 500) + 50;
				touching = false;
				for (int ii = 0; ii < clickButton.length; ii += 1) {
					if (clickButton[ii] != null && ii != i) {
						iix = clickButton[ii].getX();
						iiy = clickButton[ii].getY();
						if (TouchingBox(xpos, xpos + 64, ypos, ypos + 64, iix, iix + 64, iiy, iiy + 64)) {
							touching = true;
							ii = clickButton.length;
						}
					}
				}
			}
			clickButton[i].setBounds(xpos, ypos, 64, 64);
			contentPane.add(clickButton[i]);
			clickButton[i].setBorderPainted(false);
			clickButtonTimer[i] = new Timer();

			clickButtonTimer[i].schedule(
					new VariableTimerTask(Integer.toString(i)) {
						@Override
						public void run() {
							int ii = Integer.parseInt(param);
							if (clickButton[ii] != null) { // The button may have been clicked, so it's good to check.
								clickButton[ii].setVisible(false);
								if (clickButton[ii].IsCorrect()) {
									AddLife((byte) -7);
								}
								clickButton[ii] = null;
							}
						}
					}, (long) (1000 + Math.random() * spawnTime * 0.675));

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Unable to load files: " + e.getMessage(), "IOException",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
			// e.printStackTrace();
		}

	}


	
	//Utility functions
	
	//I've gone through a phase where I can only think inbetween as x < y < z
	public boolean InBetween(int min, int inp, int max) {
		return (min <= inp && inp <= max);
	}

	//Checks if one box is touching another
	public boolean TouchingBox(int b1x1, int b1x2, int b1y1, int b1y2,
			int b2x1, int b2x2, int b2y1, int b2y2) {
		return (InBetween(b2x1, b1x1, b2x2) || InBetween(b2x1, b1x2, b2x2))
				&& (InBetween(b2y1, b1y1, b2y2) || InBetween(b2y1, b1y2, b2y2));
	}
	
	
	//Functions that Create an array of objects
	public GameShape[] fnButtonArray(int m) {
		GameShape arrButtons[] = new GameShape[m];
		for (int i = 0; i < m; i += 1) {
			arrButtons[i] = null;
		}
		return arrButtons;

	}

	public Timer[] fnButtonTimer(int m) {
		Timer arrButtons[] = new Timer[m];
		for (int i = 0; i < m; i += 1) {
			arrButtons[i] = null;// new Timer();
		}
		return arrButtons;

	}
}
