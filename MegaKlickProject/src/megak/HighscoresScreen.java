package megak;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.PrintWriter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class HighscoresScreen extends JFrame {
	private GameScreen gameScreen;
	
	String names[] = {"Nobody"};
	int scores[] = {0};
	void SortScores(){
		String newNames[] = {}; 
		int newScores[] = {};
		int maxScore = 0;
		int len = scores.length;
		
		while (newScores.length < len){
			for (int i=0;i<len;i+=1){
				if (scores[i] >= scores[maxScore]){
					newScores[newScores.length] = scores[i];
					newNames[newNames.length] = names[i];
					scores[i] = -2147483647;
					names[i] = "NULL";
				}
			}
		}
		scores = newScores;
		names = newNames;
	}
	
	 public static String readFile(String filename)
	 {
	    String content = null;
	    File file = new File(filename); //for ex foo.txt
	    try {
	        FileReader reader = new FileReader(file);
	        char[] chars = new char[(int) file.length()];
	        reader.read(chars);
	        content = new String(chars);
	        reader.close();
	    } catch (IOException e) {
	        //e.printStackTrace();
	        JOptionPane.showMessageDialog(null,e.getMessage(),"IOException", JOptionPane.ERROR_MESSAGE);
	    }
	    return content;
	 }
	
	public void TakeScore(String player, int score){
		if (scores.length != names.length){
			System.err.println("scores.length != names.length... wtf?");
			JOptionPane.showMessageDialog(null,"The names array's length is not equal to that of the scores array.\n(There are names without scores, or scores without names)","Something strange happened.", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		scores[scores.length] = score;
		names[names.length] = player;
		
		
		try{
			String playerName = player;
			int playerScore = score;
			FileWriter fileWrite = new FileWriter("nameAndScore.txt", true);
			PrintWriter print = new PrintWriter(fileWrite);
			print.print(playerName+"\t"+playerScore+"\r\n");
			print.close();
			String readScores = readFile("nameAndScore.txt");
				}
				catch(IOException e1){
					e1.printStackTrace();
				}
		
		/*
		//name1.setText(name1.getText()+"\n\n" + player);
		//score1.setText(score1.getText()+"\n\n"+ score);
		*/
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
	void setVisibleThisNot(){
		this.setVisible(false);
	}
	
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				String names = readFile("nameAndScore.txt");
				
				name1.setText(readFile("nameAndScore.txt"));
				score1.setText(readFile("nameAndScore.txt"));
			}
		});
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
		name1.setBounds(121, 200, 200, 500);
		contentPane.add(name1);
		
		score1 = new JTextArea();
		score1.setEditable(false);
		score1.setForeground(Color.WHITE);
		score1.setBackground(Color.BLACK);
		score1.setFont(new Font("Trajan Pro", Font.PLAIN, 14));
		score1.setBounds(610, 200, 500, 500);
		contentPane.add(score1);
		
		JLabel backFromHighscore = new JLabel("Back");
		backFromHighscore.setHorizontalAlignment(SwingConstants.CENTER);
		backFromHighscore.setFont(new Font("Trajan Pro", Font.PLAIN, 14));
		backFromHighscore.setForeground(Color.WHITE);
		backFromHighscore.setBounds(381, 500, 86, 14);
		contentPane.add(backFromHighscore);
		
		JButton button = new JButton("");
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		BufferedImage buttonIcon2;
		try {
			buttonIcon2 = ImageIO.read(new File("C:/megaklick/whiteTriangle.png"));
			button = new JButton(new ImageIcon(buttonIcon2));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisibleThisNot();
				}
			});
			button.setForeground(new Color(0, 0, 0));
			button.setBounds(392, 430, 64, 64);
			contentPane.add(button);
			button.setBorderPainted(false);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	JTextArea name1 = null;
	
	JTextArea score1 = null;
}
