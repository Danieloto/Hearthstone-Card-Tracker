package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import deck.Card;
import deck.Deck;
import logReader.Log_Reader;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class Deck_tracker extends JFrame implements MouseListener, ActionListener {

	private JFrame frame;
	JFrame frame1 = new JFrame();

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
		Card[] card = new Card[30];
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
			//label[i].setIcon(dk.getCard(i).barpicture());
			label[i].setIcon(icon1);
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