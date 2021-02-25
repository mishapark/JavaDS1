package Presentation;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import Presentation.Validator;
import Business.AppData;
import data.TextFileIO;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class Record extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Record frame = new Record();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Record() {
		setResizable(false);
		setTitle("Record");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rbCity = new JRadioButton("City");
		buttonGroup.add(rbCity);
		rbCity.setBounds(154, 18, 67, 23);
		contentPane.add(rbCity);
		
		JRadioButton rbAll = new JRadioButton("All");
		buttonGroup.add(rbAll);
		rbAll.setSelected(true);
		rbAll.setBounds(25, 18, 58, 23);
		contentPane.add(rbAll);
		
		JRadioButton rbDate = new JRadioButton("Date");
		buttonGroup.add(rbDate);
		rbDate.setBounds(85, 18, 67, 23);
		contentPane.add(rbDate);
		
		JRadioButton rbCD = new JRadioButton("Date & City");
		buttonGroup.add(rbCD);
		rbCD.setBounds(217, 18, 109, 23);
		contentPane.add(rbCD);
		
		txtSearch = new JTextField();
		txtSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSearch.setColumns(10);
		txtSearch.setBounds(85, 45, 142, 20);
		contentPane.add(txtSearch);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(35, 48, 78, 14);
		contentPane.add(lblSearch);
		
		JTextArea taSearch = new JTextArea();
		taSearch.setBounds(25, 92, 375, 158);
		contentPane.add(taSearch);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String findStr = txtSearch.getText();
				taSearch.setText("");
				
				 	String wholeFile = TextFileIO.ReadFile();
				 	String[] lines = wholeFile.split("\n");
	                
	                if (rbAll.isSelected()) {
	                	for (String line : AppData.getAppData().readFile()) {
	                		taSearch.append(line);
	                		taSearch.append("\n");
	                	}
	                	
	                } else {
	            		if ((Validator.isPresent(txtSearch, "Search"))) {
							if (rbDate.isSelected()) {
								for	(String line : AppData.getAppData().readFile()) {
									String[] lineData = line.split(",");
									if(lineData[0].contains(findStr)) {
										taSearch.append(line);
				                		taSearch.append("\n");
									}
								}
							} else if (rbCity.isSelected()) {
								for	(String line : AppData.getAppData().readFile()) {
									String[] lineData = line.split(",");
									if(lineData[1].contains(findStr)) {
										taSearch.append(line);
				                		taSearch.append("\n");
									}
								}
							} else if (rbCD.isSelected()) {
								for	(String line : AppData.getAppData().readFile()) {
									String[] lineData = line.split(",");
									String[] splitFind = findStr.split(",");
									if(lineData[0].contains(splitFind[0]) && lineData[1].contains(splitFind[1])) {
										taSearch.append(line);
				                		taSearch.append("\n");
									}
								}
							}
						}
	                }	
			}
		});
		btnFind.setBounds(237, 44, 89, 23);
		contentPane.add(btnFind);
		
		
		
	}
}
