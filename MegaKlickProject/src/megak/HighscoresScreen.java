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
import java.io.PrintWriter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HighscoresScreen extends JFrame {
	String names[] = { "NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL" };
	int scores[] = { Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE };

	void SortScores() {
		String newNames[] = { "NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL","NULL" };
		int newScores[] = { Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE };
		int maxScore = 0;
		int newPlace = 0;
		while (newPlace < 10) {
			for (int i = 0; i < 10; i += 1) {
				if (scores[i] >= scores[maxScore]) {
					maxScore = i;
				}
			}
			newScores[newPlace] = scores[maxScore];
			newNames[newPlace] = names[maxScore];
			newPlace +=1;
			scores[maxScore] = -2147483647;
			names[maxScore] = "NULL";
		}
		for (int i=0;i<10;i+=1){
			scores[i] = newScores[i];
			names[i] = newNames[i];
			
		}
	}

	public static String readFile(String filename) {
		String content = null;
		File file = new File(filename); // for ex foo.txt
		try {
			FileReader reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, "You appear not to have any High Scores\n"+e.getMessage(), "IOException",
					JOptionPane.INFORMATION_MESSAGE);
		}
		return content;
	}

	void UpdateScores(){
		SortScores();
		name1.setText("");
		score1.setText("");
		try {
			PrintWriter print = new PrintWriter(new FileWriter("nameAndScore.txt", false));
			for (int i=0;i<10;i+=1){
				print.print(names[i] + "\t" + scores[i] + "\r\n");
				if (scores[i] > -2147483647){
					name1.setText(name1.getText()+"\n\n" + names[i]);
					score1.setText(score1.getText()+"\n\n"+ scores[i]);
				}
			}
			print.close();
			//String readScores = readFile("nameAndScore.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	public int GetMaximumScore(){
		return scores[0]; // The first score on the table is the maximum
	}
	public int GetMinimumScore(){
		return scores[9]; // The last score on the table is the minimum
	}
	public void TakeScore(String player, int score) {
		int minScore = 0;
		for (int i=0;i<10;i+=1){
			if (scores[i] < scores[minScore]){
				minScore = i;
			}
		}
		if (score > scores[minScore]){
			scores[minScore] = score;
			names[minScore] = player;
		}
		UpdateScores();
		/*
		 * //name1.setText(name1.getText()+"\n\n" + player);
		 * //score1.setText(score1.getText()+"\n\n"+ score);
		 */
	}

	// int currentSessionScore = GameScreen.clickedButtons;

	public void setGameScreen(GameScreen gameScreen) {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2465301543378756509L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	void setVisibleThisNot() {
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
				
				//name1.setText(readFile("nameAndScore.txt"));
				//score1.setText(readFile("nameAndScore.txt"));
			}
		});
		setTitle("MegaKlick - Highscores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.BLACK);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		setBounds(gd.getDisplayMode().getWidth() / 2 - 864 / 2, gd
				.getDisplayMode().getHeight() / 2 - 664 / 2, 864, 664);
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
			buttonIcon2 = ImageIO.read(new File(
					"resources/whiteTriangle.png"));
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
		
		String fnames = readFile("nameAndScore.txt");
		if (fnames != null){
			String[] lines = fnames.split("\r\n");
			for (int i=0;i<10;i+=1){
				try{
					String[] lineseg = lines[i].split("\t");
					names[i] = lineseg[0];
					scores[i] = Integer.parseInt(lineseg[1]);
				}catch(ArrayIndexOutOfBoundsException e){
					continue;
				}
			}
			UpdateScores();
		}

	}

	JTextArea name1 = null;

	JTextArea score1 = null;
}
