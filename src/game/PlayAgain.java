package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PlayAgain extends JPanel {
	
	private JFrame frame = new JFrame("Play Again");
	private JPanel p_tf = new JPanel();
	private JPanel p_bt = new JPanel();
	private JPanel p_cb = new JPanel();
	private JLabel lb = new JLabel("Drag the BALL to Play Again.", SwingConstants.CENTER);
	private JButton bt_hp = new JButton("Back To Home"), bt_pa = new JButton("Play Again");
	private Font font = new Font("Verdana", Font.BOLD, 20);
	
	private int x = 100;
	private int y = 100;
	private int w = 30;
	private int h = 30;
	private boolean getBall = false;
	
	public PlayAgain(){
		lb.setFont(font);
		
		bt_hp.setFont(font);
		bt_pa.setFont(font);
		
		p_tf.setLayout(new GridLayout(1,2));
		p_tf.add(lb);
		
		p_bt.add(bt_pa);
		p_bt.add(bt_hp);
		
		p_cb.setLayout(new BorderLayout());
		p_cb.add(p_tf, BorderLayout.NORTH);
		p_cb.add(this);
		p_cb.add(p_bt, BorderLayout.SOUTH);
		
		bt_hp.addActionListener(new Action());
		bt_pa.addActionListener(new Action());
		this.addMouseMotionListener(new Drag());
		this.addMouseListener(new Drag());
		
		this.setPane();
		this.setFrame();
	}
	
	private void setPane() {
		this.setBounds(0, 0, 1000, 700);
		this.setLayout(null);
	}
	
	private void setFrame() {
		frame.setBounds(getWidth()/2, getHeight()/4, 1000, 700);
		frame.setVisible(true);
		frame.setContentPane(p_cb);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		g.fillRect(270, 250, 50, 50);
		g.setColor(Color.white);
		g.fillOval(x, y, w, h);
	}
	
	public class Action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == bt_pa) {
					GamePage gp = new GamePage();
					gp.setVisible(true);
					frame.setVisible(false);
			}
			
			if (e.getSource() == bt_hp) {
				StartPage sp = new StartPage();
				sp.setVisible(true);
				frame.setVisible(false);
			}
			
		}
		
	}
	
	public class Drag implements MouseMotionListener, MouseListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			if (getBall) {
				x = e.getX() - (int)(w/2);
				y = e.getY() - (int)(h/2);
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {}

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {
			if((e.getX() > x) && (e.getX() < x+w) 
					&& (e.getY() > y) && (e.getY() < y+h)) {
				getBall = true;
			} else {
				getBall = false;
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(getBall) {
				if((e.getX() > 270) && (e.getX() < 320) && (e.getY() > 250) && (e.getY() < 300)) {
					StartPage sp = new StartPage();
					sp.setVisible(true);
					frame.setVisible(false);
				} else {
					setBackground(new Color(100,150,100));
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
	public static void main(String[] args) {
		new PlayAgain();
	}
	
}
