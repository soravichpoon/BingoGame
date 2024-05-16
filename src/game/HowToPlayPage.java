package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import board.Pattern;

public class HowToPlayPage extends JPanel{
	
	private Pattern pattern = new Pattern(200, 50, 220);
	private JButton back = new JButton("Back To Home");
	private Font font = new Font("Verdana", Font.BOLD, 20);
	public JFrame frame = new JFrame("How To Win !!");
	
	public HowToPlayPage() {
		back.setBounds(410, 580, 200, 50);
		back.setFont(font);
		back.addActionListener(new Action());
		
		this.add(back);
		this.setPanel();
		this.setFrame();
		frame.setContentPane(this);
	}
	
	private void setPanel() {
		this.setBounds(0, 0, 800, 800);
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
		try {
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(pattern.getImg("diag"), pattern.getX(), pattern.getY(), pattern.getSize(), pattern.getSize(), this);
			g.drawImage(pattern.getImg("vertic"), pattern.getX()+400, pattern.getY(), pattern.getSize(), pattern.getSize(), this);
			g.drawImage(pattern.getImg("horiz"), pattern.getX()+200, pattern.getY()+280, pattern.getSize(), pattern.getSize(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public class Action implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == back) {
				StartPage sp = new StartPage();
				sp.setVisible(true);
				frame.setVisible(false);
			}
		}
	}
	
}
