package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import app.App;
import deck.Deck;

public class Deck_tracker extends JFrame implements MouseListener, ActionListener {

	private JFrame frame;
	JFrame frame1 = new JFrame();
	private Deck deck;
	ImageIcon[] cardImages;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deck_tracker window = new Deck_tracker();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Deck_tracker() {
		//ImageIcon[] Icon = new ImageIcon[30];
		App app=new App();
		deck=app.friendlyDeck;
		cardImages=deck.images;
		//initialize(card);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	//private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 2, 2));
		JScrollPane scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scroll);
		JLabel[] label = new JLabel[30];
		
		//private MouseAdapter chessAdapter;
		
		
		ImageIcon icon1=new ImageIcon("/Users/aricylol/eclipse-workspace/Heartstone_Deck_Track/src/GUI/Cards/123.jpg");
		//ImageIcon[] icon = new ImageIcon[30];
		Deck dk = new Deck();
		//Card card = new Card();
		
		for(int i = 0; i< label.length; i++) {
			label[i]=new JLabel();
			panel.add(label[i]);
			label[i].setIcon(cardImages[i]);
//			label[i].setIcon(icon1);
			label[i].addMouseListener(this);
			
			//label[i].addMouseListener(null);
			
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel l=(JLabel) e.getSource();
	    l.setIcon(null);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		frame1.setLocation(500, 50);
		frame1.setVisible(true);
		frame1.setSize(100, 100);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		frame1.dispose();
	}

}