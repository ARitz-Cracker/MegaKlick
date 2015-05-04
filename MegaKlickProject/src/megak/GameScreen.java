package megak;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
	
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	
	HighscoresScreen hsScreen;
	public void setHighscoreScreen(HighscoresScreen scr){
		hsScreen = scr;
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public boolean InBetween(int min,int inp,int max){
		return (min <= inp && inp <= max);
	}
	
	public boolean TouchingBox(int b1x1,int b1x2,int b1y1,int b1y2,int b2x1,int b2x2,int b2y1,int b2y2){
		return (InBetween(b2x1,b1x1,b2x2) || InBetween(b2x1,b1x2,b2x2) || InBetween(b1x1,b2x1,b1x2)|| InBetween(b1x1,b2x2,b1x2) ) && (InBetween(b2y1,b1y1,b2y2) || InBetween(b2y1,b1y2,b2y2) || InBetween(b1y1,b2y1,b1y2) || InBetween(b1y1,b2y2,b1y2));
	}
	
	public JButton[] fnButtonArray(int m){
		JButton arrButtons[] = new JButton[m];
		for (int i=0;i<m;i+=1){
			arrButtons[i] = null;
		}
		return arrButtons;

	  }
	
	public Timer[] fnButtonTimer(int m){
		Timer arrButtons[] = new Timer[m];
		for (int i=0;i<m;i+=1){
			arrButtons[i] = null;//new Timer();
		}
		return arrButtons;

	  }
	
	
	
	
	int clickedButtons = 0; //first button doesn't count towards clickedButtons? - see highscore screen after playing
	byte life = 50;
	boolean playingGame = false;
	JButton clickButton[] = fnButtonArray(20);
	Timer clickButtonTimer[] = fnButtonTimer(20);
	Timer timer = null;//new Timer();
	long spawnTime = 5000;
	JProgressBar healthBar;
	
	public void AddLife(byte val){
		life += val;
		if (healthBar != null){
			healthBar.setValue((int)life);
		}
		if (life>100){
			life = 100;
		}
		if (life<= 0){
			EndGame();
		}
	}
	
	public void AddButton(){
		try {
		int i = 0;
		boolean noFreeSpace = true;
		while(noFreeSpace){
			try{
				if (clickButton[i] == null){
					noFreeSpace = false;
				}else{
					i += 1;
				}
			}catch (ArrayIndexOutOfBoundsException e){
				return;
			}
		}
		System.out.println(i);
		BufferedImage buttonIcon;
		buttonIcon = ImageIO.read(new File("C:/megaklick/whiteCircle.png"));
		clickButton[i] = new JButton(new ImageIcon(buttonIcon));
		//clickButton[i].id = i;
		clickButton[i].setActionCommand(Integer.toString(i));
		clickButton[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ii = Integer.parseInt(arg0.getActionCommand());
				if (clickButton[ii] != null){
					clickButton[ii].setVisible(false);
					clickButton[ii] = null;
					clickButtonTimer[ii].cancel();
					clickedButtons += 1;
					AddLife((byte)4);
					spawnTime = (long) (spawnTime * 0.95);
					System.out.println("Score: "+clickedButtons);
				}
			}
		});
		boolean touching = true;
		int xpos;
		int ypos;
		while (touching){
			xpos = (int) (Math.random()*800);
			ypos = (int) (Math.random()*600);
			touching = false;
			clickButton[i].setBounds(xpos,ypos, 64, 64);
			for (int ii=0;ii<clickButton.length;ii+=1){ //INFINITE i++!!!
				if (clickButton[ii] == null || ii==i){continue;}
				System.out.println("Checking box "+ii);
				if (TouchingBox(xpos,xpos+64,ypos,ypos+64,clickButton[ii].getX(),clickButton[ii].getY(),clickButton[ii].getX()+64,clickButton[ii].getY()+64)){
					System.out.println(i+" and "+ii+" are touching!");
					touching = true;
					break;
				}
			}
		}
		contentPane.add(clickButton[i]);
		clickButton[i].setBorderPainted(false);
		clickButtonTimer[i] = new Timer();
		clickButtonTimer[i].schedule(new VariableTimerTask(Integer.toString(i)) {
			  @Override
			  public void run() {
					int ii = Integer.parseInt(param);
					if (clickButton[ii] != null){
						clickButton[ii].setVisible(false);
						clickButton[ii] = null;
						AddLife((byte)-3);
					}
			  }
			}, (long) (1000 + Math.random()*spawnTime*0.675));
		}catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Unable to load files: " + e.getMessage(),"IOException", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
			//e.printStackTrace();
		}
		
	}
	public void EndGame(){
		playingGame = false;
		
		/*
		btnStartGeam = new JButton("Again?");
		btnStartGeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnStartGeam.setVisible(false);
				btnStartGeam = null;
				StartGame();
			}
		});
		
		btnStartGeam.setBounds(312, 398, 240, 60);
		contentPane.add(btnStartGeam);
		*/
		timer.cancel();
		for (int i=0;true;i+=1){ //INFINITE i++!!!
			try{
				if (clickButton[i] == null){
					continue;
				}else{
					clickButton[i].setVisible(false);
					clickButton[i] = null;
					clickButtonTimer[i].cancel();
				}
			}catch (ArrayIndexOutOfBoundsException e){
				
				if (hsScreen == null){
					JOptionPane.showMessageDialog(null,"Unable to set High Score because the High Score screen doesn't exist. (Your score was "+clickedButtons+", btw)","Warning", JOptionPane.ERROR_MESSAGE);
				}else{
					boolean needinput = true;
					String input;
					System.out.println("User must enter HS name");
					while(needinput){
						input = JOptionPane.showInputDialog("You got a high score! Enter your name so everyone can see good you are at clicking things!");
						if (input == null){
							needinput = false;
						}else if (input.length()>0){
							hsScreen.TakeScore(input,clickedButtons);
						}else{
							System.out.println("Invalid name. Retrying");
							JOptionPane.showMessageDialog(null,"A blank name? Really?","Warning", JOptionPane.WARNING_MESSAGE);
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
	
	public void StartGame(){
		this.setVisible(true);
		playingGame = true;
		System.out.println("Game interval!");
		timer = new Timer();
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  if (playingGame){
					AddButton();
				  	StartGame();
				  }
			  }
			}, (long) (Math.random()*spawnTime));
	}
	JButton btnStartGeam;
	public GameScreen() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (playingGame){
					EndGame();
				}
			}
		});
		
		setTitle("MegaKlick Game");
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		setBounds(gd.getDisplayMode().getWidth()/2 - 864/2, gd.getDisplayMode().getHeight()/2 - 664/2, 864, 664);
		//setBounds(100, 100, 864, 664);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnStartGeam = new JButton("Ready?");
		btnStartGeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnStartGeam.setVisible(false);
				btnStartGeam = null;
				StartGame();
			}
		});
		btnStartGeam.setBounds(312, 398, 240, 60);
		contentPane.add(btnStartGeam);
		
		healthBar = new JProgressBar();
		healthBar.setBounds(15, 16, 321, 24);
		contentPane.add(healthBar);
		
		healthBar.setValue(50);
	}
}
