package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AssemblerFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int width = 1435, height = 690;
	private JMenuBar menu;
	private JMenu file, assembling;
	private AssemblerPanel panel;
	JMenuItem newF, open, save, exit, assemble, listFile, objectFile;

	public AssemblerFrame() {
		super("Sic Machin Assembler");
		panel = new AssemblerPanel(this);
		initialGUI();
	}

	private void initialGUI() {
		FlowLayout layout = new FlowLayout();
		setLayout(layout);
		setLayout(new GridLayout(1, 1, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
		createMenus();
	}

	private void createMenus() {
		menu = new JMenuBar();
		setJMenuBar(menu);
		menu.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 5));
		menu.setBackground(new Color(67, 77, 89));
		// customize menus
		file = new JMenu("File");
		file.setFont(new Font("Arial", Font.PLAIN, 18));
		file.setForeground(Color.white);
		//file.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 5));
		assembling = new JMenu("Assembling");
		assembling.setFont(new Font("Arial", Font.PLAIN, 18));
		assembling.setForeground(Color.white);
		menu.add(file);
		menu.add(assembling);
		fileMenu();
		assemMenu();
		// Listeners
		addlistners();
	}

	private void assemMenu() {
		assemble = new JMenuItem("Assemble", null);
		assemble.setFont(new Font("Arial", Font.PLAIN, 16));
		objectFile = new JMenuItem("Object File");
		objectFile.setFont(new Font("Arial", Font.PLAIN, 16));
		listFile = new JMenuItem("List file");
		listFile.setFont(new Font("Arial", Font.PLAIN, 16));
		assembling.add(assemble);
		assembling.add(objectFile);
		assembling.add(listFile);
	}

	private void fileMenu() {
		newF = new JMenuItem("New");
		newF.setFont(new Font("Arial", Font.PLAIN, 16));
		open = new JMenuItem("Open");
		open.setFont(new Font("Arial", Font.PLAIN, 16));
		save = new JMenuItem("Save");
		save.setFont(new Font("Arial", Font.PLAIN, 16));
		exit = new JMenuItem("Exit");
		exit.setFont(new Font("Arial", Font.BOLD, 16));
		file.add(newF);
		file.add(open);
		file.addSeparator();
		file.add(save);
		file.addSeparator();
		file.add(exit);
	}

	private void addlistners() {
		Listner ls = new Listner(this, panel);
		newF.addActionListener(ls);
		open.addActionListener(ls);
		save.addActionListener(ls);
		exit.addActionListener(ls);
		assemble.addActionListener(ls);
		objectFile.addActionListener(ls);
		listFile.addActionListener(ls);
	}
}