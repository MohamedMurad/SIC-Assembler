package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneLayout;

public class AssemblerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	JTextArea area;
	JLabel currentPath;

	public AssemblerPanel(JFrame fr) {
		frame = fr;
		intitializePanel();
	}

	private void intitializePanel() {
		setLayout(new BorderLayout());
		setTextArea();
		setLabel();
		setBackground(Color.red);
		frame.add(this);
		frame.setVisible(true);
	}

	private void setTextArea() {
		area = new JTextArea();
		area.setFont(new Font("Courier New", Font.PLAIN, 22));
		area.setForeground(Color.white);
		area.setBackground(Color.black);
		area.setCaretColor(Color.WHITE);
		area.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JScrollPane scroll = new JScrollPane(area);
		scroll.createVerticalScrollBar();
		scroll.setLayout(new ScrollPaneLayout());
		add(scroll);
	}

	private void setLabel() {
		currentPath = new JLabel();
		currentPath.setFont(new Font("ARIAL", Font.PLAIN, 18));
		currentPath.setForeground(Color.WHITE);
		currentPath.setText("Path: ");
		JPanel labelPanel = new JPanel();
		labelPanel.setBackground(new Color(67, 77, 89));
		labelPanel.add(currentPath);
		add(labelPanel, BorderLayout.SOUTH);
	}
}