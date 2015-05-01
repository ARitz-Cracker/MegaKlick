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
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import megak.VariableTimerTask;


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

	/**
	 * Create the frame.
	 * @return 
	 */
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
	
	
	int clickedButtons = -1;
	boolean playingGame = false;
	JButton clickButton[] = fnButtonArray(20);
	Timer clickButtonTimer[] = fnButtonTimer(20);
	Timer timer = null;//new Timer();
	long spawnTime = 5000;
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
			}catch (NullPointerException e) {
				noFreeSpace = false;
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
					spawnTime = (long) (spawnTime * 0.99);
					if (clickedButtons > 199){
						EndGame();
					}
					System.out.println(clickedButtons+"/200");
				}
			}
		});
		clickButton[i].setBounds((int)(Math.random()*800),(int)(Math.random()*600), 64, 64);
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
			}catch (NullPointerException e) {
				continue;
			}catch (ArrayIndexOutOfBoundsException e){
				break;
			}
		}
	}
	
	public void StartGame(){
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
		setTitle("MegaKlick Game");
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
	}
}
