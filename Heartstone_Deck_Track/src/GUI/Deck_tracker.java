package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class Deck_tracker {

	private JFrame frame;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 2, 2));
		JScrollPane scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scroll);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		panel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("New label");
		panel.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("New label");
		panel.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("New label");
		panel.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("New label");
		panel.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("New label");
		panel.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("New label");
		panel.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("New label");
		panel.add(lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel("New label");
		panel.add(lblNewLabel_19);
		
		JLabel lblNewLabel_20 = new JLabel("New label");
		panel.add(lblNewLabel_20);
		
		JLabel lblNewLabel_21 = new JLabel("New label");
		panel.add(lblNewLabel_21);
		
		JLabel lblNewLabel_22 = new JLabel("New label");
		panel.add(lblNewLabel_22);
		
		JLabel lblNewLabel_23 = new JLabel("New label");
		panel.add(lblNewLabel_23);
		
		JLabel lblNewLabel_24 = new JLabel("New label");
		panel.add(lblNewLabel_24);
		
		JLabel lblNewLabel_25 = new JLabel("New label");
		panel.add(lblNewLabel_25);
		
		JLabel lblNewLabel_26 = new JLabel("New label");
		panel.add(lblNewLabel_26);
		
		JLabel lblNewLabel_27 = new JLabel("New label");
		panel.add(lblNewLabel_27);
		
		JLabel lblNewLabel_28 = new JLabel("New label");
		panel.add(lblNewLabel_28);
		
		JLabel lblNewLabel_29 = new JLabel("New label");
		panel.add(lblNewLabel_29);
		
		JLabel lblNewLabel_30 = new JLabel("New label");
		panel.add(lblNewLabel_30);

		
	}

}
