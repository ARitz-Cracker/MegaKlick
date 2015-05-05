package megak;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class HighscoresScreen extends JFrame {
	private GameScreen gameScreen;
	public void TakeScore(String player, int score){
		
		//String scoreGet = String.valueOf(gameScreen.clickedButtons);
		
		name1.setText(name1.getText()+"\n\n" + player);
		score1.setText(score1.getText()+"\n\n"+ score);
	}
	
	//int currentSessionScore = GameScreen.clickedButtons;

	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2465301543378756509L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HighscoresScreen frame = new HighscoresScreen();
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
	public HighscoresScreen() {
		setTitle("MegaKlick - Highscores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.BLACK);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		setBounds(gd.getDisplayMode().getWidth()/2 - 864/2, gd.getDisplayMode().getHeight()/2 - 664/2, 864, 664);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel highscoresTitle = new JLabel("Highscores");
		highscoresTitle.setFont(new Font("Trajan Pro", Font.PLAIN, 60));
		highscoresTitle.setHorizontalAlignment(SwingConstants.CENTER);
		highscoresTitle.setForeground(Color.WHITE);
		highscoresTitle.setBounds(221, 45, 406, 60);
		contentPane.add(highscoresTitle);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 20));
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setBounds(121, 150, 125, 38);
		contentPane.add(nameLabel);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setForeground(Color.WHITE);
		lblScore.setFont(new Font("Trajan Pro", Font.PLAIN, 20));
		lblScore.setHorizontalAlignment(SwingConstants.LEFT);
		lblScore.setBounds(610, 150, 94, 26);
		contentPane.add(lblScore);
		
		name1 = new JTextArea();
		name1.setEditable(false);
		name1.setFont(new Font("Trajan Pro", Font.PLAIN, 14));
		name1.setForeground(Color.WHITE);
		name1.setBackground(Color.BLACK);
		name1.setBounds(121, 170, 200, 500);
		contentPane.add(name1);
		
		score1 = new JTextArea();
		score1.setEditable(false);
		score1.setForeground(Color.WHITE);
		score1.setBackground(Color.BLACK);
		score1.setFont(new Font("Trajan Pro", Font.PLAIN, 14));
		score1.setBounds(610, 170, 500, 500);
		contentPane.add(score1);
	}
	
	JTextArea name1 = null;
	
	JTextArea score1 = null;
}
