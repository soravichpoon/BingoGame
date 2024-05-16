package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import board.BingoBoard;

public class GamePage2 extends JPanel{
	
	private BingoBoard board = new BingoBoard(100, 150, 370);
	private JButton bt_random = new JButton("Random Letter");
	private JButton[] bt_board = new JButton[25];
	private JLabel lb_time = new JLabel("");
	private JPanel p_board = new JPanel();
	private JFrame frame = new JFrame("Bingo Game !!");
	private Font font = new Font("Verdana", Font.BOLD, 20);
	private Random random;
	private char[] alphs6 = {'A', 'B', 'C', 'D', 'E',
							'F', 'G', 'H', 'I', 'J',
							'K', 'L', ' ', 'N', 'O',
							'P', 'Q', 'R', 'S', 'T',
							'U', 'V', 'W', 'X', 'Y'};
	private static int bingoCnt = 0;
	private int time = 5;
	private boolean[][] blBingo = new boolean[5][5];
	private int x = 610, y = 410, r = 80;
	private char ballChar = 'A';
	
	public Timer timer = new Timer(1000, new TimerListener());

	public GamePage2() {
		
		lb_time.setBounds(150, 50, 500, 100);
		lb_time.setFont(font);
		lb_time.setText("Time Left: "+ time);
		
		bt_random.setBounds(550, 100, 200, 150);
		bt_random.setFont(font);
		
		p_board.setBounds(100, 210, 370, 320);
		p_board.setLayout(new GridLayout(5, 5));
		
		this.shuffle();
		this.setButton();
		
		bt_random.addActionListener(new TimerListener());
		
		this.add(p_board);
		this.add(bt_random);
		this.add(lb_time);
		this.setPane();
		this.setFrame();
		frame.setContentPane(this);
	}
	
	private void setPane() {
		this.setBounds(0, 0, 1000, 700);
		this.setLayout(null);
		timer.start();
	}
	
	private void setFrame() {
		frame.setBounds(getWidth()/2, getHeight()/4, 1000, 700);
		frame.setContentPane(this);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paintComponent(Graphics g) {
		try {
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, getWidth(), getHeight());
			this.drawBingoBoard(g);
			this.drawPathWay(g);
			this.drawBox(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void drawBingoBoard(Graphics g) throws IOException {
		g.drawImage(board.getImg(), board.getX(), board.getY(), board.getSize(), board.getSize(), this);		
	}
	
	private void drawBall(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x, y, r, r);
		g.setColor(Color.WHITE);
		g.fillOval(x+10, y+10, r-30, r-30);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(""+ballChar, 635, 450);
	}
	
	private void drawBox(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(550, 350, 200, 200);
		g.setColor(Color.BLACK);
		g.drawOval(575, 375, 150, 150);
		g.drawRect(550, 350, 200, 200);
	}
	
	private void drawPathWay(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(740, 140, 170, 80);
		g.fillRect(830, 140, 80, 370);
		g.fillRect(740, 420, 170, 90);
		g.setColor(Color.BLACK);
		g.drawLine(740, 140, 910, 140);		// Horiz
		g.drawLine(740, 220, 830, 220);
		g.drawLine(910, 140, 910, 510);		// Vertic
		g.drawLine(910, 510, 740, 510);
		g.drawLine(830, 220, 830, 420);		// Horiz
		g.drawLine(830, 420, 740, 420);
	}
	
	private void setButton() {
		for (int i=0; i<25; i++) {
			bt_board[i] = new JButton(""+alphs6[i]);
			bt_board[i].setOpaque(true);
			bt_board[i].setBackground(Color.BLACK);
			bt_board[i].setForeground(Color.CYAN);
			p_board.add(bt_board[i]);
			bt_board[i].addActionListener(new Action());
		}
	}
	
	private void shuffle(){  
		 for (int i=0; i<alphs6.length * 2; i++){
			 int r1 = (int)(Math.random()*alphs6.length); 
			 int r2 = (int)(Math.random()*alphs6.length); 

			 char ch = alphs6[r1];
			 alphs6[r1] = alphs6[r2];
			 alphs6[r2] = ch;
		}
	}
	
	public void print(){
		for(int i=0; i<blBingo.length; i++){
			for(int j=0; j<blBingo.length; j++){
				System.out.print(blBingo[i][j]?"X":"_");
			}
			System.out.println();
		}
		  	System.out.println("===============================");
	}//print

	public boolean checkBingo(){
		  bingoCnt = 0;
		  int horizCnt = 0;
		  int verticCnt = 0;
		  int crossCnt1 = 0;
		  int crossCnt2 = 0;
		  
		  int i, j;
		  
		  for(i=0; i<5; i++){
			  for(j=0; j<5; j++){
				  if(blBingo[i][j]) {
					  horizCnt++;
				  }
				  if(blBingo[j][i]) {
					  verticCnt++;
				  }
				  if(blBingo[i][j] && i==j) {
					  crossCnt1++;
				  }
				  if(blBingo[i][j] && i+j==4) {
					  crossCnt2++; 
				  }
			  }
		   
			  // horizCnt, vertiCnt
			  if(horizCnt == 5) {
				  bingoCnt++;
				  for(j=0; j<5; j++){
					  if(blBingo[i][j]) {
						  bt_board[i*5+j].setBackground(Color.BLUE);
					  }
				  }
			  } horizCnt = 0;
		  
			  if(verticCnt == 5) {
				  bingoCnt++;
				  for(j=0; j<5; j++){
					  if(blBingo[j][i]) {
						  bt_board[j*5+i].setBackground(Color.BLUE);
					  }
				  }
			  } verticCnt = 0;
		   
		  	}
		  
		  	//crossCnt
		  	if(crossCnt1 == 5) {
		  		bingoCnt++;
		  		for(i=0; i<5; i++){
		  			if(blBingo[i][i]) {
		  				bt_board[i*5+i].setBackground(Color.BLUE);
		  			}
		  		}
		  	}//crossCnt1
		  
		  	if(crossCnt2 == 5) {
		  		bingoCnt++;
		  		for(i=0; i<5; i++){
		  			if(blBingo[i][4-i]) {
		  				bt_board[i*5+(4-i)].setBackground(Color.BLUE);
		  			}
		  		}
		  	}//crossCnt2
		  
		  	System.out.println(bingoCnt);
		    
		  	return bingoCnt >= 3;
		 }//checkBingo
	
	private char randomChar() {
		random = new Random();
		return (char)(65 + random.nextInt(26));
	}
	
	public class TimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			timer.setRepeats(true);
			
			if (time >= 0) {
				lb_time.setText("Time Left: " + time);
				time --;
			} else {
				timer.setRepeats(false);
				time = 5;
				timer.setRepeats(true);
			} 
			
			if (e.getSource() == bt_random) {
				Graphics g = getGraphics();
				ballChar = randomChar();
				drawBall(g);
			}
		}		
	}
	
	public class Action implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton btn = (JButton) e.getSource();
			
			if (e.getSource() == btn) {
				for(int i=0; i<bt_board.length; i++){
				    if(bt_board[i]==btn){
				    	blBingo[i/5][i%5]=true;
				    	break;
				    }
				}
				   
				btn.setBackground(Color.GRAY);
				print();
				
				if(checkBingo()){
					System.out.println("!!BINGO!!");
					PlayAgain pa = new PlayAgain();
					pa.setVisible(true);
					frame.setVisible(false);
				}
			}
			
		}
	
	}
	
}
