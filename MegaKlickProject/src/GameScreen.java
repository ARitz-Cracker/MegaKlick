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


public class GameScreen extends JFrame {

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
	
	int[] marks = new int[4];
	int clickedButtons = -1;
	boolean playingGame = false;
	JButton clickButton[] = fnButtonArray(20);
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
		clickButton[i] = new JButton();
		clickButton[i].setBorder(BorderFactory.createEmptyBorder());
		clickButton[i].setContentAreaFilled(false);
		BufferedImage buttonIcon;
		buttonIcon = ImageIO.read(new File("C:/megaklick/circ.png"));
		clickButton[i] = new JButton(new ImageIcon(buttonIcon));
		//clickButton[i].id = i;
		clickButton[i].setActionCommand(Integer.toString(i));
		clickButton[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ii = Integer.parseInt(arg0.getActionCommand());
				if (clickButton[ii] != null){
					clickButton[ii].setVisible(false);
					clickButton[ii] = null;
					clickedButtons += 1;
					if (clickedButtons > 32){
						playingGame = false;
					}
				}
			}
		});
		clickButton[i].setBounds((int)(Math.random()*800),(int)(Math.random()*600), 64, 64);
		contentPane.add(clickButton[i]);
		
		clickButton[i].setBorderPainted(false);
		}catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Unable to load files: " + e.getMessage(),"IOException", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
			//e.printStackTrace();
		}
		
	}
	public void StartGame(){
		playingGame = true;
		double startTime = System.currentTimeMillis() + 1;
		while(playingGame){
			if(System.currentTimeMillis() <= startTime){
				startTime = System.currentTimeMillis() + Math.random()*5;
				AddButton();
			}
		}
		for (int i=0;i<1337;i+=1){
			try{
				if (clickButton[i] == null){
					continue;
				}else{
					clickButton[i].setVisible(false);
					clickButton[i] = null;
				}
			}catch (NullPointerException e) {
				continue;
			}catch (ArrayIndexOutOfBoundsException e){
				break;
			}
		}
	}
	public GameScreen() {
		setTitle("MegaKlick Game");
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		setBounds(gd.getDisplayMode().getWidth()/2 - 864/2, gd.getDisplayMode().getHeight()/2 - 664/2, 864, 664);
		//setBounds(100, 100, 864, 664);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
