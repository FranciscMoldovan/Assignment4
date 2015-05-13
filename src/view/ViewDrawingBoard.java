package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;

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
	private JButton btnBrush;
	private JButton btnColor;
	
	private ImageIcon iconLine;
	private ImageIcon iconEllipse;
	private ImageIcon iconRectangle;
	private ImageIcon iconBrush;
	private ImageIcon iconColor;
	private ImageIcon iconFill;
	private ImageIcon iconRefresh;
	private ImageIcon iconUndo;
	
	private JLabel transparencyLabel; 
	private JSlider transparencySlider;
	
	private Box theBox; 
	private JButton btnFill;
	private JButton btnRefresh;
	private JButton btnUndo;
	
	
	
	
	public ViewDrawingBoard(DrawingBoard myDrawingBoard){
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();	
		Border compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
		Border redline = BorderFactory.createLineBorder(Color.red);
		compound = BorderFactory.createCompoundBorder(
                redline, compound);	
		((JComponent) this.getContentPane()).setBorder(compound);
		
		
		setTitle("Paint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(70, 70, 1200, 700);
		setResizable(false);
		
		 bottomPanel=new JPanel();		
		 iconLine = new ImageIcon("line.png");
		 iconEllipse = new ImageIcon("ellipse.png");
		 iconRectangle = new ImageIcon("rectangle.png");
		 iconBrush = new ImageIcon("brush.png");
		 iconColor = new ImageIcon("color.png");
		 iconFill = new ImageIcon("fill.png");
		 iconRefresh = new ImageIcon("refresh.png");
		 iconUndo = new ImageIcon("undo.png");
		 
		transparencyLabel=new JLabel("Transparent: 1");
		transparencySlider=new JSlider(1,99,99);
		 
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
		
		btnColor = new JButton(iconColor);
		
		
		btnBrush = new JButton(iconBrush);
		getContentPane().add(myDrawingBoard, BorderLayout.CENTER);
		
		theBox = Box.createHorizontalBox();
		
		btnUndo = new JButton(iconUndo);
		theBox.add(btnUndo);
		
		btnRefresh = new JButton(iconRefresh);
		theBox.add(btnRefresh);
		
		
		
		theBox.add(btnLine);
		theBox.add(btnEllipse);
		theBox.add(btnRectangle);
		theBox.add(btnBrush);
		theBox.add(btnColor);
		
		btnFill = new JButton(iconFill);
		theBox.add(btnFill);
		theBox.add(transparencyLabel);
		theBox.add(transparencySlider);
		bottomPanel.add(theBox);
		
		compound = BorderFactory.createCompoundBorder(
                redline, compound);	
		
		bottomPanel.setBorder(compound);
		
//		theBox.setSize(100,100);
//		bottomPanel.setSize(1000,1000);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		
		
		
		
		}
	public JButton getBtnLine() {
		return btnLine;
	}
	public void setBtnLine(JButton btnLine) {
		this.btnLine = btnLine;
	}
	public JButton getBtnEllipse() {
		return btnEllipse;
	}
	public void setBtnEllipse(JButton btnEllipse) {
		this.btnEllipse = btnEllipse;
	}
	public JButton getBtnRectangle() {
		return btnRectangle;
	}
	public void setBtnRectangle(JButton btnRectangle) {
		this.btnRectangle = btnRectangle;
	}
	public JButton getBtnBrush() {
		return btnBrush;
	}
	public void setBtnBrush(JButton btnBrush) {
		this.btnBrush = btnBrush;
	}
	
	
	
	public JLabel getTransparencyLabel() {
		return transparencyLabel;
	}
	public void setTransparencyLabel(String text) {
		this.transparencyLabel.setText(text);
	}
	public JSlider getTransparencySlider() {
		return transparencySlider;
	}
	public void setTransparencySlider(JSlider transparencySlider) {
		this.transparencySlider = transparencySlider;
	}
	
	
	
	
	
	public JButton getBtnFill() {
		return btnFill;
	}
	public void setBtnFill(JButton btnFill) {
		this.btnFill = btnFill;
	}
	public JButton getBtnColor() {
		return btnColor;
	}
	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}
	
	
	
	public JButton getBtnRefresh() {
		return btnRefresh;
	}
	public void setBtnRefresh(JButton btnRefresh) {
		this.btnRefresh = btnRefresh;
	}
	
	
	
	public JButton getBtnUndo() {
		return btnUndo;
	}
	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}
	public void addMyChangeListener(ChangeListener listener){
		transparencySlider.addChangeListener(listener);
	}
	
	public void addButtonActionListener(ActionListener listener){
		btnLine.addActionListener(listener);
		btnEllipse.addActionListener(listener);
		btnRectangle.addActionListener(listener);
		btnBrush.addActionListener(listener);
		btnColor.addActionListener(listener);
		btnFill.addActionListener(listener);
		btnRefresh.addActionListener(listener);
		btnUndo.addActionListener(listener);
	}
}





