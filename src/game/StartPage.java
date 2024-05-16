package game;

import javax.swing.*;

import board.BingoBoard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class StartPage extends JPanel {
	
	private BingoBoard board = new BingoBoard(100, 150, 370);
	private JButton start = new JButton("START !!");
	private JButton howToWin = new JButton("HOW TO WIN");
	private JRadioButton rb_bk = new JRadioButton("BLACK Board"), rb_wt = new JRadioButton("WHITE Board");
	private boolean bk, wt;
	private Font font = new Font("Verdana", Font.BOLD, 30);
	
	private JFrame frame = new JFrame("Bingo Game !!");
	
	public StartPage(){
		start.setBounds(580, 200, 300, 100);
		start.setFont(font);
		
		howToWin.setBounds(580, 350, 300, 100);
		howToWin.setFont(font);
		
		rb_bk.setBounds(600, 100, 120, 100);
		rb_wt.setBounds(720, 100, 120, 100);
		
		start.addActionListener(new Action());
		howToWin.addActionListener(new Action());
		rb_bk.addItemListener(new Mode());
		rb_wt.addItemListener(new Mode());
		
		this.add(start);
		this.add(howToWin);
		this.add(rb_bk);
		this.add(rb_wt);
		this.setPanel();
		this.setFrame();
	}
	
	private void setPanel() {
		this.setBounds(0, 0, 1000, 700);
		this.setLayout(null);
	}
	
	private void setFrame() {
		frame.setBounds(getWidth()/2, getHeight()/4, 1000, 700);
		frame.setContentPane(this);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		try {
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, getWidth(), getHeight());
			this.drawBingoBoard(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void drawBingoBoard(Graphics g) throws IOException {
		g.drawImage(board.getImg(), board.getX(), board.getY(), board.getSize(), board.getSize(), this);		
	}

	public class Action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == start) {
				System.out.println("Start!!");
				if (wt) {
					GamePage gp = new GamePage();
					gp.setVisible(true);
					frame.setVisible(false);
				} else if (bk) {
					GamePage2 gp = new GamePage2();
					gp.setVisible(true);
					frame.setVisible(false);
				}
			} else if (e.getSource() == howToWin) {
				HowToPlayPage htp = new HowToPlayPage();
				htp.setVisible(true);
				frame.setVisible(false);
			}
		}
		
	}
	
	public class Mode implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == rb_wt && e.getStateChange() == 1) {
				rb_bk.setSelected(false);
				wt = true;
				bk = false;
			} else if (e.getSource() == rb_bk && e.getStateChange() == 1) {
				rb_wt.setSelected(false);
				wt = false;
				bk = true;
			}
		}
	}
	
	public static void main(String[] args) {
		StartPage sp = new StartPage();
	}
	
}
