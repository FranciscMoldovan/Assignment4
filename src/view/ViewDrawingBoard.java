package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.DrawingBoard;

public class ViewDrawingBoard extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private JPanel contentPane;
	private JPanel bottomPanel;
	
	private JButton btnLine;
	private JButton btnEllipse;
	private JButton btnRectangle;
	
	
	private ImageIcon iconLine;
	private ImageIcon iconEllipse;
	private ImageIcon iconRectangle;
	
	private Box theBox; 
	
	public ViewDrawingBoard(DrawingBoard myDrawingBoard){
		setTitle("Paint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		
		bottomPanel=new JPanel();
		//bottomPanel.setBounds(383, 543, 115, 19);
		
//		contentPane = new JPanel(); 
//		contentPane.setBorder(new EmptyBorder(5,5,5,5));
//		setContentPane(contentPane);
//		contentPane.setLayout(new BorderLayout());
		
		 iconLine = new ImageIcon("line.png");
		 iconEllipse = new ImageIcon("ellipse.png");
		 iconRectangle = new ImageIcon("rectangle.png");
		
		btnLine = new JButton(iconLine);
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		//contentPane.setLayout(null);
		btnLine.setBounds(236, 492, 70, 70);
		//contentPane.add(btnLine);
		
		btnEllipse = new JButton(iconEllipse);
		btnEllipse.setBounds(334, 492, 70, 70);
		//contentPane.add(btnEllipse);
		
		btnRectangle = new JButton(iconRectangle);
		btnRectangle.setBounds(442, 492, 70, 70);
		//contentPane.add(btnRectangle);
		
		theBox = Box.createHorizontalBox();
		theBox.add(btnLine);
		theBox.add(btnEllipse);
		theBox.add(btnRectangle);
		bottomPanel.add(theBox);
//		theBox.setSize(100,100);
//		bottomPanel.setSize(1000,1000);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(myDrawingBoard, BorderLayout.CENTER);
		}
	public void addButtonActionListener(ActionListener listener){
		btnLine.addActionListener(listener);
		btnEllipse.addActionListener(listener);
		btnRectangle.addActionListener(listener);
	}
}





